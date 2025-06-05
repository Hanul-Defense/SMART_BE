package org.example.smart.service.spec;

import java.util.List;

import org.example.smart.dto.request.PostEstimationDto;
import org.example.smart.dto.request.PostFeedbackDto;
import org.example.smart.dto.response.ResponseRecordWithFeedbackDto;

public interface EstimationService {
	String postEstimation(Long soldierId, PostEstimationDto postEstimationDto);

	List<ResponseRecordWithFeedbackDto> getEstimationRecordList(Long soldierId);

	String patchEstimation(Long soldierId, PostEstimationDto postEstimationDto);

	ResponseRecordWithFeedbackDto getEstimationRecord(Long estimationId);

	String postFeedback(Long soldierId, PostFeedbackDto postFeedbackDto);
}
