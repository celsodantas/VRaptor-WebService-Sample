package br.com.sample.environment.support;

import java.util.logging.Logger;

public class EnvironmentFinder {

	private static Logger logger = Logger.getLogger( EnvironmentFinder.class.getName() );
	
	public static enum ENV { DEVELOPMENT, PRODUCTION, TEST }
	private static final String ENVIRONMENT_NAME = "SAMPLE_CONFIG"; 

	public static ENV getEnvironment() {
		String environment = System.getenv(ENVIRONMENT_NAME);
		
		if (environment == null) {
			noEnvironmentPathError();
			// FIXME subir exceçao!!!
			return null;
		}
		
		
		if (environment.toUpperCase().equals(ENV.PRODUCTION.toString().toUpperCase()))
			return ENV.PRODUCTION;
		else if (environment.toUpperCase().equals(ENV.TEST.toString().toUpperCase()))
			return ENV.TEST;
		else
			return ENV.DEVELOPMENT;
	};
	
	private static void noEnvironmentPathError() {
		logger.severe("A variavel de sistema "+ENVIRONMENT_NAME+" não foi configurada! \n");
		printConfigurationHints();
	}
	
	private static void printConfigurationHints() {
		logger.severe("  ----------------------------  " +
					  "   Verifique se o servidor atende a todos os passos abaixo: \n" +
					  "   1. Foi criada a variavel de ambiente com o nome " + ENVIRONMENT_NAME + " !\n" +
					  "   2. Certifique que na variavel existe o nome do ambiente [development, production, test] \n");
	}
	
}
