package com.bins.springcloud.shop.supplier.entity;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class BrandEntity {
	
	private Long id;
	
	private String brandName;

	private String brandDesc;

	private Integer status;

	private String picUrl;
	
	private Integer isDel;
	
	private Integer sort;
	
	private Long createBy;
	
	private LocalDateTime createTime;
	
	private LocalDateTime updateTime;

}
