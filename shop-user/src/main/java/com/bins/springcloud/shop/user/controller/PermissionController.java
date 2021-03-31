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
import com.bins.springcloud.shop.user.dto.PermissionDto;
import com.bins.springcloud.shop.user.dto.PermissionPageDto;
import com.bins.springcloud.shop.user.service.PermissionService;
import com.bins.springcloud.shop.user.vo.PermissionVo;
import com.github.pagehelper.PageInfo;

@RestController
@RequestMapping("/permission")
public class PermissionController {
	
	@Autowired
	private PermissionService permissionService;
	
	@RequestMapping("/pagination")
	public ResultVo<PageInfo<PermissionVo>> pageList(PermissionPageDto pageDto, HttpServletRequest req) {
		PageInfo<PermissionVo> pageInfo = permissionService.getPagination(pageDto);
		ResultVo<PageInfo<PermissionVo>> result = new ResultVo<PageInfo<PermissionVo>>();
		result.setData(pageInfo);
		return result;
	}
	
	@GetMapping("/detail")
	public ResultVo<PermissionVo> detail(PermissionDto dto) {
		if (dto.getId() == null || dto.getId() == 0) {
			return new ResultVo<PermissionVo>(ResultCodeEnum.FAILURE.getCode(), "参数错误!", null);
		}
		ResultVo<PermissionVo> result = permissionService.getDetail(dto);
		return result;
	}
	
	@RequestMapping("/edit")
	public ResultVo<Boolean> permissionEdit(@RequestBody PermissionDto dto, HttpServletRequest req){
		if (dto.getId() == null || dto.getId() == 0) {
			return new ResultVo<Boolean>(ResultCodeEnum.FAILURE.getCode(), "参数错误!", false);
		}
		ResultVo<Boolean> result = permissionService.editPermission(dto);
		return result;
	}
	
	@RequestMapping("/add")
	public ResultVo<PermissionVo> permissionAdd(@RequestBody PermissionDto dto, HttpServletRequest req){
		if (StringUtils.isBlank(dto.getPermissionCode())) {
			return new ResultVo<PermissionVo>(ResultCodeEnum.FAILURE.getCode(), "权限编码不能为空!", null);
		}
		if (StringUtils.isBlank(dto.getPermissionName())) {
			return new ResultVo<PermissionVo>(ResultCodeEnum.FAILURE.getCode(), "权限名称不能为空!", null);
		}
		return permissionService.addPermission(dto);
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public ResultVo<Boolean> delPermission(PermissionDto dto, HttpServletRequest req){
		if (dto.getId() == null || dto.getId() == 0) {
			return new ResultVo<Boolean>(ResultCodeEnum.FAILURE.getCode(), "参数错误!", false);
		}
		return permissionService.delPermission(dto);
	}

}
