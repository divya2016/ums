package com.test.ums.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.test.request.AddUserRequest;
import com.test.request.ServiceRequest;
import com.test.response.ServiceResponse;
import com.test.response.UserResponse;
import com.test.ums.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("addUser")
	public ServiceResponse<UserResponse> addUser(@RequestBody ServiceRequest<AddUserRequest> serviceRequest) {
		return userService.addUser(serviceRequest.getData());

	}

	@GetMapping("getUsers")
	public ServiceResponse<UserResponse> getUsers() {
		return userService.getUsers();

	}

}
