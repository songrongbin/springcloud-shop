package com.bins.springcloud.shop.user.service;

import com.bins.springcloud.shop.user.dto.LoginDto;
import com.bins.springcloud.shop.user.vo.LoginVo;

public interface UserService {

	LoginVo login(LoginDto dto);

}
