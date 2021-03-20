package com.bins.springcloud.shop.order.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class OrderDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private String orderCode;

	private Integer orderAmount;

	private Integer status;

	private String picUrl;
	
	private Long userId;

}
