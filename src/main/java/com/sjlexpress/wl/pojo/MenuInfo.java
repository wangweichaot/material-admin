package com.sjlexpress.wl.pojo;

import java.io.Serializable;

import lombok.Data;

@Data
public class MenuInfo implements Serializable{

	private static final long serialVersionUID = -2906239536625601379L;
	
//	[{"name":'Home',"path":'/'},{"name":"System-management","path":"/system-management"},
//	 {"name":"Staff","staff"},{"name":"Role","path":"role"}]
	
	/**
     * 
     */
    private String menu_id;

    /**
     * 菜单名称（前端定义，不一定是中文）
     */
    private String name;

    /**
     * 菜单路径
     */
    private String href;

    
}