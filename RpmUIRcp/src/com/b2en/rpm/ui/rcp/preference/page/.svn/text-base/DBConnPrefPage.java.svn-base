package com.b2en.rpm.ui.rcp.preference.page;

import org.apache.log4j.Logger;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import com.b2en.common.util.PingTestUtil;
import com.b2en.rpm.ui.rcp.Activator;
import com.b2en.rpm.ui.rcp.cons.RpmKeyStore;
import com.b2en.rpm.ui.rcp.util.DBInfoUtil;
import com.b2en.rpm.ui.rcp.util.DBInfoUtil.Conn_Result;
import com.b2en.rpm.ui.rcp.util.RpmRcpUtil;

public class DBConnPrefPage extends PreferencePage implements IWorkbenchPreferencePage {

	public static final String ID = "DBConnSettingPreferencePage";
	private Logger logger = Logger.getLogger(getClass());
	
	private Text hostText;
	private Text portText;
	private Text sidText;
	private Text userText;
	private Text passwdText;
	private Text ownerText;
	
	public DBConnPrefPage() {
	}
	
	protected Control createContents(Composite parent) {
		Composite top = new Composite(parent, SWT.LEFT);
		top.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		top.setLayout(new GridLayout(4, false));
		
		Label hostLabel = new Label(top, SWT.NONE);
		hostLabel.setText("Host");
		hostText = new Text(top, SWT.BORDER);
		hostText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
		hostText.setText(getPreferenceStore().getString(RpmKeyStore.DB_HOST));
		new Label(top, SWT.NONE);
		
		Label portLabel = new Label(top, SWT.NONE);
		portLabel.setText("Port");
		portText = new Text(top, SWT.BORDER);
		portText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		portText.setText(getPreferenceStore().getString(RpmKeyStore.DB_PORT));
		
		Button pingTestBtn = new Button(top, SWT.NONE);
		pingTestBtn.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		pingTestBtn.setText("Ping Test");
		pingTestBtn.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(isPingOk()){
					RpmRcpUtil.showMessageBox(getShell(), "Ping Test", "Success.", SWT.ICON_INFORMATION | SWT.OK);
				}
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		new Label(top, SWT.NONE);
		
		Label sidLabel = new Label(top, SWT.NONE);
		sidLabel.setText("SID");
		sidText = new Text(top, SWT.BORDER);
		sidText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
		sidText.setText(getPreferenceStore().getString(RpmKeyStore.DB_SID));
		new Label(top, SWT.NONE);
		
		Label userLabel = new Label(top, SWT.NONE);
		userLabel.setText("User");
		userText = new Text(top, SWT.BORDER);
		userText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		userText.setText(getPreferenceStore().getString(RpmKeyStore.DB_USERNAME));
		new Label(top, SWT.NONE);
		new Label(top, SWT.NONE);
		
		Label passwdLabel = new Label(top, SWT.NONE);
		passwdLabel.setText("Password");
		passwdText = new Text(top, SWT.BORDER|SWT.PASSWORD);
		passwdText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		passwdText.setText(getPreferenceStore().getString(RpmKeyStore.DB_PASSWORD));
		
		Button connTestBtn = new Button(top, SWT.NONE);
		connTestBtn.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1));
		connTestBtn.setText("Connection Test");
		connTestBtn.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				Conn_Result result = checkConnection();
				if(result.isSuccess()){
					RpmRcpUtil.showMessageBox(getShell(), "Connection Test", result.getMessage(), SWT.ICON_INFORMATION | SWT.OK);					
				}
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		new Label(top, SWT.NONE);
		
		Label ownerLabel = new Label(top, SWT.NONE);
		ownerLabel.setText("Owner");
		ownerText = new Text(top, SWT.BORDER);
		ownerText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		ownerText.setText(getPreferenceStore().getString(RpmKeyStore.DB_OWNER));
		new Label(top, SWT.NONE);
		new Label(top, SWT.NONE);
		
		return top;
	}

	@Override
	public void init(IWorkbench workbench) {
		setPreferenceStore(Activator.getDefault().getPreferenceStore());
		setDescription("General Settings for Database Connection Information.");
	}

	@Override
	public boolean performOk(){
		Conn_Result result = checkConnection();
		if(!result.isSuccess()){
			return false;
		}
		
		getPreferenceStore().setValue(RpmKeyStore.DB_HOST, hostText.getText());
		getPreferenceStore().setValue(RpmKeyStore.DB_PORT, portText.getText());
		getPreferenceStore().setValue(RpmKeyStore.DB_SID, sidText.getText());
		getPreferenceStore().setValue(RpmKeyStore.DB_USERNAME, userText.getText());
		getPreferenceStore().setValue(RpmKeyStore.DB_PASSWORD, passwdText.getText());
		getPreferenceStore().setValue(RpmKeyStore.DB_OWNER, ownerText.getText());
		
		DBInfoUtil.updateDBInfoByPref();
		RpmRcpUtil.activeViewReflesh();
		
		return true;
	}
	
	private Conn_Result checkConnection(){
		Conn_Result result = Conn_Result.FAIL;
		if(isPingOk()){
			String host = hostText.getText();
			String port = portText.getText();
			String sid = sidText.getText();
			String user = userText.getText();
			String password = passwdText.getText();
			result = DBInfoUtil.isConnected(host, port, sid, user, password);
			if(!result.isSuccess()){
				RpmRcpUtil.showMessageBox(getShell(), "Connection Test", result.getMessage(), SWT.ICON_ERROR | SWT.OK);
			}					
		}
		return result;
	}
	
	private boolean isPingOk(){
		String host = hostText.getText();
		if(logger.isDebugEnabled()){
			logger.debug("##### Host>>"+host);
		}
		if(!RpmRcpUtil.isIp(host)){
			RpmRcpUtil.showMessageBox(getShell(), "Ping Test", "Invaild Host.", SWT.ICON_ERROR | SWT.OK);
			return false;
		}
		String port = portText.getText();
		if(logger.isDebugEnabled()){
			logger.debug("##### Port>>"+port);
		}
		if(!RpmRcpUtil.isSimpleNumber(port)){
			RpmRcpUtil.showMessageBox(getShell(), "Ping Test", "Invaild Port.", SWT.ICON_ERROR | SWT.OK);
			return false;
		}
		if(!PingTestUtil.isPing(host, Integer.parseInt(port))){
			RpmRcpUtil.showMessageBox(getShell(), "Ping Test", "Fail.", SWT.ICON_ERROR | SWT.OK);
			return false;
		}
		return true;
	}
}
