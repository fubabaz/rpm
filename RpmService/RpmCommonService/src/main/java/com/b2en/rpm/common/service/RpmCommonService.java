package com.b2en.rpm.common.service;

import java.util.List;

import com.b2en.rpm.common.vo.SchemaTableInfo;

public interface RpmCommonService {

	public List<SchemaTableInfo> getSchemaTableColumnInfoList(SchemaTableInfo schemaTableInfo);
}
