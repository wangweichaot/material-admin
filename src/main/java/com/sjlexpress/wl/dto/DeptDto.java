package com.sjlexpress.wl.dto;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Id;
import lombok.Data;

@Data
public class DeptDto implements Serializable {
	
	private static final long serialVersionUID = 50896268711390717L;
	
	/**
     * 主键/部门/仓库编码
     */
    @Id
    private Integer id;
    
    /**
     * 部门/仓库名称
     */
    private String name;
    
    /**
     * 所属省ID
     */
    private String provinceId;
    
    /**
     * 所属市ID
     */
    private String cityId;
    
    /**
     * 所属区Id
     */
    private String areaId;
    
    /**
     * 所属省名称
     */
    private String provinceName;
    
    /**
     * 所属市名称
     */
    private String cityName;
    
    /**
     * 所属区名称
     */
    private String areaName;
    
    /**
     * 详细地址
     */
    private String address;
    
    /**
     * 联系电话（一般是座机）
     */
    private String phone;
    
    /**
     * 是否有效 0启用 1禁用
     */
    private Integer isDelete;
    
    /**
     * 创建人ID
     */
    private String createUser;
    
    /**
     * 创建时间
     */
    private Date createTime;
    
    /**
     * 修改人ID
     */
    private String updateUser;
    
    /**
     * 修改时间
     */
    private Date updateTime;
}
