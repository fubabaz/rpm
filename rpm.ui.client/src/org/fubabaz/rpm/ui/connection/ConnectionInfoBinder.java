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

package org.fubabaz.rpm.ui.connection;

import org.fubabaz.rpm.connection.ConnectionInfo;
import org.fubabaz.rpm.connection.DbmsType;
import org.fubabaz.rpm.ui.bind.ModelPropertyChangeBinder;

/**
 * @author ejpark
 *
 */
public class ConnectionInfoBinder extends ModelPropertyChangeBinder {

	private ConnectionInfo connectionInfo;

	public ConnectionInfoBinder(ConnectionInfo connectionInfo) {
		super(connectionInfo);
		this.connectionInfo = connectionInfo;
	}

	/**
	 * @return the dbmsType
	 */
	public int getDbmsType() {
		return DbmsType.getIndex(this.connectionInfo.getDbmsType());
	}

	/**
	 * @param dbmsType
	 *            the dbmsType to set
	 */
	public void setDbmsType(int dbmsCls) {
		DbmsType dbmsType = DbmsType.values()[dbmsCls];
		this.propertyChangeSupport.firePropertyChange("dbmsType", getDbmsType(), dbmsType);
		this.connectionInfo.setDbmsType(dbmsType);
	}

	/**
	 * @return the host
	 */
	public String getHost() {
		return this.connectionInfo.getHost();
	}

	/**
	 * @param host
	 *            the host to set
	 */
	public void setHost(String host) {
		this.propertyChangeSupport.firePropertyChange("host", getHost(), host);
		this.connectionInfo.setHost(host);
	}

	/**
	 * @return the port
	 */
	public int getPort() {
		return this.connectionInfo.getPort();
	}

	/**
	 * @param port
	 *            the port to set
	 */
	public void setPort(int port) {
		this.propertyChangeSupport.firePropertyChange("port", getPort(), port);
		this.connectionInfo.setPort(port);
	}

	/**
	 * @return the connectType
	 */
	public int getConnectType() {
		return this.connectionInfo.getConnectType();
	}

	/**
	 * @param connectType
	 *            the connectType to set
	 */
	public void setConnectType(int connectType) {
		this.propertyChangeSupport.firePropertyChange("connectType", getConnectType(), connectType);
		this.connectionInfo.setConnectType(connectType);
	}

	/**
	 * @return the databaseName
	 */
	public String getDatabaseName() {
		return this.connectionInfo.getDatabaseName();
	}

	/**
	 * @param databaseName
	 *            the databaseName to set
	 */
	public void setDatabaseName(String databaseName) {
		this.propertyChangeSupport.firePropertyChange("databaseName", getDatabaseName(), databaseName);
		this.connectionInfo.setDatabaseName(databaseName);
	}

	/**
	 * @return the user
	 */
	public String getUser() {
		return this.connectionInfo.getUser();
	}

	/**
	 * @param user
	 *            the user to set
	 */
	public void setUser(String user) {
		this.propertyChangeSupport.firePropertyChange("user", getUser(), user);
		this.connectionInfo.setUser(user);
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return this.connectionInfo.getPassword();
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.propertyChangeSupport.firePropertyChange("password", getPassword(), password);
		this.connectionInfo.setPassword(password);
	}

	/**
	 * @return the isSavePassword
	 */
	public boolean isSavePassword() {
		return this.connectionInfo.isSavePassword();
	}

	/**
	 * @param savePassword
	 *            the savePassword to set
	 */
	public void setSavePassword(boolean savePassword) {
		this.propertyChangeSupport.firePropertyChange("savePassword", isSavePassword(), savePassword);
		this.connectionInfo.setSavePassword(savePassword);
	}

	/**
	 * @return the jdbcOption
	 */
	public String getJdbcOption() {
		return this.connectionInfo.getJdbcOption();
	}

	/**
	 * @param jdbcOption
	 *            the jdbcOption to set
	 */
	public void setJdbcOption(String jdbcOption) {
		this.propertyChangeSupport.firePropertyChange("jdbcOption", getJdbcOption(), jdbcOption);
		this.connectionInfo.setJdbcOption(jdbcOption);
	}

	/**
	 * @return the connectionInfo
	 */
	public ConnectionInfo getConnectionInfo() {
		return this.connectionInfo;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return this.connectionInfo.toString();
	}
}
