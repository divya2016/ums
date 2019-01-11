package com.test.ums.service;

import com.test.request.AddUserRequest;
import com.test.response.ServiceResponse;
import com.test.response.UserResponse;

public interface UserService {

	public ServiceResponse<UserResponse> addUser(AddUserRequest serviceRequest);

	public ServiceResponse<UserResponse> getUsers();

}
