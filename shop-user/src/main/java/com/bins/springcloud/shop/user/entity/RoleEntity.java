package com.bins.springcloud.shop.user.entity;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class RoleEntity {

	private Long id;

	private String roleCode;

	private String roleName;

	private Long operatorId;
	
	private Integer sort;

	private Integer isDel;
	
	private LocalDateTime createTime;
	
	private LocalDateTime updateTime;

}
