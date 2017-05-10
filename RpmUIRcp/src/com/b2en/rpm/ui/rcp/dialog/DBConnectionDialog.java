package com.b2en.rpm.ui.rcp.dialog;

import org.apache.log4j.Logger;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.CoolBar;
import org.eclipse.swt.widgets.CoolItem;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.wb.swt.SWTResourceManager;

import com.b2en.common.util.PingTestUtil;
import com.b2en.rpm.ui.rcp.Activator;
import com.b2en.rpm.ui.rcp.cons.RpmKeyStore;
import com.b2en.rpm.ui.rcp.util.DBInfoUtil;
import com.b2en.rpm.ui.rcp.util.DBInfoUtil.Conn_Result;
import com.b2en.rpm.ui.rcp.util.RpmRcpUtil;
import org.eclipse.wb.swt.ResourceManager;

public class DBConnectionDialog extends Dialog {

	private Logger logger = Logger.getLogger(getClass());
	private Shell active = Display.getCurrent().getActiveShell();
	protected Object result;
	protected Shell shlRp;
	private Table table;
	private Text passwdText;
	private Text userText;
	private Text text_2;
	private Text hostText;
	private Text sidText;
	private Text portText;
	private final FormToolkit formToolkit = new FormToolkit(Display.getDefault());

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public DBConnectionDialog(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
	}

	
	public DBConnectionDialog(Shell parent) {

		super(parent);
		setText("SWT Dialog");
	}

	
	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open() {
		createContents();
		shlRp.open();
		shlRp.layout();
		Display display = getParent().getDisplay();
		while (!shlRp.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return result;
	}

	/**
	 * Create contents of the dialog.
	 */
	private void createContents() {
		shlRp = new Shell(getParent(), SWT.COLOR_DARK_GREEN);
		shlRp.setImage(ResourceManager.getPluginImage("RpmUIRcp", "icons/systemmonitor/database_icon.png"));
		shlRp.setSize(697, 428);
		shlRp.setText("RPM Database Login");
		shlRp.setLayout(new GridLayout(2, false));

		CoolBar coolBar = new CoolBar(shlRp, SWT.FLAT);
		coolBar.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 2, 1));
		
		CoolItem coolItem_1 = new CoolItem(coolBar, SWT.NONE);
	
		
		Composite composite = new Composite(shlRp, SWT.BORDER);
		
		table = new Table(composite, SWT.FULL_SELECTION);
		table.setFont(SWTResourceManager.getFont("Tahoma", 8, SWT.NORMAL));
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		table.setBounds(0, 0, 499, 320);
		
		TableColumn tableColumn = new TableColumn(table, SWT.NONE);
		tableColumn.setWidth(0);
		tableColumn.setText("No.");
		tableColumn.setResizable(false);
		
		TableColumn tableColumn_1 = new TableColumn(table, SWT.NONE);
		tableColumn_1.setWidth(134);
		tableColumn_1.setText("Connect Name");
		
		Composite composite_1 = new Composite(shlRp, SWT.BORDER);
		composite_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		composite_1.setLayout(new GridLayout(2, false));
		composite_1.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		
		Label label_4 = new Label(composite_1, SWT.NONE);
		label_4.setText("User Infomation");
		label_4.setFont(SWTResourceManager.getFont("Tahoma", 9, SWT.BOLD));
		label_4.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		new Label(composite_1, SWT.NONE);
		
		Label label_3 = new Label(composite_1, SWT.NONE);
		label_3.setText("Connect Name");
		label_3.setFont(SWTResourceManager.getFont("Tahoma", 8, SWT.NORMAL));
		label_3.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		new Label(composite_1, SWT.NONE);
		
