package com.bins.springcloud.shop.user.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.bins.springcloud.shop.common.vo.ResultVo;
import com.bins.springcloud.shop.user.dto.UserGroupDto;
import com.bins.springcloud.shop.user.dto.UserGroupPageDto;
import com.bins.springcloud.shop.user.service.UserGroupService;
import com.bins.springcloud.shop.user.vo.RoleVo;
import com.bins.springcloud.shop.user.vo.UserGroupVo;
import com.github.pagehelper.PageInfo;

@RestController
@RequestMapping("/userGroup")
public class UserGroupController {
	
	@Autowired
	private UserGroupService userGroupService;

	@RequestMapping("/userGroupPagination")
	public ResultVo<PageInfo<UserGroupVo>> pageList(UserGroupPageDto pageDto, HttpServletRequest req) {
		PageInfo<UserGroupVo> pageInfo = userGroupService.getUserGroupPagination(pageDto);
		ResultVo<PageInfo<UserGroupVo>> result = new ResultVo<PageInfo<UserGroupVo>>();
		result.setData(pageInfo);
		return result;
	}
	
	@RequestMapping("/userGroupEdit")
	public ResultVo<String> userGroupEdit(@RequestBody UserGroupDto dto, HttpServletRequest req) {
		UserGroupVo deptVo = userGroupService.getById(dto.getId());
		String jumpUrl = "userGroupPagination";
		// dto.setCreateBy(SecurityUtils.getUserId());
		userGroupService.updateUserGroup(dto);
		return new ResultVo<String>(0, "添加成功", "用户组修改成功");
	}
	
	
	@RequestMapping("/userGroupAdd")
	public ResultVo<String> userGroupAdd(@RequestBody UserGroupDto dto, HttpServletRequest req) {
		if (dto.getIsDel() == null) {
			dto.setIsDel(0);
		}
		// dto.setCreateBy(SecurityUtils.getUserId());
		String jumpUrl = "userGroupPagination";
		if (userGroupService.addNewUserGroup(dto)) {
			return new ResultVo<String>(0, "添加成功", "用户组修改成功");
		} else {
			return new ResultVo<String>(0, "添加成功", "用户组修改成功");
		}
	}
	
	@RequestMapping("/userGroupDel")
	@ResponseBody
	public ResultVo<Boolean> userGroupDel(UserGroupDto dto, HttpServletRequest req){
		if (dto.getId() == null || dto.getId() == 0) {
			return new ResultVo<Boolean>(1, "参数错误!", false);
		}
		return userGroupService.delUserGroup(dto);
	}

}
