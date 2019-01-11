package com.test.ums.constants;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Getter;

@Getter
@Component
public class ConfigConstants {
	public static String SUCCESS = "success";
	public static String FAILURE = "failure";
	public static String ERROR = "error";
	public static String EMAIL_REGEX = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
	public static String MOBILE_REGEX = "[6-9][0-9]{9}";
	@Value("${token.validity}")
	private Long tokenValidity;

	public enum Status {
		Active, Inactive, Blocked, Suspended
	}
}
