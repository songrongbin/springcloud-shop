package com.bins.springcloud.shop.user.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private String userName;

	private String userCode;

	private Integer status;

	private String phone;

	private String email;

	private String address;

	private Long deptId;

	private Long userGroupId;

	private Integer isDel;
	
	private Long createBy;

	private Long updateBy;
	
	private String password;

	private String oldPassword;

	private String newPassword1;

	private String newPassword2;

}
