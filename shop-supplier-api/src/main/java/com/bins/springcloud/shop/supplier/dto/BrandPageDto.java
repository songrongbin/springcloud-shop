package com.bins.springcloud.shop.supplier.dto;
import java.io.Serializable;

import com.bins.springcloud.shop.common.dto.PageDto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class BrandPageDto extends PageDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String brandName;

	private String brandDesc;

}
