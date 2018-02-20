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

/**
 * @author ejpark
 *
 */
public enum DbmsType {

	ORACLE("Oracle", "oracle.jdbc.driver.OracleDriver",
			new String[] { "jdbc:oracle:thin:@%s:%s:%s", "jdbc:oracle:thin:@//%s:%s/%s" }, 1521, "SID",
			"ServiceName"), 
	POSTGRESQL("PostgreSQL", "org.postgresql.Driver",
					new String[] { "jdbc:postgresql://%s:%s/%s" }, 5432, "DatabaseName"),;

	private String name;
	private String driverClassName;
	private String[] jdbcUrlFormat;
	private int defaultPort;
	private String[] databaseNames;

	private DbmsType(String name, String driverClassName, String[] jdbcUrlFormat, int defaultPort,
			String... databaseNames) {
		this.name = name;
		this.driverClassName = driverClassName;
		this.jdbcUrlFormat = jdbcUrlFormat;
		this.defaultPort = defaultPort;
		this.databaseNames = databaseNames;
	}

	public String getName() {
		return this.name;
	}

	public String getDriverClassName() {
		return this.driverClassName;
	}

	public String getJdbcUrlFormat(int idx) {
		return this.jdbcUrlFormat[idx];
	}

	public int getDefaultPort() {
		return this.defaultPort;
	}

	public String[] getDatabaseNames() {
		return this.databaseNames;
	}

	public static int getIndex(DbmsType dbmsType) {
		for (int i = 0; i < DbmsType.values().length; i++) {
			if (DbmsType.values()[i].equals(dbmsType)) {
				return i;
			}
		}
		return -1;
	}
}
