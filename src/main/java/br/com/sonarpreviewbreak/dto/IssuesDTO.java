package br.com.sonarpreviewbreak.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.sonarpreviewbreak.dto.ennumerated.Severity;

/**
 * DTO to mapper between json file and java class, mapper to issues founded in
 * analysis.
 * 
 * @author Vinicius Antonio Gai
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class IssuesDTO implements Serializable {

	private static final long serialVersionUID = -1208321430635343913L;

	@JsonProperty("key")
	private String key;

	@JsonProperty("component")
	private String component;

	@JsonProperty("message")
	private String message;

	@JsonProperty("severity")
	private Severity severity;

	@JsonProperty("rule")
	private String rule;

	@JsonProperty("status")
	private String status;

	@JsonProperty("isNew")
	private boolean isNew;

	@JsonProperty("assignee")
	private String assignee;

	@JsonProperty("creationDate")
	private Date creationDate;

	public String getKey() {

		return key;
	}

	public void setKey(String key) {

		this.key = key;
	}

	public String getComponent() {

		return component;
	}

	public void setComponent(String component) {

		this.component = component;
	}

	public String getMessage() {

		return message;
	}

	public void setMessage(String message) {

		this.message = message;
	}

	public Severity getSeverity() {

		return severity;
	}

	public void setSeverity(Severity severity) {

		this.severity = severity;
	}

	public String getRule() {

		return rule;
	}

	public void setRule(String rule) {

		this.rule = rule;
	}

	public String getStatus() {

		return status;
	}

	public void setStatus(String status) {

		this.status = status;
	}

	public boolean isNew() {

		return isNew;
	}

	public void setNew(boolean isNew) {

		this.isNew = isNew;
	}

	public String getAssignee() {

		return assignee;
	}

	public void setAssignee(String assignee) {

		this.assignee = assignee;
	}

	public Date getCreationDate() {

		return creationDate;
	}

	public void setCreationDate(Date creationDate) {

		this.creationDate = creationDate;
	}

}
