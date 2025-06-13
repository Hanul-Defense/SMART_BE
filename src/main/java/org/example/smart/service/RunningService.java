package org.example.smart.service;

import java.util.List;
import java.util.Optional;

import org.example.smart.domain.Running;
import org.example.smart.domain.RunningFeedback;
import org.example.smart.domain.Soldier;
import org.example.smart.domain.Standard;
import org.example.smart.domain.enums.EvaluationCategory;
import org.example.smart.dto.request.PostEstimationDto;
import org.example.smart.dto.request.PostFeedbackDto;
import org.example.smart.dto.response.ResponseRecordWithFeedbackDto;
import org.example.smart.exception.ErrorCode;
import org.example.smart.exception.GlobalException;
import org.example.smart.repository.RunningFeedbackRepository;
import org.example.smart.repository.RunningRepository;
import org.example.smart.repository.SoldierRepository;
import org.example.smart.repository.StandardRepository;
import org.example.smart.service.spec.EstimationService;
import org.example.smart.util.SoldierUtil;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@Qualifier("runningService")
@RequiredArgsConstructor
public class RunningService implements EstimationService {
	private final RunningRepository runningRepository;
	private final RunningFeedbackRepository runningFeedbackRepository;
	private final SoldierRepository soldierRepository;
	private final StandardRepository standardRepository;

	@Override
	@Transactional
	public String postEstimation(Long soldierId, PostEstimationDto postEstimationDto) {
		Soldier soldier = soldierRepository.findById(soldierId)
			.orElseThrow(() -> new GlobalException(ErrorCode.NOT_FOUND_USER));
		Optional<Running> optionalRunning = runningRepository.findRunningBySoldierAndEvaluationTypeAndEvaluationDate(
			soldier, postEstimationDto.evaluationType(), postEstimationDto.evaluationDate().toLocalDate());
		if (optionalRunning.isPresent()) {
			throw new GlobalException(ErrorCode.ALREADY_REGISTERED);
		}
		Integer age = SoldierUtil.getAgeByBirth(soldier.getBirth());
		Standard standard = standardRepository.findByAgeAndCountAndEvaluationCategory(age, postEstimationDto.record(),
				EvaluationCategory.RUNNING)
			.orElseThrow(() -> new GlobalException(ErrorCode.NOT_FOUND_DATA));

		try {
			Running running = Running.builder()
				.soldier(soldier)
				.time(postEstimationDto.record())
				.standard(standard)
				.evaluationType(postEstimationDto.evaluationType())
				.evaluationDate(postEstimationDto.evaluationDate())
				.summary(postEstimationDto.summary())
				.build();

			runningRepository.save(running);
			return "등록을 성공했습니다.";
		} catch (Exception e) {
			throw new GlobalException(ErrorCode.INTERNAL_SERVER_ERROR, e.getMessage());
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<ResponseRecordWithFeedbackDto> getEstimationRecordList(Long soldierId) {
		Soldier soldier = soldierRepository.findById(soldierId).orElseThrow();
		List<Running> runningListList = runningRepository.findRunningsBySoldier(soldier);
		return runningListList.stream().map(ResponseRecordWithFeedbackDto::fromRunning).toList();
	}

	@Override
	@Transactional
	public String patchEstimation(Long soldierId, PostEstimationDto postEstimationDto) {
		Soldier soldier = soldierRepository.findById(soldierId)
			.orElseThrow(() -> new GlobalException(ErrorCode.NOT_FOUND_USER));
		Running running = runningRepository.findRunningBySoldierAndEvaluationTypeAndEvaluationDate(soldier,
				postEstimationDto.evaluationType(), postEstimationDto.evaluationDate().toLocalDate())
			.orElseThrow(() -> new GlobalException(ErrorCode.NOT_FOUND_DATA));
		if (running.getTime() < postEstimationDto.record()) {
			return updateEstimation(running, postEstimationDto);
		} else {
			throw new GlobalException(ErrorCode.NOT_BEST_SCORE);
		}
	}

	private String updateEstimation(Running running, PostEstimationDto postEstimationDto) {
		RunningFeedback feedback = running.getRunningFeedback();
		if (feedback != null) {
			runningFeedbackRepository.delete(feedback);
		}
		running.updateRecord(postEstimationDto);
		return "최고기록을 변경했습니다.";
	}

	@Override
	@Transactional(readOnly = true)
	public ResponseRecordWithFeedbackDto getEstimationRecord(Long estimationId) {
		Running running = runningRepository.findById(estimationId)
			.orElseThrow(() -> new GlobalException(ErrorCode.NOT_FOUND_DATA));
		return ResponseRecordWithFeedbackDto.fromRunning(running);
	}

	@Override
	@Transactional
	public String postFeedback(Long soldierId, PostFeedbackDto postFeedbackDto) {
		Soldier soldier = soldierRepository.findById(soldierId)
			.orElseThrow(() -> new GlobalException(ErrorCode.NOT_FOUND_USER));
		Running running = runningRepository.findById(postFeedbackDto.estimationId())
			.orElseThrow(() -> new GlobalException(ErrorCode.BAD_REQUEST));
		try {
			RunningFeedback feedback = RunningFeedback.builder()
				.running(running)
				.soldier(soldier)
				.feedbackContent(postFeedbackDto.feedback())
				.build();

			runningFeedbackRepository.save(feedback);
			return "등록을 성공했습니다.";
		} catch (Exception e) {
			throw new GlobalException(ErrorCode.INTERNAL_SERVER_ERROR, "저장이 되지 않았습니다.");
		}
	}
}
