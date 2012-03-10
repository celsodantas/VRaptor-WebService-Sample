package br.com.sample.environment;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import br.com.sample.utils.Util;

public class QueryReader {

	private Document root;
	
	public QueryReader(String fileName) {
		// File file = new File(Util.getProjectPath() + "/WEB-INF/classes/querys/" + fileName + ".xml");
		File file = new File(Util.getProjectPath() + "/classes/querys/" + fileName + ".xml");
		initXMLReader(file);
	}
	
	private void initXMLReader(File file)
	{
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db;
		try 
		{
			db = dbf.newDocumentBuilder();
			root = db.parse(file);
		} 
		catch (ParserConfigurationException e)  { e.printStackTrace(); } 
		catch (SAXException e) 					{ e.printStackTrace(); } 
		catch (IOException e) 					{ e.printStackTrace(); }
	}


	/* *********
	 *  GETTERS 
	 * *********/
	public String getQuery(String id) {
		return root.getElementById(id).getTextContent();
	}

}
