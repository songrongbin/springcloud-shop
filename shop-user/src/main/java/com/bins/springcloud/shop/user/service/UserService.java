package com.bins.springcloud.shop.user.service;

import java.util.List;
import java.util.Map;

import com.bins.springcloud.shop.common.vo.ResultVo;
import com.bins.springcloud.shop.common.vo.SelectVo;
import com.bins.springcloud.shop.user.dto.LoginDto;
import com.bins.springcloud.shop.user.dto.UserDto;
import com.bins.springcloud.shop.user.dto.UserPageDto;
import com.bins.springcloud.shop.user.entity.UserEntity;
import com.bins.springcloud.shop.user.vo.LoginVo;
import com.bins.springcloud.shop.user.vo.UserVo;
import com.github.pagehelper.PageInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

	ResultVo<LoginVo> login(LoginDto dto);

	PageInfo<UserVo> getUserPagination(UserPageDto pageDto);

	UserVo getById(Long id);

	List<UserVo> getByIds(List<Long> ids);

	List<SelectVo> getUserSelectList();

	UserEntity findById(Long id);

	List<UserEntity> findByIds(List<Long> ids);

	String getUserName(Long id);

	ResultVo<UserVo> getDetail(UserDto dto);

	ResultVo<Boolean> editUser(UserDto dto);

	ResultVo<Boolean> delUser(UserDto dto);

	ResultVo<UserVo> addUser(UserDto dto);

    Map<Long, UserEntity> getUserEntityMap(List<Long> userIds);
}
