package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class NavigationController {

	// Registration Page Navigation Start
	@RequestMapping("customer/registration")
	public String customerRegistration() {
		return "forms/customerRegistration";
	}

	@RequestMapping("employee/registration")
	public String employeeRegistration() {
		return "forms/employeeRegistration";
	}

	@RequestMapping("manager/registration")
	public String managerRegistration() {
		return "forms/managerRegistration.html";
	}
	// Registration Page Navigation End

	// Login Page Navigation Start
	@RequestMapping("customer/login")
	public String customerLogin() {
		return "forms/customerLogin";
	}

	@RequestMapping("employee/login")
	public String employeeLogin() {
		return "forms/employeeLogin";
	}

	@RequestMapping("manager/login")
	public String managerLogin() {
		return "forms/managerLogin";
	}
	// Login Page Navigation End
}
