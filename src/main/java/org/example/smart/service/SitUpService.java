package org.example.smart.service;

import java.util.List;
import java.util.Optional;

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
import org.example.smart.util.SoldierUtil;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
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
	@Transactional
	public String postEstimation(Long soldierId, PostEstimationDto postEstimationDto) {
		log.info("postEstimation Service in");
		Soldier soldier = soldierRepository.findById(soldierId)
			.orElseThrow(() -> new GlobalException(ErrorCode.NOT_FOUND_USER));
		Optional<SitUp> optionalSitUp = sitUpRepository.findSitUpBySoldierAndEvaluationDate(soldier,
			postEstimationDto.evaluationDate().toLocalDate());
		if (optionalSitUp.isPresent())
			throw new GlobalException(ErrorCode.ALREADY_REGISTERED);
		Integer age = SoldierUtil.getAgeByBirth(soldier.getBirth());
		Standard standard = standardRepository.findByAgeAndCountAndEvaluationCategory(age, postEstimationDto.record(),
				EvaluationCategory.SIT_UP)
			.orElseThrow(() -> new RuntimeException(
				"Standard not found for age=" + age + ", record=" + postEstimationDto.record()));
		try {
			SitUp sitUp = SitUp.builder()
				.soldier(soldier)
				.count(postEstimationDto.record())
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
	@Transactional
	public String patchEstimation(Long soldierId, PostEstimationDto postEstimationDto) {
		Soldier soldier = soldierRepository.findById(soldierId)
			.orElseThrow(() -> new GlobalException(ErrorCode.NOT_FOUND_USER));
		SitUp sitUp = sitUpRepository.findSitUpBySoldierAndEvaluationDate(soldier,
				postEstimationDto.evaluationDate().toLocalDate())
			.orElseThrow(() -> new GlobalException(ErrorCode.NOT_FOUND_DATA));
		if (sitUp.getCount() < postEstimationDto.record()) {
			return updateEstimation(sitUp, postEstimationDto);
		} else {
			throw new GlobalException(ErrorCode.NOT_BEST_SCORE);
		}
	}

	private String updateEstimation(SitUp sitUp, PostEstimationDto postEstimationDto) {
		SitUpFeedback feedback = sitUp.getSitUpFeedback();
		if (feedback != null) {
			sitUpFeedbackRepository.delete(feedback);
		}
		sitUp.updateRecord(postEstimationDto);
		return "최고기록을 변경했습니다.";
	}

	@Override
	public ResponseRecordWithFeedbackDto getEstimationRecord(Long estimationId) {
		SitUp sitUp = sitUpRepository.findById(estimationId).orElseThrow();
		return ResponseRecordWithFeedbackDto.fromSitUp(sitUp);
	}

	@Override
	@Transactional
	public String postFeedback(Long soldierId, PostFeedbackDto postFeedbackDto) {
		Soldier soldier = soldierRepository.findById(soldierId)
			.orElseThrow(() -> new GlobalException(ErrorCode.NOT_FOUND_USER));
		SitUp sitUp = sitUpRepository.findById(postFeedbackDto.estimationId())
			.orElseThrow(() -> new GlobalException(ErrorCode.BAD_REQUEST));
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
