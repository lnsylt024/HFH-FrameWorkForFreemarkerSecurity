package com.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.app.base.BaseController;

@Controller
@RequestMapping("/testpath")
public class TestController extends BaseController {

	@RequestMapping("test01")
	public String test01() {
		System.out.println("testpath/test01");
		return "test01";
	}
	
	@RequestMapping("test02")
	public String test02() {
		System.out.println("testpath/test02");
		return "test02";
	}
}
