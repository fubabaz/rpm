package org.fubabaz.rpm.ui.connection;

import org.eclipse.jface.viewers.TreeNode;
import org.fubabaz.rpm.connection.ConnectionInfo;

public class ConnectionInfoNode extends TreeNode {

	public ConnectionInfoNode(Object value, ConnectionInfo connectionInfo) {
		super(new Object[] { value, connectionInfo.getHost(), connectionInfo.getPort(),
				connectionInfo.getDatabaseName() });
	}
}
