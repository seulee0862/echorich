package echorich.common;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public enum ErrorCode {

	//404에러
	INVALID_EMPLOYEE_ID(HttpStatus.NOT_FOUND, "E-001", "존재하지 않는 사원 입니다.."),

	TEST_ERROR(HttpStatus.BAD_REQUEST, "TEST", "내부 에러가 발생했습니다.");

	ErrorCode(HttpStatus httpStatus, String code, String message) {
		this.httpStatus = httpStatus;
		this.code = code;
		this.message = message;
	}

	private final HttpStatus httpStatus;
	private final String code;
	private final String message;
}
