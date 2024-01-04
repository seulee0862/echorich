package echorich.custom_api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import echorich.common.response.ApiResponse;
import echorich.custom_api.service.CustomService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/")
public class CustomController {

	private final CustomService customService;

	@Tag(name = "Custom API")
	@Operation(summary = "신나는 활동 추천 API",
		description = "language-option의 언어로 번역됩니다."
			+ "한국어, 일본어, 영어, 일본어, 중국어, 베트남어, 인도네시아어, 태국어, 독일어, 러시아어, 스페인어, 이탈리아어, 프랑스어")
	@GetMapping("/recomend-activity/{language-option}")
	public ApiResponse<String> navertest(@NotBlank @PathVariable("language-option") @Parameter(example = "한국어") String languageOption) {
		return ApiResponse.success(customService.recomendActivity(languageOption));
	}
}
