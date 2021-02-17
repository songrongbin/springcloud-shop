package com.bins.springcloud.shop.common.vo;

import java.io.Serializable;

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
	
	public ResultVo<T> isOk(T data) {
		ResultVo<T> vo = new ResultVo<T>(0, "", data);
		return vo;
	}
	
	public ResultVo<T> isFail(String msg, T data) {
		ResultVo<T> vo = new ResultVo<T>(1, msg, data);
		return vo;
	}
	
}