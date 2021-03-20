package com.bins.springcloud.shop.user.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bins.springcloud.shop.common.vo.SelectVo;
import com.bins.springcloud.shop.user.service.DeptService;
import com.bins.springcloud.shop.user.service.UserGroupService;
import com.bins.springcloud.shop.user.service.UserService;

@RestController
@RequestMapping("/select")
public class SelectController {
	
	@Autowired
	private DeptService deptService;
	
	@Autowired
	private UserGroupService userGroupService;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/deptList")
	@ResponseBody
	public List<SelectVo> deptList(HttpServletRequest req) {
		return deptService.getDeptSelectList();
	}
	
	@RequestMapping("/userGroupList")
	@ResponseBody
	public List<SelectVo> userGroupList(HttpServletRequest req) {
		return userGroupService.getUserGroupSelectList();
	}
	
	@RequestMapping("/userList")
	@ResponseBody
	public List<SelectVo> userList(HttpServletRequest req) {
		return userService.getUserSelectList();
	}

}
