package com.bins.springcloud.shop.user.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class DeptDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private String deptCode;

	private String deptName;

	private Long pid;

	private Integer sort;

	private Integer isDel;

	private Long createBy;

}
