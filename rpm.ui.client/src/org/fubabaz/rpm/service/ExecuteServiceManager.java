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

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author ejpark
 *
 */
public class ExecuteServiceManager {

	private static final Logger LOGGER = LoggerFactory.getLogger(ExecuteServiceManager.class);
	private final ExecutorService executorService;

	public ExecuteServiceManager() {
		int theadCnt = Runtime.getRuntime().availableProcessors();
		LOGGER.debug("availableProcessors:{}", theadCnt);
		this.executorService = Executors.newFixedThreadPool(theadCnt);
	}

	public Future<?> execute(Runnable service) {
		Future<?> future = this.executorService.submit(service);
		status();
		return future;
	}

	public void finish() {
		this.executorService.shutdown();
	}

	public void status() {
		ThreadPoolExecutor threadPool = (ThreadPoolExecutor) this.executorService;
		LOGGER.debug("Pool:{}, Active:{}, CorePool:{}, MaximumPool:{}", threadPool.getPoolSize(),
				threadPool.getActiveCount(), threadPool.getCorePoolSize(), threadPool.getMaximumPoolSize());
	}
}
