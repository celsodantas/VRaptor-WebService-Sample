package br.com.sample.environment;

import br.com.sample.environment.support.EnvironmentReader;

public class Environment {

	public static String getProjectPath() {
		return EnvironmentReader.getProjectPath();
	}
	
	public static String getDatabaseName() {
		return EnvironmentReader.getNodeByTag("").getTextContent();
	}
	
}
