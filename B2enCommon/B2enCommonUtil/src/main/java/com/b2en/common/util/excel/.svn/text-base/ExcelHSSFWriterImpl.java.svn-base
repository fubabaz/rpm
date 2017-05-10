package com.b2en.common.util.excel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;

import com.b2en.common.util.exception.ExcelFileException;

/**
 * ExcelHSSFWriterImpl.java 
 *
 * @author ej.park
 * @created 2016. 3. 2.
 *
 */
public class ExcelHSSFWriterImpl implements IExcelWriter {
	
	private HSSFWorkbook wb;

	@Override
	public Workbook getWorkbook(List<ExcelListSheetInfo> excelListSheetInfoList) {
		this.wb = new HSSFWorkbook();
		for(ExcelListSheetInfo excelListSheetInfo : excelListSheetInfoList){
			makeSheet(excelListSheetInfo);
		}
		return this.wb;
	}
	
	@Override
	public Workbook getWorkbook(ExcelListSheetInfo excelListSheetInfo) {
		this.wb = new HSSFWorkbook();
		makeSheet(excelListSheetInfo);
		return this.wb;
	}
	
	private void makeSheet(ExcelListSheetInfo excelListSheetInfo){
		HSSFSheet sheet = wb.createSheet(excelListSheetInfo.getSheetName());
		
		// 1. Generate Cell Formatter.
		ExcelHSSFCellStyleFormatter formatter = new ExcelHSSFCellStyleFormatter();
		
		// 2. Set Title Row. 
		HSSFCellStyle titleCellStyle = formatter.getTitleStyle(this.wb);
		String[] titles = excelListSheetInfo.getTitles();
		HSSFRow titleRow = sheet.createRow(0);
		for(int i=0; i<titles.length; i++){
			HSSFCell titleCell = titleRow.createCell(i);
			titleCell.setCellValue(titles[i]);
			titleCell.setCellStyle(titleCellStyle);
		}
		titleRow.setHeightInPoints(2*sheet.getDefaultRowHeightInPoints());
		
		// 3. Set Data Rows.
		int rowSize = excelListSheetInfo.getList().size();
		HSSFCellStyle contentCellStyle = formatter.getListStyle(this.wb);
		short[] alignment = excelListSheetInfo.getAlignment();
		Map<Short, HSSFCellStyle> alignMap = new HashMap<Short, HSSFCellStyle>();
		for(int i=0; i<rowSize; i++){
			String[] cells = excelListSheetInfo.getList().get(i);
			HSSFRow row = sheet.createRow(i+1);
			for(int j=0; j<cells.length; j++){
				HSSFCell cell = row.createCell(j);
				cell.setCellValue(cells[j]);
				if(alignment!=null && alignment.length>j){
					HSSFCellStyle cellStyle = alignMap.get(alignment[j]);
					if(cellStyle==null){
						cellStyle = formatter.getListStyle(this.wb, alignment[j]);
						alignMap.put(alignment[j], cellStyle);
					}
					cell.setCellStyle(cellStyle);
				}else{
					cell.setCellStyle(contentCellStyle);					
				}
			}
		}
		
		// 4. Set Column Width.
		if(excelListSheetInfo.getWidth()!=null 
				&& excelListSheetInfo.getWidth().length == excelListSheetInfo.getTitles().length){
			int[] width = excelListSheetInfo.getWidth();
			for(int i=0; i<width.length; i++){
				sheet.setColumnWidth(i, width[i]*256);
			}
		}else{
			for(int i=0; i<titles.length; i++){
				sheet.autoSizeColumn(i);
			}
		}
	}
	
	public void writeExcel(String filePath, String fileName, Workbook workbook){
		FileOutputStream out = null;
		try {
			if(filePath==null || filePath.isEmpty()){
				throw new ExcelFileException("FilePath is null."); 
			}
			if(fileName==null || fileName.isEmpty()){
				fileName = "excel";
			}
			File dir = new File(filePath);
			if(!dir.exists()){
				dir.mkdirs();
			}
			File file = new File(filePath+File.separator+fileName+".xls");
			out = new FileOutputStream(file);
			workbook.write(out);
			out.close();
		}catch(FileNotFoundException e) {
			throw new ExcelFileException(filePath+File.separator+fileName+".xls", e); 
		}catch(IOException e) {
			throw new ExcelFileException(filePath+File.separator+fileName+".xls", e);	
		}finally{
			if(out!=null){
				try {
					out.close();
				} catch (IOException e) {
					//Ignore.
				}
			}
		}
	}
	
	public void writeExcel(File file, Workbook workbook){
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(file);
			workbook.write(out);
			out.close();
		}catch(FileNotFoundException e) {
			throw new ExcelFileException(file.getAbsolutePath(), e); 
		}catch(IOException e) {
			throw new ExcelFileException(file.getAbsolutePath(), e);	
		}finally{
			if(out!=null){
				try {
					out.close();
				} catch (IOException e) {
					//Ignore.
				}
			}
		}
	}

}
