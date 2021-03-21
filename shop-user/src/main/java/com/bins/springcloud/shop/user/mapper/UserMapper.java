package com.bins.springcloud.shop.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.bins.springcloud.shop.user.dto.UserDto;
import com.bins.springcloud.shop.user.entity.UserEntity;

@Mapper
public interface UserMapper {

	UserEntity findByUserCode(String userCode);

	List<UserEntity> getAllUserList();

	UserEntity findById(Long id);

	int updateById(UserDto userDto);

	int inser(UserEntity userEntity);

	List<UserEntity> findByUserInfo(UserDto dto);

	int changeUserStatus(UserEntity user);

	int changeUserPassword(UserEntity user);

	int deleteUser(UserDto dto);

	List<UserEntity> findByIds(List<Long> ids);

	int changePersonInfo(UserDto userDto);

	List<UserEntity> findByUserName(String userName);

}
