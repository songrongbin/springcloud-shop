package com.bins.springcloud.shop.supplier.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.bins.springcloud.shop.common.utils.PageUtil;
import com.bins.springcloud.shop.supplier.dto.BrandPageDto;
import com.bins.springcloud.shop.supplier.entity.BrandEntity;
import com.bins.springcloud.shop.supplier.mapper.BrandMapper;
import com.bins.springcloud.shop.supplier.service.BrandService;
import com.bins.springcloud.shop.supplier.vo.BrandVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class BrandServiceImpl implements BrandService {
	
	@Autowired
	private BrandMapper brandMapper;

	@Override
	public BrandVo getById(Long brandId) {
		BrandEntity entity = brandMapper.findById(brandId);
		BrandVo vo = new BrandVo();
		BeanUtils.copyProperties(entity, vo);
		return vo;
	}

	@Override
	public List<BrandVo> getByIds(List<Long> brandIds) {
		List<BrandEntity> list = brandMapper.getByIds(brandIds);
		if (CollectionUtils.isEmpty(list)) {
			return Collections.emptyList();
		}
		List<BrandVo> voList = list.stream().map(temp -> {
			BrandVo vo = new BrandVo();
			vo.setId(temp.getId());
			vo.setBrandName(temp.getBrandName());
			vo.setBrandDesc(temp.getBrandDesc());
			vo.setIsDel(temp.getIsDel());
			vo.setSort(temp.getSort());
			vo.setCreateTime(temp.getCreateTime());
			vo.setUpdateTime(temp.getUpdateTime());
            return vo;
        }).collect(Collectors.toList());
		return voList;
	}

	@Override
	public PageInfo<BrandVo> getPagination(BrandPageDto pageDto) {
		PageHelper.startPage(pageDto.getPageNum(), pageDto.getPageSize()).setOrderBy("id DESC");
		List<BrandEntity> list = brandMapper.findList();
		PageInfo<BrandEntity> originPageInfo = new PageInfo<>(list);
		PageInfo<BrandVo> pageInfo = PageUtil.pageInfoToPageInfoVo(originPageInfo);
		List<BrandVo> voList = list.stream().map(temp -> {
			BrandVo vo = new BrandVo();
			vo.setId(temp.getId());
			vo.setBrandName(temp.getBrandName());
			vo.setBrandDesc(temp.getBrandDesc());
			vo.setIsDel(temp.getIsDel());
			vo.setSort(temp.getSort());
			vo.setCreateTime(temp.getCreateTime());
			vo.setUpdateTime(temp.getUpdateTime());
            return vo;
        }).collect(Collectors.toList());
		pageInfo.setList(voList);
		return pageInfo;
	}
}
