package com.bins.springcloud.shop.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.bins.springcloud.shop.user.dto.PermissionDto;
import com.bins.springcloud.shop.user.entity.PermissionEntity;


@Mapper
public interface PermissionMapper {

	List<PermissionEntity> findPermissionList();

	int insertPermission(PermissionDto permissionDto);

	PermissionEntity findById(Long id);

	List<PermissionEntity> getByModuelAndMenu();

	int updatePermission(PermissionDto permissionDto);

}
