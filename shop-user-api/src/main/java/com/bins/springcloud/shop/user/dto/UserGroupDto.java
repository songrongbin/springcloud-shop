package com.bins.springcloud.shop.user.dto;

import lombok.Data;

@Data
public class UserGroupDto {
	
	private Long id;
		
	private String groupName;
	
	private Integer isDel;
	
	private Long createBy;

}
