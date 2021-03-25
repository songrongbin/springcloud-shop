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

import com.bins.springcloud.shop.common.constants.BaseConstant;
import com.bins.springcloud.shop.common.constants.CommonHelper;
import com.bins.springcloud.shop.common.utils.PageUtil;
import com.bins.springcloud.shop.common.vo.ResultVo;
import com.bins.springcloud.shop.common.vo.SelectVo;
import com.bins.springcloud.shop.user.dto.DeptDto;
import com.bins.springcloud.shop.user.dto.DeptPageDto;
import com.bins.springcloud.shop.user.entity.DeptEntity;
import com.bins.springcloud.shop.user.entity.UserEntity;
import com.bins.springcloud.shop.user.mapper.DeptMapper;
import com.bins.springcloud.shop.user.service.DeptService;
import com.bins.springcloud.shop.user.service.UserService;
import com.bins.springcloud.shop.user.vo.DeptVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class DeptServiceImpl implements DeptService {
	
	@Autowired
	private DeptMapper deptMapper;
	
	@Autowired
	private UserService userService;

	public PageInfo<DeptVo> getDeptPagination(DeptPageDto pageDto) {
		PageHelper.startPage(pageDto.getPageNum(), pageDto.getPageSize()).setOrderBy("id DESC");
		List<DeptEntity> list = deptMapper.findDeptList(pageDto);
		PageInfo<DeptEntity> originPageInfo = new PageInfo<>(list);
		PageInfo<DeptVo> pageInfo = PageUtil.pageInfoToPageInfoVo(originPageInfo);
		
		if (CollectionUtils.isEmpty(list)) {
			pageInfo.setList(Collections.emptyList());
			return pageInfo;
		}
		
		List<Long> userIds = list.stream().map(DeptEntity::getCreateBy).distinct().collect(Collectors.toList());
		List<UserEntity> userList = userService.findByIds(userIds);
		Map<Long, UserEntity> userMap = userList.stream().collect(Collectors.toMap(UserEntity::getId, a -> a));
		
		List<Long> pids = list.stream().map(DeptEntity::getPid).distinct().collect(Collectors.toList());
		List<DeptEntity> entityList = deptMapper.findByIds(pids);
		Map<Long, DeptEntity> deptMap = entityList.stream().collect(Collectors.toMap(DeptEntity::getId, a -> a));
		
		List<DeptVo> deptList = list.stream().map(temp -> {
			DeptVo dept = new DeptVo();
			BeanUtils.copyProperties(temp, dept);
			UserEntity user = userMap.get(temp.getCreateBy());
			if (user != null) {
				dept.setCreateByName(user.getUserName());
			}
			DeptEntity pidDept = deptMap.get(temp.getPid());
			if (pidDept != null) {
				dept.setPidName(pidDept.getDeptName());
			}
            return dept;
        }).collect(Collectors.toList());
		pageInfo.setList(deptList);
		return pageInfo;
	}
	 
	public List<SelectVo> getDeptSelectList() {
		List<DeptEntity> list = deptMapper.findAll();
		if (CollectionUtils.isEmpty(list)) {
			return Collections.emptyList();
		}
		List<SelectVo> voList = new ArrayList<SelectVo>(list.size());
		SelectVo permission;
		for (DeptEntity entity : list) {
			permission = new SelectVo();
			permission.setCode("" + entity.getId());
			permission.setName(entity.getDeptName());
	        voList.add(permission);
		}
		return voList;
	}
	
	public DeptEntity findById(Long id) {
		return deptMapper.findById(id);
	}

	public DeptVo getById(Long id) {
		DeptEntity entity = deptMapper.findById(id);
		if (entity == null) {
			return null;
		}
		DeptVo vo = new DeptVo();
		BeanUtils.copyProperties(entity, vo);
		
		if (entity.getPid() == null || entity.getPid().longValue() == 0) {
			vo.setPidName(BaseConstant.DEPT_ROOT);
		} else {
			DeptEntity pidEntity = deptMapper.findById(entity.getPid());
			if (pidEntity != null) {
				vo.setPidName(pidEntity.getDeptName());
			}
		}
		UserEntity userEntity = userService.findById(entity.getCreateBy());
		if (userEntity != null) {
			vo.setCreateByName(userEntity.getUserName());
		}
		return vo;
	}
	
	@Override
	public DeptVo getDetailById(DeptDto dto) {
		if (dto.getId() == null) {
			return null;
		}
		return getById(dto.getId());
	}
	
	@Override
	public List<DeptEntity> findByIds(List<Long> deptIds) {
		if (CollectionUtils.isEmpty(deptIds)) {
			return Collections.emptyList();
		}
		List<DeptEntity> list = deptMapper.findByIds(deptIds);
		if (CollectionUtils.isEmpty(list)) {
			return Collections.emptyList();
		}
		return list;
	}
	
	@Override
	public List<DeptVo> getByIds(List<Long> ids) {
		if (CollectionUtils.isEmpty(ids)) {
			return Collections.emptyList();
		}
		List<DeptEntity> list = deptMapper.findByIds(ids);
		if (CollectionUtils.isEmpty(list)) {
			return Collections.emptyList();
		}
		List<DeptVo> voList = list.stream().map(temp -> {
			DeptVo vo = new DeptVo();
			BeanUtils.copyProperties(temp, vo);
            return vo;
        }).collect(Collectors.toList());
		return voList;
	}

	public int updateDept(DeptDto dto) {
		return deptMapper.updateById(dto);
	}

	public DeptVo addNewDept(DeptDto dto) {
		int result = deptMapper.insertDept(dto);
		DeptVo vo = new DeptVo();
		if (result > 0) {
			vo.setId(dto.getId());
		} else {
			vo.setId(0l);
		}
		return vo;
	}

	public ResultVo<Boolean> delDept(DeptDto dto) {
		dto.setIsDel(CommonHelper.DeleteStatus.DELETED.getCode());
		int num = deptMapper.deleteById(dto);
		if (num > 0) {
			return new ResultVo<Boolean>(0, "删除成功", true);
		}
		return new ResultVo<Boolean>(0, "删除失败", false);
	}

}
