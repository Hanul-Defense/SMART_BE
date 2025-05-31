package org.example.smart.dto.response;

import java.time.LocalDateTime;

import org.example.smart.domain.enums.EvaluationType;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;

@Builder
public record ResponseEstimationRecordDto(

	@JsonProperty(value = "category_name")
	String categoryName,

	@JsonProperty(value = "count")
	Integer count,

	@JsonProperty(value = "rank")
	String rank,

	@JsonProperty(value = "summary")
	String summary,

	@JsonProperty(value = "evaluation_type")
	EvaluationType evaluationType,

	@JsonProperty(value = "evaluation_date")
	LocalDateTime evaluationDate
) {
	public static ResponseEstimationRecordDto of(String categoryName, Integer count, String rank, String summary,
		EvaluationType evaluationType, LocalDateTime evaluationDate) {
		return ResponseEstimationRecordDto.builder()
			.categoryName(categoryName)
			.count(count)
			.rank(rank)
			.summary(summary)
			.evaluationType(evaluationType)
			.evaluationDate(evaluationDate)
			.build();
	}
}
