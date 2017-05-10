package com.b2en.common.util.excel;

import java.io.File;
import java.io.FilenameFilter;

/**
 * ExcelExtentionFilenameFilter.java 
 *
 * @author ej.park
 * @created 2016. 3. 2.
 *
 */
public class ExcelExtentionFilenameFilter implements FilenameFilter {

	@Override
	public boolean accept(File dir, String name) {
		if(name.endsWith(".xls") || name.endsWith(".xlsx")){
			return true;
		}
		return false;
	}

}
