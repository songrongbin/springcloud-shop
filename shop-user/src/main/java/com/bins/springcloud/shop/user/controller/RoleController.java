package com.bins.springcloud.shop.user.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.bins.springcloud.shop.common.vo.ResultVo;
import com.bins.springcloud.shop.user.dto.RoleDto;
import com.bins.springcloud.shop.user.dto.RolePageDto;
import com.bins.springcloud.shop.user.service.RoleService;
import com.bins.springcloud.shop.user.vo.RoleVo;
import com.bins.springcloud.shop.user.vo.UserVo;
import com.github.pagehelper.PageInfo;

@RestController
@RequestMapping("/role")
public class RoleController {

	@Autowired
	private RoleService roleService;

	@RequestMapping("/rolePagination")
	public ResultVo<PageInfo<RoleVo>> pageList(RolePageDto pageDto, HttpServletRequest req) {
		PageInfo<RoleVo> pageInfo = roleService.getRolePagination(pageDto);
		ResultVo<PageInfo<RoleVo>> result = new ResultVo<PageInfo<RoleVo>>();
		result.setData(pageInfo);
		return result;
	}
	
	@RequestMapping("/roleEdit")
	public ResultVo<String> roleEdit(RoleDto dto, HttpServletRequest req) {
		RoleVo roleVo = roleService.getById(dto.getId());
		String jumpUrl = "rolePagination";
		// dto.setOperatorId(SecurityUtils.getUserId());
		roleService.updateRole(dto);
		return new ResultVo<String>(0, "添加成功", "角色修改成功");
	}
	
	@RequestMapping("/roleAdd")
	public ResultVo<String> roleAdd(RoleDto dto, HttpServletRequest req) {
		String jumpUrl = "rolePagination";
		// dto.setOperatorId(SecurityUtils.getUserId());
		if (roleService.addNewRole(dto)) {
			return new ResultVo<String>(0, "添加成功", "角色添加成功");
		} else {
			return new ResultVo<String>(0, "添加成功", "角色添加失败");
		}
	}
	
	@RequestMapping("/roleDel")
	@ResponseBody
	public ResultVo<Boolean> roleDel(RoleDto dto, HttpServletRequest req){
		if (dto.getId() == null || dto.getId() == 0) {
			return new ResultVo<Boolean>(1, "参数错误!", false);
		}
		return roleService.delRole(dto);
	}

}
