package br.com.sample.environment.support;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import br.com.sample.environment.support.EnvironmentFinder.ENV;
import br.com.sample.utils.Util;

public class EnvironmentReader {
	private static final String file_name = "environment.xml";
	private static Document root;
	
	static {
		File file = new File(getProjectPath() + "/WEB-INF/classes/" + file_name);
		
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		
		DocumentBuilder db;
		try 
		{
			db = dbf.newDocumentBuilder();
			root = db.parse(file);
			root.getDocumentElement().normalize();
		} 
		catch (ParserConfigurationException e) 	{ e.printStackTrace(); } 
		catch (SAXException e) 					{ e.printStackTrace(); } 
		catch (IOException e) 					{ e.printStackTrace(); }
	}
	
	public static Node getNodeByTag(String element)  { 
		return getEnvironmentNamespace().getElementsByTagName(element).item(0); 
	}
	
	public static Element getEnvironmentNamespace ()  {
		return (Element) root.getElementsByTagName(getAppEnvironment().toString().toLowerCase()).item(0);
	}
	
	public static ENV getAppEnvironment () { 
		return EnvironmentFinder.getEnvironment(); 
	}
	
	public static String getProjectPath() { 
		return Util.getProjectPath();
	}
}
