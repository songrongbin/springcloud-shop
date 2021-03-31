package com.bins.springcloud.shop.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.bins.springcloud.shop.user.dto.RoleDto;
import com.bins.springcloud.shop.user.dto.RolePageDto;
import com.bins.springcloud.shop.user.entity.RoleEntity;

@Mapper
public interface RoleMapper {

	public List<RoleEntity> findRoleList(RolePageDto pageDto);

	public int insert(RoleDto dto);

	public int updateById(RoleDto dto);

	public RoleEntity findById(Long id);

	public int deleteById(RoleDto dto);

}
