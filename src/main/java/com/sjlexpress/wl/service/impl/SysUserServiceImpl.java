package com.sjlexpress.wl.service.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.google.common.collect.Lists;
import com.sjlexpress.wl.config.AdminInitConfig;
import com.sjlexpress.wl.dao.SystemMenuMapper;
import com.sjlexpress.wl.dao.SystemRoleMapper;
import com.sjlexpress.wl.dao.SystemRoleMenuMapper;
import com.sjlexpress.wl.dao.SystemUserMapper;
import com.sjlexpress.wl.dao.SystemUserRoleMapper;
import com.sjlexpress.wl.dto.SystemUserDto;
import com.sjlexpress.wl.dto.SystemMenuDto;
import com.sjlexpress.wl.exception.AlertException;
import com.sjlexpress.wl.param.SystemUserParam;
import com.sjlexpress.wl.param.LoginParam;
import com.sjlexpress.wl.pojo.Dept;
import com.sjlexpress.wl.pojo.SystemMenu;
import com.sjlexpress.wl.pojo.SystemRole;
import com.sjlexpress.wl.pojo.SystemRoleMenu;
import com.sjlexpress.wl.pojo.SystemUser;
import com.sjlexpress.wl.pojo.SystemUserRole;
import com.sjlexpress.wl.service.ISysUserService;
import com.sjlexpress.wl.service.ISystemMenuService;
import com.sjlexpress.wl.util.EncryptionUtils;
import com.sjlexpress.wl.util.StringUtil;
import com.sjlexpress.wl.util.TokenUtil;
import com.sjlexpress.wl.vo.SystemUserUpdateVo;
import com.sjlexpress.wl.vo.SystemUserVo;
import com.sjlexpress.wl.vo.PageVo;
import com.sjlexpress.wl.vo.SysUserVo;
import com.sjlexpress.wl.vo.SystemMenuVo;
import com.sjlexpress.wl.vo.TokenVo;

/**
 * 功能描述: 用户服务实现
 *
 * @author zhicheng.lai
 */
@Service
public class SysUserServiceImpl implements ISysUserService {
	
    @Autowired
    SystemUserMapper systemUserMapper;
    @Autowired
    SystemRoleMenuMapper systemRoleMenuMapper;
    @Autowired
    SystemMenuMapper systemMenuMapper;
    @Autowired
    SystemUserRoleMapper systemUserRoleMapper;
    @Autowired
    SystemRoleMapper systemRoleMapper;
    @Autowired
    ISystemMenuService systemMenuService;
    @Autowired
    AdminInitConfig adminInitConfig;

    /**
     * 登录
     */
    @Override
    public SysUserVo adminLogin(LoginParam param) throws AlertException {
    	SystemUser sysUser = new SystemUser();
    	sysUser.setUserAccount(param.getUserAccount());
    	sysUser = systemUserMapper.selectOne(sysUser);
        if (null == sysUser){
            throw new AlertException("账号不存在，请重新输入");
        }
        if (StringUtils.isNotEmpty(param.getPassword()) && !sysUser.getPassword().equals(EncryptionUtils.md5(param.getPassword()))){
             throw new AlertException("密码或账号错误，请重新输入");
        }
        SysUserVo userVo = new SysUserVo();
        String token = TokenUtil.getToken(sysUser.getId());
        userVo.setToken(token);
        userVo.setUserAccount(sysUser.getUserAccount());
        userVo.setUserName(sysUser.getUserName());
        userVo.setUserId(sysUser.getId());
        
//        List<SystemMenuVo> menuList = getMenuListNolevel(sysUser.getId());//无层级菜单列表
        
//        List<SystemMenuVo> menuList = this.builTree(sysUser.getId());//有层级菜单列表
//        menuList=menuList.stream().sorted(Comparator.comparing(SystemMenuVo::getOrders)).collect(Collectors.toList());
//        userVo.setMenuList(menuList);
        return userVo;
    }
    
