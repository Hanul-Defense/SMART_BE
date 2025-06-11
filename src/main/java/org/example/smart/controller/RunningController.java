package org.example.smart.controller;

import java.util.List;

import org.example.smart.dto.global.BaseResponseDto;
import org.example.smart.dto.request.PostEstimationDto;
import org.example.smart.dto.response.ResponseRecordWithFeedbackDto;
import org.example.smart.service.RunningService;
import org.example.smart.util.AuthenticationUtil;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/runnings")
public class RunningController {

	@Qualifier("runningService")
	private final RunningService runningService;

	@PostMapping
	public ResponseEntity<BaseResponseDto<?>> postRunning(
		@RequestBody PostEstimationDto postEstimationDto,
		Authentication authentication
	) {
		return ResponseEntity.status(HttpStatus.OK)
			.body(BaseResponseDto.ok(
				runningService.postEstimation(AuthenticationUtil.getSoldierId(authentication), postEstimationDto)));
	}

	@GetMapping
	public ResponseEntity<BaseResponseDto<List<ResponseRecordWithFeedbackDto>>> getRunningList(
		Authentication authentication
	) {
		return ResponseEntity.status(HttpStatus.OK)
			.body(BaseResponseDto.ok(
				runningService.getEstimationRecordList(AuthenticationUtil.getSoldierId(authentication))));
	}

	@PatchMapping()
	public ResponseEntity<BaseResponseDto<?>> patchRunning(
		@RequestBody PostEstimationDto postEstimationDto,
		Authentication authentication
	) {
		return ResponseEntity.status(HttpStatus.OK)
			.body(BaseResponseDto.ok(
				runningService.patchEstimation(AuthenticationUtil.getSoldierId(authentication), postEstimationDto)));
	}
}
