package org.example.smart.dto.global;

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
	public static <T> BaseResponseDto<T> ok(String message, T data) {
		return BaseResponseDto.<T>builder()
			.code(200)
			.message(message)
			.data(data)
			.build();
	}

	public static <T> BaseResponseDto<T> created(String message, T data) {
		return BaseResponseDto.<T>builder()
			.code(201)
			.message(message)
			.data(data)
			.build();
	}

	public static <T> BaseResponseDto<T> error(Integer code, String message, T data) {
		return BaseResponseDto.<T>builder()
			.code(code)
			.message(message)
			.data(data)
			.build();
	}
}
