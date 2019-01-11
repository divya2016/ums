package com.test.ums.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.test.request.ServiceRequest;
import com.test.request.TokenRequest;
import com.test.response.ServiceResponse;
import com.test.response.TokenResponse;
import com.test.ums.service.TokenService;

@RestController
public class TokenController {

	@Autowired
	private TokenService tokenService;

	@GetMapping("generateToken")
	public ServiceResponse<TokenResponse> generateToken() {
		return tokenService.generateToken();
	}

	@PostMapping("validateToken")
	public ServiceResponse<TokenResponse> validateToken(@RequestBody ServiceRequest<TokenRequest> tokenRequest) {
		return tokenService.validateToken(tokenRequest.getData());
	}

}
