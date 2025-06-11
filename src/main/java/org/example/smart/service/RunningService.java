package org.example.smart.service;

import java.util.List;
import java.util.Optional;

import org.example.smart.domain.Running;
import org.example.smart.domain.Soldier;
import org.example.smart.domain.Standard;
import org.example.smart.domain.enums.EvaluationCategory;
import org.example.smart.dto.request.PostEstimationDto;
import org.example.smart.dto.request.PostFeedbackDto;
import org.example.smart.dto.response.ResponseRecordWithFeedbackDto;
import org.example.smart.exception.ErrorCode;
import org.example.smart.exception.GlobalException;
import org.example.smart.repository.RunningRepository;
import org.example.smart.repository.SoldierRepository;
import org.example.smart.repository.StandardRepository;
import org.example.smart.service.spec.EstimationService;
import org.example.smart.util.SoldierUtil;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@Qualifier("runningService")
@RequiredArgsConstructor
public class RunningService implements EstimationService {
	private final RunningRepository runningRepository;
	private final SoldierRepository soldierRepository;
	private final StandardRepository standardRepository;

	@Override
	public String postEstimation(Long soldierId, PostEstimationDto postEstimationDto) {
		Soldier soldier = soldierRepository.findById(soldierId)
			.orElseThrow(() -> new GlobalException(ErrorCode.NOT_FOUND_USER));
		Optional<Running> optionalRunning = runningRepository.findRunningBySoldierAndEvaluationDate(soldier,
			postEstimationDto.evaluationDate().toLocalDate());
		if (optionalRunning.isPresent()) {
			throw new GlobalException(ErrorCode.ALREADY_REGISTERED);
		}
		Integer age = SoldierUtil.getAgeByBirth(soldier.getBirth());
		Standard standard = standardRepository.findByAgeAndCountAndEvaluationCategory(age, postEstimationDto.record(),
				EvaluationCategory.PUSH_UP)
			.orElseThrow(() -> new GlobalException(ErrorCode.NOT_FOUND_DATA));

		try {
			Running running = Running.builder()
				.soldier(soldier)
				.time(postEstimationDto.record())
				.standard(standard)
				.evaluationType(postEstimationDto.evaluationType())
				.evaluationDate(postEstimationDto.evaluationDate())
				.summary(postEstimationDto.summary()) // TODO
				.build();

			runningRepository.save(running);
			return "등록을 성공했습니다.";
		} catch (Exception e) {
			throw new GlobalException(ErrorCode.INTERNAL_SERVER_ERROR, e.getMessage());
		}
	}

	@Override
	public List<ResponseRecordWithFeedbackDto> getEstimationRecordList(Long soldierId) {
		return List.of();
	}

	@Override
	public String patchEstimation(Long soldierId, PostEstimationDto postEstimationDto) {
		return "";
	}

	@Override
	public ResponseRecordWithFeedbackDto getEstimationRecord(Long estimationId) {
		return null;
	}

	@Override
	public String postFeedback(Long soldierId, PostFeedbackDto postFeedbackDto) {
		return "";
	}
}
