package br.com.sonarpreviewbreak;

import java.text.MessageFormat;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

import br.com.sonarpreviewbreak.dto.QueryAnalysisDTO;
import br.com.sonarpreviewbreak.dto.ResultAnalysisDTO;
import br.com.sonarpreviewbreak.exception.SonarPreviewBreakException;
import br.com.sonarpreviewbreak.executor.AnalysisExecutor;

/**
 * Plugin to break maven compile in case of new issues on sonar analysis.
 * 
 * @author Vinicius Antonio Gai
 * @since 22/10/2016
 *
 */
@Mojo(name = "sonar-preview-break", aggregator = true)
public class SonarPreviewBreakMojo extends AbstractMojo {

	@Parameter(property = "sonar.report.export.path", required = true)
	protected String reportPath;

	@Parameter(property = "sonar.preview.break.qtdVulnerabilities")
	protected String qtdVulnerabilities;

	@Parameter(property = "sonar.preview.break.qtdBlockers")
	protected String qtdBlockers;

	@Parameter(property = "sonar.preview.break.qtdMajors")
	protected String qtdMajors;

	@Parameter(property = "sonar.preview.break.qtdMinors")
	protected String qtdMinors;

	@Override
	public void execute() throws MojoExecutionException, MojoFailureException {

		try {
			// Load mavenProject
			final MavenProject mavenProject = getMavenProject();

			getLog().info(MessageFormat.format("analysis maven project {0}:{1}:{2}", mavenProject.getGroupId(), mavenProject.getArtifactId(),
					mavenProject.getVersion()));

			// process preview.
			final AnalysisExecutor analysisExecutor = new AnalysisExecutor(getLog(), mavenProject);
			final ResultAnalysisDTO resultAnalysisDTO = analysisExecutor.processAnalysis(createQueryanalysisDTO());

			// process the result
			processResult(resultAnalysisDTO);

		} catch (SonarPreviewBreakException e) {
			throw new MojoExecutionException("Problems to process the analysis.", e);
		}
	}

	@SuppressWarnings("rawtypes")
	private MavenProject getMavenProject() throws MojoExecutionException {

		final Map pluginContext = this.getPluginContext();
		final Object project = pluginContext.get("project");
		if (!MavenProject.class.isInstance(project)) {
			throw new MojoExecutionException("Problems to get maven project.");
		}
		return (MavenProject) project;
	}

	/**
	 * Create {@link QueryAnalysisDTO}
	 * 
	 * @return {@link QueryAnalysisDTO}
	 */
	private QueryAnalysisDTO createQueryanalysisDTO() {

		// Aux
		Integer vulnerabilities = null;
		Integer blockers = null;
		Integer majors = null;
		Integer minors = null;

		// convertions
		getLog().debug("qtdVulnerabilities : " + qtdVulnerabilities);
		if (StringUtils.isNotBlank(qtdVulnerabilities)) {
			vulnerabilities = Integer.valueOf(qtdVulnerabilities);
		}

		getLog().debug("qtdBlockers : " + qtdBlockers);
		if (StringUtils.isNotBlank(qtdBlockers)) {
			blockers = Integer.valueOf(qtdBlockers);
		}

		getLog().debug("qtdMajors : " + qtdMajors);
		if (StringUtils.isNotBlank(qtdMajors)) {
			majors = Integer.valueOf(qtdMajors);
		}

		getLog().debug("qtdMinors : " + qtdMinors);
		if (StringUtils.isNotBlank(qtdMinors)) {
			minors = Integer.valueOf(qtdMinors);
		}

		return new QueryAnalysisDTO(reportPath, blockers, vulnerabilities, majors, minors);

	}

	/**
	 * Process analysis result.
	 * 
	 * @param ResultAnalysisDTO
	 */
	private void processResult(final ResultAnalysisDTO resultAnalysisDTO) throws MojoExecutionException {

		switch (resultAnalysisDTO.getAnalysisResult()) {
		case ERROR:
			getLog().error(resultAnalysisDTO.getMessage());
			throw new MojoExecutionException("Build does not passed on sonar analysis. " + resultAnalysisDTO.getMessage());
		case WARN:
			getLog().info("Build with warnings. " + resultAnalysisDTO.getMessage());
			break;
		case INFO:
			getLog().info("Build with some info messages. " + resultAnalysisDTO.getMessage());
			break;
		case SUCCESS:
			getLog().info("Build ok.");
			break;
		default:
			throw new MojoExecutionException("Unknown result state encountered: " + resultAnalysisDTO.getAnalysisResult());
		}
	}

}
