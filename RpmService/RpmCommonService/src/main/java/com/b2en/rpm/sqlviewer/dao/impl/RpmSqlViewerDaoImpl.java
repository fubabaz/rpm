package com.b2en.rpm.sqlviewer.dao.impl;

import java.util.List;

import com.b2en.framework.dao.AbstractCommonDao;
import com.b2en.rpm.sqlviewer.dao.RpmSqlViewerDao;
import com.b2en.rpm.sqlviewer.vo.SqlViewerOverViewInfo;
import com.b2en.rpm.sqlviewer.vo.SqlViewerParamInfo;
import com.b2en.rpm.sqlviewer.vo.SqlViewerSqlInfo;

public class RpmSqlViewerDaoImpl extends AbstractCommonDao implements RpmSqlViewerDao {

	public List<SqlViewerSqlInfo> selectSqlInfoList() {
		return selectList("SqlMapRpmSqlViewerDao.selectSqlInfoList", null);
	}

	public List<SqlViewerOverViewInfo> selectOverViewList(SqlViewerParamInfo sqlViewerParamInfo) {
		return selectList("SqlMapRpmSqlViewerDao.selectOverViewList", sqlViewerParamInfo);
	}
	
}
