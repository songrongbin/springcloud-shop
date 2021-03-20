package com.bins.springcloud.shop.product.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class GoodsDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private String goodsName;

	private String goodsCode;

	private Integer status;

	private String picUrl;

}
