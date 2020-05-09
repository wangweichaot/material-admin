package com.sjlexpress.wl.pojo;

import javax.persistence.Column;
import javax.persistence.Table;

import lombok.Data;

@Table(name = "system_user_role")
@Data
public class SystemUserRole extends BaseEntity {
    private String id;

    @Column(name = "role_id")
    private Integer roleId;

    @Column(name = "user_id")
    private String userId;

}