		text_2 = new Text(composite_1, SWT.BORDER);
		text_2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 2, 1));
		text_2.setText("RPM");
		text_2.setFont(SWTResourceManager.getFont("Tahoma", 8, SWT.NORMAL));
		
		Label label_2 = new Label(composite_1, SWT.NONE);
		label_2.setText("User Name");
		label_2.setFont(SWTResourceManager.getFont("Tahoma", 8, SWT.NORMAL));
		label_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		new Label(composite_1, SWT.NONE);
		
		userText = new Text(composite_1, SWT.BORDER);
		userText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 2, 1));
		userText.setText("sys as sysdba");
		userText.setFont(SWTResourceManager.getFont("Tahoma", 8, SWT.NORMAL));
		
		Label label_1 = new Label(composite_1, SWT.NONE);
		label_1.setText("PassWord");
		label_1.setFont(SWTResourceManager.getFont("Tahoma", 8, SWT.NORMAL));
		label_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		new Label(composite_1, SWT.NONE);
		
		passwdText = new Text(composite_1, SWT.BORDER | SWT.PASSWORD);
		passwdText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 2, 1));
		passwdText.setText("oracle");
		passwdText.setFont(SWTResourceManager.getFont("Tahoma", 8, SWT.NORMAL));
		
		Label label = new Label(composite_1, SWT.NONE);
		label.setText("Oracle Infomation");
		label.setFont(SWTResourceManager.getFont("Tahoma", 9, SWT.BOLD));
		label.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		new Label(composite_1, SWT.NONE);
		
		Label label_6 = new Label(composite_1, SWT.NONE);
		label_6.setFont(SWTResourceManager.getFont("Tahoma", 8, SWT.NORMAL));
		label_6.setText("Host");
		label_6.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_6.setLocation(0, 0);
		
		Label label_7 = new Label(composite_1, SWT.NONE);
		label_7.setText("Port");
		label_7.setFont(SWTResourceManager.getFont("Tahoma", 8, SWT.NORMAL));
		label_7.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_7.setLocation(0, 0);
		
		hostText = new Text(composite_1, SWT.BORDER);
		hostText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		hostText.setText("175.195.89.114");
		hostText.setFont(SWTResourceManager.getFont("Tahoma", 8, SWT.NORMAL));
		hostText.setBounds(0, 0, 73, 21);
		
		portText = new Text(composite_1, SWT.BORDER);
		portText.setText("1521");
		portText.setFont(SWTResourceManager.getFont("Tahoma", 8, SWT.NORMAL));
		portText.setBounds(0, 0, 73, 21);
		
		Label label_5 = new Label(composite_1, SWT.NONE);
		label_5.setText("SID");
		label_5.setFont(SWTResourceManager.getFont("Tahoma", 8, SWT.NORMAL));
		label_5.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_5.setLocation(0, 0);
		new Label(composite_1, SWT.NONE);
		
		sidText = new Text(composite_1, SWT.BORDER);
		sidText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 2, 1));
		sidText.setText("ORA12CR1");
		sidText.setFont(SWTResourceManager.getFont("Tahoma", 8, SWT.NORMAL));
		sidText.setBounds(0, 0, 73, 21);
		
		Button connTestBtn = new Button(composite_1, SWT.NONE);
		connTestBtn.setFont(SWTResourceManager.getFont("Tahoma", 8, SWT.NORMAL));
		connTestBtn.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 2, 1));
		connTestBtn.setText("Connection Test");
		connTestBtn.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				Conn_Result result = checkConnection();
				if(result.isSuccess()){
					RpmRcpUtil.showMessageBox(active, "Connection Test", result.getMessage(), SWT.ICON_INFORMATION | SWT.OK);					
				}
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});

		
		Button btnSave = new Button(composite_1, SWT.NONE);
		btnSave.setFont(SWTResourceManager.getFont("Tahoma", 8, SWT.NORMAL));
		btnSave.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 2, 1));
		btnSave.setToolTipText("");
		btnSave.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		btnSave.setText("SAVE");
		formToolkit.adapt(btnSave, true, true);
		
		Composite composite_3 = new Composite(shlRp, SWT.NONE);
	
		
		Button btnDelete = new Button(composite_3, SWT.NONE);
		btnDelete.setText("Delete");
		btnDelete.setFont(SWTResourceManager.getFont("Tahoma", 8, SWT.NORMAL));
		btnDelete.setBounds(0, 0, 73, 25);
		formToolkit.adapt(btnDelete, true, true);
		
		Composite composite_2 = new Composite(shlRp, SWT.NONE);
		GridData gd_composite_2 = new GridData(SWT.FILL, SWT.TOP, false, false, 1, 1);
		gd_composite_2.widthHint = 153;
		composite_2.setLayoutData(gd_composite_2);
		
		
		Button connectionBtn = new Button(composite_2, SWT.NONE);
		connectionBtn.setFont(SWTResourceManager.getFont("Tahoma", 8, SWT.NORMAL));
		connectionBtn.setBounds(7, 0, 73, 25);
		formToolkit.adapt(connectionBtn, true, true);
		connectionBtn.setText("Connect");
		
		connectionBtn.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				connection();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		
		Button btnNewButton_1 = new Button(composite_2, SWT.NONE);
		btnNewButton_1.setFont(SWTResourceManager.getFont("Tahoma", 8, SWT.NORMAL));
		btnNewButton_1.setBounds(83, 0, 73, 25);
		formToolkit.adapt(btnNewButton_1, true, true);
		btnNewButton_1.setText("Cancle");

	}
	
	
	
	public boolean connection(){
		Conn_Result result = checkConnection();
		if(!result.isSuccess()){
			return false;
		}
		
		Activator.getDefault().getPreferenceStore().setValue(RpmKeyStore.DB_HOST, hostText.getText());
		Activator.getDefault().getPreferenceStore().setValue(RpmKeyStore.DB_PORT, portText.getText());
		Activator.getDefault().getPreferenceStore().setValue(RpmKeyStore.DB_SID, sidText.getText());
		Activator.getDefault().getPreferenceStore().setValue(RpmKeyStore.DB_USERNAME, userText.getText());
		Activator.getDefault().getPreferenceStore().setValue(RpmKeyStore.DB_PASSWORD, passwdText.getText());
		//getPreferenceStore().setValue(RpmKeyStore.DB_OWNER, ownerText.getText());
		
		DBInfoUtil.updateDBInfoByPref();
		//RpmRcpUtil.activeViewReflesh();
		active.dispose();
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
				RpmRcpUtil.showMessageBox(active, "Connection Test", result.getMessage(), SWT.ICON_ERROR | SWT.OK);
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
			RpmRcpUtil.showMessageBox(active, "Ping Test", "Invaild Host.", SWT.ICON_ERROR | SWT.OK);
			return false;
		}
		String port = portText.getText();
		if(logger.isDebugEnabled()){
			logger.debug("##### Port>>"+port);
		}
		if(!RpmRcpUtil.isSimpleNumber(port)){
			RpmRcpUtil.showMessageBox(active, "Ping Test", "Invaild Port.", SWT.ICON_ERROR | SWT.OK);
			return false;
		}
		if(!PingTestUtil.isPing(host, Integer.parseInt(port))){
			RpmRcpUtil.showMessageBox(active, "Ping Test", "Fail.", SWT.ICON_ERROR | SWT.OK);
			return false;
		}
		return true;
	}
}
