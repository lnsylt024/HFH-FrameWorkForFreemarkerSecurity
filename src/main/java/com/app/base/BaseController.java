package com.app.base;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

public class BaseController {

	@Value("${server.servlet.context-path}")
	private String contextPath;
	
	@ModelAttribute
	public void setContextPath(Model model) {
		model.addAttribute("contextPath", this.contextPath);
		model.addAttribute("message", "こんにちは！");
	}
}
