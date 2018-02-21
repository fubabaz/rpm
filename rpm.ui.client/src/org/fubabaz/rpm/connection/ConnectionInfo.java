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

	private DbmsType dbmsType;
	private String host;
	private int port;
	private int connectType;
	private String databaseName;
	private String user;
	private String password;
	private boolean savePassword;
	private String jdbcOption;
	private final Properties options;

	private boolean isValid;
	private String message;

	public ConnectionInfo() {
		this.options = new Properties();
	}

	/**
	 * @return the dbmsType
	 */
	public DbmsType getDbmsType() {
		return dbmsType;
	}

	/**
	 * @param dbmsType
	 *            the dbmsType to set
	 */
	public void setDbmsType(DbmsType dbmsType) {
		this.dbmsType = dbmsType;
	}

	/**
	 * @return the host
	 */
	public String getHost() {
		return host == null ? "" : host;
	}

	/**
	 * @param host
	 *            the host to set
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
	 * @param port
	 *            the port to set
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
	 * @param connectType
	 *            the connectType to set
	 */
	public void setConnectType(int connectType) {
		this.connectType = connectType;
	}

	/**
	 * @return the databaseName
	 */
	public String getDatabaseName() {
		return databaseName == null ? "" : databaseName;
	}

	/**
	 * @param databaseName
	 *            the databaseName to set
	 */
	public void setDatabaseName(String databaseName) {
		this.databaseName = databaseName;
	}

	/**
	 * @return the user
	 */
	public String getUser() {
		return user == null ? "" : user;
	}

	/**
	 * @param user
	 *            the user to set
	 */
	public void setUser(String user) {
		this.user = user;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password == null ? "" : password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the savePassword
	 */
	public boolean isSavePassword() {
		return savePassword;
	}

	/**
	 * @param savePassword
	 *            the savePassword to set
	 */
	public void setSavePassword(boolean savePassword) {
		this.savePassword = savePassword;
	}

	/**
	 * @return the jdbcOption
	 */
	public String getJdbcOption() {
		return jdbcOption == null ? "" : jdbcOption;
	}

	/**
	 * @param jdbcOption
	 *            the jdbcOption to set
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
	 * @param options
	 *            the option to set
	 */
	public void setOption(String key, String value) {
		this.options.put(key, value);
	}

	/**
	 * @return the jdbcUrl
	 */
	public String getJdbcUrl() {
		return String.format(dbmsType.getJdbcUrlFormat(connectType), host, port, databaseName);
	}

	/**
	 * @return the isValid
	 */
	public boolean isValid() {
		return isValid;
	}

	/**
	 * @param isValid the isValid to set
	 */
	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		sb.append("dbmsType=");
		sb.append(this.dbmsType);
		sb.append(",host=");
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
		sb.append(", savePassword=");
		sb.append(this.savePassword);
		sb.append(", jdbcOption=");
		sb.append(this.jdbcOption);
		sb.append(", options=");
		sb.append(this.options.toString());
		sb.append("]");
		return sb.toString();
	}
}
