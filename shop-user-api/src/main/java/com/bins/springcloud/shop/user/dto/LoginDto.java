package com.bins.springcloud.shop.user.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class LoginDto implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String userName;
	
	private String password;
	
	private String passwordMD5;

}
