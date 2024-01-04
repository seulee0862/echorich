package echorich.common;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public enum ErrorCode {

	//Employee ErrorCode
	INVALID_EMPLOYEE_ID(HttpStatus.NOT_FOUND, "E-001", "존재하지 않는 사원 입니다.."),

	//Department ErrorCode
	INVALID_DEPARTMENT_ID(HttpStatus.NOT_FOUND, "D-001", "존재하지 않는 부서 입니다."),

	//Job & JobHistory
	NOT_EXIST_IN_DEPARTMENT(HttpStatus.NOT_FOUND, "J-001", "해당 부서에는 아직 직급 체계가 만들어지지 않았습니다."),

	TEST_ERROR(HttpStatus.BAD_REQUEST, "TEST", "테스트 에러 발생.");

	ErrorCode(HttpStatus httpStatus, String code, String message) {
		this.httpStatus = httpStatus;
		this.code = code;
		this.message = message;
	}

	private final HttpStatus httpStatus;
	private final String code;
	private final String message;
}
