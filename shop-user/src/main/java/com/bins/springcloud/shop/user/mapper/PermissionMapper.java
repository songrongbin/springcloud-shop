package com.bins.springcloud.shop.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.bins.springcloud.shop.user.dto.PermissionDto;
import com.bins.springcloud.shop.user.dto.PermissionPageDto;
import com.bins.springcloud.shop.user.entity.PermissionEntity;


@Mapper
public interface PermissionMapper {

	List<PermissionEntity> findPermissionList(PermissionPageDto pageDto);

	List<PermissionEntity> findAll();

	PermissionEntity findById(Long id);

	List<PermissionEntity> getByModuelAndMenu();

	int insert(PermissionDto dto);

	int updateById(PermissionDto dto);

	int deleteById(PermissionDto dto);

}
