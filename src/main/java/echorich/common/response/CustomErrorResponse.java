package echorich.common.response;

import java.util.List;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CustomErrorResponse {

	private String errorCode;
	private String errorMessage;

	public static CustomErrorResponse of(String errorCode, String errorMEssage) {
		return CustomErrorResponse.builder()
			.errorCode(errorCode)
			.errorMessage(errorMEssage)
			.build();
	}

	public static CustomErrorResponse of(String errorCode, BindingResult bindingResult) {
		return CustomErrorResponse.builder()
			.errorCode(errorCode)
			.errorMessage(createErrorMessage(bindingResult))
			.build();
	}

	private static String createErrorMessage(BindingResult bindingResult){
		StringBuilder sb = new StringBuilder();
		boolean isFirst = true;
		List<FieldError> fieldErrors = bindingResult.getFieldErrors();
		for (FieldError fieldError : fieldErrors) {
			if(!isFirst){
				sb.append(", ");
			} else {
				isFirst = false;
			}
			sb.append("[");
			sb.append(fieldError.getField());
			sb.append("]");
			sb.append(fieldError.getDefaultMessage());
		}

		return sb.toString();
	}
}
