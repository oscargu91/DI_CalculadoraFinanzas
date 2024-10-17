package com.carballeira.aplicacion.model;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class OperacionesBBDDXML {

	private final static String PATH_XML="src/application/basededatos.xml";
	private final static String NODE="usuario";
	
	public static NodeList getNodesXML() {
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder;
		try {
			docBuilder = docFactory.newDocumentBuilder();

			try {
				Document doc = docBuilder.parse(new File(PATH_XML));
				doc.getDocumentElement().normalize();

				return doc.getElementsByTagName(NODE);

			} catch (SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}
}
