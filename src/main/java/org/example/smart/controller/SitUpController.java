package org.example.smart.controller;

import java.util.List;

import org.example.smart.dto.global.BaseResponseDto;
import org.example.smart.dto.request.PostEstimationDto;
import org.example.smart.dto.request.PostFeedbackDto;
import org.example.smart.dto.response.ResponseRecordWithFeedbackDto;
import org.example.smart.service.SitUpService;
import org.example.smart.util.AuthenticationUtil;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1/situps")
@RequiredArgsConstructor
public class SitUpController {

	@Qualifier("sitUpService")
	private final SitUpService sitUpService;

	@GetMapping
	public ResponseEntity<BaseResponseDto<List<ResponseRecordWithFeedbackDto>>> getSitUpList(
		Authentication authentication
	) {
		return ResponseEntity.status(HttpStatus.OK)
			.body(BaseResponseDto.ok(
				sitUpService.getEstimationRecordList(AuthenticationUtil.getSoldierId(authentication))));
	}

	@PostMapping()
	public ResponseEntity<BaseResponseDto<?>> postSitUp(
		Authentication authentication,
		@RequestBody PostEstimationDto postEstimationDto
	) {
		log.info("post pushup contorller");
		return ResponseEntity.status(HttpStatus.CREATED)
			.body(BaseResponseDto.created(
				sitUpService.postEstimation(AuthenticationUtil.getSoldierId(authentication), postEstimationDto)));
	}

	@PatchMapping()
	public ResponseEntity<BaseResponseDto<?>> patchSitUp(
		@RequestBody PostEstimationDto postEstimationDto,
		Authentication authentication
	) {
		return ResponseEntity.status(HttpStatus.OK)
			.body(BaseResponseDto.ok(
				sitUpService.patchEstimation(AuthenticationUtil.getSoldierId(authentication), postEstimationDto)));
	}

	@GetMapping("/{id}")
	public ResponseEntity<BaseResponseDto<ResponseRecordWithFeedbackDto>> getSitUpRecord(
		@PathVariable("id") Long pushUpId
	) {
		return ResponseEntity.status(HttpStatus.OK)
			.body(BaseResponseDto.ok(sitUpService.getEstimationRecord(pushUpId)));
	}

	@PostMapping("/feedback")
	public ResponseEntity<BaseResponseDto<?>> postFeedback(
		@RequestBody PostFeedbackDto postFeedbackDto,
		Authentication authentication
	) {
		return ResponseEntity.status(HttpStatus.CREATED)
			.body(BaseResponseDto.created(
				sitUpService.postFeedback(AuthenticationUtil.getSoldierId(authentication), postFeedbackDto)));
	}
}
