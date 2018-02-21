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

package org.fubabaz.rpm.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author ejpark
 *
 */
public class ConnectionTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(ConnectionTest.class);

	public static void test(ConnectionInfo connectionInfo) {
		Connection connection = null;
		try {
			String driverClassName = connectionInfo.getDbmsType().getDriverClassName();
			LOGGER.debug("driverClassName:{}", driverClassName);
			Class.forName(driverClassName);
			LOGGER.debug("connect... url:{}", connectionInfo.getJdbcUrl());
			connection = DriverManager.getConnection(connectionInfo.getJdbcUrl(), connectionInfo.getUser(),
					connectionInfo.getPassword());
			LOGGER.debug("connection:{}", connection);
			if(connection.isValid(3)) {
				connectionInfo.setValid(true);
			}
		} catch (ClassNotFoundException e) {
			LOGGER.error("{}", e.getCause());
			connectionInfo.setMessage(e.getMessage());
		} catch (SQLException e) {
			LOGGER.error("{}", e.getCause());
			connectionInfo.setMessage(e.getMessage());
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					// ignore
				}
			}
		}
	}
}
