package com.bins.springcloud.shop.user.service.impl;

import org.springframework.stereotype.Service;

import com.bins.springcloud.shop.user.dto.LoginDto;
import com.bins.springcloud.shop.user.service.UserService;
import com.bins.springcloud.shop.user.vo.LoginVo;

@Service
public class UserServiceImpl implements UserService {

	@Override
	public LoginVo login(LoginDto dto) {
		LoginVo vo = new LoginVo();
		vo.setUserName("admin");
		vo.setUserCode("admin");
		return vo;
	}

}
