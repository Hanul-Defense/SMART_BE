package org.example.smart.service;

import java.util.List;

import org.example.smart.domain.Military;
import org.example.smart.dto.request.GetUnitNameListDto;
import org.example.smart.dto.response.ResponseUnitNameListDto;
import org.example.smart.repository.MilitaryRepository;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MilitaryService {
	private final MilitaryRepository militaryRepository;

	public ResponseUnitNameListDto getUnitNameList(String keyword) {
		List<Military> militaryList = militaryRepository.findMilitariesByKeyword(keyword);
		List<String> unitNameList = militaryList.stream()
			.map(Military::getMilitaryName)
			.toList();
		return new ResponseUnitNameListDto(unitNameList);
	}
}
