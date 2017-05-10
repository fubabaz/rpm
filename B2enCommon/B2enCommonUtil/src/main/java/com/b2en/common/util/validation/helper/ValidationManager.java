package com.b2en.common.util.validation.helper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.b2en.common.util.validation.result.IValidatorResult;
import com.b2en.common.util.validation.result.ValidationResult;
import com.b2en.common.util.validation.validator.AbstractListValidator;
import com.b2en.common.util.validation.validator.AbstractValidator;
import com.b2en.common.util.validation.validator.IListValidator;
import com.b2en.common.util.validation.validator.IValidator;

/**
 * ValidationManager.java 
 *
 * @author ej.park
 * @created 2016. 3. 15.
 *
 */
public class ValidationManager implements IValidationHelper {

	private String name;
	private List<String[]> dataList;
	private Map<Integer, List<AbstractValidator>> validatorMap;
	private List<AbstractListValidator> arrayValidatorList;
	private String[] fieldNames;
	private List<IValidatorResult> errList;
	private int startIdx;
	
	public ValidationManager(){
		this.validatorMap = new HashMap<Integer, List<AbstractValidator>>();
		this.arrayValidatorList = new ArrayList<AbstractListValidator>();
		this.errList = new ArrayList<IValidatorResult>();
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public void setDataList(List<String[]> dataList){
		this.dataList = dataList;
	}
	
	public void setStartIdx(int startIdx){
		this.startIdx = startIdx;
	}
	
	public void setFieldNames(String... title){
		this.fieldNames = title;
	}
	
	public void addListValidator(IListValidator rowValidator){
		this.arrayValidatorList.add((AbstractListValidator)rowValidator);
	}
	
	public void addValidator(int colIdx, IValidator validator){
		List<AbstractValidator> validatorList = validatorMap.get(colIdx);
		if(validatorList==null){
			validatorList = new ArrayList<AbstractValidator>();
			validatorMap.put(colIdx, validatorList);
		}
		validatorList.add((AbstractValidator) validator);
	}
	
	public void execute(){
		for(AbstractListValidator listValidator : this.arrayValidatorList){
			listValidator.setName(this.name);
			listValidator.setDataList(this.dataList);
			listValidator.setStartIdx(this.startIdx);
			if(!listValidator.execute()){
				this.errList.add(new ValidationResult(listValidator));
			}
		}
		
		Map<String, ValidationResult> resultMap = new HashMap<String, ValidationResult>();
		for(int rowIdx=0; rowIdx<this.dataList.size(); rowIdx++ ){
			String[] row = this.dataList.get(rowIdx);
			for(int colIdx=0; colIdx<row.length; colIdx++){
				String value = row[colIdx];
				List<AbstractValidator> validatorList = this.validatorMap.get(colIdx);
				if(validatorList!=null){
					for(AbstractValidator validator : validatorList){
						validator.setValue(value);
						if(!validator.execute()){
							validator.setName(this.name);
							validator.setRowIdx(rowIdx+this.startIdx+1);
							validator.setColIdx(colIdx+1);
							if(this.fieldNames!=null & this.fieldNames.length>colIdx){
								validator.setFieldName(this.fieldNames[colIdx]);
							}
							String key = validator.getRowIdx()+"_"+validator.getColIdx();
							ValidationResult validationResult = resultMap.get(key);
							if(validationResult==null){
								this.errList.add(new ValidationResult(validator));								
							}else{
								validationResult.addErrMsg(validator.getErrMessage());
							}
						}
					}					
				}
			}
		}
	}
	
	public boolean hasError(){
		if(this.errList.size()>0){
			return true;
		}
		return false;
	}
	
	public List<IValidatorResult> getErrList(){
		return this.errList;
	}
}
