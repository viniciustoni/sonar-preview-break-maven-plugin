package br.com.sonarpreviewbreak.executor;

import static org.mockito.Mockito.when;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.maven.model.Model;
import org.apache.maven.plugin.logging.Log;
import org.apache.maven.project.MavenProject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.sonarpreviewbreak.dto.IssuesDTO;
import br.com.sonarpreviewbreak.dto.PreviewDTO;
import br.com.sonarpreviewbreak.dto.QueryAnalysisDTO;
import br.com.sonarpreviewbreak.dto.ResultAnalysisDTO;
import br.com.sonarpreviewbreak.dto.ennumerated.AnalysisResult;
import br.com.sonarpreviewbreak.exception.SonarPreviewBreakException;
import br.com.sonarpreviewbreak.utils.SonarUtils;

@Ignore
@RunWith(PowerMockRunner.class)
@PrepareForTest(value =
	{ SonarUtils.class, MavenProject.class, Log.class })
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

	@Test
	public void testDeserializer() throws JsonParseException, JsonMappingException, IOException {

		final ObjectMapper mapper = new ObjectMapper();
		mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss"));

		final InputStream jsonFile = AnalysisExecutorTest.class.getClassLoader().getResourceAsStream("json.json");

		final PreviewDTO previewDTO = mapper.readValue(jsonFile, PreviewDTO.class);

		final List<IssuesDTO> newIssues = previewDTO.getIssues().stream().filter(issue -> issue.isNew()).distinct().collect(Collectors.toList());

		System.out.println(newIssues);

	}

}
