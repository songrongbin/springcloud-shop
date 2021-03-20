package com.bins.springcloud.shop.user.entity;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class PermissionEntity {
	
	private Long id;

    private String permissionName;

    private String permissionCode;

    private Integer permissionType;

    private String url;

    private Integer sort;
    
    private Integer level;
    
    private Long pid;
    
    private Long operatorId;
    
    private Integer isDel;
    
    private LocalDateTime createTime;
    
    private LocalDateTime updateTime;

}