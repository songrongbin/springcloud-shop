package com.bins.springcloud.shop.user.entity;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UserGroupEntity {
	
	private Long id;
	
	private String groupName;
	
	private Integer isDel;
	
	private Long createBy;
	
	private LocalDateTime createTime;
	
}
