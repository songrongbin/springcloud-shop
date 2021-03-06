package com.bins.springcloud.shop.user.vo;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class DeptVo implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String deptCode;
	
	private String deptName;
	
	private Long pid;
	
	private String pidName;
	
	private Integer sort;
	
	private Integer isDel;
	
	private Long createBy;
	
	private String createByName;
	
	private LocalDateTime createTime;
	
	private LocalDateTime updateTime;

}
