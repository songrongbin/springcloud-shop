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
import com.bins.springcloud.shop.user.dto.RoleDto;
import com.bins.springcloud.shop.user.dto.RolePageDto;
import com.bins.springcloud.shop.user.service.RoleService;
import com.bins.springcloud.shop.user.vo.RoleVo;
import com.github.pagehelper.PageInfo;

@RestController
@RequestMapping("/role")
public class RoleController {

	@Autowired
	private RoleService roleService;

	@RequestMapping("/pagination")
	public ResultVo<PageInfo<RoleVo>> pageList(RolePageDto pageDto, HttpServletRequest req) {
		PageInfo<RoleVo> pageInfo = roleService.getRolePagination(pageDto);
		ResultVo<PageInfo<RoleVo>> result = new ResultVo<PageInfo<RoleVo>>();
		result.setData(pageInfo);
		return result;
	}
	
	@GetMapping("/detail")
	public ResultVo<RoleVo> detail(RoleDto dto) {
		if (dto.getId() == null || dto.getId() == 0) {
			return new ResultVo<RoleVo>(1, "参数错误!", null);
		}
		ResultVo<RoleVo> result = roleService.getDetail(dto);
		return result;
	}
	
	@RequestMapping("/edit")
	public ResultVo<Boolean> edit(@RequestBody RoleDto dto) {
		if (dto.getId() == null || dto.getId() == 0) {
			return new ResultVo<Boolean>(ResultCodeEnum.FAILURE.getCode(), "参数错误!", false);
		}
		ResultVo<Boolean> result = roleService.updateRole(dto);
		return result;
	}
	
	@RequestMapping("/add")
	public ResultVo<RoleVo> roleAdd(@RequestBody RoleDto dto) {
		if (StringUtils.isBlank(dto.getRoleCode())) {
			return new ResultVo<RoleVo>(ResultCodeEnum.FAILURE.getCode(), "角色编码不能为空!", null);
		}
		if (StringUtils.isBlank(dto.getRoleName())) {
			return new ResultVo<RoleVo>(ResultCodeEnum.FAILURE.getCode(), "角色名称不能为空!", null);
		}
		return roleService.addNewRole(dto);
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public ResultVo<Boolean> roleDel(RoleDto dto, HttpServletRequest req){
		if (dto.getId() == null || dto.getId() == 0) {
			return new ResultVo<Boolean>(ResultCodeEnum.FAILURE.getCode(), "参数错误!", false);
		}
		return roleService.delRole(dto);
	}

}
