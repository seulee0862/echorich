package echorich.open_api.papago.service;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import echorich.open_api.papago.code.Language;

/**
 * document
 * -> https://developers.naver.com/docs/papago/papago-nmt-example-code.md
 */
@Service
public class TranslateService {

	@Value("${naver.client.id}")
	String clientId;
	@Value("${naver.client.secret}")
	String clientSecret;
	@Value("${naver.url}")
	String apiUrl;

	public String extractText(String responseBody) {
		String translatedText = null;

		try {
			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode jsonNode = objectMapper.readTree(responseBody);

			translatedText = jsonNode
				.get("message")
				.get("result")
				.get("translatedText")
				.asText();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return translatedText;
	}

	/**
	 *
	 * @param toTranslateText - 번역할 문장
	 * @param languageOption - 번역할 포맷
	 * @return - 번역된 문장
	 */
	public String translateRequest(String toTranslateText, String languageOption) {
		String translateFormat = Language.getTranslateFormat(languageOption);
		String text;
		try {
			text = URLEncoder.encode(toTranslateText, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("인코딩 실패", e);
		}

		Map<String, String> requestHeaders = new HashMap<>();
		requestHeaders.put("X-Naver-Client-Id", clientId);
		requestHeaders.put("X-Naver-Client-Secret", clientSecret);

		String responseBody = post(apiUrl, requestHeaders, text, translateFormat);

		return extractText(responseBody);
	}

	private static String post(String apiUrl, Map<String, String> requestHeaders, String text, String translateFormat){
		HttpURLConnection con = connect(apiUrl);
		String postParams = "source=en&target="+ translateFormat +"&text=" + text;
		try {
			con.setRequestMethod("POST");
			for(Map.Entry<String, String> header :requestHeaders.entrySet()) {
				con.setRequestProperty(header.getKey(), header.getValue());
			}

			con.setDoOutput(true);
			try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
				wr.write(postParams.getBytes());
				wr.flush();
			}

			int responseCode = con.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 응답
				return readBody(con.getInputStream());
			} else {  // 에러 응답
				return readBody(con.getErrorStream());
			}
		} catch (IOException e) {
			throw new RuntimeException("API 요청과 응답 실패", e);
		} finally {
			con.disconnect();
		}
	}

	private static HttpURLConnection connect(String apiUrl){
		try {
			URL url = new URL(apiUrl);
			return (HttpURLConnection)url.openConnection();
		} catch (MalformedURLException e) {
			throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
		} catch (IOException e) {
			throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
		}
	}

	private static String readBody(InputStream body){
		InputStreamReader streamReader = new InputStreamReader(body);

		try (BufferedReader lineReader = new BufferedReader(streamReader)) {
			StringBuilder responseBody = new StringBuilder();

			String line;
			while ((line = lineReader.readLine()) != null) {
				responseBody.append(line);
			}

			return responseBody.toString();
		} catch (IOException e) {
			throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e);
		}
	}
}
