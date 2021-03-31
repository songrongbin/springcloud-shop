package com.bins.springcloud.shop.user.service;

import com.bins.springcloud.shop.common.vo.ResultVo;
import com.bins.springcloud.shop.user.dto.RoleDto;
import com.bins.springcloud.shop.user.dto.RolePageDto;
import com.bins.springcloud.shop.user.vo.RoleVo;
import com.github.pagehelper.PageInfo;

public interface RoleService {

	ResultVo<Boolean> delRole(RoleDto dto);

	ResultVo<RoleVo> addNewRole(RoleDto dto);

	PageInfo<RoleVo> getRolePagination(RolePageDto pageDto);

	RoleVo getById(Long id);

	ResultVo<Boolean> updateRole(RoleDto dto);

	ResultVo<RoleVo> getDetail(RoleDto dto);

}
