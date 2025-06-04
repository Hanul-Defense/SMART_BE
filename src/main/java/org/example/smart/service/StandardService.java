package org.example.smart.service;

import java.util.List;

import org.example.smart.domain.Soldier;
import org.example.smart.domain.Standard;
import org.example.smart.domain.enums.EvaluationCategory;
import org.example.smart.dto.response.ResponseStandardDto;
import org.example.smart.repository.SoldierRepository;
import org.example.smart.repository.StandardRepository;
import org.example.smart.util.SoldierUtil;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StandardService {
	private final StandardRepository standardRepository;
	private final SoldierRepository soldierRepository;

	public List<ResponseStandardDto> getStandarList(Long soldierId, String categoryName) {
		Soldier soldier = soldierRepository.findById(soldierId).orElseThrow();
		Integer age = SoldierUtil.getAgeByBirth(soldier.getBirth());
		EvaluationCategory evaluationCategory = EvaluationCategory.getEvaluationCategoryByCategoryName(categoryName);
		List<Standard> standardList = standardRepository.findStandardsByEvaluationCategoryAndAge(evaluationCategory,
			age);
		return standardList.stream().map(
			standard -> ResponseStandardDto.builder()
				.rank(standard.getStandardRank().getRankName())
				.maxValue(standard.getMaxValue())
				.minValue(standard.getMinValue())
				.build()
		).toList();
	}
}
