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
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @author ejpark
 *
 */
public class PingTest implements Runnable {

	public enum PING_RESULT {
		SUCCESS,
		HOST_NOT_FOUND,
		NOT_CONNECTED,
		TIMEOUT
		;
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
			InetAddress inetAddress = InetAddress.getByName(host);
			if (inetAddress.isReachable(maxWait)) {
				socket = new Socket(inetAddress, port);
				if(socket.isConnected()) {
					result = PING_RESULT.SUCCESS;
				}else {
					result = PING_RESULT.NOT_CONNECTED;
				}
			} else {
				result = PING_RESULT.HOST_NOT_FOUND;
			}
		} catch (UnknownHostException e) {
			result = PING_RESULT.HOST_NOT_FOUND;
		} catch (IOException e) {
			result = PING_RESULT.NOT_CONNECTED;
		} finally {
			if(socket!=null) {
				try {
					socket.close();
				} catch (IOException e) {
					//Ignore.
				}
			}
		}
	}

	public static PING_RESULT ping(String host, int port, int maxWait) {
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
