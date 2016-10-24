package br.com.sonarpreviewbreak.dto;

import java.io.Serializable;

import br.com.sonarpreviewbreak.dto.ennumerated.AnalysisResult;

/**
 * Class return the result about sonar analysis.
 * 
 * @author Vinicius Antonio Gai
 *
 */
public class ResultAnalysisDTO implements Serializable {

	private static final long serialVersionUID = 375258350282676650L;

	private final AnalysisResult analysisResult;

	private final String message;

	public ResultAnalysisDTO(AnalysisResult analysisResult, String message) {
		super();
		this.analysisResult = analysisResult;
		this.message = message;
	}

	public AnalysisResult getAnalysisResult() {

		return analysisResult;
	}

	public String getMessage() {

		return message;
	}

	/**
	 * Create success result.
	 * 
	 * @return {@link ResultAnalysisDTO}
	 */
	public static ResultAnalysisDTO createSuccess() {

		return new ResultAnalysisDTO(AnalysisResult.SUCCESS, "Success");
	}

	/**
	 * Create warn result.
	 * 
	 * @return {@link ResultAnalysisDTO}
	 */
	public static ResultAnalysisDTO createWarn(final String message) {

		return new ResultAnalysisDTO(AnalysisResult.WARN, message);
	}

	/**
	 * Create Info result.
	 * 
	 * @return {@link ResultAnalysisDTO}
	 */
	public static ResultAnalysisDTO createInfo(final String message) {

		return new ResultAnalysisDTO(AnalysisResult.INFO, message);
	}

	/**
	 * Create Error result.
	 * 
	 * @return {@link ResultAnalysisDTO}
	 */
	public static ResultAnalysisDTO createError(final String message) {

		return new ResultAnalysisDTO(AnalysisResult.ERROR, message);
	}

	/**
	 * Create custon result.
	 * 
	 * @return {@link ResultAnalysisDTO}
	 */
	public static ResultAnalysisDTO createCustonResult(final AnalysisResult analysisResult, final String message) {

		return new ResultAnalysisDTO(analysisResult, message);
	}

}
