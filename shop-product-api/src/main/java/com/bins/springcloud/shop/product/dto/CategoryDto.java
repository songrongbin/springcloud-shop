package com.bins.springcloud.shop.product.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class CategoryDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private String categoryName;

	private String categoryDesc;
	
	private Long pid;

	private String picUrl;
	
	private String iconUrl;
	
	private Integer level;
	
	private Integer status;
	
	private Integer sort;

}
