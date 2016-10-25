package br.com.sonarpreviewbreak.executor;

import static org.mockito.Mockito.when;

import java.io.FileNotFoundException;

import org.apache.maven.plugin.logging.Log;
import org.apache.maven.project.MavenProject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import br.com.sonarpreviewbreak.dto.QueryAnalysisDTO;
import br.com.sonarpreviewbreak.dto.ResultAnalysisDTO;
import br.com.sonarpreviewbreak.dto.ennumerated.AnalysisResult;
import br.com.sonarpreviewbreak.exception.SonarPreviewBreakException;
import br.com.sonarpreviewbreak.utils.SonarUtils;

@RunWith(PowerMockRunner.class)
@PrepareForTest(value =
	{ SonarUtils.class })
public class AnalysisExecutorTest {

	private static final String REPORT_PATH_JSON_NO_ISSUE = "junitNoNewIssues.json";

	private static final String REPORT_PATH_JSON_2_ISSUES_TO_ALL_SEVERITY = "junit2NewIssuesForAllSeverety.json";

	private static final Integer QTD_BLOCKERS = 1;

	private static final Integer QTD_VULNERABILITIES = 1;

	private static final Integer QTD_MAJORS = 1;

	private static final Integer QTD_MINORS = 1;

	@Mock
	private Log log;
	
	@Mock
	private MavenProject mavenProject;

	private AnalysisExecutor analysisExecutor;

	@Before
	public void init() {

		// mock static
		PowerMockito.mockStatic(SonarUtils.class);

		// initialize executor
		analysisExecutor = new AnalysisExecutor(log, mavenProject);

	}

	@Test
	public void testProcessAnalysisNoIssues() throws FileNotFoundException, SonarPreviewBreakException {

		// events
		when(SonarUtils.getJsonPreviewFile(mavenProject, REPORT_PATH_JSON_NO_ISSUE))
				.thenReturn(AnalysisExecutorTest.class.getClassLoader().getResourceAsStream(REPORT_PATH_JSON_NO_ISSUE));

		// execute
		final ResultAnalysisDTO resultAnalysisDTO = analysisExecutor
				.processAnalysis(new QueryAnalysisDTO(REPORT_PATH_JSON_NO_ISSUE, QTD_BLOCKERS, QTD_VULNERABILITIES, QTD_MAJORS, QTD_MINORS));

		// asserts
		Assert.assertEquals(AnalysisResult.SUCCESS, resultAnalysisDTO.getAnalysisResult());
	}
	
	@Test
	public void testProcessAnalysisQualityGatesPassed() throws FileNotFoundException, SonarPreviewBreakException {

		// events
		when(SonarUtils.getJsonPreviewFile(mavenProject, REPORT_PATH_JSON_2_ISSUES_TO_ALL_SEVERITY))
				.thenReturn(AnalysisExecutorTest.class.getClassLoader().getResourceAsStream(REPORT_PATH_JSON_2_ISSUES_TO_ALL_SEVERITY));

		// execute
		final ResultAnalysisDTO resultAnalysisDTO = analysisExecutor
				.processAnalysis(new QueryAnalysisDTO(REPORT_PATH_JSON_2_ISSUES_TO_ALL_SEVERITY, 3, null, null, null));

		// asserts
		Assert.assertEquals(AnalysisResult.SUCCESS, resultAnalysisDTO.getAnalysisResult());
	}
	
	@Test
	public void testProcessAnalysisIssues() throws FileNotFoundException, SonarPreviewBreakException {

		// events
		when(SonarUtils.getJsonPreviewFile(mavenProject, REPORT_PATH_JSON_2_ISSUES_TO_ALL_SEVERITY))
				.thenReturn(AnalysisExecutorTest.class.getClassLoader().getResourceAsStream(REPORT_PATH_JSON_2_ISSUES_TO_ALL_SEVERITY));

		// execute
		final ResultAnalysisDTO resultAnalysisDTO = analysisExecutor
				.processAnalysis(new QueryAnalysisDTO(REPORT_PATH_JSON_2_ISSUES_TO_ALL_SEVERITY, QTD_BLOCKERS, QTD_VULNERABILITIES, QTD_MAJORS, QTD_MINORS));

		// asserts
		Assert.assertEquals(AnalysisResult.ERROR, resultAnalysisDTO.getAnalysisResult());
	}

}
