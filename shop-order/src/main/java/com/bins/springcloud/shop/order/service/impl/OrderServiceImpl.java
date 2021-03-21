package com.bins.springcloud.shop.order.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.bins.springcloud.shop.common.utils.PageUtil;
import com.bins.springcloud.shop.order.dto.OrderDto;
import com.bins.springcloud.shop.order.dto.OrderPageDto;
import com.bins.springcloud.shop.order.entity.OrderEntity;
import com.bins.springcloud.shop.order.entity.OrderGoodsEntity;
import com.bins.springcloud.shop.order.enums.OrderStatusEnum;
import com.bins.springcloud.shop.order.mapper.OrderGoodsMapper;
import com.bins.springcloud.shop.order.mapper.OrderMapper;
import com.bins.springcloud.shop.order.service.OrderService;
import com.bins.springcloud.shop.order.vo.OrderDetailVo;
import com.bins.springcloud.shop.order.vo.OrderGoodsVo;
import com.bins.springcloud.shop.order.vo.OrderVo;
import com.bins.springcloud.shop.supplier.api.SupplierApi;
import com.bins.springcloud.shop.supplier.vo.SupplierVo;
import com.bins.springcloud.shop.user.api.UserApi;
import com.bins.springcloud.shop.user.vo.UserVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;

@Service
public class OrderServiceImpl implements OrderService {
	
	final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private OrderMapper orderMapper;
	
	@Autowired
	private OrderGoodsMapper orderGoodsMapper;
	
	@Autowired
	private UserApi userApi;
	
	@Autowired
	private SupplierApi supplierApi;
	
	@Override
	public PageInfo<OrderVo> getPagination(OrderPageDto pageDto) {
		PageHelper.startPage(pageDto.getPageNum(), pageDto.getPageSize()).setOrderBy("id DESC");
		List<OrderEntity> list = orderMapper.findList();
		PageInfo<OrderEntity> originPageInfo = new PageInfo<>(list);
		PageInfo<OrderVo> pageInfo = PageUtil.pageInfoToPageInfoVo(originPageInfo);
		if (CollectionUtils.isEmpty(list)) {
			pageInfo.setList(Collections.emptyList());
			return pageInfo;
		}
		
		List<Long> userIds = list.stream().map(OrderEntity::getUserId).distinct().collect(Collectors.toList());
		List<UserVo> userList = userApi.getByIds(userIds);
		Map<Long, UserVo> userMap = userList.stream().collect(Collectors.toMap(UserVo::getId, UserVo-> UserVo));
		
		List<OrderVo> deptList = list.stream().map(temp -> {
			OrderVo vo = new OrderVo();
			BeanCopier beanCopier = BeanCopier.create(OrderEntity.class, OrderVo.class, false);
			beanCopier.copy(temp, vo, null);
			UserVo userVo = userMap.get(temp.getUserId());
			if (userVo != null) {
				vo.setUserName(userVo.getUserName());
			}
			vo.setStatusName(OrderStatusEnum.getName(temp.getStatus()));
            return vo;
        }).collect(Collectors.toList());
		pageInfo.setList(deptList);
		return pageInfo;
	}

	@Override
	public OrderVo getById(OrderDto dto) {
		OrderEntity entity = orderMapper.findById(dto.getId());
		if (entity == null) {
			logger.info("order entity is null, orderId:{}", dto.getId());
			return null;
		}
		return null;
	}

	@Override
	public OrderDetailVo getOrderDetailById(OrderDto dto) {
		OrderEntity entity = orderMapper.findById(dto.getId());
		if (entity == null) {
			logger.info("order entity is null, orderId:{}", dto.getId());
			return null;
		}
		BeanCopier orderCopier = BeanCopier.create(OrderEntity.class, OrderDetailVo.class, false);
		OrderDetailVo vo = new OrderDetailVo();
		orderCopier.copy(entity, vo, null);
		
		UserVo userVo = userApi.getById(entity.getUserId());
		if (userVo != null) {
			vo.setUserName(userVo.getUserName());
		}
		
		SupplierVo supplierVo = supplierApi.getSupplierById(entity.getSupplierId());
		if (supplierVo != null) {
			vo.setSupplierName(supplierVo.getSupplierName());
		}
		
		List<OrderGoodsEntity> orderGoodsList = orderGoodsMapper.findListByOrderId(dto.getId());
		if (CollectionUtils.isEmpty(orderGoodsList)) {
			List<OrderGoodsVo> goodsList = Lists.newArrayListWithCapacity(0);
			vo.setOrderGoodsList(goodsList);
			return vo;
		}
		
		List<OrderGoodsVo> goodsList = orderGoodsList.stream().map(goodsTemp -> {
			OrderGoodsVo goodsVo = new OrderGoodsVo();
			BeanCopier goodsCopier = BeanCopier.create(OrderGoodsEntity.class, OrderGoodsVo.class, false);
			goodsCopier.copy(goodsTemp, goodsVo, null);
            return goodsVo;
        }).collect(Collectors.toList());
		vo.setOrderGoodsList(goodsList);
		return vo;
	}

}
