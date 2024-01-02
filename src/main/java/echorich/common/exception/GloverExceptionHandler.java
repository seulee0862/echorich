package echorich.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import echorich.common.exception.customException.BusinessException;
import echorich.common.response.CustomErrorResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GloverExceptionHandler {

	/**
	 * - @Valid 검증 에러 발생할 경우
	 */
	@ExceptionHandler(BindException.class)
	protected ResponseEntity<CustomErrorResponse> handleBindException(BindException e) {
		log.error("handleBindException", e);
		CustomErrorResponse errorResponse = CustomErrorResponse.of(HttpStatus.BAD_REQUEST.toString(), e.getBindingResult());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
			.body(errorResponse);
	}

	/**
	 * 	비즈니스 로직 실행 중 오류
	 */
	@ExceptionHandler(value = {BusinessException.class})
	protected ResponseEntity<CustomErrorResponse> handleConflict(BusinessException e) {
		log.error("BusinessException", e);
		CustomErrorResponse errorResponse = CustomErrorResponse.of(e.getErrorCode().getCode(), e.getMessage());

		return ResponseEntity.status(e.getErrorCode().getHttpStatus())
			.body(errorResponse);
	}

}
