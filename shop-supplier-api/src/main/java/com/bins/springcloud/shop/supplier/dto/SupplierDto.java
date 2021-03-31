package com.bins.springcloud.shop.supplier.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class SupplierDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private String supplierName;

	private String supplierCode;

	private Integer status;

	private String picUrl;
	
	private Integer sort;
	
	private Integer isDel;
	
	private Long createBy;
	
	private Long updateBy;

}
