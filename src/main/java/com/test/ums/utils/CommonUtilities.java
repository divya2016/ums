package com.test.ums.utils;

import org.springframework.stereotype.Component;

import com.test.ums.constants.ConfigConstants;

@Component
public class CommonUtilities {
	public static Boolean validateEmail(String email) {
		return email.matches(ConfigConstants.EMAIL_REGEX);
	}

	public static Boolean validateMobile(String mobile) {
		return mobile.matches(ConfigConstants.MOBILE_REGEX);
	}

}
