package com.bins.springcloud.shop.supplier.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bins.springcloud.shop.supplier.feign.FeignService;

@RestController
public class FeignTestController {

	@Autowired
	private FeignService service;

	@GetMapping("consumer/get")
	public String get() {
		return service.get();
	}

}