package com.bins.springcloud.shop.user.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bins.springcloud.shop.common.constants.CommonHelper.ResultCodeEnum;
import com.bins.springcloud.shop.common.vo.ResultVo;
import com.bins.springcloud.shop.user.dto.DeptDto;
import com.bins.springcloud.shop.user.dto.UserGroupDto;
import com.bins.springcloud.shop.user.dto.UserGroupPageDto;
import com.bins.springcloud.shop.user.service.UserGroupService;
import com.bins.springcloud.shop.user.vo.UserGroupVo;
import com.github.pagehelper.PageInfo;

@RestController
@RequestMapping("/userGroup")
public class UserGroupController {
	
	@Autowired
	private UserGroupService userGroupService;

	@RequestMapping("/pagination")
	public ResultVo<PageInfo<UserGroupVo>> pageList(UserGroupPageDto pageDto, HttpServletRequest req) {
		PageInfo<UserGroupVo> pageInfo = userGroupService.getUserGroupPagination(pageDto);
		ResultVo<PageInfo<UserGroupVo>> result = new ResultVo<PageInfo<UserGroupVo>>();
		result.setData(pageInfo);
		return result;
	}
	
	@GetMapping("/detail")
	public ResultVo<UserGroupVo> detail(DeptDto dto) {
		if (dto.getId() == null || dto.getId() == 0) {
			return new ResultVo<UserGroupVo>(1, "参数错误!", null);
		}
		ResultVo<UserGroupVo> result = userGroupService.getDetail(dto);
		return result;
	}
	
	@RequestMapping("/edit")
	public ResultVo<Boolean> userGroupEdit(@RequestBody UserGroupDto dto, HttpServletRequest req) {
		if (dto.getId() == null || dto.getId() == 0) {
			return new ResultVo<Boolean>(ResultCodeEnum.FAILURE.getCode(), "参数错误!", false);
		}
		ResultVo<Boolean> result = userGroupService.updateUserGroup(dto);
		return result;
	}
	
	@RequestMapping("/add")
	public ResultVo<UserGroupVo> userGroupAdd(@RequestBody UserGroupDto dto, HttpServletRequest req) {
		if (StringUtils.isBlank(dto.getGroupCode())) {
			return new ResultVo<UserGroupVo>(ResultCodeEnum.FAILURE.getCode(), "用户组编码不能为空!", null);
		}
		if (StringUtils.isBlank(dto.getGroupName())) {
			return new ResultVo<UserGroupVo>(ResultCodeEnum.FAILURE.getCode(), "用户组名称不能为空!", null);
		}
		return userGroupService.addNewUserGroup(dto);
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public ResultVo<Boolean> userGroupDel(UserGroupDto dto, HttpServletRequest req){
		if (dto.getId() == null || dto.getId() == 0) {
			return new ResultVo<Boolean>(ResultCodeEnum.FAILURE.getCode(), "参数错误!", false);
		}
		return userGroupService.delUserGroup(dto);
	}

}
