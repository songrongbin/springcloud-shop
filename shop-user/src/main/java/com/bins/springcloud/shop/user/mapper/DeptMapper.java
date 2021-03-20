package com.bins.springcloud.shop.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.bins.springcloud.shop.user.dto.DeptDto;
import com.bins.springcloud.shop.user.entity.DeptEntity;


@Mapper
public interface DeptMapper {

	public int insertDept(DeptDto dto);

	public int updateDept(DeptDto dto);

	public DeptEntity findById(Long id);

	public List<DeptEntity> findDeptList();

	public List<DeptEntity> getDeptByIds(List<Long> deptIds);

	public int deleteDept(DeptDto dto);

}
