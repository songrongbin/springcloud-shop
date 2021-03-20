package com.bins.springcloud.shop.supplier.entity;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class SupplierEntity {
	
	private Long id;
	
	private String supplierCode;
	
	private String supplierName;
	
	private String picUrl;
	
	private Integer status;
	
	private Integer sort;
	
	private Integer isDel;
	
	private Long createBy;
	
	private LocalDateTime createTime;
	
	private LocalDateTime updateTime;

}
