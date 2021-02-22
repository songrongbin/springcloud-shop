package com.bins.springcloud.shop.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.bins.springcloud.shop.user.dto.UserDto;
import com.bins.springcloud.shop.user.entity.UserEntity;


@Mapper
public interface UserMapper {

	UserEntity findByUserCode(String userCode);

	List<UserEntity> getAllUserList();

	UserEntity getById(Long id);

	int updateUser(UserDto userDto);

	int insertUser(UserEntity userEntity);

	List<UserEntity> findByUserInfo(UserDto dto);

	int changeUserStatus(UserEntity user);

	int changeUserPassword(UserEntity user);

	int deleteUser(UserDto dto);

	List<UserEntity> getByIds(List<Long> ids);

	int changePersonInfo(UserDto userDto);

}
