package com.bins.springcloud.shop.user.service.impl;

import java.util.*;
import java.util.stream.Collectors;

import com.bins.springcloud.shop.common.constants.CommonHelper;
import com.bins.springcloud.shop.user.dto.UserDto;
import com.bins.springcloud.shop.user.entity.UserEntity;
import com.bins.springcloud.shop.user.security.SecurityUtils;
import com.bins.springcloud.shop.user.service.UserService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

	@Autowired
	private UserService userService;

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

	public PermissionVo findById(Long id) {
		PermissionEntity permission = permissionMapper.findById(id);
		if (permission == null) {
			return null;
		}
		PermissionVo vo = new PermissionVo();
        BeanUtils.copyProperties(permission, vo);
        return vo;
	}

	@Override
	public List<PermissionVo> findByUserId(Long id) {
		List<PermissionEntity> list = permissionMapper.findAll();
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

	public List<SelectVo> getByModuelAndMenu() {
		List<PermissionEntity> list = permissionMapper.getByModuelAndMenu();
		if (CollectionUtils.isEmpty(list)) {
			return null;
		}
		List<SelectVo> voList = transferSelectVoList(list, false);
		return voList;
	}

	private List<SelectVo> transferSelectVoList(List<PermissionEntity> list, Boolean isHasRoot) {
		List<SelectVo> voList;
		if (isHasRoot) {
			voList = new ArrayList<SelectVo>(list.size() + 1);
			SelectVo rootSelectVo = new SelectVo();
			rootSelectVo.setCode("0");
			rootSelectVo.setName("根权限");
			voList.add(rootSelectVo);
		} else {
			voList = new ArrayList<SelectVo>(list.size());
		}

		SelectVo permission;
		for (PermissionEntity permissionEntity : list) {
			permission = new SelectVo();
			permission.setCode("" + permissionEntity.getId());
			permission.setName(permissionEntity.getPermissionName());
			voList.add(permission);
		}
		return voList;
	}

	@Transactional
	@Override
	public ResultVo<Boolean> editPermission(PermissionDto dto) {
		ResultVo<Boolean> result = new ResultVo<Boolean>();
		PermissionEntity entity = permissionMapper.findById(dto.getId());
		if (entity == null) {
			result.isFail("权限不存在", null);
			return result;
		}
		dto.setUpdateBy(1l);
		if (permissionMapper.updateById(dto) == 0) {
			result.isFail("权限未修改");
			return result;
		}
		result.isOk(Boolean.TRUE);
		return result;
	}

	@Transactional
	@Override
	public ResultVo<PermissionVo> addPermission(PermissionDto dto) {
		ResultVo<PermissionVo> result = new ResultVo<PermissionVo>();
		dto.setCreateBy(1l);
		dto.setUpdateBy(1l);
		dto.setPermissionType(dto.getPermissionType());
		dto.setLevel(dto.getPermissionType());
		dto.setPid(dto.getPid());
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

        if (entity.getPid() == null || entity.getPid().longValue() == 0) {
			vo.setPidName("");
		} else {
			PermissionEntity pidEntity = permissionMapper.findById(entity.getPid());
			if (pidEntity == null) {
				vo.setPidName("");
			} else {
				vo.setPidName(pidEntity.getPermissionName());
			}
		}

		UserEntity createBy = userService.findById(vo.getCreateBy());
		if (createBy != null) {
			vo.setCreateByName(createBy.getUserName());
		}

        result.setData(vo);
        return result;
	}

	@Transactional
	@Override
	public ResultVo<Boolean> delPermission(PermissionDto dto) {
		ResultVo<Boolean> result = new ResultVo<Boolean>();
		dto.setUpdateBy(1l);
		if (permissionMapper.deleteById(dto) > 0) {
			result.isOk(Boolean.TRUE);
			return result;
		}
		result.isFail("权限删除失败");
		return result;
	}

	@Override
	public ResultVo<List<PermissionVo>> getUserMenuList(UserDto dto) {
		List<PermissionEntity> list = permissionMapper.findAll();
		if (CollectionUtils.isEmpty(list)) {
			return new ResultVo<List<PermissionVo>>(CommonHelper.ResultCodeEnum.SUCCESS.getCode(), "未查询到数据!", null);
		}
		Set<PermissionVo> moduleList = Sets.newHashSet();
		Map<Long, List<PermissionVo>> menuMap = Maps.newHashMap();
		PermissionVo vo;
		for (PermissionEntity e : list) {
			Integer type = e.getPermissionType();
			Optional<Integer> optional = Optional.ofNullable(type);
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
			return new ResultVo<List<PermissionVo>>(CommonHelper.ResultCodeEnum.SUCCESS.getCode(), "未查询到数据!", null);
		}
		List<PermissionVo> voList = Lists.newArrayList();
		for (PermissionVo c : moduleList) {
			List<PermissionVo> menuList = menuMap.get(c.getId());
			c.setPermissionList(menuList);
			voList.add(c);
		}
		return new ResultVo<List<PermissionVo>>(CommonHelper.ResultCodeEnum.SUCCESS.getCode(), "", voList);
	}

	@Override
	public ResultVo<List<SelectVo>> pidList(PermissionDto dto) {
		List<PermissionEntity> list = permissionMapper.pidList();
		if (CollectionUtils.isEmpty(list)) {
			return new ResultVo<List<SelectVo>>(CommonHelper.ResultCodeEnum.SUCCESS.getCode(), "未查询到数据!", null);
		}
		List<SelectVo> voList = transferSelectVoList(list, true);
		return new ResultVo<List<SelectVo>>(CommonHelper.ResultCodeEnum.SUCCESS.getCode(), "未查询到数据!", voList);
	}

}
