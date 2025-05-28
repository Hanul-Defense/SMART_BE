package org.example.smart.service.spec;

import java.util.List;

import org.example.smart.dto.request.PostEstimationDto;
import org.example.smart.dto.response.ResponseEstimationRecordDto;

public interface EstimationService {
	String postEstimation(Long soldierId, PostEstimationDto postEstimationDto);

	List<ResponseEstimationRecordDto> getEstimationRecordList(Long soldierId);
}
