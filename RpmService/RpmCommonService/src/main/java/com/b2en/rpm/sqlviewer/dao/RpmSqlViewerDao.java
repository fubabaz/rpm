package com.b2en.rpm.sqlviewer.dao;

import java.util.List;

import com.b2en.rpm.sqlviewer.vo.SqlViewerOverViewInfo;
import com.b2en.rpm.sqlviewer.vo.SqlViewerParamInfo;
import com.b2en.rpm.sqlviewer.vo.SqlViewerSqlInfo;


public interface RpmSqlViewerDao {

	public List<SqlViewerSqlInfo> selectSqlInfoList();
	
	public List<SqlViewerOverViewInfo> selectOverViewList(SqlViewerParamInfo sqlViewerParamInfo);
}
