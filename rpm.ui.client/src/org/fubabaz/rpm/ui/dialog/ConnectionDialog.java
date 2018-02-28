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

package org.fubabaz.rpm.ui.dialog;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.BeanProperties;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.fubabaz.rpm.connection.ConnectionInfo;
import org.fubabaz.rpm.connection.ConnectionTest;
import org.fubabaz.rpm.connection.DbmsType;
import org.fubabaz.rpm.connection.IConnectionData;
import org.fubabaz.rpm.connection.PingTest;
import org.fubabaz.rpm.connection.PingTest.PING_RESULT;
import org.fubabaz.rpm.connection.pool.ConnectionPoolFactory;
import org.fubabaz.rpm.connection.pool.IConnectionInitializer;
import org.fubabaz.rpm.ui.connection.ConnectionDataFactory;
import org.fubabaz.rpm.ui.connection.ConnectionInfoBinder;
import org.fubabaz.rpm.ui.connection.PortVerifyListener;
import org.fubabaz.rpm.ui.table.ObjectArrayColumnLabelProvider;
import org.fubabaz.rpm.ui.util.ImagePath;
import org.fubabaz.rpm.ui.util.ResourceUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.net.HostSpecifier;

/**
 * @author ejpark
 *
 */
public class ConnectionDialog extends Dialog {

	private static final Logger LOGGER = LoggerFactory.getLogger(ConnectionDialog.class);

	private static final String DIALOG_TITLE = "Connections/New Connection";
	private static final int SAVE_BUTTON_ID = 100;
	private static final int TEST_BUTTON_ID = 200;
	private static final String SAVE_BUTTON_LABEL = "Save";
	private static final String TEST_BUTTON_LABEL = "Test";
	private static final String CONNECT_BUTTON_LABEL = "Connect";

	private final ConnectionInfoBinder connectionInfoBinder;
	private final IConnectionData connectionData;

	private TableViewer connectionListViewer;

	private DbmsType dbmsType;
	private Combo dbmsCls;
	private Text connctionName;
	private Text host;
	private Text port;
	private Combo connectType;
	private Text databaseName;
	private Text user;
	private Text password;
	private Text jdbcOption;

	private Button btn_ping;
	private Button ckbtn_savePassword;

