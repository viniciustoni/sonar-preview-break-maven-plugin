package br.com.sonarpreviewbreak.dto;

import java.io.Serializable;

/**
 * Class with all informations about execution of analysis sonar on project.
 * 
 * @author Vinicius Antonio Gai
 *
 */
public class QueryAnalysisDTO implements Serializable {

	private static final long serialVersionUID = -5010169850321120772L;

	private final String reportPath;

	private final Integer qtdBlockers;

	private final Integer qtdVulnerabilities;

	private final Integer qtdMajors;

	private final Integer qtdMinors;

	/**
	 * Constructor.
	 * 
	 * @param reportPath
	 *            Path to preview json
	 * @param qtdBlockers
	 *            Max blockers issues acceptable to compile.
	 * @param qtdVulnerabilities
	 *            vulnerabilities majors issues acceptable to compile.
	 * @param qtdMajors
	 *            Max majors issues acceptable to compile.
	 * @param qtdMinors
	 *            Max minors issues acceptable to compile.
	 */
	public QueryAnalysisDTO(final String reportPath, final Integer qtdBlockers, final Integer qtdVulnerabilities, final Integer qtdMajors,
			final Integer qtdMinors) {
		super();
		this.reportPath = reportPath;
		this.qtdBlockers = qtdBlockers;
		this.qtdVulnerabilities = qtdVulnerabilities;
		this.qtdMajors = qtdMajors;
		this.qtdMinors = qtdMinors;
	}

	public String getReportPath() {

		return reportPath;
	}

	public Integer getQtdMajors() {

		return qtdMajors;
	}

	public Integer getQtdBlockers() {

		return qtdBlockers;
	}

	public Integer getQtdMinors() {

		return qtdMinors;
	}

	public Integer getQtdVulnerabilities() {

		return qtdVulnerabilities;
	}

}
