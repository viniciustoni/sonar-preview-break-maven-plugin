package br.com.sonarpreviewbreak.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * Java mapper to json file.
 * 
 * @author Vinicius Antonio Gai
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PreviewDTO implements Serializable {

	private static final long serialVersionUID = 5038911633170297706L;

	@JsonProperty("version")
	private String version;

	@JsonProperty("issues")
	private List<IssuesDTO> issues;

	public List<IssuesDTO> getIssues() {

		return issues;
	}

	public void setIssues(List<IssuesDTO> issues) {

		this.issues = issues;
	}

	public String getVersion() {

		return version;
	}

	public void setVersion(String version) {

		this.version = version;
	}

}
