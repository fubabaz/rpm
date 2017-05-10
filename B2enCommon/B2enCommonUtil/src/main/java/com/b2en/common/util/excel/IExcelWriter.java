package com.b2en.common.util.excel;

import java.io.File;
import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;

/**
 * IExcelWriter.java 
 *
 * @author ej.park
 * @created 2016. 3. 2.
 *
 */
public interface IExcelWriter {
	
	/**
	 * @param excelListSheetInfoList
	 * @return
	 */
	public Workbook getWorkbook(List<ExcelListSheetInfo> excelListSheetInfoList); 
	
	/**
	 * @param excelListSheetInfo
	 * @return
	 */
	public Workbook getWorkbook(ExcelListSheetInfo excelListSheetInfo); 
	
	/**
	 * @param filePath
	 * @param fileName
	 * @param workbook
	 */
	public void writeExcel(String filePath, String fileName, Workbook workbook);
	
	/**
	 * @param file
	 * @param workbook
	 */
	public void writeExcel(File file, Workbook workbook);
}
