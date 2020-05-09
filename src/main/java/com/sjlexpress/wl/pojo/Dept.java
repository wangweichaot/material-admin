package com.sjlexpress.wl.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Table(name = "t_dept")
@Data
public class Dept extends BaseEntity {
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
    @Column(name = "province_id")
    private String provinceId;

    /**
     * 所属市ID
     */
    @Column(name = "city_id")
    private String cityId;

    /**
     * 所属区Id
     */
    @Column(name = "area_id")
    private String areaId;

    /**
     * 所属省名称
     */
    @Column(name = "province_name")
    private String provinceName;

    /**
     * 所属市名称
     */
    @Column(name = "city_name")
    private String cityName;

    /**
     * 所属区名称
     */
    @Column(name = "area_name")
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
    @Column(name = "is_delete")
    private Integer isDelete;

    /**
     * 创建人ID
     */
    @Column(name = "create_user")
    private String createUser;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 修改人ID
     */
    @Column(name = "update_user")
    private String updateUser;

    /**
     * 修改时间
     */
    @Column(name = "update_time")
    private Date updateTime;

}