package com.bins.springcloud.shop.user.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.bins.springcloud.shop.common.vo.SelectVo;
import com.bins.springcloud.shop.user.dto.PermissionDto;
import com.bins.springcloud.shop.user.dto.PermissionPageDto;
import com.bins.springcloud.shop.user.entity.PermissionEntity;
import com.bins.springcloud.shop.user.mapper.PermissionMapper;
import com.bins.springcloud.shop.user.service.PermissionService;
import com.bins.springcloud.shop.user.vo.PermissionVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Optional;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

@Service
public class PermissionServiceImpl implements PermissionService {
	
	@Autowired
    private PermissionMapper permissionMapper;

	public PageInfo<PermissionEntity> getPermissionList(PermissionPageDto dto) {
		PageHelper.startPage(dto.getPageNum(), dto.getPageSize()).setOrderBy("id ASC");
		List<PermissionEntity> list = permissionMapper.findPermissionList();
		PageInfo<PermissionEntity> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}
	
	public List<PermissionVo> getPermissionList() {
		List<PermissionEntity> list = permissionMapper.findPermissionList();
		if (list == null) {
			return null;
		}
		List<PermissionVo> voList = Lists.newArrayList();
		PermissionVo vo;
		for (PermissionEntity eo : list) {
			vo = new PermissionVo();
			BeanUtils.copyProperties(eo, vo);
			voList.add(vo);
		}
		return voList;
	}

	public PermissionVo findById(Long id) {
		PermissionEntity permission = permissionMapper.findById(id);
		if (permission == null) {
			return null;
		}
		PermissionVo vo = new PermissionVo();
        BeanUtils.copyProperties(permission, vo);
        return vo;
		
	}

	public List<SelectVo> getByModuelAndMenu() {
		List<PermissionEntity> list = permissionMapper.getByModuelAndMenu();
		if (CollectionUtils.isEmpty(list)) {
			return null;
		}
		List<SelectVo> voList = new ArrayList<SelectVo>(list.size());
		SelectVo permission;
		for (PermissionEntity permissionEntity : list) {
			permission = new SelectVo();
			permission.setCode("" + permissionEntity.getId());
			permission.setName(permissionEntity.getPermissionName());
	        voList.add(permission);
		}
		return voList;
	}
	
	public PermissionVo createPermission(PermissionDto permissionDto) {
		permissionDto.setLevel(permissionDto.getPermissionType());
		permissionDto.setOperatorId(1l);
		PermissionVo vo = new PermissionVo();
		if (permissionMapper.insertPermission(permissionDto) > 0) {
			vo.setId(permissionDto.getId());
		} else {
			vo.setId(0l);
		}
        return vo;
	}

	public boolean permissionEdit(PermissionDto permissionDto) {
		permissionDto.setLevel(permissionDto.getPermissionType());
		permissionDto.setOperatorId(1l);
		int i = permissionMapper.updatePermission(permissionDto);
		if (i > 0) {
			return true;
		} else {
			return false;
		}
	}

	public List<PermissionVo> getPermissionList(Long id) {
		List<PermissionEntity> list = permissionMapper.findPermissionList();
		if (CollectionUtils.isEmpty(list)) {
			return null;
		}
		Set<PermissionVo> moduleList = Sets.newHashSet();
		Map<Long, List<PermissionVo>> menuMap = Maps.newHashMap();
		PermissionVo vo;
		for (PermissionEntity e : list) {
			Integer type = e.getPermissionType();
			Optional<Integer> optional = Optional.fromNullable(type);
			if (!optional.isPresent()) {
				continue;
			}
			if (e.getPermissionType() == 1) {
				vo = new PermissionVo();
				BeanUtils.copyProperties(e, vo);
				moduleList.add(vo);
			} else if (e.getPermissionType() == 2) {
				List<PermissionVo> menuList = menuMap.get(e.getPid());
				if (CollectionUtils.isEmpty(menuList)) {
					menuList = Lists.newArrayList();
				}
				vo = new PermissionVo();
				BeanUtils.copyProperties(e, vo);
				menuList.add(vo);
				menuMap.put(e.getPid(), menuList);
			}
		}
		if (CollectionUtils.isEmpty(moduleList)) {
			return null;
		}
		List<PermissionVo> voList = Lists.newArrayList();
		for (PermissionVo c : moduleList) {
			List<PermissionVo> menuList = menuMap.get(c.getId());
			c.setPermissionList(menuList);
			voList.add(c);
		}
		return voList;
	}

}
