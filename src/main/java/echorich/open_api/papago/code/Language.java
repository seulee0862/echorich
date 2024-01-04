package echorich.open_api.papago.code;

import echorich.common.ErrorCode;
import echorich.common.exception.customException.BusinessException;
import lombok.Getter;

@Getter
public enum Language {
	ko("한국어"),
	en("영어"),
	ja("일본어"),
	zh_CN("중국어 간체"),
	vi("베트남어"),
	id("인도네시아어"),
	th("태국어"),
	de("독일어"),
	ru("러시아어"),
	es("스페인어"),
	it("이탈리아어"),
	fr("프랑스어");

	private final String languageName;

	Language(String languageName) {
		this.languageName = languageName;
	}

	public static String getTranslateFormat(String languageOption) {
		String format = getFormatFromLanguageOption(languageOption);
		if (format == null) {
			throw new BusinessException(ErrorCode.NOT_SUPPORTED_LANGUAGE);
		}

		return format;
	}

	private static String getFormatFromLanguageOption(String languageOption) {
		for (Language language : Language.values()) {
			if (language.getLanguageName().equalsIgnoreCase(languageOption)) {
				return language.toString();
			}
		}
		return null;
	}
}

