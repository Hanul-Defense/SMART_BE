package org.example.smart.controller;

import org.example.smart.domain.Soldier;
import org.example.smart.dto.global.BaseResponseDto;
import org.example.smart.dto.request.PostSignUpDto;
import org.example.smart.dto.response.ResponseSignUpDto;
import org.example.smart.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
	private final AuthService authService;

	@PostMapping("/sign-up")
	public BaseResponseDto<ResponseSignUpDto> signUp(
		@RequestBody PostSignUpDto postSignUpDto
	) {
		ResponseSignUpDto responseSignUpDto = authService.signUp(postSignUpDto);
		return BaseResponseDto.of(responseSignUpDto.code(), responseSignUpDto.message(), null);
	}
}
