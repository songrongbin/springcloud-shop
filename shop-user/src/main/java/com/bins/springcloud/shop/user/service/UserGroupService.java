package com.bins.springcloud.shop.user.service;

import java.util.List;

import com.bins.springcloud.shop.common.vo.ResultVo;
import com.bins.springcloud.shop.common.vo.SelectVo;
import com.bins.springcloud.shop.user.dto.DeptDto;
import com.bins.springcloud.shop.user.dto.UserGroupDto;
import com.bins.springcloud.shop.user.dto.UserGroupPageDto;
import com.bins.springcloud.shop.user.entity.UserGroupEntity;
import com.bins.springcloud.shop.user.vo.UserGroupVo;
import com.github.pagehelper.PageInfo;

public interface UserGroupService {

	List<SelectVo> getUserGroupSelectList();

	PageInfo<UserGroupVo> getUserGroupPagination(UserGroupPageDto pageDto);

	ResultVo<Boolean> updateUserGroup(UserGroupDto dto);

	UserGroupVo getById(Long id);
	
	List<UserGroupVo> getByIds(List<Long> ids);

	ResultVo<UserGroupVo> addNewUserGroup(UserGroupDto dto);

	ResultVo<Boolean> delUserGroup(UserGroupDto dto);
	
	UserGroupEntity findById(Long id);

	List<UserGroupEntity> findByIds(List<Long> ids);

	ResultVo<UserGroupVo> getDetail(DeptDto dto);

}
