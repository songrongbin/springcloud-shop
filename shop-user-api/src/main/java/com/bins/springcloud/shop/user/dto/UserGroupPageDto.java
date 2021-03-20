package com.bins.springcloud.shop.user.dto;

import java.io.Serializable;

import com.bins.springcloud.shop.common.dto.PageDto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class UserGroupPageDto extends PageDto implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String deptCode;
	
	private String deptName;

}
