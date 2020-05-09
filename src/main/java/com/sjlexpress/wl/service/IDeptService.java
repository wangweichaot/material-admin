package com.sjlexpress.wl.service;

import java.util.List;

import com.sjlexpress.wl.dto.DeptDto;
import com.sjlexpress.wl.exception.AlertException;
import com.sjlexpress.wl.param.DeptParam;
import com.sjlexpress.wl.pojo.Dept;
import com.sjlexpress.wl.vo.DeptUpdateVo;
import com.sjlexpress.wl.vo.DeptVo;
import com.sjlexpress.wl.vo.PageVo;
import com.sjlexpress.wl.vo.TokenVo;

/**
 * 部门/仓库管理接口层
 */
public interface IDeptService {

	/**
	 * 分页查询部门/仓库列表
	 * @param tokenVo
	 * @param param
	 * @return
	 * @throws AlertException
	 */
    PageVo<DeptDto> getDeptList(TokenVo tokenVo, DeptParam param) throws AlertException;
    
    /**
	 * 查询部门/仓库列表(不分页，下拉框使用)
	 * @param tokenVo
	 * @param param
	 * @return
	 * @throws AlertException
	 */
    List<Dept> getDeptListNoPage() throws AlertException;
    
    /**
     * 新增
     * @param addressBody
     * @param tokenVo
     * @throws AlertException
     */
    void addDept(DeptVo sysRoleVo, TokenVo tokenVo) throws AlertException;
    
    /**
     * 修改
     * @param addressBody
     * @param tokenVo
     * @throws AlertException
     */
    void updateDept(DeptVo sysRoleVo, TokenVo tokenVo) throws AlertException;
    
    /**
     * 详情
     * @param tokenVo
     * @param id
     * @return
     * @throws AlertException
     */
    DeptVo getDeptDetail(TokenVo tokenVo, String id) throws AlertException;
    
    /**
     * 启用/禁用
     * @param addressBody
     * @param tokenVo
     * @throws AlertException
     */
    void openCloseDept(DeptUpdateVo sysRoleVo, TokenVo tokenVo) throws AlertException;
    


}
