/**
  * Copyright 2017 FuBaBaz Team.
  *
  * Licensed under the Apache License, Version 2.0 (the "License");
  * you may not use this file except in compliance with the License.
  * You may obtain a copy of the License at
  * 
  *     http://www.apache.org/licenses/LICENSE-2.0
  * 
  * Unless required by applicable law or agreed to in writing, software
  * distributed under the License is distributed on an "AS IS" BASIS,
  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  * See the License for the specific language governing permissions and
  * limitations under the License.
  */

package org.fubabaz.rpm.service.sql;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ejpark
 *
 */
public class SqlResultInfo {

	private List<ColumnMetaInfo> columnMetaInfoList = new ArrayList<ColumnMetaInfo>();
	private List<Object[]> dataList = new ArrayList<Object[]>();

	public List<ColumnMetaInfo> getColumnMetaInfoList() {
		return columnMetaInfoList;
	}

	public void addColumnMetaInfo(ColumnMetaInfo columnMetaInfo) {
		this.columnMetaInfoList.add(columnMetaInfo);
	} 

	public List<Object[]> getDataList() {
		return dataList;
	}

	public void addRow(Object[] row) {
		this.dataList.add(row);
	} 
}
