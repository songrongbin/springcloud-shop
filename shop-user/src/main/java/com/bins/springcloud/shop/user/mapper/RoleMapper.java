package com.bins.springcloud.shop.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.bins.springcloud.shop.user.dto.RoleDto;
import com.bins.springcloud.shop.user.entity.RoleEntity;

@Mapper
public interface RoleMapper {

	public List<RoleEntity> findRoleList();

	public int insertRole(RoleDto dto);

	public int updateRole(RoleDto dto);

	public RoleEntity findById(Long id);

	public int deleteRole(RoleDto dto);

}
