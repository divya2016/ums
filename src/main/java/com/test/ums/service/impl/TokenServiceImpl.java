package com.test.ums.service.impl;

import java.util.Date;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.request.TokenRequest;
import com.test.response.ServiceResponse;
import com.test.response.TokenResponse;
import com.test.ums.constants.ConfigConstants;
import com.test.ums.data.model.Token;
import com.test.ums.service.TokenService;
import com.test.ums.service.helper.TokenHelper;
import com.test.ums.utils.DatabaseUtils;

@Service
public class TokenServiceImpl implements TokenService {
	private static Logger logger = Logger.getLogger(TokenServiceImpl.class);

	@Autowired
	private DatabaseUtils databaseUtils;
	@Autowired
	private ConfigConstants configConstants;
	@Autowired
	private TokenHelper roleServiceHelper;

	@Override
	public ServiceResponse<TokenResponse> generateToken() {
		ServiceResponse<TokenResponse> response = new ServiceResponse<>();
		try {
			Date date = new Date();
			date.setTime(date.getTime() + configConstants.getTokenValidity());
			Token token = new Token(UUID.randomUUID().toString().replaceAll("-", ""), date);
			databaseUtils.save(token);
			logger.info("Token generated.");
			response = new ServiceResponse<>("Token generated.", ConfigConstants.SUCCESS,
					new TokenResponse(token.getTokenId()));
		} catch (Exception e) {
			logger.error("An error occurred::", e);
			response = new ServiceResponse<>("An error occurred.", ConfigConstants.ERROR, null);
		}
		return response;
	}

	@Override
	public ServiceResponse<TokenResponse> validateToken(TokenRequest request) {
		try {
			return roleServiceHelper.validateTokenRequest(request);
		} catch (Exception e) {
			logger.error("An error occurred::", e);
			return new ServiceResponse<>("An error occurred.", ConfigConstants.ERROR, null);
		}
	}

}
