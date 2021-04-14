package com.bins.springcloud.shop.user.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.bins.springcloud.shop.common.constants.CommonHelper;
import com.bins.springcloud.shop.common.constants.CommonHelper.ResultCodeEnum;
import com.bins.springcloud.shop.common.utils.PageUtil;
import com.bins.springcloud.shop.common.vo.ResultVo;
import com.bins.springcloud.shop.common.vo.SelectVo;
import com.bins.springcloud.shop.user.dto.DeptDto;
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
		List<UserGroupEntity> list = userGroupMapper.findList(pageDto);
		if (CollectionUtils.isEmpty(list)) {
			return new PageInfo<>(Collections.emptyList());
		}
		PageInfo<UserGroupEntity> originPageInfo = new PageInfo<>(list);
		PageInfo<UserGroupVo> pageInfo = PageUtil.pageInfoToPageInfoVo(originPageInfo);

		List<Long> userIds = list.stream().map(UserGroupEntity::getCreateBy).distinct().collect(Collectors.toList());
		List<UserEntity> userList = userService.findByIds(userIds);
		Map<Long, String> userMap = userList.stream().collect(Collectors.toMap(UserEntity::getId, UserEntity::getUserName));

		List<UserGroupVo> userGroupList = list.stream().map(temp -> {
			UserGroupVo vo = new UserGroupVo();
			BeanUtils.copyProperties(temp, vo);
			vo.setCreateByName(userMap.get(temp.getCreateBy()));
            return vo;
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

	@Transactional
	public ResultVo<Boolean> updateUserGroup(UserGroupDto dto) {
		ResultVo<Boolean> result = new ResultVo<Boolean>();
		UserGroupEntity entity = userGroupMapper.findById(dto.getId());
		if (entity == null) {
			result.isFail("用户组不存在", null);
			return result;
		}
		if (userGroupMapper.updateById(dto) == 0) {
			result.isFail("用户组未修改");
			return result;
		}
		result.isOk(Boolean.TRUE);
		return result;
	}

	@Transactional
	public ResultVo<UserGroupVo> addNewUserGroup(UserGroupDto dto) {
		dto.setCreateBy(1l);
		dto.setUpdateBy(1l);
		int result = userGroupMapper.insert(dto);
		if (result > 0) {
			UserGroupVo vo = new UserGroupVo();
			vo.setId(dto.getId());
			return new ResultVo<UserGroupVo>(ResultCodeEnum.SUCCESS.getCode(), "保存成功", vo);
		} else {
			return new ResultVo<UserGroupVo>(ResultCodeEnum.FAILURE.getCode(), "保存失败", null);
		}
	}

	@Transactional
	public ResultVo<Boolean> delUserGroup(UserGroupDto dto) {
		dto.setIsDel(CommonHelper.DeleteStatus.DELETED.getCode());
		dto.setIsDel(CommonHelper.DeleteStatus.NO_DELETE.getCode());
		int num = userGroupMapper.deleteById(dto);
		if (num > 0) {
			return new ResultVo<Boolean>(ResultCodeEnum.SUCCESS.getCode(), "删除成功", true);
		}
		return new ResultVo<Boolean>(ResultCodeEnum.FAILURE.getCode(), "删除失败", false);
	}

	public List<SelectVo> getUserGroupSelectList() {
		List<UserGroupEntity> list = userGroupMapper.findSelectList();
		if (CollectionUtils.isEmpty(list)) {
			return Collections.emptyList();
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

	@Override
	public ResultVo<UserGroupVo> getDetail(DeptDto dto) {
		ResultVo<UserGroupVo> result = new ResultVo<UserGroupVo>();
		UserGroupEntity entity = userGroupMapper.findById(dto.getId());
		if (entity == null) {
			result.isFail("用户组不存在", null);
			return result;
		}
		UserGroupVo vo = new UserGroupVo();
        BeanUtils.copyProperties(entity, vo);

        UserEntity createBy = userService.findById(vo.getCreateBy());
        if (createBy != null) {
        	vo.setCreateByName(createBy.getUserName());
        }
        result.setData(vo);
        return result;
	}

}
