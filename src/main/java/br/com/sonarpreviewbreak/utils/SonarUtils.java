package br.com.sonarpreviewbreak.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.apache.maven.project.MavenProject;

/**
 * Utils.
 * 
 * @author Vinicius Antonio Gai
 *
 */
public final class SonarUtils {

	/**
	 * Constructor
	 */
	private SonarUtils() {
		// Do nothing.
	}

	/**
	 * Get json preview file to analysis
	 * 
	 * @param mavenProject
	 * @param jsonFileName
	 * @return
	 * @throws FileNotFoundException 
	 */
	public static InputStream getJsonPreviewFile(final MavenProject mavenProject, final String jsonFileName) throws FileNotFoundException {

		Validate.notNull(mavenProject, "Maven project is mandatory.");
		Validate.notNull(jsonFileName, "Json file name is mandatory.");
		
		return new FileInputStream(new File(getSonarWorkDir(mavenProject), jsonFileName));
		
	}
	
	/**
	 * Get sonar work directory
	 * 
	 * @param mavenProject
	 * @return
	 */
	public static File getSonarWorkDir(final MavenProject mavenProject) {

		return new File(getBuildDir(mavenProject), "sonar");
	}

	/**
	 * Get project's builddir.
	 * 
	 * @param mavenProject
	 * @return
	 */
	public static File getBuildDir(final MavenProject mavenProject) {

		return resolvePath(mavenProject.getBuild().getDirectory(), mavenProject.getBasedir());
	}

	/**
	 * Resolve path.
	 * 
	 * @param path
	 *            Path
	 * @param basedir
	 *            Base dir.
	 * @return File
	 */
	public static File resolvePath(final String path, final File basedir) {

		if (path != null) {
			File file = new File(StringUtils.trim(path));
			if (!file.isAbsolute()) {
				file = new File(basedir, path).getAbsoluteFile();
			}
			return file;
		}
		return null;
	}

}
