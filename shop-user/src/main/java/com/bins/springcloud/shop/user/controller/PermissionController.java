package com.bins.springcloud.shop.user.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.bins.springcloud.shop.common.vo.ResultVo;
import com.bins.springcloud.shop.common.vo.SelectVo;
import com.bins.springcloud.shop.user.dto.PermissionDto;
import com.bins.springcloud.shop.user.dto.PermissionPageDto;
import com.bins.springcloud.shop.user.entity.PermissionEntity;
import com.bins.springcloud.shop.user.service.PermissionService;
import com.bins.springcloud.shop.user.vo.PermissionVo;
import com.bins.springcloud.shop.user.vo.RoleVo;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;

@RestController
public class PermissionController {
	
	@Autowired
	private PermissionService permissionService;
	
	@RequestMapping("permission/list")
	public ModelAndView permissionManage(HttpServletRequest req) {
		List<PermissionVo> permissionList = permissionService.getPermissionList();
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("myPageUrlName", "manager/permissionManager.jsp");
		model.put("myPageTitle", "权限管理分页");
		model.put("permissionList", permissionList);
		model.put("myPageNav", "15");
		return new ModelAndView("baseJsp", model);
	}
	
	@RequestMapping("/permission/pageList")
	public ResultVo<PageInfo<PermissionEntity>> pageList(PermissionPageDto dto, HttpServletRequest req) {
		PageInfo<PermissionEntity> pageInfo = permissionService.getPermissionList(dto);
		ResultVo<PageInfo<PermissionEntity>> result = new ResultVo<PageInfo<PermissionEntity>>();
		result.setData(pageInfo);
		return result;
	}
	
	@RequestMapping("permission/view")
	public ModelAndView viewPermissionEdit(String jobId, HttpServletRequest req) {
		String userJobId = (String) req.getSession().getAttribute("userJobId");
		String idStr = (String)req.getParameter("id");
		Map<String, Object> model = new HashMap<String, Object>();
		if (idStr != null) {
			Long permissionId = Long.parseLong(idStr);
			PermissionVo permissionVo = permissionService.findById(permissionId);
			model.put("vo", permissionVo);
		} else {
			
		}
		List<SelectVo> typeList = Lists.newArrayList();
		SelectVo typeVo = new SelectVo();
		typeVo.setCode("1");
		typeVo.setName("模块");
		typeList.add(typeVo);
		typeVo = new SelectVo();
		typeVo.setCode("2");
		typeVo.setName("菜单");
		typeList.add(typeVo);
		typeVo = new SelectVo();
		typeVo.setCode("3");
		typeVo.setName("操作权限");
		typeList.add(typeVo);
		
		List<SelectVo> parentList = permissionService.getByModuelAndMenu();
		model.put("myPageUrlName", "manager/permissionEdit.jsp");
		model.put("myPageTitle", "权限管理――修改");
		model.put("typeList", typeList);
		model.put("parentList", parentList);
		model.put("myPageNav", "15");
		return new ModelAndView("baseJsp", model);
	}
	
	@RequestMapping("/permission/edit")
	public ResultVo<String> permissionEdit(PermissionDto permissionDto, HttpServletRequest req){
		boolean res = false;
		String baseContent = null;
		String baseUrl = "list";
			Long id = permissionDto.getId();
			if (id != null && id > 0) {
				res = permissionService.permissionEdit(permissionDto);
			} else {
				res = permissionService.permissionEdit(permissionDto);
			}

		if(!res){
			baseContent = "修改信息失败";
		}else{
			baseContent = "修改个人信息成功！";
		}
		return new ResultVo<String>(0, "添加成功", baseContent);
	}
	
	@RequestMapping("/permission/add")
	public ResultVo<PermissionVo> permissionAdd(PermissionDto permissionDto, HttpServletRequest req){
		PermissionVo res = null;
		res = permissionService.createPermission(permissionDto);
		return new ResultVo<PermissionVo>(0, "添加成功", res);
	}

}
