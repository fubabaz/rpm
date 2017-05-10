package com.b2en.common.util.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.b2en.common.util.exception.ExcelFileException;

/**
 * ExcelReaderImpl.java 
 *
 * @author ej.park
 * @created 2016. 3. 2.
 *
 */
public class ExcelReaderImpl implements IExcelReader {

	@Override
	public List<String> getWorkbookPathList(String fileDir){
		List<String> workbookList = new ArrayList<String>();
		File dir = new File(fileDir);
		if(dir.isDirectory()){
			File[] excelFiles = dir.listFiles(new ExcelExtentionFilenameFilter());
			for(File file : excelFiles){
				workbookList.add(file.getAbsolutePath());
			}
		}
		return workbookList;
	}
	
	@Override
	public Workbook getWorkbook(String filePath, String fileName) {
		return getWorkbook(filePath+File.separator+fileName, false);
	}
	
	@Override
	public Workbook getWorkbook(String filePath, String fileName, boolean isDeleteFile) {
		return getWorkbook(filePath+File.separator+fileName, isDeleteFile);
	}
	
	@Override
	public Workbook getWorkbook(String fileFullPath){
		return getWorkbook(fileFullPath, false);
	}
	
	@Override
	public Workbook getWorkbook(String fileFullPath, boolean isDeleteFile) {
		FileInputStream fis = null;
		Workbook workbook = null;
		try {
			fis = new FileInputStream(fileFullPath);
			workbook = WorkbookFactory.create(fis);
			fis.close();
			if(isDeleteFile){
				File file = new File(fileFullPath);
				if(file.exists()){
					file.delete();
				}
			}
		} catch (FileNotFoundException e) {
			throw new ExcelFileException(fileFullPath, e);
		} catch (IOException e) {
			throw new ExcelFileException(fileFullPath, e);
		} catch (InvalidFormatException e) {
			throw new ExcelFileException(fileFullPath, e);
		}finally {
			if(fis!=null){
				try {
					fis.close();
				} catch (IOException e) {
					//Ignore.
				}
			}
		}
		return workbook;
	}

	@Override
	public List<String[]> getWorkbookData(Workbook workbook) {
		return getWorkbookData(workbook, 0);
	}
	
	@Override
	public List<String[]> getWorkbookData(Workbook workbook, int sheetIndex) {
		return getWorkbookData(workbook, sheetIndex, 0);
	}

	@Override
	public List<String[]> getWorkbookData(Workbook workbook, int sheetIndex, int startRowIndex) {
		return getWorkbookData(workbook, sheetIndex, startRowIndex, 0);
	}
	
	@Override
	public List<String[]> getWorkbookData(Workbook workbook, int sheetIndex, int startRowIndex, int limitRowIndex){
		List<String[]> workbookDataList = null;
		if(workbook instanceof XSSFWorkbook){
			workbookDataList = getExcelData((XSSFWorkbook)workbook, sheetIndex, startRowIndex, limitRowIndex);
		}else if(workbook instanceof HSSFWorkbook){
			workbookDataList = getExcelData((HSSFWorkbook)workbook, sheetIndex, startRowIndex, limitRowIndex);
		}else{
			throw new ExcelFileException("Workbook type is not XSSF or HSSF.");
		}
		return workbookDataList;
	}
	
	private List<String[]> getExcelData(XSSFWorkbook workbook, int sheetIndex, int startRowIndex, int limitRowIndex){
		List<String[]> rowDataList = new ArrayList<String[]>();
		if(workbook!=null && workbook.getNumberOfSheets()>sheetIndex){
			XSSFSheet sheet = workbook.getSheetAt(sheetIndex);
			if(sheet!=null){
				for(int i=startRowIndex; i<sheet.getPhysicalNumberOfRows(); i++){
					XSSFRow row = sheet.getRow(i);
					if(row!=null){
						String[] rowStr = new String[row.getPhysicalNumberOfCells()];
						for(int j=0; j<row.getPhysicalNumberOfCells(); j++){
							XSSFCell cell = row.getCell(j);
							if(cell!=null){
								rowStr[j] = ExcelCellValueGetter.getCellValue(cell);
							}else{
								rowStr[j] = "";
							}
						}
						rowDataList.add(rowStr);
						if(limitRowIndex!=0 && i>limitRowIndex){
							break;
						}
					}
				}
			}
		}
		return rowDataList;
	}
	
	private List<String[]> getExcelData(HSSFWorkbook workbook, int sheetIndex, int startRowIndex, int limitRowIndex){
		List<String[]> rowDataList = new ArrayList<String[]>();
		if(workbook!=null && workbook.getNumberOfSheets()>sheetIndex){
			HSSFSheet sheet = workbook.getSheetAt(sheetIndex);
			if(sheet!=null){
				for(int i=startRowIndex; i<sheet.getPhysicalNumberOfRows(); i++){
					HSSFRow row = sheet.getRow(i);
					if(row!=null){
						String[] rowStr = new String[row.getPhysicalNumberOfCells()];
						for(int j=0; j<row.getPhysicalNumberOfCells(); j++){
							HSSFCell cell = row.getCell(j);
							if(cell!=null){
								rowStr[j] = ExcelCellValueGetter.getCellValue(cell);
							}else{
								rowStr[j] = "";
							}
						}
						rowDataList.add(rowStr);
						if(limitRowIndex!=0 && i>limitRowIndex){
							break;
						}
					}
				}
			}
		}
		return rowDataList;
	}

	@Override
	public boolean deleteFile(String filePath, String fileName) {
		return deleteFile(filePath+File.separator+fileName);
	}

	@Override
	public boolean deleteFile(String fileFullPath) {
		File file = new File(fileFullPath);
		if(file.exists()){
			return file.delete();
		}
		return false;
	}
}
