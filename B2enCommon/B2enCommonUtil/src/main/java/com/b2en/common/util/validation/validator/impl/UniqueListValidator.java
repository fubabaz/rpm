package com.b2en.common.util.validation.validator.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import com.b2en.common.util.validation.validator.AbstractListValidator;

public class UniqueListValidator extends AbstractListValidator {

	private int[] keyIdxs;
	private List<Integer> dupulicateKey = new ArrayList<Integer>();
	
	public UniqueListValidator(int[] keyIdxs){
		this.keyIdxs = keyIdxs;
	}
	
	@Override
	public boolean execute() {
		List<String> keyList = new ArrayList<String>(); 
		for(String[] row : this.dataList){
			StringBuffer keySb = new StringBuffer();
			for(int keyIdx : this.keyIdxs){
				keySb.append("_"+row[keyIdx]);
			}
			keyList.add(keySb.toString());
		}
		
		List<String> keyCopyList = new ArrayList<String>(); 
		keyCopyList.addAll(keyList);
		
		List<String> uniqueArray = new ArrayList<String>(new HashSet< String >(keyCopyList));
		if(uniqueArray.size() == this.dataList.size()){
			return true;
		}
		
		for(String val : uniqueArray){
			keyCopyList.remove(val);	
		}
		
		for(String val : keyCopyList){
			dupulicateKey.add(keyList.indexOf(val)+this.startIdx+1);
		}
		return false;
	}

	@Override
	public String getErrMessage() {
		if(this.errMsg == null){
			this.errMsg = "중복된 행이 존재합니다.";
		}
		return this.errMsg+dupulicateKey.toString();
	}
}
