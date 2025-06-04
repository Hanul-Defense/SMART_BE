package org.example.smart.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PostFeedbackDto(

	@JsonProperty(value = "estimation_id")
	Long estimationId,

	@JsonProperty(value = "feedback")
	String feedback
) {
}
