package com.edney.projectsmanager.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

	@GetMapping("/")
	public String index(Map<String, Product> model) {
		var p = new Product();
		p.setName("edney r r");
		model.put("product", p);
		return "index";
	}
	
}
