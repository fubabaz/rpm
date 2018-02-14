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

package org.fubabaz.rpm.connection;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author ejpark
 *
 */
public class PingTest implements Runnable {

	private static final Logger LOGGER = LoggerFactory.getLogger(PingTest.class);

	public enum PING_RESULT {
		SUCCESS, HOST_NOT_FOUND, NOT_CONNECTED, TIMEOUT;
	};

	private String host;
	private int port;
	private int maxWait;
	private PING_RESULT result;

	private PingTest(String host, int port, int maxWait) {
		this.host = host;
		this.port = port;
		this.maxWait = maxWait;
		this.result = PING_RESULT.HOST_NOT_FOUND;
	}

	@Override
	public void run() {
		Socket socket = null;
		try {
			LOGGER.debug("inetSocketAddress>>host:{}, port:{}", host, port);
			InetSocketAddress inetSocketAddress = new InetSocketAddress(host, port);
			if (inetSocketAddress.isUnresolved()) {
				LOGGER.debug("inetSocketAddress>>isUnresolved:{}", inetSocketAddress.isUnresolved());
				this.result = PING_RESULT.HOST_NOT_FOUND;
			} else {
				LOGGER.debug("socket>>maxWait:{}", maxWait);
				socket = new Socket();
				socket.connect(inetSocketAddress, maxWait);
				if (socket.isConnected()) {
					result = PING_RESULT.SUCCESS;
				} else {
					result = PING_RESULT.NOT_CONNECTED;
				}
			}
			socket.close();
		} catch (IOException ex) {
			result = PING_RESULT.NOT_CONNECTED;
		} finally {
			if (socket != null) {
				try {
					socket.close();
				} catch (IOException e) {
					// Ignore.
				}
			}
		}
	}

	public static PING_RESULT ping(String host, int port, int maxWait) {
		LOGGER.debug("host:{}, port:{}, maxWait:{}", host, port, maxWait);
		PingTest pingTest = new PingTest(host, port, maxWait);

		Thread ping = new Thread(pingTest);
		ping.setDaemon(true);
		ping.start();
		try {
			ping.join(maxWait);
		} catch (InterruptedException e) {
			return PING_RESULT.TIMEOUT;
		}
		return pingTest.result;
	}
}
