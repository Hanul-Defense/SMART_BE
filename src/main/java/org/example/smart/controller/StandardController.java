package org.example.smart.controller;

import java.util.List;

import org.example.smart.dto.global.BaseResponseDto;
import org.example.smart.dto.response.ResponseStandardDto;
import org.example.smart.service.StandardService;
import org.example.smart.util.AuthenticationUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/standards")
@RequiredArgsConstructor
public class StandardController {
	private final StandardService standardService;

	@GetMapping("/{category_name}")
	public ResponseEntity<BaseResponseDto<List<ResponseStandardDto>>> getStandardList(
		@PathVariable("category_name")String categoryName,
		Authentication authentication
	) {
		return ResponseEntity.status(HttpStatus.OK)
			.body(BaseResponseDto.ok(
				standardService.getStandarList(AuthenticationUtil.getSoldierId(authentication), categoryName)));
	}
}
