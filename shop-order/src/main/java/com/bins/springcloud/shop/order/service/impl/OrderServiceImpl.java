package com.bins.springcloud.shop.order.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.bins.springcloud.shop.common.utils.PageUtil;
import com.bins.springcloud.shop.order.dto.OrderPageDto;
import com.bins.springcloud.shop.order.entity.OrderEntity;
import com.bins.springcloud.shop.order.feign.UserFeignService;
import com.bins.springcloud.shop.order.mapper.OrderMapper;
import com.bins.springcloud.shop.order.service.OrderService;
import com.bins.springcloud.shop.order.vo.OrderVo;
import com.bins.springcloud.shop.user.api.UserApi;
import com.bins.springcloud.shop.user.vo.UserVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private OrderMapper orderMapper;
	
	@Autowired
	private UserApi userApi;
	
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
			vo.setId(temp.getId());
			vo.setOrderCode(temp.getOrderCode());
			vo.setUserId(temp.getUserId());
//			vo.setUserName(userFeignService.getUserName(temp.getUserId()));
//			UserVo userVo = userApi.getUserName(temp.getUserId());
			UserVo userVo = userMap.get(temp.getUserId());
			if (userVo != null) {
				vo.setUserName(userVo.getUserName());
			}
			vo.setStatus(temp.getStatus());
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