    //建立树形结构
    private List<SystemMenuVo> builTree(String userId){
        List<SystemMenuVo> treeMenus =new  ArrayList<SystemMenuVo>();
        for(SystemMenuVo menuNode : getRootNode(userId)) {
            menuNode=buildChilTree(menuNode, userId);
            treeMenus.add(menuNode);
        }
        return treeMenus;
    }

    //递归，建立子树形结构
    private SystemMenuVo buildChilTree(SystemMenuVo pNode, String userId){
        List<SystemMenuVo> allMenuList = systemMenuMapper.getSystemMenuListByUserId(userId);
        
        List<SystemMenuVo> chilMenuList = new ArrayList<>();
        for(SystemMenuVo menuNode : allMenuList) {
            if(menuNode.getPid().equals(pNode.getId()+"")) {
            	chilMenuList.add(buildChilTree(menuNode, userId));
            }
        }
        pNode.setChildren(chilMenuList);
        return pNode;
    }

    //获取根节点
    private List<SystemMenuVo> getRootNode(String userId) {         
    	List<SystemMenuVo> allMenuList = systemMenuMapper.getSystemMenuListByUserId(userId);
    	
    	List<SystemMenuVo> rootMenuLists = new ArrayList<>();
        for(SystemMenuVo menuNode : allMenuList) {
            if(menuNode.getPid().equals("0")) {
                rootMenuLists.add(menuNode);
            }
        }
        return rootMenuLists;
    }
    
    /**
     * 注册
     */
    @Override
    public SysUserVo adminRegister(LoginParam param) throws AlertException {

    	SystemUser sysUser = new SystemUser();
    	sysUser.setUserName(param.getUserAccount());
    	SystemUser newUser = systemUserMapper.selectOne(sysUser);
        if (null != newUser){
            throw new AlertException("该账号已经被注册");
        }
        SystemUser user = new SystemUser();
        String uUid = StringUtil.getUUid();
        Date date = new Date();
        user.setId(uUid);
        user.setUserAccount(param.getUserAccount());
        user.setIsDelete(0);
        user.setPassword(EncryptionUtils.md5(param.getPassword()));
        user.setCreateUser(uUid);
        user.setCreateTime(date);
        user.setUpdateUser(uUid);
        user.setUpdateTime(date);
        int insertSelective = systemUserMapper.insertSelective(user);
        if (insertSelective == 0) {
			throw new AlertException("注册失败");
		}
        SysUserVo userVo = new SysUserVo();
        String token = TokenUtil.getToken(uUid);
        userVo.setToken(token);
        
        /**
         * 获取权限 这里还没有配置权限  需要到数据库设置一下默认权限
         */
        List<SystemMenuVo> menuList = getMenuListNolevel("12");
        userVo.setMenuList(menuList);
        return userVo;
    }
    
    /**
     * 通过userId获取菜单列表（无层级列表）
     * @param userId
     * @return
     */
	private List<SystemMenuVo> getMenuListNolevel(String userId){
    	//实现思路：通过userId查询system_user_role，获取roleId关联查询system_role_menu，获取menuId查询system_menu获取菜单名称
		
		//通过userId查询system_user_role，获取roleId
		SystemUserRole systemUserRole = new SystemUserRole();
		systemUserRole.setUserId(userId);
		List<SystemUserRole> userRoleList = systemUserRoleMapper.select(systemUserRole);
		if(!CollectionUtils.isEmpty(userRoleList)) {
			for(SystemUserRole sysUserRole:userRoleList) {
				SystemRoleMenu systemRoleMenu = new SystemRoleMenu();
		    	systemRoleMenu.setRoleId(sysUserRole.getRoleId());
		    	List<SystemRoleMenu> select = systemRoleMenuMapper.select(systemRoleMenu);
		    	List<SystemMenuVo> menuList = Lists.transform(select, menu -> {
		    		SystemMenuVo systemMenuVo = new SystemMenuVo();
		    		SystemMenu systemMenu = systemMenuMapper.selectByPrimaryKey(menu.getMenuId());
		    		BeanUtils.copyProperties(systemMenu, systemMenuVo);
		    		return systemMenuVo;
		    	});
		    	return menuList;
			}
		}
    	return null;
    }
	
