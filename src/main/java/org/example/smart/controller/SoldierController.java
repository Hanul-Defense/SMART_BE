package org.example.smart.controller;

import org.example.smart.dto.global.BaseResponseDto;
import org.example.smart.dto.response.ResponseSoldierDto;
import org.example.smart.service.SoldierService;
import org.example.smart.util.AuthenticationUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/soldiers")
@RequiredArgsConstructor
public class SoldierController {
	private final SoldierService soldierService;

	@GetMapping
	public ResponseEntity<BaseResponseDto<ResponseSoldierDto>> getSoldier(
		Authentication authentication
	) {
		return ResponseEntity.status(HttpStatus.OK)
			.body(BaseResponseDto.ok(soldierService.getSoldier(AuthenticationUtil.getSoldierId(authentication))));
	}
}
