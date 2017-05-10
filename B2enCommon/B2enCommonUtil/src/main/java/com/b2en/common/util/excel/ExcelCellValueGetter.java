package com.b2en.common.util.excel;

import java.text.SimpleDateFormat;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFCell;

/**
 * ExcelCellValueGetter.java 
 *
 * @author ej.park
 * @created 2016. 3. 2.
 *
 */
public class ExcelCellValueGetter {

	public static String getCellValue(Cell cell){
		if(cell instanceof XSSFCell){
			return getCellValue((XSSFCell)cell);
		}else if(cell instanceof HSSFCell){
			return getCellValue((HSSFCell)cell);
		}
		return null;
	}
	
	public static String getCellValue(XSSFCell cell){
		String value = "";
		switch(cell.getCellType()){
			case XSSFCell.CELL_TYPE_BLANK :
				break;
			case XSSFCell.CELL_TYPE_BOOLEAN : 
				value = String.valueOf(cell.getBooleanCellValue());
				break;
			case XSSFCell.CELL_TYPE_ERROR :
				value = cell.getErrorCellString();
				break;
			case XSSFCell.CELL_TYPE_FORMULA :
				if(!cell.toString().isEmpty()){
					FormulaEvaluator evaluator = cell.getSheet().getWorkbook().getCreationHelper().createFormulaEvaluator();
					int cellType = evaluator.evaluateFormulaCell(cell);
					if(cellType==XSSFCell.CELL_TYPE_NUMERIC){
						value = getNumberTypeValue(cell.getNumericCellValue());
					}else if(cellType==XSSFCell.CELL_TYPE_STRING){
						value = cell.getStringCellValue();
					}else if(cellType==XSSFCell.CELL_TYPE_BOOLEAN){
						value = String.valueOf(cell.getBooleanCellValue());
					}else{
						value = cell.getCellFormula();
					}
				}
				break;
			case XSSFCell.CELL_TYPE_NUMERIC :
				if(HSSFDateUtil.isCellDateFormatted(cell)){
					SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
					value = dateFormatter.format(cell.getDateCellValue());
				}else{
					value =  getNumberTypeValue(cell.getNumericCellValue());
				}
				break;
			case XSSFCell.CELL_TYPE_STRING :
				value = cell.getStringCellValue();
				break;
			default : 
		}
		return value;
	}
	
	public static String getCellValue(HSSFCell cell){
		String value = "";
		switch(cell.getCellType()){
			case HSSFCell.CELL_TYPE_BLANK :
				break;
			case HSSFCell.CELL_TYPE_BOOLEAN : 
				value = String.valueOf(cell.getBooleanCellValue());
				break;
			case HSSFCell.CELL_TYPE_ERROR :
				value = String.valueOf(cell.getErrorCellValue());
				break;
			case HSSFCell.CELL_TYPE_FORMULA :
				if(!cell.toString().isEmpty()){
					FormulaEvaluator evaluator = cell.getSheet().getWorkbook().getCreationHelper().createFormulaEvaluator();
					int cellType = evaluator.evaluateFormulaCell(cell);
					if(cellType==HSSFCell.CELL_TYPE_NUMERIC){
						value = getNumberTypeValue(cell.getNumericCellValue());
					}else if(cellType==HSSFCell.CELL_TYPE_STRING){
						value = cell.getStringCellValue();
					}else if(cellType==HSSFCell.CELL_TYPE_BOOLEAN){
						value = String.valueOf(cell.getBooleanCellValue());
					}else{
						value = cell.getCellFormula();
					}
				}
				break;
			case HSSFCell.CELL_TYPE_NUMERIC :
				if(HSSFDateUtil.isCellDateFormatted(cell)){
					SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
					value = dateFormatter.format(cell.getDateCellValue());
				}else{
					value =  getNumberTypeValue(cell.getNumericCellValue());
				}
				break;
			case HSSFCell.CELL_TYPE_STRING :
				value = cell.getStringCellValue();
				break;
			default : 
		}
		return value;
	}
	
	private static String getNumberTypeValue(Double cellValue){
		if(cellValue - Math.round(cellValue) == 0.0){
			return String.valueOf(Math.round(cellValue));
		}
		return String.valueOf(cellValue);
	}
}
