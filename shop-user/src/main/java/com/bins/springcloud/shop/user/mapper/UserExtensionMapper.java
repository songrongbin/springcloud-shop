package com.bins.springcloud.shop.user.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.bins.springcloud.shop.user.dto.UserDto;
import com.bins.springcloud.shop.user.entity.UserExtensionEntity;

@Mapper
public interface UserExtensionMapper {

	UserExtensionEntity findByUserId(Long userId);

	int updateByUserId(UserDto userDto);

	int insertUserExtension(UserDto userDto);

}
