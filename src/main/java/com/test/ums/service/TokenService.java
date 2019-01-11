package com.test.ums.service;

import com.test.request.TokenRequest;
import com.test.response.ServiceResponse;
import com.test.response.TokenResponse;

public interface TokenService {
	public ServiceResponse<TokenResponse> generateToken();

	public ServiceResponse<TokenResponse> validateToken(TokenRequest request);
}
