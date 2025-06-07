package org.example.smart.service;

import org.example.smart.domain.Soldier;
import org.example.smart.dto.response.ResponseSoldierDto;
import org.example.smart.exception.ErrorCode;
import org.example.smart.exception.GlobalException;
import org.example.smart.repository.SoldierRepository;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SoldierService {
	private final SoldierRepository soldierRepository;

	public ResponseSoldierDto getSoldier(Long soldierId) {
		Soldier soldier = soldierRepository.findById(soldierId)
			.orElseThrow(() -> new GlobalException(ErrorCode.NOT_FOUND_USER));

		return ResponseSoldierDto.from(soldier);
	}
}
