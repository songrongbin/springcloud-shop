package com.bins.springcloud.shop.user.controller;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
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
	public ResultVo<LoginVo> login(@RequestBody LoginDto dto) {
		ResultVo<LoginVo> result = new ResultVo<LoginVo>();
		if (StringUtils.isEmpty(dto.getUserName())) {
			result.isFail("用户名不能空", null);
		}
		if (StringUtils.isEmpty(dto.getPassword())) {
			result.isFail("密码不能空", null);
		}
		result = userService.login(dto);
		return result;
	}
	
}
