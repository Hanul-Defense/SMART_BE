package org.example.smart.controller;

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
	public ResponseEntity<ResponseUnitNameListDto> getUnitNameList(
		@RequestParam(required = true) String keyword
	) {
		return ResponseEntity.ok().body(militaryService.getUnitNameList(keyword));
	}
}
