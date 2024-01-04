package echorich.http_tool;

import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

public class WebCleintManager {

	public static Map getBlock(WebClient webClient, String uri) {
		return webClient.get()
			.uri(uri)
			.retrieve()
			.bodyToMono(Map.class)
			.block();
	}

	public static WebClient getWebClient(String baseUrl) {
		WebClient webClient = WebClient.builder()
			.baseUrl(baseUrl)
			.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
			.build();
		return webClient;
	}
}
