package org.example.smart.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PostSignInDto(
	@JsonProperty(value = "service_number")
	String serviceNumber,

	@JsonProperty(value = "password")
	String password
	) {
}
