package org.example.smart.controller;

import org.example.smart.dto.global.BaseResponseDto;
import org.example.smart.dto.request.PostSignInDto;
import org.example.smart.dto.request.PostSignUpDto;
import org.example.smart.dto.response.ResponseSignInDto;
import org.example.smart.dto.response.ResponseSignUpDto;
import org.example.smart.exception.ErrorCode;
import org.example.smart.service.AuthService;
import org.springframework.http.HttpStatus;
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
	public ResponseEntity<BaseResponseDto<?>> signUp(
		@RequestBody PostSignUpDto postSignUpDto
	) {
		ResponseSignUpDto responseSignUpDto = authService.signUp(postSignUpDto);
		if (responseSignUpDto.code() == 500) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(BaseResponseDto.error(ErrorCode.INTERNAL_SERVER_ERROR));
		}
		return ResponseEntity.ok(BaseResponseDto.of(responseSignUpDto.message(), null));
	}

	@PostMapping("/sign-in")
	public ResponseEntity<BaseResponseDto<ResponseSignInDto>> signIn(
		@RequestBody PostSignInDto postSignInDto
	){
		return ResponseEntity.ok(BaseResponseDto.of("로그인에 성공했습니다.", authService.signIn(postSignInDto)));
	}
}
