package org.example.smart.service.spec;

import org.example.smart.dto.request.PostEstimationDto;

public interface EstimationService {
	public String postEstimation(Long soldierId, PostEstimationDto postEstimationDto);
}
