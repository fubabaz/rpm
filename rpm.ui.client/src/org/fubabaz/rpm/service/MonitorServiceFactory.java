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

import org.fubabaz.rpm.service.monitor.ISystemMonitor;
import org.fubabaz.rpm.service.monitor.SystemMonitorService;

/**
 * @author ejpark
 *
 */
public class MonitorServiceFactory {

	/** instance */
	private volatile static MonitorServiceFactory instance;

	public static MonitorServiceFactory getInstance() {
		synchronized (MonitorServiceFactory.class) {
			if (instance == null) {
				instance = new MonitorServiceFactory();
			}
		}
		return instance;
	}

	private MonitorServiceFactory() {
	}

	public ISystemMonitor getSystemMonitor() {
		ISystemMonitor systemMonitor = new SystemMonitorService();
		return systemMonitor;
	}
}
