package echorich.common.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

	@Bean
	public OpenAPI springOpenAPI() {
		return new OpenAPI()
			.info(
				new Info().title("echo rich API")
					.description("API 명세및 테스트 용도입니다.")
					.version("v0.0.1"));
	}
}
