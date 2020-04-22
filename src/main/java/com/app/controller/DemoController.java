package com.app.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/demo")
public class DemoController {

	@RequestMapping("index")
	public String index(Model model) {
		System.out.println("DemoController");
		
		String title ="Demo Test";
		model.addAttribute("title", title);
		
		String message = "こんにちは！";
		model.addAttribute("message", message);
				
		List<String> list = new ArrayList<>();
		list.add("a");
		list.add("b");
		list.add("c");
		list.add("d");
		list.add("e");
		list.add("f");
		list.add("g");
		model.addAttribute("outList", list);
		
		Map<String, String> map = new HashMap<>();
		map.put("key1", "value1");
		map.put("key2", "value2");
		map.put("key3", "value3");
		map.put("key4", "value4");
		model.addAttribute("outMap", map);
		
		List<Person> listp = new ArrayList<>();
		Person p1 = new Person();
		p1.setId("p1");
		p1.setName("user01");
		listp.add(p1);
		Person p2 = new Person();
		p2.setId("p2");
		p2.setName("user02");
		listp.add(p2);
		model.addAttribute("personList", listp);
		
		model.addAttribute("showFlg", false);
		
		model.addAttribute("showDate", new Date());
		
		model.addAttribute("showNull", null);
		
		return "demo";
	}
	
	@RequestMapping(path = "gonext", method = RequestMethod.POST)
	public String goNext(Model model) {
		System.out.println("gonext");
		return "success";
	}

	public class Person{
		String id;
		String name;
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
	}
}
