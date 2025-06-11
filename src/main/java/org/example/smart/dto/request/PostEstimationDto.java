package org.example.smart.dto.request;

import java.time.LocalDateTime;

import org.example.smart.domain.enums.EvaluationType;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PostEstimationDto(
	@JsonProperty(value = "record")
	Integer record,

	@JsonProperty(value = "summary")
	String summary,

	@JsonProperty(value = "evaluation_type")
	EvaluationType evaluationType,

	@JsonProperty(value = "evaluation_date")
	LocalDateTime evaluationDate
) {
}
