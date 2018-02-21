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

package org.fubabaz.rpm.connection.pool;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.fubabaz.rpm.connection.ConnectionInfo;
import org.fubabaz.rpm.exception.SystemException;

/**
 * @author ejpark
 *
 */
public abstract class AbstractConnectionPool implements IConnectionPool, IConnectionInitializer {

	private DataSource dataSource;
	
	@Override
	public void initialize(ConnectionInfo connectionInfo) {
		this.dataSource = getDataSource(connectionInfo);
	}

	@Override
	public Connection getConnection() {
		Connection connection = null;
		try {
			connection = this.dataSource.getConnection();
		} catch (SQLException e) {
			throw new SystemException(e);
		}
		return connection;
	}

	protected int getInitConnCount() {
		return ConnectionPoolConfig.INIT_CONN_CNT;
	}
	
	protected int getMaxConnCount() {
		return ConnectionPoolConfig.MAX_CONN_COUNT;
	}
	
	protected long getMaxWait() {
		return ConnectionPoolConfig.MAX_WAIT;
	}

	public abstract DataSource getDataSource(ConnectionInfo connectionInfo);
}
