package org.example.smart.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;

@Builder
public record ResponseSignUpDto(
	@JsonProperty("code")
	Integer code,
	@JsonProperty("message")
	String message
) {
}