	/**
	 * 超级管理员初始化
	 */
	@Override
	@Transactional
	public void adminInit() {
		//1、插入system_user
		SystemUser sysUser = new SystemUser();
		String userId = StringUtil.getUUid();
//		String userAccount = PropertiesUtil.getConfigValue("/adminInit.properties", "system_user.userAccount");
//		String password = PropertiesUtil.getConfigValue("/adminInit.properties", "system_user.password");
		String userAccount = adminInitConfig.getUserAccount();
		String password = adminInitConfig.getPswd();
		sysUser.setId(userId);
		sysUser.setUserAccount(userAccount);
		sysUser.setPassword(EncryptionUtils.md5(password));//入库前必须加密
		sysUser.setUserName("超级管理员");
		sysUser.setSex("1");
		sysUser.setIsDelete(0);
		sysUser.setCreateUser(userId);
		sysUser.setCreateTime(new Date());
		systemUserMapper.insert(sysUser);
		
		//2、插入system_role
		SystemRole systemRole = new SystemRole();
		systemRole.setId(1);//主键
//		systemRole.setRoleId(roleId);
		systemRole.setRoleName("超级管理员");
		systemRole.setIsSuper(1);
		systemRole.setIsDelete(0);
		systemRole.setCreateUser(userId);
		systemRole.setCreateTime(new Date());
//		systemRole.setIds(ids);
		systemRoleMapper.insert(systemRole);
		
		//3、插入system_user_role
		SystemUserRole systemUserRole = new SystemUserRole();
		String urid = StringUtil.getUUid();
		systemUserRole.setId(urid);
		systemUserRole.setRoleId(1);
		systemUserRole.setUserId(userId);
		systemUserRoleMapper.insert(systemUserRole);
		
		//4、获取菜单信息
		List<SystemMenuDto> menuList = systemMenuService.getSystemMenuAllList();
		for(SystemMenuDto dto : menuList) {
			
			//5、插入system_role_menu(超级管理员需要插入所有菜单信息)
			SystemRoleMenu systemRoleMenu = new SystemRoleMenu();
			String rmid = StringUtil.getUUid();
			systemRoleMenu.setId(rmid);
			systemRoleMenu.setMenuId(dto.getId());
			systemRoleMenu.setRoleId(1);
			systemRoleMenu.setCreateUser(userId);
			systemRoleMenu.setCreateTime(new Date());
			systemRoleMenuMapper.insert(systemRoleMenu);
		}
	}
	
	/**
     * 分页查询员工列表
     */
	@Override
	public PageVo<SystemUserDto> getSysUserList(TokenVo tokenVo, SystemUserParam param) throws AlertException {
		param.pageHelper();
        List<SystemUserDto> hytSysUserList = systemUserMapper.getSysUserList(param);
        return new PageVo<>(hytSysUserList, param);
	}
	
	/**
	 * 新增
	 */
	@Override
	@Transactional
	public void addSysUser(SystemUserVo hytSysUserVo, TokenVo tokenVo) throws AlertException {
		
		this.checkSysUserInfo("1", null, hytSysUserVo.getUserAccount());
		
		//2、插入基表
		SystemUser hytSysUser = new SystemUser();
		BeanUtils.copyProperties(hytSysUserVo, hytSysUser);
		String id = StringUtil.getUUid();
		hytSysUser.setId(id);
		hytSysUser.setCreateUser(tokenVo.getId());
		hytSysUser.setCreateTime(new Date());
		hytSysUser.setPassword(EncryptionUtils.md5(hytSysUserVo.getPassword()));
		int i = systemUserMapper.insertSelective(hytSysUser);
		if(i < 1) {
			throw new AlertException("新增员工失败");
		}
		
		//3、绑定权限system_user_role
		SystemUserRole systemUserRole = new SystemUserRole();
		String surId = StringUtil.getUUid();
		systemUserRole.setId(surId);
		systemUserRole.setRoleId(hytSysUserVo.getRoleId());
		systemUserRole.setUserId(id);
		int count = systemUserRoleMapper.insert(systemUserRole);
		if(count < 1) {
			throw new AlertException("绑定角色失败");
		}
	}
	
