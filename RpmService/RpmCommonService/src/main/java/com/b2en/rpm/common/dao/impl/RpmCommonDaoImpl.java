package com.b2en.rpm.common.dao.impl;

import java.util.List;

import com.b2en.framework.dao.AbstractCommonDao;
import com.b2en.rpm.common.dao.RpmCommonDao;
import com.b2en.rpm.common.vo.SchemaTableInfo;

public class RpmCommonDaoImpl extends AbstractCommonDao implements RpmCommonDao {

	public List<SchemaTableInfo> selectSchemaTableColumnInfoList(SchemaTableInfo schemaTableInfo){
		return selectList("SqlMapRpmCommonDao.selectSchemaTableColumnInfoList", schemaTableInfo);
	}
}