	public ConnectionDialog(Shell parentShell) {
		super(parentShell);
		connectionInfoBinder = new ConnectionInfoBinder(new ConnectionInfo());
		connectionData = ConnectionDataFactory.getInstance().getConnectionData();
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite area = (Composite) super.createDialogArea(parent);
		area.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		area.setLayout(new GridLayout(2, false));

		Composite listComp = new Composite(area, SWT.NONE);
		GridData gd_listComp = new GridData(SWT.FILL, SWT.FILL, true, false);
		gd_listComp.widthHint = 360;
		listComp.setLayoutData(gd_listComp);
		listComp.setLayout(new GridLayout(1, false));

		connectionListViewer = new TableViewer(listComp);
		Table table = connectionListViewer.getTable();
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		connectionListViewer.setContentProvider(new ArrayContentProvider());
		connectionListViewer.getTable().setHeaderVisible(true);
		connectionListViewer.getTable().setLinesVisible(true);

		TableViewerColumn dpNameViewerColumn = new TableViewerColumn(this.connectionListViewer, SWT.NONE);
		dpNameViewerColumn.getColumn().setWidth(80);
		dpNameViewerColumn.getColumn().setAlignment(SWT.LEFT);
		dpNameViewerColumn.getColumn().setText("Name");
		dpNameViewerColumn.setLabelProvider(new ObjectArrayColumnLabelProvider(0));

		TableViewerColumn hostViewerColumn = new TableViewerColumn(this.connectionListViewer, SWT.NONE);
		hostViewerColumn.getColumn().setWidth(140);
		hostViewerColumn.getColumn().setAlignment(SWT.LEFT);
		hostViewerColumn.getColumn().setText("Host");
		hostViewerColumn.setLabelProvider(new ObjectArrayColumnLabelProvider(1));

		TableViewerColumn portViewerColumn = new TableViewerColumn(this.connectionListViewer, SWT.NONE);
		portViewerColumn.getColumn().setWidth(60);
		portViewerColumn.getColumn().setAlignment(SWT.LEFT);
		portViewerColumn.getColumn().setText("Port");
		portViewerColumn.setLabelProvider(new ObjectArrayColumnLabelProvider(2));

		TableViewerColumn dbNameViewerColumn = new TableViewerColumn(this.connectionListViewer, SWT.NONE);
		dbNameViewerColumn.getColumn().setWidth(60);
		dbNameViewerColumn.getColumn().setAlignment(SWT.LEFT);
		dbNameViewerColumn.getColumn().setText("Database");
		dbNameViewerColumn.setLabelProvider(new ObjectArrayColumnLabelProvider(3));

		Button btn_delete = new Button(listComp, SWT.NONE);
		btn_delete.setText("Delete");
		btn_delete.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				TableItem[] selectedItems = connectionListViewer.getTable().getSelection();
				for (TableItem selectedItem : selectedItems) {
					String connectionName = selectedItem.getText(0);
					connectionData.removeConnectionInfo(connectionName);
				}
				refreshConnectionList();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});

		Composite container = new Composite(area, SWT.NONE);
		container.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		container.setLayout(new GridLayout(1, false));

		Composite dbmsComposite = new Composite(container, SWT.BORDER);
		dbmsComposite.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false));
		dbmsComposite.setLayout(new GridLayout(2, true));

		Label lbl_dbms = new Label(dbmsComposite, SWT.RIGHT);
		lbl_dbms.setText("Database");
		lbl_dbms.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));

		dbmsCls = new Combo(dbmsComposite, SWT.BORDER | SWT.READ_ONLY);
		for (DbmsType dbmsType : DbmsType.values()) {
			dbmsCls.add(dbmsType.getName());
		}
		dbmsCls.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		dbmsCls.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				int selectedIdx = ((Combo) e.widget).getSelectionIndex();
				dbmsType = DbmsType.values()[selectedIdx];
				port.setText(String.valueOf(dbmsType.getDefaultPort()));

				connectType.removeAll();
				connectType.setItems(dbmsType.getDatabaseNames());
				connectType.select(0);
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});

		Label lbl_connctionName = new Label(dbmsComposite, SWT.RIGHT);
		lbl_connctionName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		lbl_connctionName.setText("Display Name");

		connctionName = new Text(dbmsComposite, SWT.BORDER);
		connctionName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, true));

		Group dbmsGroup = new Group(container, SWT.NONE);
		dbmsGroup.setText("Connection Info.");
		GridLayout gl_dbmsGroup = new GridLayout(3, false);
		dbmsGroup.setLayout(gl_dbmsGroup);
		dbmsGroup.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false));

		Label lbl_host = new Label(dbmsGroup, SWT.RIGHT);
		lbl_host.setText("Host");
		lbl_host.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false));
		host = new Text(dbmsGroup, SWT.BORDER);
		host.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, true, 2, 1));
		host.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e) {
				if (!host.getText().isEmpty() && !port.getText().isEmpty()) {
					btn_ping.setEnabled(true);
				} else {
					btn_ping.setEnabled(false);
				}
			}
		});

		Label lbl_port = new Label(dbmsGroup, SWT.RIGHT);
		lbl_port.setText("Port");
		lbl_port.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false));
		port = new Text(dbmsGroup, SWT.BORDER);
		port.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, true));
		port.addVerifyListener(new PortVerifyListener());
		port.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e) {
				if (!host.getText().isEmpty() && !port.getText().isEmpty()) {
					btn_ping.setEnabled(true);
				} else {
					btn_ping.setEnabled(false);
				}
			}
		});

		btn_ping = new Button(dbmsGroup, SWT.NONE);
		btn_ping.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		btn_ping.setText("Ping");
		btn_ping.setEnabled(false);
		btn_ping.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				if (isValidNetwork()) {
					MessageDialog.open(MessageDialog.INFORMATION, getShell(), "Information", "Success.", SWT.NONE);
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});

		connectType = new Combo(dbmsGroup, SWT.READ_ONLY);
		connectType.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		databaseName = new Text(dbmsGroup, SWT.BORDER);
		databaseName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, true, 2, 1));

		new Label(dbmsGroup, SWT.NONE);
		Label lbl_1 = new Label(dbmsGroup, SWT.SEPARATOR | SWT.HORIZONTAL);
		lbl_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 2, 1));

		Label lbl_username = new Label(dbmsGroup, SWT.RIGHT);
		lbl_username.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		lbl_username.setText("User Name");
		user = new Text(dbmsGroup, SWT.BORDER);
		user.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, true, 2, 1));

		Label lbl_password = new Label(dbmsGroup, SWT.RIGHT);
		lbl_password.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		lbl_password.setText("Password");
		password = new Text(dbmsGroup, SWT.BORDER | SWT.PASSWORD);
		password.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, true, 2, 1));
		new Label(dbmsGroup, SWT.NONE);

		ckbtn_savePassword = new Button(dbmsGroup, SWT.CHECK);
		ckbtn_savePassword.setText("Save Password");
		new Label(dbmsGroup, SWT.NONE);
		new Label(dbmsGroup, SWT.NONE);

		Label lbl_2 = new Label(dbmsGroup, SWT.SEPARATOR | SWT.HORIZONTAL);
		lbl_2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 2, 1));

		Label lbl_option = new Label(dbmsGroup, SWT.RIGHT);
		lbl_option.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		lbl_option.setText("JDBC Option");

		jdbcOption = new Text(dbmsGroup, SWT.BORDER);
		jdbcOption.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));

		bindValues();

		dbmsCls.select(0);
		dbmsCls.notifyListeners(SWT.Selection, new Event());

		initConnectionList();

		connectionListViewer.addPostSelectionChangedListener(new ISelectionChangedListener() {

			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				StructuredSelection selection = (StructuredSelection) event.getSelection();
				Object[] connectionObject = (Object[]) selection.getFirstElement();
				if (connectionObject != null) {
					String connectionName = String.valueOf(connectionObject[0]);
					LOGGER.debug("connectionName:{}", connectionName);
					ConnectionInfo connectionInfo = connectionData.getConnectionInfo(connectionName);
					LOGGER.debug("connectionInfo:{}", connectionInfo.toString());

					clearForm();

					connctionName.setText(connectionName);
					dbmsCls.select(DbmsType.getIndex(connectionInfo.getDbmsType()));
					dbmsCls.notifyListeners(SWT.Selection, new Event());

					host.setText(connectionInfo.getHost());
					port.setText(String.valueOf(connectionInfo.getPort()));
					connectType.select(connectionInfo.getConnectType());
					databaseName.setText(connectionInfo.getDatabaseName());
					user.setText(connectionInfo.getUser());
					if (connectionInfo.isSavePassword()) {
						password.setText(connectionInfo.getPassword());
						ckbtn_savePassword.setSelection(connectionInfo.isSavePassword());
					}
					jdbcOption.setText(connectionInfo.getJdbcOption());

					// Object non-event value set binding is not working.
					connectionInfoBinder.setDbmsType(DbmsType.getIndex(connectionInfo.getDbmsType()));
					connectionInfoBinder.setConnectType(connectionInfo.getConnectType());
					connectionInfoBinder.setSavePassword(connectionInfo.isSavePassword());

					LOGGER.debug("binding connectionInfo:{}", connectionInfoBinder.getConnectionInfo());
				}
			}
		});

		connectionListViewer.addDoubleClickListener(new IDoubleClickListener() {

			@Override
			public void doubleClick(DoubleClickEvent event) {
				buttonPressed(IDialogConstants.OK_ID);
			}
		});
		return area;
	}

	private void refreshConnectionList() {
		connectionListViewer.getTable().clearAll();
		initConnectionList();
	}

	private void clearForm() {
		dbmsCls.clearSelection();
		connctionName.setText("");
		host.setText("");
		port.setText("");
		connectType.clearSelection();
		databaseName.setText("");
		user.setText("");
		password.setText("");
		ckbtn_savePassword.setSelection(false);
		jdbcOption.setText("");
	}

	private void initConnectionList() {
		String[] connectionNames = connectionData.getConnectionNames();
		Object[] connectionNameNodes = new Object[connectionNames.length];
		for (int i = 0; i < connectionNameNodes.length; i++) {
			ConnectionInfo connectionInfo = connectionData.getConnectionInfo(connectionNames[i]);
			Object[] connectionNameNode = { connectionNames[i], connectionInfo.getHost(), connectionInfo.getPort(),
					connectionInfo.getDatabaseName() };
			connectionNameNodes[i] = connectionNameNode;
		}
		connectionListViewer.setInput(connectionNameNodes);
	}

	private void bindValues() {
		DataBindingContext ctx = new DataBindingContext();
		ctx.bindValue(WidgetProperties.singleSelectionIndex().observe(dbmsCls),
				BeanProperties.value(ConnectionInfoBinder.class, "dbmsType").observe(connectionInfoBinder));
		ctx.bindValue(WidgetProperties.text(SWT.Modify).observe(host),
				BeanProperties.value(ConnectionInfoBinder.class, "host").observe(connectionInfoBinder));
		ctx.bindValue(WidgetProperties.text(SWT.Modify).observe(port),
				BeanProperties.value(ConnectionInfoBinder.class, "port").observe(connectionInfoBinder));
		ctx.bindValue(WidgetProperties.singleSelectionIndex().observe(connectType),
				BeanProperties.value(ConnectionInfoBinder.class, "connectType").observe(connectionInfoBinder));
		ctx.bindValue(WidgetProperties.text(SWT.Modify).observe(databaseName),
				BeanProperties.value(ConnectionInfoBinder.class, "databaseName").observe(connectionInfoBinder));
		ctx.bindValue(WidgetProperties.text(SWT.Modify).observe(user),
				BeanProperties.value(ConnectionInfoBinder.class, "user").observe(connectionInfoBinder));
		ctx.bindValue(WidgetProperties.text(SWT.Modify).observe(password),
				BeanProperties.value(ConnectionInfoBinder.class, "password").observe(connectionInfoBinder));
		ctx.bindValue(WidgetProperties.selection().observe(ckbtn_savePassword),
				BeanProperties.value(ConnectionInfoBinder.class, "savePassword").observe(connectionInfoBinder));
		ctx.bindValue(WidgetProperties.text(SWT.Modify).observe(jdbcOption),
				BeanProperties.value(ConnectionInfoBinder.class, "jdbcOption").observe(connectionInfoBinder));
	}

	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText(DIALOG_TITLE);
		newShell.setImage(ResourceUtil.getImage(ImagePath.ICONS_CONNECTIONS));
	}

	@Override
	protected Point getInitialSize() {
		return new Point(740, 500);
	}

	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		createButton(parent, SAVE_BUTTON_ID, SAVE_BUTTON_LABEL, false);
		createButton(parent, TEST_BUTTON_ID, TEST_BUTTON_LABEL, false);
		createButton(parent, IDialogConstants.OK_ID, CONNECT_BUTTON_LABEL, true);
		createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL, false);
	}

	protected void buttonPressed(int buttonId) {
		if (IDialogConstants.OK_ID == buttonId) {
			okPressed();
		} else if (IDialogConstants.CANCEL_ID == buttonId) {
			cancelPressed();
		} else if (TEST_BUTTON_ID == buttonId) {
			connectionTest();
		} else if (SAVE_BUTTON_ID == buttonId) {
			saveConnectionData();
		}
	}

	@Override
	protected void okPressed() {
		ConnectionTest.test(connectionInfoBinder.getConnectionInfo());
		if (connectionInfoBinder.getConnectionInfo().isValid()) {
			IConnectionInitializer connectionInitializer = (IConnectionInitializer) ConnectionPoolFactory.getInstance()
					.getConnectionPool();
			connectionInitializer.initialize(connectionInfoBinder.getConnectionInfo());
			super.okPressed();
		} else {
			MessageDialog.open(MessageDialog.WARNING, getShell(), "Check",
					connectionInfoBinder.getConnectionInfo().getMessage(), SWT.NONE);
		}
	}

	private void connectionTest() {
		LOGGER.debug("connectionInfoBinder:{}", connectionInfoBinder.toString());

		if (isValidConnectMandatoy() && isValidNetwork()) {
			ConnectionTest.test(connectionInfoBinder.getConnectionInfo());
			if (connectionInfoBinder.getConnectionInfo().isValid()) {
				connectionInfoBinder.getConnectionInfo().setValid(false);
				MessageDialog.open(MessageDialog.INFORMATION, getShell(), "Information", "Success.", SWT.NONE);
			} else {
				MessageDialog.open(MessageDialog.WARNING, getShell(), "Check",
						connectionInfoBinder.getConnectionInfo().getMessage(), SWT.NONE);
			}
		}
	}

	private void saveConnectionData() {
		if (isValidSave()) {
			connectionData.addConnectionInfo(connctionName.getText(), connectionInfoBinder.getConnectionInfo());
			refreshConnectionList();
		}
	}

	private boolean isValidNetworkMandatoy() {
		if (host.getText().isEmpty()) {
			MessageDialog.open(MessageDialog.WARNING, getShell(), "Check", "Host is empty.", SWT.NONE);
			host.setFocus();
			return false;
		}
		if (port.getText().isEmpty()) {
			MessageDialog.open(MessageDialog.WARNING, getShell(), "Check", "Port is empty.", SWT.NONE);
			port.setFocus();
			return false;
		}
		return true;
	}

	private boolean isValidSave() {
		if (connctionName.getText().isEmpty()) {
			MessageDialog.open(MessageDialog.WARNING, getShell(), "Check", "Display Name is empty.", SWT.NONE);
			connctionName.setFocus();
			return false;
		}
		return true;
	}

	private boolean isValidConnectMandatoy() {
		if (connctionName.getText().isEmpty()) {
			MessageDialog.open(MessageDialog.WARNING, getShell(), "Check", "Display Name is empty.", SWT.NONE);
			connctionName.setFocus();
			return false;
		}
		if (databaseName.getText().isEmpty()) {
			MessageDialog.open(MessageDialog.WARNING, getShell(), "Check", "Database Name is empty.", SWT.NONE);
			databaseName.setFocus();
			return false;
		}
		if (user.getText().isEmpty()) {
			MessageDialog.open(MessageDialog.WARNING, getShell(), "Check", "User Name is empty.", SWT.NONE);
			user.setFocus();
			return false;
		}
		if (password.getText().isEmpty()) {
			MessageDialog.open(MessageDialog.WARNING, getShell(), "Check", "Password is empty.", SWT.NONE);
			password.setFocus();
			return false;
		}
		return true;
	}

	private boolean isValidNetwork() {
		if (!isValidNetworkMandatoy()) {
			return false;
		}
		String hostStr = host.getText();
		if (!HostSpecifier.isValid(hostStr)) {
			MessageDialog.open(MessageDialog.WARNING, getShell(), "Check", "Host is not valid.", SWT.NONE);
			host.setFocus();
			return false;
		}
		int portNum = Integer.valueOf(port.getText());
		PING_RESULT result = PingTest.ping(hostStr, portNum, 3000);
		if (PING_RESULT.SUCCESS != result) {
			MessageDialog.open(MessageDialog.WARNING, getShell(), "Check", result.name(), SWT.NONE);
			return false;
		}
		return true;
	}
}
