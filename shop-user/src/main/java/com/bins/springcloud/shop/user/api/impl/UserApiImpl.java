package com.bins.springcloud.shop.user.api.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.bins.springcloud.shop.user.api.UserApi;
import com.bins.springcloud.shop.user.service.UserService;
import com.bins.springcloud.shop.user.vo.UserVo;

@RestController
public class UserApiImpl implements UserApi {
	
	@Autowired
	private UserService userService;

	@Override
	public UserVo getById(Long id) {
		return userService.getById(id);
	}

	@Override
	public List<UserVo> getByIds(List<Long> ids) {
		return userService.getByIds(ids);
	}

	@Override
	public String getUserName(Long id) {
		return userService.getUserName(id);
	}

}
