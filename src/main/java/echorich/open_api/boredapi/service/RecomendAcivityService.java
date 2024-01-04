package echorich.open_api.boredapi.service;

import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class RecomendAcivityService {

	public String getRecomendActivity() {
		WebClient webClient = WebClient.builder()
			.baseUrl("https://www.boredapi.com/api/")
			.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
			.build();

		Map block = webClient.get()
			.uri("activity/")
			.retrieve()
			.bodyToMono(Map.class)
			.block();

		return String.valueOf(block.get("activity"));
	}
}
