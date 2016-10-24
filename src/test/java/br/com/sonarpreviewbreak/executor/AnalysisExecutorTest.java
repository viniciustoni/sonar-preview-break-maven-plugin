package br.com.sonarpreviewbreak.executor;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.maven.plugin.logging.Log;
import org.apache.maven.project.MavenProject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.sonarpreviewbreak.dto.IssuesDTO;
import br.com.sonarpreviewbreak.dto.PreviewDTO;
import br.com.sonarpreviewbreak.utils.SonarUtils;

@RunWith(PowerMockRunner.class)
@PrepareForTest(value =
	{ SonarUtils.class })
public class AnalysisExecutorTest {

	@Mock
	private Log log;

	@Mock
	private MavenProject mavenProject;

	private AnalysisExecutor analysisExecutor;
	
	@Before
	public void init() {
		analysisExecutor = new AnalysisExecutor(log, mavenProject);
	}
	
	@Test
	public void testProcessAnalysis() {

		
		
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
