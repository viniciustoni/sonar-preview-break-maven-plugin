# sonar-preview-break

Sonar-preview-break it's a maven plugin to run together the sonar:sonar plugin with preview mode on.

When the sonar:sonar plugin was executing with preview mode, the plugin has a parameter(sonar.report.export.path) to create a Json file with all issues found after execution, so after that sonar-preview-break will read the json file and analysis all new issues(in json file, there is a list of issues, inside all object has a param named with isNew), the analysis is a count about 4 types of severity:

* Vulnerability
* Blockers
* Majors
* Minors

All qualyties gates was configured by parameters on execution of plugin.

## Usage ##

```
Its necessary to you clone the project and compile to use it.

In a fell days I'll configure to sent the plugin to a maven repo.

```

To use plugin is necessary import the plugin on your pom.xml.


```
<build>
	...
	<plugins>
		...
		<plugin>
			<groupId>br.com.sonarpreviewbreak</groupId>
			<artifactId>sonar-preview-break-maven-plugin</artifactId>
			<version>0.1</version>
		</plugin>
	</plugins>
</build>
```

After configuring the plugin, it's just run maven comand with sonar:sonar(preview mode) plugin and sonar-preview-break:sonar-preview-break

```
mvn sonar:sonar sonar-preview-break:sonar-preview-break -Dsonar.analysis.mode=preview -Dsonar.report.export.path=jsonFile.json
```

The parameters *sonar.report.export.path* is necessary to informe the sonar and sonar-break-preview plugin what is the name of json file to generate/analysis.

### Custom parameters

To break maven compilation in case of to many issues founded by severity, it's necessary given the qualities gates to plugin, example, if the count issues is greater than the inform in configuration, the compilation will be break:

```
<build>
	...
	<plugins>
		...
		<plugin>
			<groupId>br.com.sonarpreviewbreak</groupId>
			<artifactId>sonar-preview-break-maven-plugin</artifactId>
			<version>0.1</version>
			<configuration>
					<maxVulnerabilities>0</maxVulnerabilities>
					<maxBlockers>0</maxBlockers>
					<maxMajors>5</maxMajors>
					<maxMinors>20</maxMinors>
			</configuration>
		</plugin>
	</plugins>
</build>
```

# Developers

Auhor: Vinicius Antonio Gai

Since: 25/10/2016

Version: 0.1

# Release notes

0.2 - Show in the log, error, class and line found on analysis

0.1 - New beta version.
