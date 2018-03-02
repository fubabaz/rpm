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

package org.fubabaz.rpm.service.monitor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.fubabaz.rpm.connection.pool.ConnectionPoolFactory;
import org.fubabaz.rpm.connection.pool.IConnectionPool;
import org.fubabaz.rpm.exception.SystemException;
import org.fubabaz.rpm.service.QueryManager;
import org.fubabaz.rpm.service.QueryManagerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author ejpark
 *
 */
public class SystemMonitorService implements ISystemMonitor {

	private static final Logger LOGGER = LoggerFactory.getLogger(SystemMonitorService.class);
	private IConnectionPool connectionPool;
	private QueryManager queryManager;

	public SystemMonitorService() {
		this.connectionPool = ConnectionPoolFactory.getInstance().getConnectionPool();
		this.queryManager = QueryManagerFactory.getInstance().getQueryManager();
	}

	public DatabaseInfo getDatabaseInfo() {
		DatabaseInfo databaseInfo = new DatabaseInfo();
		Connection connection = this.connectionPool.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			preparedStatement = connection.prepareStatement(this.queryManager.getQuery("monitor.system.database"));
			resultSet = preparedStatement.executeQuery();
		} catch (SQLException e) {
			throw new SystemException(e);
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
		return databaseInfo;
	}
}
