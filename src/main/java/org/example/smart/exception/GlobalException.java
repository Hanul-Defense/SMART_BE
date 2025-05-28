package org.example.smart.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class GlobalException extends RuntimeException {
	private final ErrorCode errorCode;
	private final String message;

	public GlobalException(ErrorCode errorCode) {
		this.errorCode = errorCode;
		this.message = errorCode.getMessage();
	}

	public String getMessage() {
		if (message.isEmpty())
			return this.errorCode.getMessage();
		return this.message;
	}
}
