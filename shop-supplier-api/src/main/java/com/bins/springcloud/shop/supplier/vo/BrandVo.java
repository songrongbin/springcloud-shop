package com.bins.springcloud.shop.supplier.vo;
import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class BrandVo implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Long id;

	private String brandName;

	private String brandDesc;

	private Integer status;

	private String picUrl;
	
	private Integer isDel;
	
	private Integer sort;
	
	private Long createBy;
	
	private LocalDateTime createTime;
	
	private LocalDateTime updateTime;

}