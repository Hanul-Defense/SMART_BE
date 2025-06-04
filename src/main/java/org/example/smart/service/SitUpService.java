package org.example.smart.service;

import java.util.List;

import org.example.smart.domain.SitUp;
import org.example.smart.domain.SitUpFeedback;
import org.example.smart.domain.Soldier;
import org.example.smart.domain.Standard;
import org.example.smart.domain.enums.EvaluationCategory;
import org.example.smart.dto.request.PostEstimationDto;
import org.example.smart.dto.request.PostFeedbackDto;
import org.example.smart.dto.response.ResponseRecordWithFeedbackDto;
import org.example.smart.exception.ErrorCode;
import org.example.smart.exception.GlobalException;
import org.example.smart.repository.SitUpFeedbackRepository;
import org.example.smart.repository.SitUpRepository;
import org.example.smart.repository.SoldierRepository;
import org.example.smart.repository.StandardRepository;
import org.example.smart.service.spec.EstimationService;
import org.example.smart.util.BirthUtil;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Qualifier("sitUpService")
@RequiredArgsConstructor
public class SitUpService implements EstimationService {
	private final SoldierRepository soldierRepository;
	private final SitUpRepository sitUpRepository;
	private final SitUpFeedbackRepository sitUpFeedbackRepository;
	private final StandardRepository standardRepository;

	@Override
	public String postEstimation(Long soldierId, PostEstimationDto postEstimationDto) {
		log.info("postEstimation Service in");
		Soldier soldier = soldierRepository.findById(soldierId).orElseThrow(() -> new RuntimeException(
			"Soldier not found for soldierId=" + soldierId
		));
		Integer age = BirthUtil.getAgeByBirth(soldier.getBirth());
		Standard standard = standardRepository.findByAgeAndCountAndEvaluationCategory(age, postEstimationDto.count(),
				EvaluationCategory.PUSH_UP)
			.orElseThrow(() -> new RuntimeException(
				"Standard not found for age=" + age + ", count=" + postEstimationDto.count()));
		try {
			SitUp sitUp = SitUp.builder()
				.soldier(soldier)
				.count(postEstimationDto.count())
				.standard(standard)
				.evaluationType(postEstimationDto.evaluationType())
				.evaluationDate(postEstimationDto.evaluationDate())
				.summary(postEstimationDto.summary()) // TODO
				.build();

			sitUpRepository.save(sitUp);
			return "등록을 성공했습니다.";
		} catch (Exception e) {
			throw new GlobalException(ErrorCode.INTERNAL_SERVER_ERROR, e.getMessage());
		}
	}

	@Override
	public List<ResponseRecordWithFeedbackDto> getEstimationRecordList(Long soldierId) {
		Soldier soldier = soldierRepository.findById(soldierId).orElseThrow();
		List<SitUp> sitUpList = sitUpRepository.getSitUpsBySoldier(soldier);
		return sitUpList.stream().map(ResponseRecordWithFeedbackDto::fromSitUp).toList();
	}

	@Override
	public ResponseRecordWithFeedbackDto getEstimationRecord(Long estimationId) {
		SitUp sitUp = sitUpRepository.findById(estimationId).orElseThrow();
		return ResponseRecordWithFeedbackDto.fromSitUp(sitUp);
	}

	@Override
	public String postFeedback(Long soldierId, PostFeedbackDto postFeedbackDto) {
		Soldier soldier = soldierRepository.findById(soldierId).orElseThrow();
		SitUp sitUp = sitUpRepository.findById(postFeedbackDto.estimationId()).orElseThrow();
		try {
			SitUpFeedback feedback = SitUpFeedback.builder()
				.sitUp(sitUp)
				.soldier(soldier)
				.feedbackContent(postFeedbackDto.feedback())
				.build();

			sitUpFeedbackRepository.save(feedback);
			return "등록을 성공했습니다.";
		} catch (Exception e) {
			throw new GlobalException(ErrorCode.INTERNAL_SERVER_ERROR, "저장이 되지 않았습니다.");
		}
	}
}
