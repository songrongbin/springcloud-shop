package com.bins.springcloud.shop.product.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.bins.springcloud.shop.common.utils.PageUtil;
import com.bins.springcloud.shop.product.dto.CategoryPageDto;
import com.bins.springcloud.shop.product.entity.CategoryEntity;
import com.bins.springcloud.shop.product.mapper.CategoryMapper;
import com.bins.springcloud.shop.product.service.CategoryService;
import com.bins.springcloud.shop.product.vo.CategoryVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryMapper categoryMapper;

	@Override
	public CategoryVo getById(Long id) {
		CategoryEntity entity = categoryMapper.findById(id);
		if (entity == null) {
			return null;
		}
		CategoryVo vo = new CategoryVo();
		BeanUtils.copyProperties(entity, vo);
		return vo;
	}

	@Override
	public List<CategoryVo> getByIds(List<Long> ids) {
		List<CategoryEntity> list = categoryMapper.getByIds(ids);
		if (CollectionUtils.isEmpty(list)) {
			return Collections.emptyList();
		}
		List<CategoryVo> voList = list.stream().map(temp -> {
			CategoryVo vo = new CategoryVo();
			vo.setId(temp.getId());
			vo.setCategoryName(temp.getCategoryName());
			vo.setCategoryDesc(temp.getCategoryDesc());
			vo.setSort(temp.getSort());
			vo.setStatus(temp.getStatus());
			vo.setIsDel(temp.getIsDel());
			vo.setSort(temp.getSort());
			vo.setCreateTime(temp.getCreateTime());
			vo.setUpdateTime(temp.getUpdateTime());
            return vo;
        }).collect(Collectors.toList());
		return voList;
	}

	@Override
	public PageInfo<CategoryVo> getPagination(CategoryPageDto pageDto) {
		PageHelper.startPage(pageDto.getPageNum(), pageDto.getPageSize()).setOrderBy("id DESC");
		List<CategoryEntity> list = categoryMapper.findList();
		PageInfo<CategoryEntity> originPageInfo = new PageInfo<>(list);
		PageInfo<CategoryVo> pageInfo = PageUtil.pageInfoToPageInfoVo(originPageInfo);
		List<CategoryVo> voList = list.stream().map(temp -> {
			CategoryVo vo = new CategoryVo();
			vo.setId(temp.getId());
			vo.setCategoryName(temp.getCategoryName());
			vo.setCategoryDesc(temp.getCategoryDesc());
			vo.setSort(temp.getSort());
			vo.setStatus(temp.getStatus());
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
