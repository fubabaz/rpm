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

package org.fubabaz.rpm.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.InvalidPropertiesFormatException;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import org.fubabaz.rpm.exception.SystemException;

/**
 * @author ejpark
 *
 */
public class QueryManager {

	private Map<String, String> queryMap = new HashMap<String, String>();
	private final String path = "query";

	public QueryManager() {
		load(path);
	}

	private void load(String path) {
		File dir = new File(Thread.currentThread().getContextClassLoader().getResource(path).getFile());

		if (dir == null || !dir.exists()) {
			throw new IllegalArgumentException(path + " not found.");
		}

		if (dir.isDirectory()) {
			File[] files = dir.listFiles();
			for (File file : files) {
				loadFile(file);
			}
		} else {
			loadFile(dir);
		}
	}

	private void loadFile(File file) {
		InputStream in = null;
		Properties props = new Properties();
		try {
			String fileName = file.getName();
			int dotIndex = fileName.lastIndexOf('.');
			String extension = fileName.substring(dotIndex + 1).toLowerCase(Locale.ENGLISH);
			if("xml".equals(extension)) {
				in = new FileInputStream(file);
				props.loadFromXML(in);				
			}
		} catch (InvalidPropertiesFormatException e) {
			throw new SystemException(e);
		} catch (IOException e) {
			throw new SystemException(e);
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				// Ignore.
			}
		}
		queryMap.putAll(new HashMap(props));
	}

	public String getQuery(String key) {
		return this.queryMap.get(key);
	}
}
