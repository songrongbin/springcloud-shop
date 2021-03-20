package com.bins.springcloud.shop.product.dto;
import java.io.Serializable;

import com.bins.springcloud.shop.common.dto.PageDto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class GoodsPageDto extends PageDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String goodsName;
	
	private String goodsCode;

}
