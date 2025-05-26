package org.example.smart.dto.request;

import java.time.LocalDateTime;

import org.example.smart.domain.enums.EvaluationType;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PostEstimationDto(
	@JsonProperty(value = "count")
	Integer count,

	@JsonProperty(value = "evaluation_type")
	EvaluationType evaluationType,

	@JsonProperty(value = "estimation_date")
	LocalDateTime estimationDate
) {
}
