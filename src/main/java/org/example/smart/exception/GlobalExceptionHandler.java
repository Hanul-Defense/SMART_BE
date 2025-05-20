package org.example.smart.exception;

import org.example.smart.dto.global.BaseResponseDto;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public BaseResponseDto<?> exception(Exception e) {
		log.info(e.getMessage());
		return BaseResponseDto.error(500, "서버에러입니다.", null);
	}
}
