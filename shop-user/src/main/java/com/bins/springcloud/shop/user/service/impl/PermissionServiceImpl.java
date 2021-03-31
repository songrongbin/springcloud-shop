package com.bins.springcloud.shop.user.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.bins.springcloud.shop.common.utils.PageUtil;
import com.bins.springcloud.shop.common.vo.ResultVo;
import com.bins.springcloud.shop.common.vo.SelectVo;
import com.bins.springcloud.shop.user.dto.PermissionDto;
import com.bins.springcloud.shop.user.dto.PermissionPageDto;
import com.bins.springcloud.shop.user.entity.PermissionEntity;
import com.bins.springcloud.shop.user.mapper.PermissionMapper;
import com.bins.springcloud.shop.user.service.PermissionService;
import com.bins.springcloud.shop.user.vo.PermissionVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class PermissionServiceImpl implements PermissionService {
	
	@Autowired
    private PermissionMapper permissionMapper;

//	public PageInfo<PermissionEntity> getPermissionList(PermissionPageDto dto) {
//		PageHelper.startPage(dto.getPageNum(), dto.getPageSize()).setOrderBy("id ASC");
//		List<PermissionEntity> list = permissionMapper.findPermissionList();
//		PageInfo<PermissionEntity> pageInfo = new PageInfo<>(list);
//		return pageInfo;
//	}
	
	@Override
	public PageInfo<PermissionVo> getPagination(PermissionPageDto pageDto) {
		PageHelper.startPage(pageDto.getPageNum(), pageDto.getPageSize()).setOrderBy("id ASC");
		List<PermissionEntity> list = permissionMapper.findPermissionList(pageDto);
		
		if (CollectionUtils.isEmpty(list)) {
			return new PageInfo<>(Collections.emptyList());
		}
		PageInfo<PermissionEntity> originPageInfo = new PageInfo<>(list);
		PageInfo<PermissionVo> pageInfo = PageUtil.pageInfoToPageInfoVo(originPageInfo);
		
		List<PermissionVo> pageList = list.stream().map(temp -> {
			PermissionVo vo = new PermissionVo();
			BeanUtils.copyProperties(temp, vo);
            return vo;
        }).collect(Collectors.toList());
		pageInfo.setList(pageList);
		return pageInfo;
	}
	
//	public List<PermissionVo> getPermissionList() {
//		List<PermissionEntity> list = permissionMapper.findPermissionList();
//		if (list == null) {
//			return null;
//		}
//		List<PermissionVo> voList = Lists.newArrayList();
//		PermissionVo vo;
//		for (PermissionEntity eo : list) {
//			vo = new PermissionVo();
//			BeanUtils.copyProperties(eo, vo);
//			voList.add(vo);
//		}
//		return voList;
//	}

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
		permissionDto.setCreateBy(1l);
		permissionDto.setUpdateBy(1l);
		permissionDto.setLevel(1);
		permissionDto.setPid(0l);
		PermissionVo vo = new PermissionVo();
		if (permissionMapper.insert(permissionDto) > 0) {
			vo.setId(permissionDto.getId());
		} else {
			vo.setId(0l);
		}
        return vo;
	}

	public boolean permissionEdit(PermissionDto permissionDto) {
		permissionDto.setLevel(permissionDto.getPermissionType());
		permissionDto.setUpdateBy(1l);
		int i = permissionMapper.updateById(permissionDto);
		if (i > 0) {
			return true;
		} else {
			return false;
		}
	}

	/*public List<PermissionVo> getPermissionList(Long id) {
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
	}*/

	@Override
	public ResultVo<Boolean> editPermission(PermissionDto dto) {
		ResultVo<Boolean> result = new ResultVo<Boolean>();
		PermissionEntity entity = permissionMapper.findById(dto.getId());
		if (entity == null) {
			result.isFail("权限不存在", null);
			return result;
		}
		if (permissionMapper.updateById(dto) == 0) {
			result.isFail("权限未修改");
			return result;
		}
		result.isOk(Boolean.TRUE);
		return result;
	}

	@Override
	public ResultVo<PermissionVo> addPermission(PermissionDto dto) {
		ResultVo<PermissionVo> result = new ResultVo<PermissionVo>();
		dto.setCreateBy(1l);
		dto.setUpdateBy(1l);
		dto.setPermissionType(1);
		dto.setLevel(1);
		dto.setPid(0l);
		if (permissionMapper.insert(dto) > 0) {
			PermissionVo vo = new PermissionVo();
			vo.setId(dto.getId());
			result.isOk(vo);
			return result;
		}
		result.isFail("添加失败");
		return result;
	}

	@Override
	public ResultVo<PermissionVo> getDetail(PermissionDto dto) {
		ResultVo<PermissionVo> result = new ResultVo<PermissionVo>();
		PermissionEntity entity = permissionMapper.findById(dto.getId());
		if (entity == null) {
			result.isFail("权限不存在", null);
			return result;
		}
		PermissionVo vo = new PermissionVo();
        BeanUtils.copyProperties(entity, vo);
        result.setData(vo);
        return result;
	}

	@Override
	public ResultVo<Boolean> delPermission(PermissionDto dto) {
		ResultVo<Boolean> result = new ResultVo<Boolean>();
		if (permissionMapper.deleteById(dto) > 0) {
			result.isOk(Boolean.TRUE);
			return result;
		}
		result.isFail("权限删除失败");
		return result;
	}

}
