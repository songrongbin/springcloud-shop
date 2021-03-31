package com.bins.springcloud.shop.user.vo;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class RoleVo implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private String roleCode;
	
	private String roleName;
	
	private Integer sort;
	
	private Integer isDel;
	
	private Long createBy;
	
	private Long updateBy;
	
	private String createByName;
	
	private LocalDateTime createTime;
	
	private LocalDateTime updateTime;

}
