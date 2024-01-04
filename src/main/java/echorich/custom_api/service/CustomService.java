package echorich.custom_api.service;

import org.springframework.stereotype.Service;

import echorich.open_api.boredapi.service.RecomendAcivityService;
import echorich.open_api.papago.service.TranslateService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CustomService {

	private final RecomendAcivityService recomendAcivityService;
	private final TranslateService translateService;

	public String recomendActivity(String languageOption) {
		String recomendActivity = recomendAcivityService.getRecomendActivity();
		if (languageOption.equals("영어")) {
			return recomendActivity;
		}

		return translateService.translateRequest(recomendActivity, languageOption);
	}
}
