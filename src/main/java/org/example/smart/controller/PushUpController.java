package org.example.smart.controller;

import org.example.smart.dto.global.BaseResponseDto;
import org.example.smart.dto.request.PostEstimationDto;
import org.example.smart.service.spec.EstimationService;
import org.example.smart.util.AuthenticationUtil;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/pushups")
public class PushUpController {
	private final EstimationService estimationService;

	public PushUpController(@Qualifier("pushUpService") EstimationService estimationService) {
		this.estimationService = estimationService;
	}

	@PostMapping()
	public ResponseEntity<BaseResponseDto<?>> postPushUp(
		Authentication authentication,
		@RequestBody PostEstimationDto postEstimationDto
	) {
		return ResponseEntity.status(HttpStatus.CREATED)
			.body(new BaseResponseDto<>(HttpStatus.CREATED.value(),
				estimationService.postEstimation(AuthenticationUtil.getSoldierId(authentication), postEstimationDto),
				null));
	}
}
