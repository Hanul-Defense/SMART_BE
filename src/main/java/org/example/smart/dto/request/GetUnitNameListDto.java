package org.example.smart.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record GetUnitNameListDto(
	@JsonProperty(value = "keyword")
	String keyword
) {
}
