package br.com.sonarpreviewbreak.exception;

/**
 * Exception for project
 * 
 * @author Vinicius Antonio Gai
 *
 */
public class SonarPreviewBreakException extends Exception {

	private static final long serialVersionUID = -8486418083317883856L;

	public SonarPreviewBreakException() {
		super();
	}

	public SonarPreviewBreakException(String message, Throwable cause) {
		super(message, cause);
	}

	public SonarPreviewBreakException(String message) {
		super(message);
	}

	public SonarPreviewBreakException(Throwable cause) {
		super(cause);
	}

}
