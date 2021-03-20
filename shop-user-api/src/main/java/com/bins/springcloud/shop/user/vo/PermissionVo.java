package com.bins.springcloud.shop.user.vo;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class PermissionVo implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private String permissionName;

    private String permissionCode;

    private Integer permissionType;

    private String url;

    private Integer sort;
    
    private Integer level;
    
    private Long pid;
    
    private Long operatorId;
    
    private List<PermissionVo> permissionList;

}
