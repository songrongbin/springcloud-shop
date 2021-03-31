package com.bins.springcloud.shop.user.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class PermissionDto implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;

	private String permissionName;

    private String permissionCode;

    private Integer permissionType;

    private String url;

    private Integer sort;
    
    private Integer level;
    
    private Long pid;
    
    private Long createBy;
    
    private Long updateBy;
    
}
