package org.example.smart.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ResponseSignInDto(
	@JsonProperty(value = "soldier_id")
	Long soldierId,
	@JsonProperty(value = "access_token")
	String accessToken,
	@JsonProperty(value = "refresh_token")
	String refreshToken
) {
}
