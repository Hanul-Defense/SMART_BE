package org.example.smart.service;

import java.util.List;

import org.example.smart.domain.Military;
import org.example.smart.dto.response.ResponseUnitNameListDto;
import org.example.smart.exception.ErrorCode;
import org.example.smart.exception.GlobalException;
import org.example.smart.repository.MilitaryRepository;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MilitaryService {
	private final MilitaryRepository militaryRepository;

	public ResponseUnitNameListDto getMilitaryNameList(String keyword) {
		List<String> militaryNameList = militaryRepository.findMilitaryNameByKeyword(keyword);
		if (militaryNameList.isEmpty()) {
			throw new GlobalException(ErrorCode.BAD_KEYWORD);
		}
		return new ResponseUnitNameListDto(militaryNameList);
	}
}
