package com.bins.springcloud.shop.user.service;

import java.util.List;

import com.bins.springcloud.shop.common.vo.ResultVo;
import com.bins.springcloud.shop.common.vo.SelectVo;
import com.bins.springcloud.shop.user.dto.DeptDto;
import com.bins.springcloud.shop.user.dto.DeptPageDto;
import com.bins.springcloud.shop.user.vo.DeptVo;
import com.github.pagehelper.PageInfo;

public interface DeptService {

	PageInfo<DeptVo> getDeptPagination(DeptPageDto pageDto);

	ResultVo<Boolean> delDept(DeptDto dto);

	DeptVo addNewDept(DeptDto dto);

	DeptVo getById(Long id);

	int updateDept(DeptDto dto);

	List<SelectVo> getDeptSelectList();

}
