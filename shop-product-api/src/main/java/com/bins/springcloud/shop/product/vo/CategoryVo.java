package com.bins.springcloud.shop.product.vo;
import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class CategoryVo implements Serializable {
	
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
	
	private Integer isDel;
	
	private Long createBy;
	
	private LocalDateTime createTime;
	
	private LocalDateTime updateTime;

}