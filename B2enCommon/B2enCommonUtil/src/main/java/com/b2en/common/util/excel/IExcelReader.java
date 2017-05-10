package com.b2en.common.util.excel;

import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;

/**
 * IExcelReader.java 
 *
 * @author ej.park
 * @created 2016. 3. 2.
 *
 */
public interface IExcelReader {
	
	/**
	 * @param fileDir
	 * @return
	 */
	public List<String> getWorkbookPathList(String fileDir);
	
	/**
	 * @param filePath
	 * @param fileName
	 * @return
	 */
	public Workbook getWorkbook(String filePath, String fileName);
	
	/**
	 * @param filePath
	 * @param fileName
	 * @param isDeleteFile
	 * @return
	 */
	public Workbook getWorkbook(String filePath, String fileName, boolean isDeleteFile);
	
	/**
	 * @param fileFullPath
	 * @return
	 */
	public Workbook getWorkbook(String fileFullPath);
	
	/**
	 * @param fileFullPath
	 * @param isDeleteFile
	 * @return
	 */
	public Workbook getWorkbook(String fileFullPath, boolean isDeleteFile);
	
	/**
	 * @param workbook
	 * @return
	 */
	public List<String[]> getWorkbookData(Workbook workbook);
	
	/**
	 * @param workbook
	 * @param sheetIndex
	 * @return
	 */
	public List<String[]> getWorkbookData(Workbook workbook, int sheetIndex);
	
	/**
	 * @param workbook
	 * @param sheetIndex
	 * @param startRowIndex
	 * @return
	 */
	public List<String[]> getWorkbookData(Workbook workbook, int sheetIndex, int startRowIndex);

	/**
	 * @param workbook
	 * @param sheetIndex
	 * @param startRowIndex
	 * @param limitRowIndex
	 * @return
	 */
	public List<String[]> getWorkbookData(Workbook workbook, int sheetIndex, int startRowIndex, int limitRowIndex);
	
	/**
	 * @param filePath
	 * @param fileName
	 * @return
	 */
	public boolean deleteFile(String filePath, String fileName);
	
	/**
	 * @param fileFullPath
	 * @return
	 */
	public boolean deleteFile(String fileFullPath);
	
}
