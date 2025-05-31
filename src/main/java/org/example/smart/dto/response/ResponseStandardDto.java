package org.example.smart.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;

@Builder
public record ResponseStandardDto(
	@JsonProperty(value = "rank")
	String rank,
	@JsonProperty(value = "max_value")
	Integer maxValue,
	@JsonProperty(value = "min_value")
	Integer minValue
) {
}
