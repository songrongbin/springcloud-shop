package com.bins.springcloud.shop.user.entity;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class DeptEntity {
	
	private Long id;
	
	private String deptCode;
	
	private String deptName;
	
	private Long pid;
	
	private Integer sort;
	
	private Integer isDel;
	
	private Long createBy;
	
	private LocalDateTime createTime;
	
	private LocalDateTime updateTime;

}
