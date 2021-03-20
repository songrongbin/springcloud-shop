package com.bins.springcloud.shop.user.dto;

import java.io.Serializable;

import com.bins.springcloud.shop.common.dto.PageDto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class PermissionPageDto extends PageDto implements Serializable {
	
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
    
}
