package com.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.app.base.BaseController;

@Controller
@RequestMapping("/admin")
public class AccountController extends BaseController {
	
	@GetMapping("index")
	public String index() {
		System.out.println("admin/index");
		return "login";
	}

	@RequestMapping(path = "login",method = {RequestMethod.POST,RequestMethod.GET})
	public void login(Model model) {
		System.out.println("admin/login");		
	}
	
	@RequestMapping("login2")
	@ResponseBody
	public void login2(Model model) {
		System.out.println("admin/login2");
		model.addAttribute("key", "value");
	}
	
	@RequestMapping("logout")
	public String logout(Model model) {
		System.out.println("admin/logout");
		return "index";
	}
	
	@RequestMapping("success")
	public String success(Model model) {
		System.out.println("admin/success");
		return "success";
	}
	
	@RequestMapping("errors")
	public String errors(Model model) {
		System.out.println("admin/errors");
		return "errors";
	}
}
