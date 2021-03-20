package com.bins.springcloud.shop.product.entity;

import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@ToString
public class GoodsEntity {
	
	private Long id;

	private String goodsName;

	private String goodsCode;
	
	private Long brandId;
	
	private Long categoryId;
	
	private String goodsBrief;
	
	private Integer goodsPrice;
	
	private String picUrl;

	private String keywords;

	private Integer isOnSale;
	
	private Integer isDel;
	
	private Long createBy;
	
	private LocalDateTime createTime;
	
	private LocalDateTime updateTime;
}
