package org.example.smart.controller;

import org.apache.coyote.Response;
import org.example.smart.dto.global.BaseResponseDto;
import org.example.smart.dto.request.PostEstimationDto;
import org.example.smart.service.RunningService;
import org.example.smart.util.AuthenticationUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/runnings")
public class RunningController {
	private final RunningService runningService;

	@PostMapping
	public ResponseEntity<BaseResponseDto<?>> postEstimation(
		@RequestBody PostEstimationDto postEstimationDto,
		Authentication authentication
	) {
		return ResponseEntity.status(HttpStatus.OK)
			.body(BaseResponseDto.ok(
				runningService.postEstimation(AuthenticationUtil.getSoldierId(authentication), postEstimationDto)));
	}
}
