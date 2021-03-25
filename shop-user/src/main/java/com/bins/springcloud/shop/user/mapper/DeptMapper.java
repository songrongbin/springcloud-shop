package com.bins.springcloud.shop.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.bins.springcloud.shop.user.dto.DeptDto;
import com.bins.springcloud.shop.user.dto.DeptPageDto;
import com.bins.springcloud.shop.user.entity.DeptEntity;


@Mapper
public interface DeptMapper {

	public int insertDept(DeptDto dto);

	public int updateById(DeptDto dto);

	public DeptEntity findById(Long id);
	
	public List<DeptEntity> findByIds(List<Long> ids);

	public List<DeptEntity> findDeptList(DeptPageDto pageDto);

	public int deleteById(DeptDto dto);

	public List<DeptEntity> findAll();

}
