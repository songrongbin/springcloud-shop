package com.bins.springcloud.shop.user.entity;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UserEntity {
	
	private Long id;

    private String userCode;

    private String userName;

    private String password;
    
    private String phone;
    
    private String email;
    
    private Long deptId;
    
    private Long userGroupId;
    
    private Integer status;
    
    private Long operatorId;
    
    private Integer isDel;
    
    private LocalDateTime createTime;
    
    private LocalDateTime updateTime;

}
