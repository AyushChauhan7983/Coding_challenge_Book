package com.book.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.book.Model.Users;
import com.book.Service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserService service;

	@PostMapping("/register")
	public Users register(@RequestBody Users user) {
		return service.register(user);
	}
	
	@PostMapping("/login")
	public String login(@RequestBody Users u) {
		return service.verify(u);
	}
}