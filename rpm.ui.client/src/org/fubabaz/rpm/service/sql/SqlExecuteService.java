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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.fubabaz.rpm.connection.pool.ConnectionPoolFactory;
import org.fubabaz.rpm.connection.pool.IConnectionPool;
import org.fubabaz.rpm.exception.SystemException;
import org.fubabaz.rpm.service.IServiceCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author ejpark
 *
 */
public class SqlExecuteService implements Runnable {

	private static final Logger LOGGER = LoggerFactory.getLogger(SqlExecuteService.class);

	private IConnectionPool connectionPool;
	private String sql;
	private IServiceCallback serviceCallback;

	public SqlExecuteService(IServiceCallback serviceCallback) {
		this.connectionPool = ConnectionPoolFactory.getInstance().getConnectionPool();
		this.serviceCallback = serviceCallback;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	@Override
	public void run() {
		LOGGER.debug("SQL:{}", sql);
		Connection connection = this.connectionPool.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			resultSet = preparedStatement.executeQuery();
			ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
			int columnCnt = resultSetMetaData.getColumnCount();
			resultSet.last();
			int totalRow = resultSet.getRow();
			resultSet.beforeFirst();
			SqlResultInfo sqlResultInfo = new SqlResultInfo(totalRow);
			LOGGER.debug("columnCnt:{}, totalRow:{}", columnCnt, totalRow);
			for (int i = 0; i < columnCnt; i++) {
				ColumnMetaInfo columnMetaInfo = new ColumnMetaInfo();
				columnMetaInfo.setLabel(resultSetMetaData.getColumnLabel(i + 1));
				columnMetaInfo.setWidth(resultSetMetaData.getColumnDisplaySize(i + 1));
				sqlResultInfo.addColumnMetaInfo(columnMetaInfo);
			}
			try {
				while (resultSet.next()) {
					Object[] row = new Object[columnCnt];
					for (int i = 0; i < columnCnt; i++) {
						row[i] = resultSet.getObject(i + 1);
					}
					sqlResultInfo.addRow(resultSet.getRow() - 1, row);
				}
			} catch (SQLException e) {
				throw new SystemException(e);
			}
			serviceCallback.success(sqlResultInfo, sql);
		} catch (SQLException e) {
			serviceCallback.error(e, sql);
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				// Ignore.
			}
		}
	}
}
