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

package org.fubabaz.rpm.ui.part;

import javax.inject.Inject;

import org.eclipse.e4.ui.di.UISynchronize;
import org.fubabaz.rpm.service.ExecuteServiceManager;
import org.fubabaz.rpm.service.IServiceCallback;
import org.fubabaz.rpm.service.ServiceManagerFactory;
import org.fubabaz.rpm.service.sql.SqlExecuteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author ejpark
 *
 */
public abstract class SqlServiceView implements IServiceCallback {

	private static final Logger LOGGER = LoggerFactory.getLogger(SqlServiceView.class);
	private ExecuteServiceManager executeServiceManager;
	private SqlExecuteService sqlExecuteService;

	@Inject
	private UISynchronize uiSync;

	protected SqlServiceView() {
		this.executeServiceManager = ServiceManagerFactory.getInstance().getServiceManager();
		this.sqlExecuteService = new SqlExecuteService(this);
	}

	protected void executeSql(String sql, int fetchSize) {
		this.sqlExecuteService.setSql(sql, fetchSize);
		this.executeServiceManager.execute(this.sqlExecuteService);
	}

	@Override
	public void start(Object result, String attachement) {
		LOGGER.debug("start:{}", result);
		uiSync.asyncExec(new Runnable() {

			@Override
			public void run() {
				start_callback(result, attachement);
			}
		});
	}

	@Override
	public void process(Object result, String attachement) {
		LOGGER.debug("process:{}", result);
		uiSync.asyncExec(new Runnable() {

			@Override
			public void run() {
				process_callback(result, attachement);
			}
		});
	}

	@Override
	public void end(Object result, String attachement) {
		LOGGER.debug("end:{}", result);
		uiSync.asyncExec(new Runnable() {

			@Override
			public void run() {
				end_callback(result, attachement);
			}
		});
	}

	@Override
	public void error(Throwable exception, String attachment) {
		LOGGER.error(attachment, exception);
	}

	public void start_callback(Object result, String attachement) {};
	public void process_callback(Object result, String attachement) {};
	public void end_callback(Object result, String attachement) {}
}
