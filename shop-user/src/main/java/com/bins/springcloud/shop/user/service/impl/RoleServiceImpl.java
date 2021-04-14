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
import org.springframework.transaction.annotation.Transactional;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleMapper roleMapper;

	@Autowired
	private UserService userService;

	public PageInfo<RoleVo> getRolePagination(RolePageDto pageDto) {
		PageHelper.startPage(pageDto.getPageNum(), pageDto.getPageSize()).setOrderBy("id DESC");
		List<RoleEntity> list = roleMapper.findRoleList(pageDto);
		PageInfo<RoleEntity> originPageInfo = new PageInfo<>(list);
		PageInfo<RoleVo> pageInfo = PageUtil.pageInfoToPageInfoVo(originPageInfo);

		List<Long> userIds = list.stream().map(RoleEntity::getCreateBy).distinct().collect(Collectors.toList());
		List<UserEntity> userList = userService.findByIds(userIds);
		Map<Long, UserEntity> userMap = userList.stream().collect(Collectors.toMap(UserEntity::getId, a -> a));

		List<RoleVo> roleList = list.stream().map(temp -> {
			RoleVo role = new RoleVo();
			BeanUtils.copyProperties(temp, role);
			UserEntity user = userMap.get(temp.getCreateBy());
			if (user != null) {
				role.setCreateByName(user.getUserName());
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

	@Transactional
	public ResultVo<Boolean> updateRole(RoleDto dto) {
		ResultVo<Boolean> result = new ResultVo<Boolean>();
		RoleEntity entity = roleMapper.findById(dto.getId());
		if (entity == null) {
			result.isFail("角色不存在", null);
			return result;
		}
		dto.setUpdateBy(entity.getUpdateBy());
		if (roleMapper.updateById(dto) > 0) {
			result.isOk(Boolean.TRUE);
			return result;
		}
		result.isFail("删除失败");
		return result;
	}

	@Transactional
	public ResultVo<RoleVo> addNewRole(RoleDto dto) {
		ResultVo<RoleVo> result = new ResultVo<RoleVo>();
		dto.setCreateBy(1l);
		dto.setUpdateBy(1l);
		if (roleMapper.insert(dto) > 0) {
			RoleVo vo = new RoleVo();
			vo.setId(dto.getId());
			result.isOk(vo);
			return result;
		} else {
			result.isFail("添加失败");
			return result;
		}
	}

	@Transactional
	public ResultVo<Boolean> delRole(RoleDto dto) {
		dto.setIsDel(CommonHelper.DeleteStatus.DELETED.getCode());
		int num = roleMapper.deleteById(dto);
		if (num > 0) {
			return new ResultVo<Boolean>(0, "删除成功", true);
		}
		return new ResultVo<Boolean>(0, "删除失败", false);
	}

	@Override
	public ResultVo<RoleVo> getDetail(RoleDto dto) {
		ResultVo<RoleVo> result = new ResultVo<RoleVo>();
		RoleEntity entity = roleMapper.findById(dto.getId());
		if (entity == null) {
			result.isFail("角色不存在", null);
			return result;
		}
		RoleVo vo = new RoleVo();
        BeanUtils.copyProperties(entity, vo);

        UserEntity createBy = userService.findById(vo.getCreateBy());
        if (createBy != null) {
        	vo.setCreateByName(createBy.getUserName());
        }
        result.setData(vo);
        return result;
	}

}
