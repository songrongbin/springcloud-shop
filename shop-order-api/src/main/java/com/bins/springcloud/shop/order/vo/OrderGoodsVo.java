package com.bins.springcloud.shop.order.vo;
import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class OrderGoodsVo implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Long id;

	private String goodsName;

	private String orderCode;

	private Integer status;

	private String picUrl;
	
	private Integer isDel;
	
	private Long createBy;
	
	private LocalDateTime createTime;
	
	private LocalDateTime updateTime;

}