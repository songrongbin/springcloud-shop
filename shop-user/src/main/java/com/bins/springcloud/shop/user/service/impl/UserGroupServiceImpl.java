package com.bins.springcloud.shop.user.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.bins.springcloud.shop.common.constants.CommonHelper;
import com.bins.springcloud.shop.common.utils.PageUtil;
import com.bins.springcloud.shop.common.vo.ResultVo;
import com.bins.springcloud.shop.common.vo.SelectVo;
import com.bins.springcloud.shop.user.dto.UserGroupDto;
import com.bins.springcloud.shop.user.dto.UserGroupPageDto;
import com.bins.springcloud.shop.user.entity.UserEntity;
import com.bins.springcloud.shop.user.entity.UserGroupEntity;
import com.bins.springcloud.shop.user.mapper.UserGroupMapper;
import com.bins.springcloud.shop.user.service.UserGroupService;
import com.bins.springcloud.shop.user.service.UserService;
import com.bins.springcloud.shop.user.vo.UserGroupVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class UserGroupServiceImpl implements UserGroupService {
	
	@Autowired
	private UserGroupMapper userGroupMapper;
	
	@Autowired
	private UserService userService;
	
	public PageInfo<UserGroupVo> getUserGroupPagination(UserGroupPageDto pageDto) {
		PageHelper.startPage(pageDto.getPageNum(), pageDto.getPageSize()).setOrderBy("id DESC");
		List<UserGroupEntity> list = userGroupMapper.findList();
		if (CollectionUtils.isEmpty(list)) {
			return new PageInfo<>(Collections.emptyList());
		}
		PageInfo<UserGroupEntity> originPageInfo = new PageInfo<>(list);
		PageInfo<UserGroupVo> pageInfo = PageUtil.pageInfoToPageInfoVo(originPageInfo);
		
		List<Long> userIds = list.stream().map(UserGroupEntity::getCreateBy).distinct().collect(Collectors.toList());
		List<UserEntity> userList = userService.findByIds(userIds);
		Map<Long, UserEntity> userMap = userList.stream().collect(Collectors.toMap(UserEntity::getId, a -> a));
		
		List<UserGroupVo> userGroupList = list.stream().map(temp -> {
			UserGroupVo userGroup = new UserGroupVo();
			userGroup.setId(temp.getId());
			userGroup.setGroupName(temp.getGroupName());
			userGroup.setIsDel(temp.getIsDel());
			userGroup.setCreateBy(temp.getCreateBy());
			userGroup.setCreateTime(temp.getCreateTime());
			UserEntity user = userMap.get(temp.getCreateBy());
			if (user != null) {
				userGroup.setCreateName(userMap.get(temp.getCreateBy()).getUserName());
			}
            return userGroup;
        }).collect(Collectors.toList());
		pageInfo.setList(userGroupList);
		return pageInfo;
	}

	public UserGroupVo getById(Long id) {
		UserGroupEntity entity = userGroupMapper.findById(id);
		if (entity == null) {
			return null;
		}
		UserGroupVo vo = new UserGroupVo();
		BeanUtils.copyProperties(entity, vo);
		return vo;
	}
	
	public UserGroupEntity findById(Long id) {
		return userGroupMapper.findById(id);
	}
	
	@Override
	public List<UserGroupEntity> findByIds(List<Long> ids) {
		if (CollectionUtils.isEmpty(ids)) {
			return Collections.emptyList();
		}
		List<UserGroupEntity> list = userGroupMapper.findByIds(ids);
		if (CollectionUtils.isEmpty(list)) {
			return Collections.emptyList();
		}
		return list;
	}
	
	@Override
	public List<UserGroupVo> getByIds(List<Long> ids) {
		if (CollectionUtils.isEmpty(ids)) {
			return Collections.emptyList();
		}
		List<UserGroupEntity> list = userGroupMapper.findByIds(ids);
		if (CollectionUtils.isEmpty(list)) {
			return Collections.emptyList();
		}
		List<UserGroupVo> voList = list.stream().map(temp -> {
			UserGroupVo vo = new UserGroupVo();
			BeanUtils.copyProperties(temp, vo);
            return vo;
        }).collect(Collectors.toList());
		return voList;
	}

	public int updateUserGroup(UserGroupDto dto) {
		return userGroupMapper.update(dto);
	}
	
	public boolean addNewUserGroup(UserGroupDto dto) {
		int result = userGroupMapper.insert(dto);
		if (result > 0) {
			return true;
		} else {
			return false;
		}
	}

	public ResultVo<Boolean> delUserGroup(UserGroupDto dto) {
		dto.setIsDel(CommonHelper.DeleteStatus.DELETED.getCode());
		int num = userGroupMapper.deleteUserGroup(dto);
		if (num > 0) {
			return new ResultVo<Boolean>(0, "删除成功", true);
		}
		return new ResultVo<Boolean>(0, "删除失败", false);
	}
	
	public List<SelectVo> getUserGroupSelectList() {
		List<UserGroupEntity> list = userGroupMapper.findList();
		if (CollectionUtils.isEmpty(list)) {
			return null;
		}
		List<SelectVo> voList = new ArrayList<SelectVo>(list.size());
		SelectVo vo;
		for (UserGroupEntity entity : list) {
			vo = new SelectVo();
			vo.setCode("" + entity.getId());
			vo.setName(entity.getGroupName());
	        voList.add(vo);
		}
		return voList;
	}

}
