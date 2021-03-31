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
	
	private Integer sort;

	private Integer isDel;
	
	private Long createBy;
	
	private Long updateBy;
	
	private LocalDateTime createTime;
	
	private LocalDateTime updateTime;

}
