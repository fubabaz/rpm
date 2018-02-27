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
import java.sql.SQLException;

import org.fubabaz.rpm.connection.pool.ConnectionPoolFactory;
import org.fubabaz.rpm.connection.pool.IConnectionPool;
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
	private int fetchSize;
	private IServiceCallback serviceCallback;
	private SqlResultInfo sqlResultInfo;

	public SqlExecuteService(IServiceCallback serviceCallback) {
		this.connectionPool = ConnectionPoolFactory.getInstance().getConnectionPool();
		this.serviceCallback = serviceCallback;
		this.sqlResultInfo = new SqlResultInfo(serviceCallback);
	}

	public void setSql(String sql, int fetchSize) {
		this.sql = sql;
		this.fetchSize = fetchSize;
	}

	@Override
	public void run() {
		LOGGER.debug("SQL:{}", sql);
		sqlResultInfo.close();
		Connection connection = this.connectionPool.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			preparedStatement.setFetchSize(fetchSize);
			resultSet = preparedStatement.executeQuery();
			sqlResultInfo.setUp(connection, preparedStatement, resultSet, fetchSize);
			sqlResultInfo.fetchRows();
		} catch (SQLException e) {
			serviceCallback.error(e, sql);
		}
	}
}
