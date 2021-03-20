package com.bins.springcloud.shop.supplier.service.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bins.springcloud.shop.common.utils.PageUtil;
import com.bins.springcloud.shop.supplier.dto.SupplierPageDto;
import com.bins.springcloud.shop.supplier.entity.SupplierEntity;
import com.bins.springcloud.shop.supplier.mapper.SupplierMapper;
import com.bins.springcloud.shop.supplier.service.SupplierService;
import com.bins.springcloud.shop.supplier.vo.SupplierVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class SupplierServiceImpl implements SupplierService {
	
	@Autowired
	private SupplierMapper supplierMapper;
	
	@Override
	public PageInfo<SupplierVo> getSupplierPagination(SupplierPageDto pageDto) {
		PageHelper.startPage(pageDto.getPageNum(), pageDto.getPageSize()).setOrderBy("id DESC");
		List<SupplierEntity> list = supplierMapper.findSupplierList();
		PageInfo<SupplierEntity> originPageInfo = new PageInfo<>(list);
		PageInfo<SupplierVo> pageInfo = PageUtil.pageInfoToPageInfoVo(originPageInfo);
		
		// List<Long> userIds = list.stream().map(SupplierEntity::getCreateBy).distinct().collect(Collectors.toList());
		// List<UserEntity> userList = userService.findByIds(userIds);
		// Map<Long, UserEntity> userMap = userList.stream().collect(Collectors.toMap(UserEntity::getId, a -> a));
		
		List<SupplierVo> deptList = list.stream().map(temp -> {
			SupplierVo vo = new SupplierVo();
			vo.setId(temp.getId());
			vo.setSupplierCode(temp.getSupplierCode());
			vo.setSupplierName(temp.getSupplierName());
			vo.setIsDel(temp.getIsDel());
			vo.setCreateBy(temp.getCreateBy());
			vo.setCreateTime(temp.getCreateTime());
			vo.setUpdateTime(temp.getUpdateTime());
//			UserEntity user = userMap.get(temp.getCreateBy());
//			if (user != null) {
//				vo.setCreateName(userMap.get(temp.getCreateBy()).getUserName());
//			}
            return vo;
        }).collect(Collectors.toList());
		pageInfo.setList(deptList);
		return pageInfo;
	}

}
