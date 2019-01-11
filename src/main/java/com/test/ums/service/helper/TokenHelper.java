package com.test.ums.service.helper;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.test.request.TokenRequest;
import com.test.response.ServiceResponse;
import com.test.response.TokenResponse;
import com.test.ums.constants.ConfigConstants;
import com.test.ums.data.model.Token;
import com.test.ums.utils.DatabaseUtils;

public class TokenHelper {
	private static Logger logger = Logger.getLogger(TokenHelper.class);
	@Autowired
	private DatabaseUtils databaseUtils;
	@Autowired
	private ConfigConstants configConstants;

	public ServiceResponse<TokenResponse> validateTokenRequest(TokenRequest request) {
		if (null == request) {
			logger.info("Token request cannot be empty.");
			return new ServiceResponse<>("Token request cannot be empty.", ConfigConstants.FAILURE, null);
		}
		if (null == request.getTokenId() || request.getTokenId().isEmpty()) {
			logger.info("Token Id cannot be empty.");
			return new ServiceResponse<>("Token Id cannot be empty.", ConfigConstants.FAILURE, null);
		}
		Token token = databaseUtils.getTokenByTokenId(request.getTokenId());
		if (null == token || null == token.getValidTill()) {
			logger.info("Invalid token");
			return new ServiceResponse<>("Invalid token.", ConfigConstants.FAILURE, null);
		}
		Date date = new Date();
		if (token.getValidTill().getTime() < date.getTime()) {
			logger.info("Token expired.");
			return new ServiceResponse<>("Invalid token.", ConfigConstants.FAILURE, null);
		}
		token.setValidTill(new Date(date.getTime() + configConstants.getTokenValidity()));
		databaseUtils.save(token);
		logger.info("Request validated.");
		return new ServiceResponse<>("Request validated.", ConfigConstants.SUCCESS, null);
	}

}
