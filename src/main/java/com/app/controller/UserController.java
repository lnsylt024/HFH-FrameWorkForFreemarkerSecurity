package com.app.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import com.app.base.BaseController;
import com.app.dao.AccountDAO;
import com.app.entity.AccountEntity;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

	@Autowired
	private AccountDAO dao;

	@RequestMapping("index")
	public String index(Model model) {
		model.addAttribute("message", "こんにちは！");
		AccountEntity entity = dao.findByName("admin");
		System.out.println("username:" + entity.username);
		return "index";
	}
	
	@RequestMapping("login")
	public String login(@Valid AccountEntity entity,BindingResult result,HttpSession session, Model model) {
		System.out.println("login");
		return "redirect:/query";
	}
}
