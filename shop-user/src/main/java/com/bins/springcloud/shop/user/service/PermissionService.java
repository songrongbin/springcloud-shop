package com.bins.springcloud.shop.user.service;

import java.util.List;

import com.bins.springcloud.shop.common.vo.ResultVo;
import com.bins.springcloud.shop.common.vo.SelectVo;
import com.bins.springcloud.shop.user.dto.PermissionDto;
import com.bins.springcloud.shop.user.dto.PermissionPageDto;
import com.bins.springcloud.shop.user.dto.UserDto;
import com.bins.springcloud.shop.user.vo.PermissionVo;
import com.github.pagehelper.PageInfo;

public interface PermissionService {

	PermissionVo findById(Long id);

	List<PermissionVo> findByUserId(Long id);

	ResultVo<Boolean> editPermission(PermissionDto dto);

	ResultVo<PermissionVo> addPermission(PermissionDto dto);

	PageInfo<PermissionVo> getPagination(PermissionPageDto pageDto);

	ResultVo<PermissionVo> getDetail(PermissionDto dto);

	ResultVo<Boolean> delPermission(PermissionDto dto);

	ResultVo<List<PermissionVo>> getUserMenuList(UserDto dto);

	ResultVo<List<SelectVo>> pidList(PermissionDto dto);
}
