package com.bins.springcloud.shop.user.vo;

import java.io.Serializable;
import lombok.Data;

@Data
public class LoginVo implements Serializable {

	private static final long serialVersionUID = 4601500018909278569L;

	private Long id;

	private String userName;

	private String userCode;

	private String token;

	private Integer status;

	private String phone;

	private String email;

	private String address;

	private Long deptId;

	private Long userGroupId;

	private String errorMsg;

}
