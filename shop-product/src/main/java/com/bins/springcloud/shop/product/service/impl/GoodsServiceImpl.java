package com.bins.springcloud.shop.product.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bins.springcloud.shop.common.utils.PageUtil;
import com.bins.springcloud.shop.product.dto.GoodsPageDto;
import com.bins.springcloud.shop.product.entity.GoodsEntity;
import com.bins.springcloud.shop.product.mapper.GoodsMapper;
import com.bins.springcloud.shop.product.service.GoodsService;
import com.bins.springcloud.shop.product.vo.GoodsVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class GoodsServiceImpl implements GoodsService {
	
	@Autowired
	private GoodsMapper goodsMapper;
	
	@Override
	public PageInfo<GoodsVo> pagination(GoodsPageDto pageDto) {
		PageHelper.startPage(pageDto.getPageNum(), pageDto.getPageSize()).setOrderBy("id DESC");
		List<GoodsEntity> list = goodsMapper.findList();
		PageInfo<GoodsEntity> originPageInfo = new PageInfo<>(list);
		PageInfo<GoodsVo> pageInfo = PageUtil.pageInfoToPageInfoVo(originPageInfo);
		List<GoodsVo> deptList = list.stream().map(temp -> {
			GoodsVo vo = new GoodsVo();
			vo.setId(temp.getId());
			vo.setGoodsCode(temp.getGoodsCode());
			vo.setGoodsName(temp.getGoodsName());
			vo.setIsDel(temp.getIsDel());
			vo.setCreateBy(temp.getCreateBy());
			vo.setCreateTime(temp.getCreateTime());
			vo.setUpdateTime(temp.getUpdateTime());
            return vo;
        }).collect(Collectors.toList());
		pageInfo.setList(deptList);
		return pageInfo;
	}

}
