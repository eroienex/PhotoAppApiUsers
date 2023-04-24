package com.appsdeveloperblog.photoapp.api.users.ui.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;


@RestController
@RequestMapping("/users")
public class UsersController {

	@Autowired
	Environment environment;
	@GetMapping("/status/check")
	public String status()
	{
		return "Working on port " + environment.getProperty("local.server.port");
	}

	
}
