package com.bins.springcloud.user.dto;

import lombok.Data;

@Data
public class RoleDto {

	private Long id;

	private String roleCode;

	private String roleName;

	private Long operatorId;

	private Integer sort;

	private Integer isDel;

}
