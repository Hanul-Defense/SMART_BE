package org.example.smart.service;

import java.util.List;

import org.example.smart.dto.request.PostEstimationDto;
import org.example.smart.dto.request.PostFeedbackDto;
import org.example.smart.dto.response.ResponseRecordWithFeedbackDto;
import org.example.smart.repository.RunningRepository;
import org.example.smart.service.spec.EstimationService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@Qualifier("runningService")
@RequiredArgsConstructor
public class RunningService implements EstimationService{
	private final RunningRepository runningRepository;

	@Override
	public String postEstimation(Long soldierId, PostEstimationDto postEstimationDto) {
		return "";
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
