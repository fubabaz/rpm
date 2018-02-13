/**
 * Copyright (c) 2018 fubabaz 
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
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
