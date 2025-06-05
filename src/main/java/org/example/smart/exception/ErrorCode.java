package org.example.smart.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
	//400
	BAD_REQUEST(40000, HttpStatus.BAD_REQUEST, "잘못된 요청 형식입니다."),
	DUPLICATE_SERVICENUMBER(40001, HttpStatus.BAD_REQUEST, "이미 존재하는 군번입니다."),
	BAD_MILITARY_NAME(40002, HttpStatus.BAD_REQUEST, "입력하신 정보에 해당하는 군 부대가 없습니다."),
	BAD_KEYWORD(40003, HttpStatus.BAD_REQUEST, "잘못된 검색어 형식입니다."),
	ALREADY_REGISTERED(40004, HttpStatus.BAD_REQUEST, "이미 기록이 등록되었습니다."),

	//401
	UNAUTHORIZED(40100, HttpStatus.UNAUTHORIZED, "인증이 필요합니다."),

	INVALID_SERVICE_NUMBER(40101, HttpStatus.UNAUTHORIZED, "군번이 올바르지 않습니다."),

	INVALID_PASSWORD(40102, HttpStatus.UNAUTHORIZED, "비밀번호가 올바르지 않습니다."),

	//403
	FORBIDDEN(40300, HttpStatus.FORBIDDEN, "요청 권한이 없습니다."),

	// 404
	NOT_FOUND_USER(40400, HttpStatus.NOT_FOUND, "존재하지 않는 유저입니다."),

	NOT_FOUND_MEMBER(40401, HttpStatus.NOT_FOUND, "존재하지 않는 멤버입니다."),

	NOT_FOUND_DATA(40402, HttpStatus.NOT_FOUND, "해당 데이터를 찾을 수 없습니다."),

	// 409
	CONFLICT_ID(40901, HttpStatus.CONFLICT, "이미 존재하는 아이디입니다."),

	CONFLICT_USER(40902, HttpStatus.CONFLICT, "이미 존재하는 회원입니다."),

	CONFLICT_INVITATION(40903, HttpStatus.CONFLICT, "이미 수락된 초대입니다."),

	// 500
	INTERNAL_SERVER_ERROR(50000, HttpStatus.INTERNAL_SERVER_ERROR, "서버내부 오류입니다."),

	//503
	REDIS_CONNECTION_FAILED(50300, HttpStatus.SERVICE_UNAVAILABLE, "REDIS 연결에 실패했습니다.");

	private final Integer code;
	private final HttpStatus httpStatus;
	private final String message;
}
