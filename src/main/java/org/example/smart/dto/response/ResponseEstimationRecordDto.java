package org.example.smart.dto.response;

import java.time.LocalDateTime;

import org.example.smart.domain.enums.EvaluationType;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;

@Builder
public record ResponseEstimationRecordDto(

	@JsonProperty(value = "count")
	Integer count,

	@JsonProperty(value = "summary")
	String summary,

	@JsonProperty(value = "evaluation_type")
	EvaluationType evaluationType,

	@JsonProperty(value = "evaluation_date")
	LocalDateTime evaluationDate
) {
	public static ResponseEstimationRecordDto of(Integer count, String summary, EvaluationType evaluationType,
		LocalDateTime evaluationDate) {
		return ResponseEstimationRecordDto.builder()
			.count(count)
			.summary(summary)
			.evaluationType(evaluationType)
			.evaluationDate(evaluationDate)
			.build();
	}
}
