package com.b2en.common.util.validation;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;

import com.b2en.common.util.excel.ExcelReaderImpl;
import com.b2en.common.util.excel.IExcelReader;
import com.b2en.common.util.validation.helper.IValidationHelper;
import com.b2en.common.util.validation.result.IValidatorResult;
import com.b2en.common.util.validation.validator.IListValidator;
import com.b2en.common.util.validation.validator.IValidator;
import com.b2en.common.util.validation.validator.impl.MandatoryValidator;
import com.b2en.common.util.validation.validator.impl.RegExpValidator;
import com.b2en.common.util.validation.validator.impl.UniqueListValidator;

public class ValidationFactoryTest {

	@Test
	public void testValidation() {
		long startTime = Calendar.getInstance().getTimeInMillis();
		String filePath = "E:/EJ/10.Project/20.SDQ/..old";
		String fileName = "표준도메인그룹.xlsx";
		
		IExcelReader reader = new ExcelReaderImpl();
		Workbook wb = reader.getWorkbook(filePath, fileName);
		int startIdx = 2;
		List<String[]> domainGrpList = reader.getWorkbookData(wb, 0, startIdx);
		
		IValidationHelper validationHelper = ValidationFactory.getValidationManager(domainGrpList, startIdx);
		validationHelper.addListValidator(new UniqueListValidator(new int[] {1}));
		
		IValidator mandatoryValidator = new MandatoryValidator();
		IValidator useYnValidator = new RegExpValidator("Y|N");
		IValidator blankOrNumberValidator = new RegExpValidator("\\p{Space}|^[0-9]*$");
		
		validationHelper.setFieldNames(new String[] {"", "도메인그룹명", "설명", "사용여부"});
		
		validationHelper.addValidator(1, mandatoryValidator);
		validationHelper.addValidator(2, mandatoryValidator);
		validationHelper.addValidator(3, mandatoryValidator);
		validationHelper.addValidator(3, useYnValidator);
		validationHelper.execute();
		
		if(validationHelper.hasError()){
			List<IValidatorResult> errList = validationHelper.getErrList();
			for(IValidatorResult result : errList){
				System.out.println(result.getErrMessage());
			}
		}
		
		List<String[]> domainGrpDataTypeList = reader.getWorkbookData(wb, 1, startIdx);
		IValidationHelper domainGrpDataTypeValidation = ValidationFactory.getValidationManager(domainGrpDataTypeList, startIdx);
		domainGrpDataTypeValidation.addListValidator(new UniqueListValidator(new int[] {1,2,3,4,5}));
		
		domainGrpDataTypeValidation.setFieldNames(new String[] {"", "도메인그룹명", "DBMS종류", "데이터타입", "타입길이", "소수점길이"});
		domainGrpDataTypeValidation.addValidator(1, mandatoryValidator);
		domainGrpDataTypeValidation.addValidator(2, mandatoryValidator);
		domainGrpDataTypeValidation.addValidator(3, mandatoryValidator);
		domainGrpDataTypeValidation.addValidator(4, blankOrNumberValidator);
		domainGrpDataTypeValidation.addValidator(5, blankOrNumberValidator);
		domainGrpDataTypeValidation.execute();
		
		if(domainGrpDataTypeValidation.hasError()){
			List<IValidatorResult> errList = domainGrpDataTypeValidation.getErrList();
			for(IValidatorResult result : errList){
				System.out.println(result.getErrMessage());
			}
		}
		
		long endTime = Calendar.getInstance().getTimeInMillis();
		
		System.out.println("S:"+startTime);
		System.out.println("E:"+endTime);
		System.out.println("D:"+(new BigDecimal(endTime - startTime).movePointLeft(3).toPlainString()));
	}
	
	@Test
	public void testValid() {
		List<String> array = new ArrayList<String>();
		array.add("1_1_1");
		array.add("1_1_2");
		array.add("1_1_3");
		array.add("1_1_4");
		array.add("1_1_5");
		array.add("1_1_6");
		array.add("1_1_3");
		array.add("1_1_4");
		
		List<String> arrayCopy = new ArrayList<String>();
		arrayCopy.addAll(array);
		
		System.out.println(arrayCopy);
		List<String> uniqueArray = new ArrayList<String>(new HashSet< String >(arrayCopy));
		System.out.println(uniqueArray);
		
		for(String val : uniqueArray){
			arrayCopy.remove(val);	
		}
		
		System.out.println(arrayCopy);
		
		for(String val : arrayCopy){
			System.out.println(array.indexOf(val));
		}
	}
	
	@Test
	public void testValidationWord() {
		String filePath = "E:/EJ/10.Project/20.SDQ/..old";
		String fileName = "표준단어.xlsx";
		IExcelReader reader = new ExcelReaderImpl();
		Workbook wb = reader.getWorkbook(filePath, fileName);
		int startIdx = 1;
		List<String[]> wordList = reader.getWorkbookData(wb, 0, startIdx);
		IValidationHelper wordValidation = ValidationFactory.getValidationManager(wb.getSheetName(0), wordList, startIdx);
		IListValidator wordUniqueListValidator = new UniqueListValidator(new int[] {1});
		wordUniqueListValidator.setErrMsg("중복된 행이 존재합니다. <B>rows</B>:");
		wordValidation.addListValidator(wordUniqueListValidator);
		
		IValidator mandatoryValidator = new MandatoryValidator();
		IValidator blankYnValidator = new RegExpValidator("\\p{Space}|Y|N*", true);
		IValidator englishOrNumberValidator = new RegExpValidator("^[\\p{Space}A-Za-z0-9]*$", true);
		IValidator noKoreanValidator = new RegExpValidator("^[^가-힣|]*$", true);
		wordValidation.setFieldNames(new String[] {"", "단어명", "단어약어명", "단어영문명", "단어설명", "분류어여부"
				, "도메인그룹명", "유사어여부", "금칙어여부", "대체단어명", "사용여부"});
		
		wordValidation.addValidator(1, mandatoryValidator);
		wordValidation.addValidator(2, englishOrNumberValidator);
		wordValidation.addValidator(3, noKoreanValidator);
		wordValidation.addValidator(4, mandatoryValidator);
		wordValidation.addValidator(5, blankYnValidator);
		wordValidation.addValidator(7, blankYnValidator);
		wordValidation.addValidator(8, blankYnValidator);
		wordValidation.addValidator(10, blankYnValidator);
		
		wordValidation.execute();
		if(wordValidation.hasError()){
			List<IValidatorResult> errList = wordValidation.getErrList();
			for(IValidatorResult result : errList){
				
				System.out.println(result.getFieldName()+":"+result.getRowIdx()+result.getErrMessage());
			}
		}
		
	}
	
	@Test
	public void testRegExp(){
		String val = "ZERO ONE".trim();
		System.out.println(Pattern.matches("^[\\p{Space}A-Za-z0-9]*$", "ZERO ONE"));
		System.out.println(val);
		System.out.println(Pattern.matches("^[A-Za-z0-9]*$", val));
	}

}