	/**
	 * 校验登录账户唯一性
	 * @param operFlag
	 * @param id
	 * @param userAccount
	 */
	private void checkSysUserInfo(String operFlag, String id, String userAccount) {
		int count = systemUserMapper.selectByUserAccount(operFlag, id, userAccount);
		if(count >0) {
			throw new AlertException("登录账户不能重复");
		}
		
	}

	/**
	 * 修改
	 */
	@Override
	public void updateSysUser(SystemUserVo hytSysUserVo, TokenVo tokenVo) throws AlertException {
		
		this.checkSysUserInfo("2", hytSysUserVo.getId(), hytSysUserVo.getUserAccount());
		
		SystemUser hytSysUser = new SystemUser();
		BeanUtils.copyProperties(hytSysUserVo, hytSysUser);
		hytSysUser.setUpdateUser(tokenVo.getId());
		hytSysUser.setUpdateTime(new Date());
//		hytSysUser.setPassword(EncryptionUtils.md5(hytSysUserVo.getPassword()));//修改功能暂不支持密码修改
		int i = systemUserMapper.updateByPrimaryKeySelective(hytSysUser);
		if(i < 1) {
			throw new AlertException("更新员工失败");
		}
		
		//3、绑定权限system_user_role
		int count = systemUserRoleMapper.updateRoleIdByUserId(hytSysUserVo.getRoleId()+"", hytSysUserVo.getId());
		if(count < 1) {
			throw new AlertException("绑定角色失败");
		}
		
	}
	
	/**
	 * 详情
	 */
	@Override
	public SystemUserDto getSysUserDetail(TokenVo tokenVo, String id) throws AlertException {
		SystemUserDto dto = systemUserMapper.getSysUserDetail(id);
		return dto;
	}
	
	/**
	 * 启用/禁用
	 */
	@Override
	public void openCloseSysUser(SystemUserUpdateVo hytSysUserUpdateVo, TokenVo tokenVo) throws AlertException {
		SystemUser hytSysUser = new SystemUser();
		hytSysUser.setId(hytSysUserUpdateVo.getId());
		String operFlag = hytSysUserUpdateVo.getOperFlag();
		if("0".equals(operFlag)) {//启用
			hytSysUser.setIsDelete(0);
		}else if("1".equals(operFlag)){//禁用
			hytSysUser.setIsDelete(1);
		}else {
			throw new AlertException("操作标志非法");
		}
		hytSysUser.setUpdateUser(tokenVo.getId());
		hytSysUser.setUpdateTime(new Date());
		int i = systemUserMapper.updateByPrimaryKeySelective(hytSysUser);
		if(i < 1) {
			throw new AlertException("更新员工状态失败");
		}
	}
	
	/**
	 * 重置密码
	 */
	@Override
	public void resetPswd(SystemUserVo hytSysUserVo, TokenVo tokenVo) throws AlertException {
		SystemUser hytSysUser = new SystemUser();
		hytSysUser.setId(hytSysUserVo.getId());
		hytSysUser.setPassword(EncryptionUtils.md5("abc123456"));
		int i = systemUserMapper.updateByPrimaryKeySelective(hytSysUser);
		if(i < 1) {
			throw new AlertException("重置密码失败");
		}
		
	}
	
	/**
	 * 修改密码
	 */
	@Override
	public void updatePswd(SystemUserVo hytSysUserVo, TokenVo tokenVo) throws AlertException {
		SystemUser hytSysUser = new SystemUser();
		hytSysUser.setId(hytSysUserVo.getId());
		hytSysUser.setPassword(EncryptionUtils.md5(hytSysUserVo.getPassword()));
		int i = systemUserMapper.updateByPrimaryKeySelective(hytSysUser);
		if(i < 1) {
			throw new AlertException("修改密码失败");
		}
	}

	@Override
	public List<SystemMenuVo> getMenuList(String userId) throws AlertException {
		List<SystemMenuVo> menuList = this.builTree(userId);//有层级菜单列表
        menuList=menuList.stream().sorted(Comparator.comparing(SystemMenuVo::getOrders)).collect(Collectors.toList());
		return menuList;
	}


}
