/*
 * Copyright 2004-2016 (C) B2EN, Inc. All Rights Reserved.
 * This software is the proprietary information of B2EN.
 */
package com.b2en.common.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.b2en.common.util.exception.DocumentFileException;

/**
 * DocumentUtil
 * 
 * @author ej.park
 *
 */
public class DocumentUtil {
	
	/**
	 * 
	 * @param path
	 * @return
	 */
	public static Document getDocument(String path){
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		Document doc = null;
		try {
			InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
			DocumentBuilder db = dbf.newDocumentBuilder();
			doc = db.parse(is);
			doc.getDocumentElement().normalize();
		} catch (ParserConfigurationException e) {
			throw new DocumentFileException("File Path>>"+path, e);
		} catch (FileNotFoundException e) {
			throw new DocumentFileException("File Path>>"+path, e);
		} catch (SAXException e) {
			throw new DocumentFileException("File Path>>"+path, e);
		} catch (IOException e) {
			throw new DocumentFileException("File Path>>"+path, e);
		}
		return doc;
	}
}
