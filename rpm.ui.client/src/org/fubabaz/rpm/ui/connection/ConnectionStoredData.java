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
import java.util.Arrays;
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
	private static final String PREFERENCE_URL = "connection.list";
	private ISecurePreferences connectionPreferences;
	private Gson gson;

	public ConnectionStoredData() {
		HashMap<String, Object> options = new HashMap<String, Object>();
		options.put(IProviderHints.DEFAULT_PASSWORD, new PBEKeySpec(PREFERENCE_URL.toCharArray()));
		try {
			LOGGER.debug("initialize.");
			this.connectionPreferences = SecurePreferencesFactory.open(PreferenceStoredData.getPreferenceURL(PREFERENCE_URL), options);
		} catch (IOException e) {
			throw new SystemException(e);
		}
		this.gson = new Gson();
	}

	@Override
	public void addConnectionInfo(String connectionName, ConnectionInfo connectionInfo) {
		LOGGER.debug("addConnection:{}", connectionName);
		try {
			this.connectionPreferences.put(connectionName, getGsonData(connectionInfo), true);
			this.connectionPreferences.flush();
		} catch (StorageException e) {
			throw new SystemException(e);
		} catch (IOException e) {
			throw new SystemException(e);
		}
	}

	@Override
	public void removeConnectionInfo(String connectionName) {
		this.connectionPreferences.remove(connectionName);
	}

	@Override
	public ConnectionInfo getConnectionInfo(String connectionName) {
		String connectionInfoJson = "";
		try {
			connectionInfoJson = this.connectionPreferences.get(connectionName, "");
		} catch (StorageException e) {
			throw new SystemException(e);
		}
		return getConnectionInfoFromJson(connectionInfoJson);
	}

	@Override
	public String[] getConnectionNames() {
		String[] keys = this.connectionPreferences.keys();
		Arrays.sort(keys);
		return keys;
	}

	private String getGsonData(ConnectionInfo connectionInfo) {
		return this.gson.toJson(connectionInfo);
	}

	private ConnectionInfo getConnectionInfoFromJson(String connectionInfoJson) {
		return this.gson.fromJson(connectionInfoJson, ConnectionInfo.class);
	}
}
