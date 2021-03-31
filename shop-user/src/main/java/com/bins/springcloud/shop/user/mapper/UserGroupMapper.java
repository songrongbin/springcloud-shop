package com.bins.springcloud.shop.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.bins.springcloud.shop.user.dto.UserGroupDto;
import com.bins.springcloud.shop.user.dto.UserGroupPageDto;
import com.bins.springcloud.shop.user.entity.UserGroupEntity;


@Mapper
public interface UserGroupMapper {

	public UserGroupEntity findById(Long id);

	public int insert(UserGroupDto dto);

	public int updateById(UserGroupDto dto);

	public List<UserGroupEntity> findList(UserGroupPageDto pageDto);

	public List<UserGroupEntity> findByIds(List<Long> userGroupIds);
	
	public List<UserGroupEntity> findSelectList();

	public int deleteById(UserGroupDto dto);

}
