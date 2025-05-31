package org.example.smart.controller;

import java.util.List;

import org.example.smart.dto.global.BaseResponseDto;
import org.example.smart.dto.request.PostEstimationDto;
import org.example.smart.dto.response.ResponseEstimationRecordDto;
import org.example.smart.service.PushUpService;
import org.example.smart.service.spec.EstimationService;
import org.example.smart.util.AuthenticationUtil;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/pushups")
public class PushUpController {
	private final EstimationService estimationService;

	@Qualifier("pushUpService")
	private final PushUpService pushUpService;

	@GetMapping
	public ResponseEntity<BaseResponseDto<List<ResponseEstimationRecordDto>>> getPushUpList(
		Authentication authentication
	) {
		return ResponseEntity.status(HttpStatus.OK)
			.body(new BaseResponseDto<>(HttpStatus.OK.value(), "",
				pushUpService.getEstimationRecordList(AuthenticationUtil.getSoldierId(authentication))));
	}

	@PostMapping()
	public ResponseEntity<BaseResponseDto<?>> postPushUp(
		Authentication authentication,
		@RequestBody PostEstimationDto postEstimationDto
	) {
		log.info("post pushup contorller");
		return ResponseEntity.status(HttpStatus.CREATED)
			.body(new BaseResponseDto<>(HttpStatus.CREATED.value(),
				estimationService.postEstimation(AuthenticationUtil.getSoldierId(authentication), postEstimationDto),
				null));
	}

	@GetMapping("/{id}")
	public ResponseEntity<BaseResponseDto<ResponseEstimationRecordDto>> getPushUpRecord(
		@PathVariable("id") Long pushUpId
	) {
		return ResponseEntity.status(HttpStatus.OK)
			.body(new BaseResponseDto<>(HttpStatus.OK.value(), "",
				estimationService.getEstimationRecord(pushUpId)));
	}
}
