package com.b2en.rpm.sqlviewer.service.impl;

import java.util.List;

import org.apache.log4j.Logger;

import com.b2en.common.service.AbstractCommonService;
import com.b2en.rpm.sqlviewer.dao.RpmSqlViewerDao;
import com.b2en.rpm.sqlviewer.service.RpmSqlViewerService;
import com.b2en.rpm.sqlviewer.vo.SqlViewerOverViewInfo;
import com.b2en.rpm.sqlviewer.vo.SqlViewerParamInfo;
import com.b2en.rpm.sqlviewer.vo.SqlViewerSqlInfo;

public class RpmSqlViewerServiceImpl extends AbstractCommonService implements RpmSqlViewerService {

	private Logger logger = Logger.getLogger(this.getClass());
	private RpmSqlViewerDao rpmSqlViewerDao;
	
	public RpmSqlViewerServiceImpl(){
		this.rpmSqlViewerDao = (RpmSqlViewerDao)getDao(RpmSqlViewerDao.class);
	}

	public List<SqlViewerSqlInfo> getSqlInfoList() {
		return this.rpmSqlViewerDao.selectSqlInfoList();
	}
	
	
	public List<SqlViewerOverViewInfo> getOverViewList(SqlViewerParamInfo sqlViewerParamInfo){
		return this.rpmSqlViewerDao.selectOverViewList(sqlViewerParamInfo);
	}
}
