package com.test.ums.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.request.AddUserRequest;
import com.test.response.ServiceResponse;
import com.test.response.UserResponse;
import com.test.ums.constants.ConfigConstants;
import com.test.ums.constants.ConfigConstants.Status;
import com.test.ums.data.model.Users;
import com.test.ums.service.UserService;
import com.test.ums.service.helper.UserServiceHelper;
import com.test.ums.utils.DatabaseUtils;

@Service
public class UserServiceImpl implements UserService {

	private static Logger logger = Logger.getLogger(UserServiceImpl.class);

	@Autowired
	private UserServiceHelper userServiceHelper;
	@Autowired
	private DatabaseUtils databaseUtils;

	@Override
	public ServiceResponse<UserResponse> addUser(AddUserRequest serviceRequest) {
		ServiceResponse<UserResponse> serviceResponse = new ServiceResponse<>();
		try {
			serviceResponse = userServiceHelper.validateAddUserRequest(serviceRequest);
			if (serviceResponse.getStatus().equals(ConfigConstants.SUCCESS)) {
				Users user = new Users(databaseUtils.getNextSequenceId("user", Users.class),
						serviceRequest.getName().toUpperCase(), serviceRequest.getEmail().toLowerCase(),
						serviceRequest.getMobile(), 0, 0, Status.Active, null, null);
				databaseUtils.save(user);
				logger.info("User added successfully.");
				serviceResponse = new ServiceResponse<>("Module updated Successfully ", ConfigConstants.SUCCESS, null);
			}
		} catch (Exception e) {
			logger.error("An error occurred while adding user :: ", e);
			serviceResponse = new ServiceResponse<>("An error occurred while adding user :: ", ConfigConstants.ERROR,
					null);
		}
		return serviceResponse;
	}

	@Override
	public ServiceResponse<UserResponse> getUsers() {
		try {
			List<Users> userList = databaseUtils.findAll(Users.class);
			if (null != userList) {
				UserResponse userResponse = userServiceHelper.convertUserstoResponse(userList);
				logger.info("total users found :: " + userResponse);
				return new ServiceResponse<>("users find successfully ", ConfigConstants.SUCCESS, userResponse);
			} else {
				logger.info("users not exists :: ");
				return new ServiceResponse<>("users not exists :: ", ConfigConstants.FAILURE, null);
			}
		} catch (Exception e) {
			logger.error("An error occurred while finding all userGroups:: ", e);
			return new ServiceResponse<>("An error occurred while finding users:: ", ConfigConstants.ERROR, null);
		}
	}

}
