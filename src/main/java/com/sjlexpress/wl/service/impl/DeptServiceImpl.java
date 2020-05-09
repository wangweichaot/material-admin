package com.sjlexpress.wl.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sjlexpress.wl.dao.DeptMapper;
import com.sjlexpress.wl.dto.DeptDto;
import com.sjlexpress.wl.exception.AlertException;
import com.sjlexpress.wl.param.DeptParam;
import com.sjlexpress.wl.pojo.Dept;
import com.sjlexpress.wl.service.IDeptService;
import com.sjlexpress.wl.util.StringUtil;
import com.sjlexpress.wl.vo.DeptUpdateVo;
import com.sjlexpress.wl.vo.DeptVo;
import com.sjlexpress.wl.vo.PageVo;
import com.sjlexpress.wl.vo.TokenVo;

/**
 * 功能描述: 部门/仓库管理接口实现层
 * @author wangweichao
 */
@Service
public class DeptServiceImpl implements IDeptService {
	
    @Autowired
    DeptMapper deptMapper;

    /**
     * 分页查询部门/仓库列表
     */
	@Override
	public PageVo<DeptDto> getDeptList(TokenVo tokenVo, DeptParam param) throws AlertException {
		param.pageHelper();
        List<DeptDto> DeptList = deptMapper.getDeptList(param);
        return new PageVo<>(DeptList, param);
	}
	
	/**
     * 查询部门/仓库列表(不分页，下拉框使用)
     */
	@Override
	public List<Dept> getDeptListNoPage() throws AlertException {
        List<Dept> DeptList = deptMapper.getDeptListNoPage();
        return DeptList;
	}
	
	/**
	 * 新增
	 */
	@Override
	public void addDept(DeptVo DeptVo, TokenVo tokenVo) throws AlertException {
		
		this.checkDeptInfo("1", null, DeptVo.getName());
		
		Dept Dept = new Dept();
		BeanUtils.copyProperties(DeptVo, Dept);
		Dept.setCreateUser(tokenVo.getId());
		Dept.setCreateTime(new Date());
		Dept.setIsDelete(0);//启用
		int i = deptMapper.insertSelective(Dept);
		if(i < 1) {
			throw new AlertException("新增部门/仓库失败");
		}
		
	}
	
	/**
	 * 部门名称不能重复
	 * @param operFlag 1新增 2修改
	 * @param id
	 * @param warehouseId
	 * @return
	 */
	private void checkDeptInfo(String operFlag, Integer id, String name) {
		int count = deptMapper.checkDeptInfo(operFlag, id, name);
		if(count >0) {
			throw new AlertException("部门/仓库+仓库不能重复");
		}
	}

	/**
	 * 修改
	 */
	@Override
	public void updateDept(DeptVo DeptVo, TokenVo tokenVo) throws AlertException {
		
		this.checkDeptInfo("2", DeptVo.getId(), DeptVo.getName());
		
		Dept dept = new Dept();
		BeanUtils.copyProperties(DeptVo, dept);
		dept.setUpdateUser(tokenVo.getId());
		dept.setUpdateTime(new Date());
		int i = deptMapper.updateByPrimaryKeySelective(dept);
		if(i < 1) {
			throw new AlertException("更新部门/仓库失败");
		}
		
	}
	
	/**
	 * 详情
	 */
	@Override
	public DeptVo getDeptDetail(TokenVo tokenVo, String id) throws AlertException {
		Dept Dept = deptMapper.selectByPrimaryKey(id);
		DeptVo DeptVo = new DeptVo();
		BeanUtils.copyProperties(Dept, DeptVo);
		return DeptVo;
	}
	
	/**
	 * 启用/禁用
	 */
	@Override
	public void openCloseDept(DeptUpdateVo DeptUpdateVo, TokenVo tokenVo) throws AlertException {
		
		Dept entity = deptMapper.selectByPrimaryKey(DeptUpdateVo.getId());
		
		this.checkDeptInfo("2", entity.getId(), entity.getName());
		
		Dept Dept = new Dept();
		Dept.setId(DeptUpdateVo.getId());
		String operFlag = DeptUpdateVo.getOperFlag();
		if("0".equals(operFlag)) {//启用
			Dept.setIsDelete(0);
		}else if("1".equals(operFlag)){//禁用
			Dept.setIsDelete(1);
		}else {
			throw new AlertException("操作标志非法");
		}
		Dept.setUpdateUser(tokenVo.getId());
		Dept.setUpdateTime(new Date());
		int i = deptMapper.updateByPrimaryKeySelective(Dept);
		if(i < 1) {
			throw new AlertException("更新部门/仓库状态失败");
		}
	}

}
