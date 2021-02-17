package com.bins.springcloud.shop.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bins.springcloud.shop.common.vo.ResultVo;
import com.bins.springcloud.shop.user.dto.LoginDto;
import com.bins.springcloud.shop.user.service.UserService;
import com.bins.springcloud.shop.user.vo.LoginVo;

@RestController
public class LoginController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/login")
	public ResultVo<LoginVo> login(LoginDto dto) {
		LoginVo vo = userService.login(dto);
		ResultVo<LoginVo> result = new ResultVo<LoginVo>();
		result.isOk(vo);
		return result;
	}
	
}
