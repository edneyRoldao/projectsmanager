package com.edney.projectsmanager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class IndexController {

	public String index(Model model, HttpServletRequest request) {
		return "index";
	}
	
}
