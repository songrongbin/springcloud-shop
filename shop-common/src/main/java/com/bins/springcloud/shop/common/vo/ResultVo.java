package com.bins.springcloud.shop.common.vo;

import java.io.Serializable;

import com.bins.springcloud.shop.common.constants.CommonHelper.ResultCodeEnum;

import lombok.Data;

@Data
public class ResultVo<T> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int code;
	
	private String msg;
	
	private T data;
	
	public ResultVo() {
		
	}
	
	public ResultVo(int code, String msg, T data) {
		this.code = code;
		this.msg = msg;
		this.data = data;
	}
	
	public void isOk(T data) {
		this.code = ResultCodeEnum.SUCCESS.getCode();
		this.data = data;
	}
	
	public void isFail(String msg, T data) {
		this.code = ResultCodeEnum.FAILURE.getCode();
		this.msg = msg;
		this.data = data;
	}
	
	public void isFail(String msg) {
		this.code = ResultCodeEnum.FAILURE.getCode();
		this.msg = msg;
		this.data = null;
	}
	
}