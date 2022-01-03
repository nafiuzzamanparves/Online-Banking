package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.example.demo.service.AccountService;

@Controller
public class AccountController {

	@Autowired
	AccountService accountService;

}
