package com.test.ums.service.helper;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.test.request.AddUserRequest;
import com.test.response.ServiceResponse;
import com.test.response.UserResponse;
import com.test.ums.constants.ConfigConstants;
import com.test.ums.data.model.Users;
import com.test.ums.utils.CommonUtilities;
import com.test.ums.utils.DatabaseUtils;

@Component
public class UserServiceHelper {

	private static Logger logger = Logger.getLogger(UserServiceHelper.class);

	@Autowired
	private DatabaseUtils databaseUtils;

	public ServiceResponse<UserResponse> validateAddUserRequest(AddUserRequest data) {
		if (null == data) {
			logger.info("User request cannot be empty.");
			return new ServiceResponse<>("User request cannot be empty.", ConfigConstants.FAILURE, null);
		}
		if (null == data.getName() || data.getName().isEmpty()) {
			logger.info("User name cannot be empty.");
			return new ServiceResponse<>("User name cannot be empty.", ConfigConstants.FAILURE, null);
		}
		if (null == data.getEmail() || data.getEmail().isEmpty()) {
			logger.info("User email cannot be empty.");
			return new ServiceResponse<>("User email cannot be empty.", ConfigConstants.FAILURE, null);
		}
		if (!CommonUtilities.validateEmail(data.getEmail())) {
			logger.info("Email id not valid.");
			return new ServiceResponse<>("Email id not valid.", ConfigConstants.FAILURE, null);
		}
		if (null == data.getMobile() || data.getMobile().isEmpty()) {
			logger.info("User mobile cannot be empty.");
			return new ServiceResponse<>("User mobile cannot be empty.", ConfigConstants.FAILURE, null);
		}
		if (!CommonUtilities.validateMobile(data.getMobile())) {
			logger.info("Mobile number not valid.");
			return new ServiceResponse<>("Mobile number not valid.", ConfigConstants.FAILURE, null);
		}
		if (null == data.getUserGroup() || data.getUserGroup().isEmpty()) {
			logger.info("User group name cannot be empty.");
			return new ServiceResponse<>("User group name cannot be empty.", ConfigConstants.FAILURE, null);
		}
		if (null != databaseUtils.findUserByMobile(data.getMobile())) {
			logger.info("Mobile already registered with other user.");
			return new ServiceResponse<>("Mobile already registered with other user.", ConfigConstants.FAILURE, null);
		}
		if (null != databaseUtils.findUserByEmail(data.getEmail())) {
			logger.info("Email already registered with other user.");
			return new ServiceResponse<>("Email already registered with other user.", ConfigConstants.FAILURE, null);
		}
		logger.info("Request validated.");
		return new ServiceResponse<>("Request validated.", ConfigConstants.SUCCESS, null);
	}

	public UserResponse convertUserstoResponse(List<Users> userList) {
		// TODO Auto-generated method stub
		return null;
	}

}
