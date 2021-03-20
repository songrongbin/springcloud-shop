package com.bins.springcloud.shop.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    
    @GetMapping(value = "/userPagination")
	public ResultVo<PageInfo<UserVo>> pageList(UserPageDto userPageDto) {
		PageInfo<UserVo> pageInfo = userService.getUserPagination(userPageDto);
		ResultVo<PageInfo<UserVo>> result = new ResultVo<PageInfo<UserVo>>();
		result.setData(pageInfo);
		return result;
	}

}
