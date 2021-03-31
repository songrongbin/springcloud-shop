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
import com.bins.springcloud.shop.user.dto.UserDto;
import com.bins.springcloud.shop.user.dto.UserPageDto;
import com.bins.springcloud.shop.user.service.UserService;
import com.bins.springcloud.shop.user.vo.UserVo;
import com.github.pagehelper.PageInfo;

/**
 * Created by songrongbin on 2017/5/26.
 */

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;

    @RequestMapping("/getPersonInfo")
    public UserDto getPersonInfo(Long userId) {
        UserDto user = new UserDto();
        user.setId(11l);
        user.setUserCode("admin2");
        user.setUserName("admin2");
        return user;
    }
    
    @GetMapping(value = "/pagination")
	public ResultVo<PageInfo<UserVo>> pageList(UserPageDto pageDto) {
		PageInfo<UserVo> pageInfo = userService.getUserPagination(pageDto);
		ResultVo<PageInfo<UserVo>> result = new ResultVo<PageInfo<UserVo>>();
		result.setData(pageInfo);
		return result;
	}
    
    @GetMapping("/detail")
	public ResultVo<UserVo> detail(UserDto dto) {
		if (dto.getId() == null || dto.getId() == 0) {
			return new ResultVo<UserVo>(ResultCodeEnum.FAILURE.getCode(), "参数错误!", null);
		}
		return userService.getDetail(dto);
	}
	
	@RequestMapping("/edit")
	public ResultVo<Boolean> editUser(@RequestBody UserDto dto, HttpServletRequest req){
		if (dto.getId() == null || dto.getId() == 0) {
			return new ResultVo<Boolean>(ResultCodeEnum.FAILURE.getCode(), "参数错误!", false);
		}
		return userService.editUser(dto);
	}
	
	@RequestMapping("/add")
	public ResultVo<UserVo> addUser(@RequestBody UserDto dto, HttpServletRequest req){
		if (StringUtils.isBlank(dto.getUserCode())) {
			return new ResultVo<UserVo>(ResultCodeEnum.FAILURE.getCode(), "用户编码不能为空!", null);
		}
		if (StringUtils.isBlank(dto.getUserName())) {
			return new ResultVo<UserVo>(ResultCodeEnum.FAILURE.getCode(), "用户名称不能为空!", null);
		}
		return userService.addUser(dto);
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public ResultVo<Boolean> delPermission(UserDto dto, HttpServletRequest req){
		if (dto.getId() == null || dto.getId() == 0) {
			return new ResultVo<Boolean>(ResultCodeEnum.FAILURE.getCode(), "参数错误!", false);
		}
		return userService.delUser(dto);
	}

}
