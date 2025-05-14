package org.example.smart.controller;

import org.example.smart.dto.request.PostSignUpDto;
import org.example.smart.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
	private final AuthService authService;

	@PostMapping("/sign-up")
	public ResponseEntity<?> signUp(
		@RequestBody PostSignUpDto postSignUpDto
	) {
		return ResponseEntity.ok(authService.signUp(postSignUpDto));
	}
}
