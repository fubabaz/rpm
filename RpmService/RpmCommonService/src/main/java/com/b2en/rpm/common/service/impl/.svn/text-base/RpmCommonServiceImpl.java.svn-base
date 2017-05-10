package com.b2en.rpm.common.service.impl;

import java.util.List;

import org.apache.log4j.Logger;

import com.b2en.common.service.AbstractCommonService;
import com.b2en.rpm.common.dao.RpmCommonDao;
import com.b2en.rpm.common.service.RpmCommonService;
import com.b2en.rpm.common.vo.SchemaTableInfo;

public class RpmCommonServiceImpl extends AbstractCommonService implements RpmCommonService {

	private Logger logger = Logger.getLogger(this.getClass());
	private RpmCommonDao rpmCommonDao;
	
	public RpmCommonServiceImpl(){
		this.rpmCommonDao = (RpmCommonDao)getDao(RpmCommonDao.class);
	}
	
	public List<SchemaTableInfo> getSchemaTableColumnInfoList(SchemaTableInfo schemaTableInfo){
		if(logger.isDebugEnabled()){
			logger.debug("###### selectSchemaTableColumnInfoList>>");
			logger.debug(schemaTableInfo);
		}
		return this.rpmCommonDao.selectSchemaTableColumnInfoList(schemaTableInfo);
	}
}
