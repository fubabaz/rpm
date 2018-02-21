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

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

import org.fubabaz.rpm.service.sql.SqlExecuteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author ejpark
 *
 */
public class SqlServiceManager {

	private static final Logger LOGGER = LoggerFactory.getLogger(SqlServiceManager.class);
	private final ExecutorService executorService;

	public SqlServiceManager() {
		int theadCnt = Runtime.getRuntime().availableProcessors();
		LOGGER.debug("availableProcessors:{}", theadCnt);
		this.executorService = Executors.newFixedThreadPool(theadCnt);
	}

	public void start(String sql, IServiceCallback serviceCallback) {
		SqlExecuteService service = new SqlExecuteService(sql, serviceCallback);
		LOGGER.debug("submit:{}", sql);
		Future<?> future = this.executorService.submit(service);
		status();
		try {
			future.get();
		} catch (InterruptedException e) {
			serviceCallback.failed(e, sql);
		} catch (ExecutionException e) {
			serviceCallback.failed(e, sql);
		}
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
