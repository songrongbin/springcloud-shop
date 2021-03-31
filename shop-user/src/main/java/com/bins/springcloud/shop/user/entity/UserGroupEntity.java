package com.bins.springcloud.shop.user.entity;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UserGroupEntity {
	
	private Long id;
	
	private String groupCode;
	
	private String groupName;
	
	private Integer sort;
	
	private Integer isDel;
	
	private Long createBy;
	
	private Long updateBy;
	
	private LocalDateTime createTime;
	
}
