package com.bins.springcloud.shop.order.entity;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class OrderEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Long id;

	private Long userId;

	private String orderCode;
	
	private Integer orderAmount;
	
	private Integer couponAmount;
	
	private Integer discountAmount;
	
	private Integer shoppingAmount;
	
	private LocalDate payDate;
	
	private LocalDate confirmDate;
	
	private LocalDate deliveryDate;

	private Integer status;

	private String picUrl;
	
	private Integer isDel;
	
	private Long createBy;
	
	private LocalDateTime createTime;
	
	private LocalDateTime updateTime;

}