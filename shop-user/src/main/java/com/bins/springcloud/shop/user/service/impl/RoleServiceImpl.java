package com.bins.springcloud.shop.user.service.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bins.springcloud.shop.common.constants.CommonHelper;
import com.bins.springcloud.shop.common.utils.PageUtil;
import com.bins.springcloud.shop.common.vo.ResultVo;
import com.bins.springcloud.shop.user.dto.RoleDto;
import com.bins.springcloud.shop.user.dto.RolePageDto;
import com.bins.springcloud.shop.user.entity.RoleEntity;
import com.bins.springcloud.shop.user.entity.UserEntity;
import com.bins.springcloud.shop.user.mapper.RoleMapper;
import com.bins.springcloud.shop.user.service.RoleService;
import com.bins.springcloud.shop.user.service.UserService;
import com.bins.springcloud.shop.user.vo.RoleVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleMapper roleMapper;
	
	@Autowired
	private UserService userService;
	
	public PageInfo<RoleVo> getRolePagination(RolePageDto pageDto) {
		PageHelper.startPage(pageDto.getPageNum(), pageDto.getPageSize()).setOrderBy("id DESC");
		List<RoleEntity> list = roleMapper.findRoleList();
		PageInfo<RoleEntity> originPageInfo = new PageInfo<>(list);
		PageInfo<RoleVo> pageInfo = PageUtil.pageInfoToPageInfoVo(originPageInfo);
		
		List<Long> userIds = list.stream().map(RoleEntity::getOperatorId).distinct().collect(Collectors.toList());
		List<UserEntity> userList = userService.findByIds(userIds);
		Map<Long, UserEntity> userMap = userList.stream().collect(Collectors.toMap(UserEntity::getId, a -> a));
		
		List<RoleVo> roleList = list.stream().map(temp -> {
			RoleVo role = new RoleVo();
			role.setId(temp.getId());
			role.setRoleCode(temp.getRoleCode());
			role.setRoleName(temp.getRoleName());
			role.setIsDel(temp.getIsDel());
			role.setOperatorId(temp.getOperatorId());
			role.setSort(temp.getSort());
			role.setCreateTime(temp.getCreateTime());
			role.setUpdateTime(temp.getUpdateTime());
			UserEntity user = userMap.get(temp.getOperatorId());
			if (user != null) {
				role.setOperatorName(user.getUserName());
			}
            return role;
        }).collect(Collectors.toList());
		pageInfo.setList(roleList);
		return pageInfo;
	}

	public RoleVo getById(Long id) {
		RoleEntity entity = roleMapper.findById(id);
		if (entity == null) {
			return null;
		}
		RoleVo vo = new RoleVo();
		BeanUtils.copyProperties(entity, vo);
		return vo;
	}
	
	public RoleEntity findById(Long id) {
		return roleMapper.findById(id);
	}

	public int updateRole(RoleDto dto) {
		return roleMapper.updateRole(dto);
	}

	public boolean addNewRole(RoleDto dto) {
		// dto.setOperatorId(SecurityUtils.getUserId());
		int result = roleMapper.insertRole(dto);
		if (result > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public ResultVo<Boolean> delRole(RoleDto dto) {
		dto.setIsDel(CommonHelper.DeleteStatus.DELETED.getCode());
		int num = roleMapper.deleteRole(dto);
		if (num > 0) {
			return new ResultVo<Boolean>(0, "删除成功", true);
		}
		return new ResultVo<Boolean>(0, "删除失败", false);
	}

}
