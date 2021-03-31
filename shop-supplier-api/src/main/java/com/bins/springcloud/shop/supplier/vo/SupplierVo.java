package com.bins.springcloud.shop.supplier.vo;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class SupplierVo implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Long id;

	private String supplierName;

	private String supplierCode;

	private Integer status;

	private String picUrl;
	
	private Integer isDel;
	
	private Long createBy;
	
	private String createByName;
	
	private LocalDateTime createTime;
	
	private LocalDateTime updateTime;

}