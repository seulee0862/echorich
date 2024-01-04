package echorich.open_api.boredapi.service;

import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import echorich.http_tool.WebCleintManager;

@Service
public class RecomendAcivityService {

	public String getRecomendActivity() {
		WebClient webClient = WebCleintManager.getWebClient("https://www.boredapi.com/api/");
		Map block = WebCleintManager.getBlock(webClient, "activity/");

		return String.valueOf(block.get("activity"));
	}
}
