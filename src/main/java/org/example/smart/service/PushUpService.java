package org.example.smart.service;

import java.util.List;

import org.example.smart.domain.PushUp;
import org.example.smart.domain.PushUpFeedback;
import org.example.smart.domain.Soldier;
import org.example.smart.domain.Standard;
import org.example.smart.domain.enums.EvaluationCategory;
import org.example.smart.dto.request.PostEstimationDto;
import org.example.smart.dto.request.PostFeedbackDto;
import org.example.smart.dto.response.ResponseRecordWithFeedbackDto;
import org.example.smart.exception.ErrorCode;
import org.example.smart.exception.GlobalException;
import org.example.smart.repository.PushUpFeedbackRepository;
import org.example.smart.repository.PushUpRepository;
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
@Service(value = "pushUpService")
@Qualifier("pushUpService")
@RequiredArgsConstructor
public class PushUpService implements EstimationService {
	private final SoldierRepository soldierRepository;
	private final PushUpRepository pushUpRepository;
	private final PushUpFeedbackRepository pushUpFeedbackRepository;
	private final StandardRepository standardRepository;

	@Override
	@Transactional
	public String postEstimation(Long soldierId, PostEstimationDto postEstimationDto) {
		log.info("postEstimation Service in");
		Soldier soldier = soldierRepository.findById(soldierId)
			.orElseThrow(() -> new GlobalException(ErrorCode.NOT_FOUND_DATA));
		Integer age = SoldierUtil.getAgeByBirth(soldier.getBirth());
		Standard standard = standardRepository.findByAgeAndCountAndEvaluationCategory(age, postEstimationDto.count(),
				EvaluationCategory.PUSH_UP)
			.orElseThrow(() -> new RuntimeException(
				"Standard not found for age=" + age + ", count=" + postEstimationDto.count()));
		try {
			PushUp pushUp = PushUp.builder()
				.soldier(soldier)
				.count(postEstimationDto.count())
				.standard(standard)
				.evaluationType(postEstimationDto.evaluationType())
				.evaluationDate(postEstimationDto.evaluationDate())
				.summary(postEstimationDto.summary()) // TODO
				.build();

			pushUpRepository.save(pushUp);
			return "등록을 성공했습니다.";
		} catch (Exception e) {
			throw new GlobalException(ErrorCode.INTERNAL_SERVER_ERROR, e.getMessage());
		}
	}

	@Override
	public List<ResponseRecordWithFeedbackDto> getEstimationRecordList(Long soldierId) {
		Soldier soldier = soldierRepository.findById(soldierId).orElseThrow();
		List<PushUp> pushUpList = pushUpRepository.getPushUpsBySoldier(soldier);

		return pushUpList.stream()
			.map(ResponseRecordWithFeedbackDto::fromPushUp).toList();
	}

	@Override
	@Transactional
	public String patchEstimation(Long soldierId, PostEstimationDto postEstimationDto) {
		PushUp pushUp = pushUpRepository.findPushUpByEvaluationDate(postEstimationDto.evaluationDate().toLocalDate())
			.orElseThrow(() -> new GlobalException(ErrorCode.NOT_FOUND_DATA));
		if (pushUp.getCount() < postEstimationDto.count()) {
			return updateEstimation(pushUp, postEstimationDto);
		}
		throw new GlobalException(ErrorCode.BAD_REQUEST);
	}

	private String updateEstimation(PushUp pushUp, PostEstimationDto postEstimationDto) {
		PushUpFeedback feedback = pushUp.getPushUpFeedback();
		if (feedback != null) {
			pushUpFeedbackRepository.delete(feedback);
		}
		pushUp.updateRecord(postEstimationDto);
		return "최고기록을 변경했습니다.";
	}

	@Override
	public ResponseRecordWithFeedbackDto getEstimationRecord(Long estimationId) {
		PushUp pushUp = pushUpRepository.findById(estimationId).orElseThrow();

		return ResponseRecordWithFeedbackDto.fromPushUp(pushUp);
	}

	@Override
	@Transactional
	public String postFeedback(Long soldierId, PostFeedbackDto postFeedbackDto) {
		Soldier soldier = soldierRepository.findById(soldierId).orElseThrow();
		PushUp pushUp = pushUpRepository.findById(postFeedbackDto.estimationId()).orElseThrow();
		try {
			PushUpFeedback feedback = PushUpFeedback.builder()
				.pushUp(pushUp)
				.soldier(soldier)
				.feedbackContent(postFeedbackDto.feedback())
				.build();

			pushUpFeedbackRepository.save(feedback);
			return "등록을 성공했습니다.";
		} catch (Exception e) {
			throw new GlobalException(ErrorCode.INTERNAL_SERVER_ERROR, "저장이 되지 않았습니다.");
		}
	}
}
