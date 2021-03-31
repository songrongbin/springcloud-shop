package com.bins.springcloud.shop.user.dto;

import lombok.Data;

@Data
public class UserGroupDto {
	
	private Long id;
	
	private String groupCode;
		
	private String groupName;
	
	private Integer isDel;
	
	private Integer sort;
	
	private Long createBy;
	
	private Long updateBy;

}
