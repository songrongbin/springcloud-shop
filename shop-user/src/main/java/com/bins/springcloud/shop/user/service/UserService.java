package com.bins.springcloud.shop.user.service;

import java.util.List;

import com.bins.springcloud.shop.common.vo.SelectVo;
import com.bins.springcloud.shop.user.dto.LoginDto;
import com.bins.springcloud.shop.user.dto.UserPageDto;
import com.bins.springcloud.shop.user.entity.UserEntity;
import com.bins.springcloud.shop.user.vo.LoginVo;
import com.bins.springcloud.shop.user.vo.UserVo;
import com.github.pagehelper.PageInfo;

public interface UserService {

	LoginVo login(LoginDto dto);

	PageInfo<UserVo> getUserPagination(UserPageDto userPageDto);

	UserVo getById(Long id);
	
	List<UserVo> getByIds(List<Long> ids);

	List<SelectVo> getUserSelectList();

	UserEntity findById(Long id);

	List<UserEntity> findByIds(List<Long> ids);

	String getUserName(Long id);
	
}
