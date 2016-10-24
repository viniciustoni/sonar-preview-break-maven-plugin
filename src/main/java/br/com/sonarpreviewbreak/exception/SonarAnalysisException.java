package br.com.sonarpreviewbreak.exception;

import br.com.sonarpreviewbreak.dto.ennumerated.AnalysisResult;

/**
 * Exception used to break analysis of sonar preview.
 * 
 * @author Vinicius Antonio Gai
 *
 */
public class SonarAnalysisException extends Exception {

	private static final long serialVersionUID = -8486418083317883856L;

	private final String analisysMessage;

	private final AnalysisResult analysisResult;

	public SonarAnalysisException(String analisysMessage, AnalysisResult analysisResult) {
		super();
		this.analisysMessage = analisysMessage;
		this.analysisResult = analysisResult;
	}

	public SonarAnalysisException(String analisysMessage) {
		super();
		this.analisysMessage = analisysMessage;
		this.analysisResult = null;
	}

	public String getAnalisysMessage() {

		return analisysMessage;
	}

	public AnalysisResult getAnalysisResult() {

		return analysisResult;
	}

}
