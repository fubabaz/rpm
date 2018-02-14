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

import java.io.IOException;
import java.util.HashMap;

import javax.crypto.spec.PBEKeySpec;

import org.eclipse.equinox.security.storage.ISecurePreferences;
import org.eclipse.equinox.security.storage.SecurePreferencesFactory;
import org.eclipse.equinox.security.storage.StorageException;
import org.eclipse.equinox.security.storage.provider.IProviderHints;
import org.fubabaz.rpm.connection.ConnectionInfo;
import org.fubabaz.rpm.connection.IConnectionData;
import org.fubabaz.rpm.exception.SystemException;
import org.fubabaz.rpm.ui.preference.PreferenceStoredData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

/**
 * @author ejpark
 *
 */
public class ConnectionStoredData implements IConnectionData {

	private static final Logger LOGGER = LoggerFactory.getLogger(ConnectionStoredData.class);
	private ISecurePreferences connectionPreferences;
	private Gson gson;

	public ConnectionStoredData() {
		HashMap<String, Object> options = new HashMap<String, Object>();
		options.put(IProviderHints.DEFAULT_PASSWORD, new PBEKeySpec("connection".toCharArray()));
		try {
			LOGGER.debug("initialize.");
			this.connectionPreferences = SecurePreferencesFactory.open(PreferenceStoredData.getPreferenceURL("connection.list"), options);
		} catch (IOException e) {
			throw new SystemException(e);
		}
		this.gson = new Gson();
	}

	@Override
	public void addConnection(String connectionName, ConnectionInfo connectionInfo) {
		LOGGER.debug("addConnection:{}", connectionName);
		ISecurePreferences preferences = this.connectionPreferences.node(connectionName);
		try {
			preferences.put("connectionInfo", getGsonData(connectionInfo), true);
			preferences.flush();
		} catch (StorageException e) {
			throw new SystemException(e);
		} catch (IOException e) {
			throw new SystemException(e);
		}
	}

	@Override
	public ConnectionInfo getConnection(String connectionName) {
		ISecurePreferences preferences = this.connectionPreferences.node(connectionName);
		String connectionInfo = "";
		try {
			connectionInfo = preferences.get(connectionName, "");
		} catch (StorageException e) {
			throw new SystemException(e);
		}
		return getConnectionInfo(connectionInfo);
	}

	@Override
	public String[] getConnectionNames() {
		return this.connectionPreferences.keys();
	}

	private String getGsonData(ConnectionInfo connectionInfo) {
		return this.gson.toJson(connectionInfo);
	}

	private ConnectionInfo getConnectionInfo(String connectionInfo) {
		return this.gson.fromJson(connectionInfo, ConnectionInfo.class);
	}
}
