package org.example.smart.dto.global;

import org.example.smart.exception.ErrorCode;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;

@Builder
public record BaseResponseDto<T>(
	@JsonProperty(value = "code")
	Integer code,

	@JsonProperty(value = "message")
	String message,

	@JsonProperty(value = "data")
	T data
) {
	public static <T> BaseResponseDto<T> of(String message, T data) {
		return BaseResponseDto.<T>builder()
			.code(200)
			.message(message)
			.data(data)
			.build();
	}

	public static <T> BaseResponseDto<T> error(ErrorCode errorCode) {
		return BaseResponseDto.<T>builder()
			.code(errorCode.getCode())
			.message(errorCode.getMessage())
			.data(null)
			.build();
	}
}
