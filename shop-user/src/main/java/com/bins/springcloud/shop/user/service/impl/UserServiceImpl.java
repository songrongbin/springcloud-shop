package com.bins.springcloud.shop.user.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.bins.springcloud.shop.user.security.CustomUserDetails;
import com.bins.springcloud.shop.user.service.PermissionService;
import com.bins.springcloud.shop.user.vo.PermissionVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.bins.springcloud.shop.common.constants.CommonHelper;
import com.bins.springcloud.shop.common.utils.PageUtil;
import com.bins.springcloud.shop.common.vo.ResultVo;
import com.bins.springcloud.shop.common.vo.SelectVo;
import com.bins.springcloud.shop.user.common.UserHelper;
import com.bins.springcloud.shop.user.dto.LoginDto;
import com.bins.springcloud.shop.user.dto.UserDto;
import com.bins.springcloud.shop.user.dto.UserPageDto;
import com.bins.springcloud.shop.user.entity.DeptEntity;
import com.bins.springcloud.shop.user.entity.UserEntity;
import com.bins.springcloud.shop.user.entity.UserGroupEntity;
import com.bins.springcloud.shop.user.mapper.UserMapper;
import com.bins.springcloud.shop.user.service.DeptService;
import com.bins.springcloud.shop.user.service.UserGroupService;
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

	@Autowired
	private DeptService deptService;

	@Autowired
	private UserGroupService userGroupService;

	@Autowired
	private PermissionService permissionService;

	@Override
	public ResultVo<LoginVo> login(LoginDto dto) {
		ResultVo<LoginVo> result = new ResultVo<LoginVo>();
		List<UserEntity> userList = userMapper.findByUserName(dto.getUserName());
		if (CollectionUtils.isEmpty(userList)) {
			result.isFail("未找到用户", null);
			return result;
		}
		UserEntity userEntity = userList.get(0);
		// TODO 增加密码锁定功能
//		BCryptPasswordEncoder bcryptPasswordEncoder = new BCryptPasswordEncoder();
//		boolean isMatch = bcryptPasswordEncoder.matches(dto.getPassword(), userEntity.getPassword());
//		if (!isMatch) {
//			result.isFail("密码错误", null);
//			return result;
//		}
		LoginVo vo = new LoginVo();
		BeanUtils.copyProperties(userEntity, vo);
		result.isOk(vo);
		return result;
	}

	@Override
	public PageInfo<UserVo> getUserPagination(UserPageDto pageDto) {
		PageHelper.startPage(pageDto.getPageNum(), pageDto.getPageSize()).setOrderBy("id DESC");
		List<UserEntity> userList = userMapper.getUserList(pageDto);
		if (CollectionUtils.isEmpty(userList)) {
			PageInfo<UserVo> pageInfo = new PageInfo<>(Collections.emptyList());
			return pageInfo;
		}
		PageInfo<UserEntity> originPageInfo = new PageInfo<UserEntity>(userList);
		PageInfo<UserVo> pageInfo = PageUtil.pageInfoToPageInfoVo(originPageInfo);
	    List<Long> deptIds = userList.stream().map(UserEntity::getDeptId).distinct().collect(Collectors.toList());
	    List<Long> userGroupIds = userList.stream().map(UserEntity::getUserGroupId).distinct().collect(Collectors.toList());
	    List<DeptEntity> deptList = deptService.findByIds(deptIds);
	    List<UserGroupEntity> userGroupList = userGroupService.findByIds(userGroupIds);
        Map<Long, String> deptMap = deptList.stream().collect(Collectors.toMap(DeptEntity::getId, DeptEntity::getDeptName));
        Map<Long, String> userGroupMap = userGroupList.stream().collect(Collectors.toMap(UserGroupEntity::getId, UserGroupEntity::getGroupName));
		UserVo vo;
		List<UserVo> list = Lists.newArrayListWithCapacity(userList.size());
		for (UserEntity entity : userList) {
			vo = new UserVo();
			BeanUtils.copyProperties(entity, vo);
			vo.setDeptName(deptMap.get(entity.getDeptId()));
			vo.setUserGroupName(userGroupMap.get(entity.getUserGroupId()));
			vo.setStatusName(UserHelper.Status.getStatusName(entity.getStatus()));
			list.add(vo);
		}
		pageInfo.setList(list);
		return pageInfo;
	}

	@Override
	public UserEntity findById(Long id) {
		return userMapper.findById(id);
	}

	@Override
	public List<UserEntity> findByIds(List<Long> ids) {
		if (CollectionUtils.isEmpty(ids)) {
			return Collections.emptyList();
		}
		return userMapper.findByIds(ids);
	}

	@Override
	public List<SelectVo> getUserSelectList() {
		// TODO Auto-generated method stub
		return null;
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
			vo.setUserName(temp.getUsername());
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
		return entity.getUsername();
	}

	@Override
	public ResultVo<UserVo> getDetail(UserDto dto) {
		ResultVo<UserVo> result = new ResultVo<UserVo>();
		UserEntity entity = userMapper.findById(dto.getId());
		if (entity == null) {
			result.isFail("用户不存在", null);
			return result;
		}
		UserVo vo = new UserVo();
        BeanUtils.copyProperties(entity, vo);

        UserEntity createByEntity = userMapper.findById(entity.getCreateBy());
        if (createByEntity != null) {
        	vo.setCreateByName(createByEntity.getUsername());
        }

        DeptEntity deptEntity = deptService.findById(entity.getDeptId());
        if (deptEntity != null) {
        	vo.setDeptName(deptEntity.getDeptName());
        }

        UserGroupEntity userGroupEntity = userGroupService.findById(entity.getUserGroupId());
        if (userGroupEntity != null) {
        	vo.setUserGroupName(userGroupEntity.getGroupName());
        }
        result.setData(vo);
        return result;
	}

	@Override
	public ResultVo<Boolean> editUser(UserDto dto) {
		ResultVo<Boolean> result = new ResultVo<Boolean>();
		UserEntity entity = userMapper.findById(dto.getId());
		if (entity == null) {
			result.isFail("用户不存在", null);
			return result;
		}
		dto.setUpdateBy(1l);
		if (userMapper.updateById(dto) == 0) {
			result.isFail("用户未改变");
			return result;
		}
		result.isOk(Boolean.TRUE);
		return result;
	}

	@Override
	public ResultVo<Boolean> delUser(UserDto dto) {
		dto.setIsDel(CommonHelper.DeleteStatus.DELETED.getCode());
		int num = userMapper.deleteById(dto);
		if (num > 0) {
			return new ResultVo<Boolean>(0, "删除成功", true);
		}
		return new ResultVo<Boolean>(0, "删除失败", false);
	}

	@Override
	public ResultVo<UserVo> addUser(UserDto dto) {
		ResultVo<UserVo> result = new ResultVo<UserVo>();
		dto.setCreateBy(1l);
		dto.setUpdateBy(1l);
		dto.setStatus(1);
		dto.setPassword("123456");
		if (userMapper.insert(dto) > 0) {
			UserVo vo = new UserVo();
			vo.setId(dto.getId());
			result.isOk(vo);
			return result;
		} else {
			result.isFail("添加失败");
			return result;
		}
	}

	@Override
	public Map<Long, UserEntity> getUserEntityMap(List<Long> userIds) {
		if (CollectionUtils.isEmpty(userIds)) {
			return Collections.emptyMap();
		}
		List<UserEntity> userList = findByIds(userIds);
		if (CollectionUtils.isEmpty(userList)) {
			return Collections.emptyMap();
		}
		Map<Long, UserEntity> userMap = userList.stream().collect(Collectors.toMap(UserEntity::getId, a -> a));
		return userMap;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		CustomUserDetails userDetails = null;
		UserEntity userEntity = userMapper.findByUserCode(username);
		if (userEntity == null) {
			return null;
		}
		List<PermissionVo> permissionList = permissionService.findByUserId(userEntity.getId());
		userDetails = new CustomUserDetails(username, userEntity.getPassword(), permissionList);
		userDetails.setUserId(userEntity.getId());
		return userDetails;
	}
}
