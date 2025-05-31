package org.example.smart.controller;

import org.example.smart.dto.global.BaseResponseDto;
import org.example.smart.dto.response.ResponseUnitNameListDto;
import org.example.smart.service.MilitaryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/militaries")
@RequiredArgsConstructor
public class MilitaryController {
	private final MilitaryService militaryService;

	@GetMapping()
	public ResponseEntity<BaseResponseDto<ResponseUnitNameListDto>> getUnitNameList(
		@RequestParam String keyword
	) {
		return ResponseEntity.ok(BaseResponseDto.ok(militaryService.getMilitaryNameList(keyword)));
	}
}
