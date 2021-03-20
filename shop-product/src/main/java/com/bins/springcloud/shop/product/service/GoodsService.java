package com.bins.springcloud.shop.product.service;

import com.bins.springcloud.shop.product.dto.GoodsPageDto;
import com.bins.springcloud.shop.product.vo.GoodsVo;
import com.github.pagehelper.PageInfo;

public interface GoodsService {

	PageInfo<GoodsVo> pagination(GoodsPageDto pageDto);

}
