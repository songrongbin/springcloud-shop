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
	
	public void isOk(T data) {
		this.code = 0;
		this.data = data;
	}
	
	public void isFail(String msg, T data) {
		this.code = 1;
		this.msg = msg;
		this.data = data;
	}
	
}