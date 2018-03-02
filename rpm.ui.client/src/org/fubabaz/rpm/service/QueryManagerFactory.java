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

/**
 * @author ejpark
 *
 */
public class QueryManagerFactory {

	/** instance */
	private volatile static QueryManagerFactory instance;
	private QueryManager queryManager;

	public static QueryManagerFactory getInstance() {
		synchronized (QueryManagerFactory.class) {
			if (instance == null) {
				instance = new QueryManagerFactory();
			}
		}
		return instance;
	}

	private QueryManagerFactory() {
	}

	public QueryManager getQueryManager() {
		if (this.queryManager == null) {
			this.queryManager = new QueryManager();
		}
		return this.queryManager;
	}
}
