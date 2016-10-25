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

	private final Integer maxBlockers;

	private final Integer maxVulnerabilities;

	private final Integer maxMajors;

	private final Integer maxMinors;

	/**
	 * Constructor.
	 * 
	 * @param reportPath
	 *            Path to preview json
	 * @param maxBlockers
	 *            Max blockers issues acceptable to compile.
	 * @param maxVulnerabilities
	 *            vulnerabilities majors issues acceptable to compile.
	 * @param maxMajors
	 *            Max majors issues acceptable to compile.
	 * @param maxMinors
	 *            Max minors issues acceptable to compile.
	 */
	public QueryAnalysisDTO(final String reportPath, final Integer maxBlockers, final Integer maxVulnerabilities, final Integer maxMajors,
			final Integer maxMinors) {
		super();
		this.reportPath = reportPath;
		this.maxBlockers = maxBlockers;
		this.maxVulnerabilities = maxVulnerabilities;
		this.maxMajors = maxMajors;
		this.maxMinors = maxMinors;
	}

	public String getReportPath() {

		return reportPath;
	}

	public Integer getMaxMajors() {

		return maxMajors;
	}

	public Integer getMaxBlockers() {

		return maxBlockers;
	}

	public Integer getMaxMinors() {

		return maxMinors;
	}

	public Integer getMaxVulnerabilities() {

		return maxVulnerabilities;
	}

}
