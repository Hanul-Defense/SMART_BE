package org.example.smart.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class GlobalException extends RuntimeException{
	private final ErrorCode errorCode;
	public String getMessage(){
		return this.errorCode.getMessage();
	}
}
