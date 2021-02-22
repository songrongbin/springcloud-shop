package com.bins.springcloud.shop.user.service;

import com.bins.springcloud.shop.user.dto.LoginDto;
import com.bins.springcloud.shop.user.dto.UserPageDto;
import com.bins.springcloud.shop.user.vo.LoginVo;
import com.bins.springcloud.shop.user.vo.UserVo;
import com.github.pagehelper.PageInfo;

public interface UserService {

	LoginVo login(LoginDto dto);

	PageInfo<UserVo> getUserPagination(UserPageDto userPageDto);

}
