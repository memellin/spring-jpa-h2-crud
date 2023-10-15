package com.enock.todolist.user;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
	
	/*
	 * body
	 * 
	 */
	@PostMapping("/")
	public void create(@RequestBody UserModel userModel) {
		System.out.println(userModel.getUsername());
	}
}
