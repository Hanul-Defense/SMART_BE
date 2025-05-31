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
	public static <T> BaseResponseDto<T> ok(String message, T data) {
		return BaseResponseDto.<T>builder()
			.code(200)
			.message(message)
			.data(data)
			.build();
	}

	public static <T> BaseResponseDto<T> ok(T data) {
		return BaseResponseDto.<T>builder()
			.code(200)
			.message("요청을 성공했습니다.")
			.data(data)
			.build();
	}

	public static <T> BaseResponseDto<T> created(String message) {
		return BaseResponseDto.<T>builder()
			.code(201)
			.message(message)
			.data(null)
			.build();
	}

	public static <T> BaseResponseDto<T> error(ErrorCode errorCode) {
		return BaseResponseDto.<T>builder()
			.code(errorCode.getCode())
			.message(errorCode.getMessage())
			.data(null)
			.build();
	}

	public static <T> BaseResponseDto<T> error(ErrorCode errorCode, String message) {
		return BaseResponseDto.<T>builder()
			.code(errorCode.getCode())
			.message(message)
			.data(null)
			.build();
	}
}
