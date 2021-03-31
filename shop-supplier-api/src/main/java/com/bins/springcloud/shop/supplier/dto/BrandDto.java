package com.bins.springcloud.shop.supplier.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class BrandDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private String brandName;

	private String brandDesc;

	private Integer status;

	private String picUrl;
	
	private String sort;
	
	private Integer isDel;
	
	private Long createBy;
	
	private Long updateBy;
	

}
