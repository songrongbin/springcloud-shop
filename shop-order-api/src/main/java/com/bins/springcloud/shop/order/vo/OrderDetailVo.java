package com.bins.springcloud.shop.order.vo;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

@Data
public class OrderDetailVo implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Long id;

	private Long userId;
	
	private String userName;
	
	private Long supplierId;
	
	private String supplierName;
	
	private Long orderId;

	private String orderCode;
	
	private Integer orderAmount;
	
	private Integer couponAmount;
	
	private Integer discountAmount;
	
	private Integer shoppingAmount;

	private Integer status;
	
	private String statusName;

	private String picUrl;
	
	private Integer isDel;
	
	private Long createBy;
	
	private List<OrderGoodsVo> orderGoodsList;
	
	private LocalDateTime createTime;
	
	private LocalDateTime updateTime;

}