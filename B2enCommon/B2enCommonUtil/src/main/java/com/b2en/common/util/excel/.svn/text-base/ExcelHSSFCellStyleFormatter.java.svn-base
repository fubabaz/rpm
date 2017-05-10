package com.b2en.common.util.excel;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Font;

/**
 * ExcelHSSFCellStyleFormatter.java 
 *
 * @author ej.park
 * @created 2016. 3. 2.
 *
 */
public class ExcelHSSFCellStyleFormatter {
	private HSSFFont font;
	
	public HSSFCellStyle getTitleStyle(HSSFWorkbook wb){
		HSSFCellStyle style = wb.createCellStyle();
			
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		
		style.setFillBackgroundColor(HSSFColor.WHITE.index);
		style.setFillForegroundColor(HSSFColor.WHITE.index);
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

		style.setBorderBottom(HSSFCellStyle.BORDER_DOUBLE);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);

		style.setWrapText(true);
		
		style.setFont(getTitlefont(wb));
		return style;
	}
	
	public HSSFCellStyle getListStyle(HSSFWorkbook wb, short align){
		HSSFCellStyle style = wb.createCellStyle();

		style.setAlignment(align);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		
		style.setFillBackgroundColor(HSSFColor.WHITE.index);
		style.setFillForegroundColor(HSSFColor.WHITE.index);
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		
		style.setFont(getDatafont(wb));
		return style;
	}
	
	public HSSFCellStyle getListStyle(HSSFWorkbook wb){
		return getListStyle(wb, HSSFCellStyle.ALIGN_LEFT);
	}
	
	private HSSFFont getTitlefont(HSSFWorkbook wb){
		if(font==null){
			font = wb.createFont();
		}
		font.setBoldweight(Font.BOLDWEIGHT_NORMAL);
		font.setFontHeightInPoints((short)9);
		font.setFontName("맑은 고딕");

		return font;
	}
	
	private HSSFFont getDatafont(HSSFWorkbook wb){
		if(font==null){
			font = wb.createFont();
		}
		font.setFontHeightInPoints((short)9);
		font.setFontName("맑은 고딕");

		return font;
	}
}
