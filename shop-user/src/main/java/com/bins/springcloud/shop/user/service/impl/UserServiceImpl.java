package com.bins.springcloud.shop.user.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.bins.springcloud.shop.common.utils.PageUtil;
import com.bins.springcloud.shop.common.vo.SelectVo;
import com.bins.springcloud.shop.user.dto.LoginDto;
import com.bins.springcloud.shop.user.dto.UserPageDto;
import com.bins.springcloud.shop.user.entity.UserEntity;
import com.bins.springcloud.shop.user.mapper.UserMapper;
import com.bins.springcloud.shop.user.service.UserService;
import com.bins.springcloud.shop.user.vo.LoginVo;
import com.bins.springcloud.shop.user.vo.UserVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper userMapper;

	@Override
	public LoginVo login(LoginDto dto) {
		LoginVo vo = new LoginVo();
		vo.setUserName("admin");
		vo.setUserCode("admin");
		return vo;
	}

	@Override
	public PageInfo<UserVo> getUserPagination(UserPageDto userPageDto) {
		PageHelper.startPage(userPageDto.getPageNum(), userPageDto.getPageSize()).setOrderBy("id DESC");
		List<UserEntity> userList = userMapper.getAllUserList();
		if (CollectionUtils.isEmpty(userList)) {
			PageInfo<UserVo> pageInfo = new PageInfo<>(Collections.emptyList());
			return pageInfo;
		}
		PageInfo<UserEntity> originPageInfo = new PageInfo<UserEntity>(userList);
		PageInfo<UserVo> pageInfo = PageUtil.pageInfoToPageInfoVo(originPageInfo);
	    // List<Long> deptIds = userList.stream().map(UserEntity::getDeptId).distinct().collect(Collectors.toList());
	    // List<Long> userGroupIds = userList.stream().map(UserEntity::getUserGroupId).distinct().collect(Collectors.toList());
	    // List<DeptEntity> deptList = deptService.getDeptByIds(deptIds);
	    // List<UserGroupEntity> userGroupList = userGroupService.getUserGroupByIds(userGroupIds);
        // Map<Long, String> deptMap = deptList.stream().collect(Collectors.toMap(DeptEntity::getId, DeptEntity::getDeptName));
        // Map<Long, String> userGroupMap = userGroupList.stream().collect(Collectors.toMap(UserGroupEntity::getId, UserGroupEntity::getGroupName));
		UserVo vo;
		List<UserVo> list = Lists.newArrayListWithCapacity(userList.size());
		for (UserEntity entity : userList) {
			vo = new UserVo();
			BeanUtils.copyProperties(entity, vo);
			// vo.setDeptName(deptMap.get(entity.getDeptId()));
			// vo.setUserGroupName(userGroupMap.get(entity.getUserGroupId()));
			// vo.setStatusName(UserHelper.Status.getStatusName(entity.getStatus()));
			list.add(vo);
		}
		pageInfo.setList(list);
		return pageInfo;
	}

	@Override
	public List<UserEntity> findByIds(List<Long> ids) {
		return userMapper.findByIds(ids);
	}

	@Override
	public List<SelectVo> getUserSelectList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserEntity findById(Long id) {
		return userMapper.findById(id);
	}

	@Override
	public List<UserVo> getByIds(List<Long> ids) {
		List<UserEntity> list = userMapper.findByIds(ids);
		if (CollectionUtils.isEmpty(list)) {
			return Collections.emptyList();
		}
		List<UserVo> voList = list.stream().map(temp -> {
			UserVo vo = new UserVo();
			vo.setId(temp.getId());
			vo.setUserName(temp.getUserName());
			vo.setUserCode(temp.getUserCode());
			vo.setIsDel(temp.getIsDel());
			vo.setCreateTime(temp.getCreateTime());
			vo.setUpdateTime(temp.getUpdateTime());
            return vo;
        }).collect(Collectors.toList());
		return voList;
	}

	@Override
	public UserVo getById(Long id) {
		UserEntity entity = userMapper.findById(id);
		UserVo vo = new UserVo();
		BeanUtils.copyProperties(entity, vo);
		return vo;
	}

	@Override
	public String getUserName(Long id) {
		UserEntity entity = userMapper.findById(id);
		if (entity == null) {
			return "";
		}
		return entity.getUserName();
	}

}
