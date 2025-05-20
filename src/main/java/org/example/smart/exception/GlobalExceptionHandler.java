package org.example.smart.exception;

import org.example.smart.dto.global.BaseResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(GlobalException.class)
	@ResponseBody
	public ResponseEntity<BaseResponseDto<?>> globalException(GlobalException e) {
		log.error(e.getMessage());
		return ResponseEntity.status(e.getErrorCode().getHttpStatus())
			.body(BaseResponseDto.error(e.getErrorCode()));
	}

	@ExceptionHandler(Exception.class)
	@ResponseBody
	public ResponseEntity<BaseResponseDto<?>> exception(Exception e) {
		log.info(e.getMessage());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
			.body(BaseResponseDto.error(ErrorCode.INTERNAL_SERVER_ERROR));
	}
}
