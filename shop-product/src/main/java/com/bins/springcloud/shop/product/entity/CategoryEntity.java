package com.bins.springcloud.shop.product.entity;

import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@ToString
public class CategoryEntity {
	
	private Long id;

	private String categoryName;

	private String categoryDesc;
	
	private Long pid;

	private String picUrl;
	
	private String iconUrl;
	
	private Integer level;
	
	private Integer status;
	
	private Integer sort;
	
	private Integer isDel;
	
	private Long createBy;
	
	private LocalDateTime createTime;
	
	private LocalDateTime updateTime;
}
