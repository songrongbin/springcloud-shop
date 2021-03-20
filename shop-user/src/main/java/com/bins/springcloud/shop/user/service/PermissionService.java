package com.bins.springcloud.shop.user.service;

import java.util.List;

import com.bins.springcloud.shop.common.vo.SelectVo;
import com.bins.springcloud.shop.user.dto.PermissionDto;
import com.bins.springcloud.shop.user.dto.PermissionPageDto;
import com.bins.springcloud.shop.user.entity.PermissionEntity;
import com.bins.springcloud.shop.user.vo.PermissionVo;
import com.github.pagehelper.PageInfo;

public interface PermissionService {

	PageInfo<PermissionEntity> getPermissionList(PermissionPageDto dto);

	List<PermissionVo> getPermissionList();

	PermissionVo findById(Long permissionId);

	List<SelectVo> getByModuelAndMenu();

	boolean permissionEdit(PermissionDto permissionDto);

	PermissionVo createPermission(PermissionDto permissionDto);

}
