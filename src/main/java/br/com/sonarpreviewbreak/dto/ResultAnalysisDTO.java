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

	@Override
	public int hashCode() {

		final int prime = 31;
		int result = 1;
		result = prime * result + ((analysisResult == null) ? 0 : analysisResult.hashCode());
		result = prime * result + ((message == null) ? 0 : message.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {

		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		ResultAnalysisDTO other = (ResultAnalysisDTO) obj;
		if (analysisResult != other.analysisResult) return false;
		if (message == null) {
			if (other.message != null) return false;
		} else if (!message.equals(other.message)) return false;
		return true;
	}

}
