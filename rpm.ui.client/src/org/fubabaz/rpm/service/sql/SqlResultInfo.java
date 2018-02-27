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

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.fubabaz.rpm.service.IServiceCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author ejpark
 *
 */
public class SqlResultInfo {

	private static final Logger LOGGER = LoggerFactory.getLogger(SqlResultInfo.class);

	private Connection connection;
	private Statement statement;
	private ResultSet resultSet;
	private IServiceCallback serviceCallback;

	private List<ColumnMetaInfo> columnMetaInfoList = new ArrayList<ColumnMetaInfo>();
	private Object[] rows;
	private int totalRows;
	private int fetchSize;
	private int currentRow = 0;
	private int columnCnt;
	private boolean isFirst = true;
	private boolean isLast = false;

	public SqlResultInfo(Connection connection, Statement statement, ResultSet resultSet,
			IServiceCallback serviceCallback, int fetchSize) {
		this.connection = connection;
		this.statement = statement;
		this.resultSet = resultSet;
		this.serviceCallback = serviceCallback;
		this.fetchSize = fetchSize;
		initResultInfo();
	}

	private void initResultInfo() {
		try {
			ResultSetMetaData resultSetMetaData = this.resultSet.getMetaData();
			this.columnCnt = resultSetMetaData.getColumnCount();
			for (int i = 0; i < this.columnCnt; i++) {
				ColumnMetaInfo columnMetaInfo = new ColumnMetaInfo();
				columnMetaInfo.setLabel(resultSetMetaData.getColumnLabel(i + 1));
				columnMetaInfo.setWidth(resultSetMetaData.getColumnDisplaySize(i + 1));
				this.columnMetaInfoList.add(columnMetaInfo);
			}
			this.resultSet.last();
			this.totalRows = this.resultSet.getRow();
			this.resultSet.beforeFirst();
			LOGGER.debug("columnCnt:{}, totalRows:{}", this.columnCnt, this.totalRows);
		} catch (SQLException e) {
			serviceCallback.error(e, "");
		}
	}

	public List<ColumnMetaInfo> getColumnMetaInfoList() {
		return columnMetaInfoList;
	}

	public Object[] getRowData() {
		return rows;
	}

	public void fetchRows() {
		if (isLast) {
			return;
		}
		int rowSize = this.fetchSize;
		if (this.currentRow + this.fetchSize > this.totalRows) {
			rowSize = this.totalRows - this.currentRow;
			isLast = true;
		}
		this.rows = new Object[rowSize];
		try {
			this.resultSet.absolute(this.currentRow);
			int rowCont = 0;
			while (rowCont < rowSize && this.resultSet.next()) {
				this.currentRow++;
				Object[] row = new Object[this.columnCnt];
				for (int i = 0; i < this.columnCnt; i++) {
					row[i] = this.resultSet.getObject(i + 1);
				}
				rows[rowCont] = row;
				rowCont++;
			}
		} catch (SQLException e) {
			this.serviceCallback.error(e, "");
		}

		if (isFirst) {
			isFirst = false;
			this.serviceCallback.start(this, "");
		} else {
			this.serviceCallback.process(this, "");
		}

		if (isLast) {
			this.close();
		}
	}

	public void close() {
		try {
			if (this.statement != null) {
				this.statement.close();
			}
			if (this.connection != null) {
				this.connection.close();
			}
		} catch (SQLException e) {
			// Ignore.
		}
	}
}
