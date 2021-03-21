package com.bins.springcloud.shop.supplier.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

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
		List<SupplierVo> deptList = list.stream().map(temp -> {
			SupplierVo vo = new SupplierVo();
			vo.setId(temp.getId());
			vo.setSupplierCode(temp.getSupplierCode());
			vo.setSupplierName(temp.getSupplierName());
			vo.setIsDel(temp.getIsDel());
			vo.setCreateBy(temp.getCreateBy());
			vo.setCreateTime(temp.getCreateTime());
			vo.setUpdateTime(temp.getUpdateTime());
            return vo;
        }).collect(Collectors.toList());
		pageInfo.setList(deptList);
		return pageInfo;
	}

	@Override
	public SupplierVo getById(Long id) {
		SupplierEntity entity = supplierMapper.findById(id);
		SupplierVo vo = new SupplierVo();
		BeanUtils.copyProperties(entity, vo);
		return vo;
	}

	@Override
	public List<SupplierVo> getByIds(List<Long> ids) {
		List<SupplierEntity> list = supplierMapper.findByIds(ids);
		if (CollectionUtils.isEmpty(list)) {
			return Collections.emptyList();
		}
		List<SupplierVo> voList = list.stream().map(temp -> {
			SupplierVo vo = new SupplierVo();
			BeanCopier voCopier = BeanCopier.create(SupplierEntity.class, SupplierVo.class, false);
			voCopier.copy(temp, vo, null);
            return vo;
        }).collect(Collectors.toList());		
		return voList;
	}

}
