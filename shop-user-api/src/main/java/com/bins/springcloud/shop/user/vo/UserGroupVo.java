package com.bins.springcloud.shop.user.vo;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class UserGroupVo implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String groupName;
	
	private Integer isDel;
	
	private Long createBy;
	
	private String createName;
	
	private LocalDateTime createTime;

}
