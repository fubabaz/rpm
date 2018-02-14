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

import java.util.Properties;

/**
 * @author ejpark
 *
 */
public class ConnectionInfo {

	private String host;
	private int port;
	private int connectType;
	private String databaseName;
	private String user;
	private String password;
	private String jdbcOption;
	private final Properties options;
	
	public ConnectionInfo() {
		this.options = new Properties();
	}

	/**
	 * @return the host
	 */
	public String getHost() {
		return host;
	}
	/**
	 * @param host the host to set
	 */
	public void setHost(String host) {
		this.host = host;
	}
	/**
	 * @return the port
	 */
	public int getPort() {
		return port;
	}
	/**
	 * @param port the port to set
	 */
	public void setPort(int port) {
		this.port = port;
	}
	/**
	 * @return the connectType
	 */
	public int getConnectType() {
		return connectType;
	}
	/**
	 * @param connectType the connectType to set
	 */
	public void setConnectType(int connectType) {
		this.connectType = connectType;
	}
	/**
	 * @return the databaseName
	 */
	public String getDatabaseName() {
		return databaseName;
	}
	/**
	 * @param databaseName the databaseName to set
	 */
	public void setDatabaseName(String databaseName) {
		this.databaseName = databaseName;
	}
	/**
	 * @return the user
	 */
	public String getUser() {
		return user;
	}
	/**
	 * @param user the user to set
	 */
	public void setUser(String user) {
		this.user = user;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the jdbcOption
	 */
	public String getJdbcOption() {
		return jdbcOption;
	}

	/**
	 * @param jdbcOption the jdbcOption to set
	 */
	public void setJdbcOption(String jdbcOption) {
		this.jdbcOption = jdbcOption;
	}
	/**
	 * @return the options
	 */
	public Properties getOptions() {
		return options;
	}
	/**
	 * @return the option
	 */
	public String getOption(String key) {
		return options.getProperty(key);
	}
	/**
	 * @param options the option to set
	 */
	public void setOption(String key, String value) {
		this.options.put(key, value);
	}

	public String getJdbcUrl(DbmsType dbmsType) {
		return String.format(dbmsType.getJdbcUrlFormat(connectType), host, port, databaseName);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		sb.append("host=");		
		sb.append(this.host);
		sb.append(", port=");
		sb.append(this.port);
		sb.append(", connectType=");
		sb.append(this.connectType);
		sb.append(", databaseName=");
		sb.append(this.databaseName);
		sb.append(", user=");
		sb.append(this.user);
		sb.append(", password=");	
		sb.append(this.password);
		sb.append(", jdbcOption=");
		sb.append(this.jdbcOption);
		sb.append(", options=");		
		sb.append(this.options.toString());
		sb.append("]");
		return sb.toString();
	}
}
