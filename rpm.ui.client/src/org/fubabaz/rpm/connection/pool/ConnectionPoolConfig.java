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

package org.fubabaz.rpm.connection.pool;

/**
 * @author ejpark
 *
 */
public class ConnectionPoolConfig {

	public static final DBPoolType DB_POOL_TYPE = DBPoolType.DBCP;
	public static final int INIT_CONN_CNT = 5;
	public static final int MAX_CONN_COUNT = 5;
	public static final long MAX_WAIT = 5L;
}
