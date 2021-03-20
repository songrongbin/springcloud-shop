package com.bins.springcloud.shop.user.vo;

import java.io.Serializable;

import lombok.Data;

@Data
public class PersonInfoVo implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private String userName;
	
	private String userCode;
	
	private String phone;
	
    private String email;
	
	private Integer status;
	
	private String statusName;
	
	private Long deptId;
    
    private String deptName;
    
    private Long userGroupId;
    
    private String userGroupName;
	
	private String address;

}
