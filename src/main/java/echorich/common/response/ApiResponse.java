package echorich.common.response;

import lombok.Getter;

@Getter
public class ApiResponse<T> {

	private final T data;
	private final int code;
	private final String message;

	private ApiResponse(T data, int code, String message) {
		this.data = data;
		this.code = code;
		this.message = message;
	}

	public static <T> ApiResponse<T> success(T data) {
		return new ApiResponse<>(data, 200, "success");
	}
}
