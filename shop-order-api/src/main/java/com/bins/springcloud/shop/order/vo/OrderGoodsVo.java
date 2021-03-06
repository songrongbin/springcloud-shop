package com.bins.springcloud.shop.order.vo;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class OrderGoodsVo implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Long id;

	private Long orderId;

	private String orderCode;
	
	private Long supplierId;

	private Long goodsId;

	private String goodsCode;
	
	private String goodsName;
	
	private String picUrl;
	
	private Integer goodsPrice;

	private Integer goodsNumber;

	private Integer goodsAmount;
	
	private Integer couponAmount;
	
	private Integer discountAmount;
	
	private Integer shoppingAmount;
	
	private Integer status;

	private String statusName;
	
	private Integer isDel;
	
	private Long createBy;
	
	private LocalDateTime createTime;
	
	private LocalDateTime updateTime;

}