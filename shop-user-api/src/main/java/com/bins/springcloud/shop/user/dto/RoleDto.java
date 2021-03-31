package com.bins.springcloud.shop.user.dto;

import lombok.Data;

@Data
public class RoleDto {

	private Long id;

	private String roleCode;

	private String roleName;

	private Integer sort;

	private Integer isDel;
	
	private Long createBy;
	
	private Long updateBy;

}
