package com.b2en.rpm.ui.rcp.view.session;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.forms.widgets.Form;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.wb.swt.SWTResourceManager;

import com.b2en.rpm.session.service.RpmSessionService;
import com.b2en.rpm.session.vo.SessionViewInfo;
import com.b2en.rpm.ui.rcp.util.SQLFormater;
import com.b2en.rpm.ui.rcp.util.SWTResourceUtil;
import com.b2en.rpm.ui.rcp.view.AbstractUtilView;
import com.b2en.ui.rcp.view.table.provider.StringArrayListLabelProvider;
import com.b2en.ui.rcp.view.text.listener.SqlCommandLineStyleListener;

public class SessionListView extends AbstractUtilView {

	private Logger logger = Logger.getLogger(getClass());
	
	public static final String ID = "RpmUIRcp.SessionListView";
	
	private final FormToolkit formToolkit = new FormToolkit(Display.getDefault());
	private Form frmSessionList;
	
	private RpmSessionService rpmSessionService;
	
	private TableViewer tableViewer;
	private Table table;
	
	private CTabFolder tapFolder;
	private StyledText sqlInfoText;
	
	public SessionListView(){
		this.rpmSessionService = (RpmSessionService)getBizService(RpmSessionService.class);
	}
	
	@Override
	public void createPartControl(Composite parent) {
		
		frmSessionList = formToolkit.createForm(parent);
		frmSessionList.getHead().setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		formToolkit.paintBordersFor(frmSessionList);
		frmSessionList.getHead().setFont(getDefaultFont());
		frmSessionList.getHead().setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		frmSessionList.setFont(SWTResourceManager.getFont("맑은 고딕", 8, SWT.BOLD));
		frmSessionList.setForeground(SWTResourceManager.getColor(SWT.COLOR_TITLE_INACTIVE_FOREGROUND));
		frmSessionList.setText("Session List");
		frmSessionList.getBody().setLayout(new FillLayout(SWT.HORIZONTAL));
		
		ScrolledComposite scrolledComposite = new ScrolledComposite(frmSessionList.getBody(), SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		formToolkit.adapt(scrolledComposite);
		formToolkit.paintBordersFor(scrolledComposite);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
		
		Composite mainComposite = new Composite(scrolledComposite, SWT.NONE);
		formToolkit.adapt(mainComposite);
		formToolkit.paintBordersFor(mainComposite);
		GridLayout gl_mainComposite = new GridLayout(1, false);
		gl_mainComposite.marginHeight = 0;
		gl_mainComposite.verticalSpacing = 0;
		gl_mainComposite.marginWidth = 0;
		gl_mainComposite.horizontalSpacing = 0;
		mainComposite.setLayout(gl_mainComposite);
		mainComposite.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, true, 1, 1));
		
		ScrolledComposite tableScrolledComposite = new ScrolledComposite(mainComposite, SWT.H_SCROLL | SWT.V_SCROLL);
		tableScrolledComposite.setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_FOREGROUND));
		tableScrolledComposite.setFont(SWTResourceManager.getFont("Tahoma", 8, SWT.NORMAL));
		GridData gd_tableScrolledComposite = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		gd_tableScrolledComposite.heightHint = 105;
		tableScrolledComposite.setLayoutData(gd_tableScrolledComposite);
		formToolkit.adapt(tableScrolledComposite);
		formToolkit.paintBordersFor(tableScrolledComposite);		
		tableScrolledComposite.setExpandHorizontal(true);
		tableScrolledComposite.setExpandVertical(true);
		
		String[] titles = {"username", "depth", "instId", "sid"
				, "serial", "logonTime", "status", "serviceName"
				, "module", "action", "clientInfo", "machine"
				, "osuser", "program", "sqlId", "sqlChildNumber"
				, "sqlExecStart", "sqlExecId", "pxDop", "saddr"
				, "paddr", "taddr", "pid", "pserial"};
		int[] bounds = {100, 100, 100, 100
				,100, 100, 100, 100
				,100, 100, 100, 100
				,100, 100, 100, 100
				,100, 100, 100, 100
				,100, 100, 100, 100
				};
		int[] aligns = {SWT.LEFT, SWT.RIGHT, SWT.RIGHT, SWT.RIGHT
				, SWT.RIGHT, SWT.LEFT, SWT.LEFT, SWT.LEFT
				, SWT.LEFT, SWT.LEFT, SWT.LEFT, SWT.LEFT
				, SWT.LEFT, SWT.LEFT, SWT.LEFT, SWT.LEFT
				, SWT.LEFT, SWT.LEFT, SWT.LEFT, SWT.LEFT
				, SWT.LEFT, SWT.LEFT, SWT.LEFT, SWT.LEFT
				};
		
		tableViewer = new TableViewer(tableScrolledComposite, SWT.BORDER | SWT.FULL_SELECTION);
		
		createTableColumn(tableViewer, titles, bounds, aligns);
		tableViewer.setContentProvider(new ArrayContentProvider());
		tableViewer.setLabelProvider(new StringArrayListLabelProvider());
		table = tableViewer.getTable();
		table.setFont(SWTResourceManager.getFont("Tahoma", 8, SWT.NORMAL));
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1));
		tableScrolledComposite.setContent(table);
		tableScrolledComposite.setMinSize(table.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		formToolkit.paintBordersFor(table);
		
		tapFolder = new CTabFolder(mainComposite, SWT.BORDER);
		tapFolder.setTabPosition(SWT.TOP);
		tapFolder.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		tapFolder.setFont(SWTResourceManager.getFont("맑은 고딕", 8, SWT.BOLD));
		tapFolder.setForeground(SWTResourceManager.getColor(SWT.COLOR_TITLE_INACTIVE_FOREGROUND));
	
		
		tapFolder.setSelectionBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		
		CTabItem tapItemStr = new CTabItem(tapFolder, SWT.NONE);
		tapItemStr.setText("Current SQL");
		tapItemStr.setFont(SWTResourceManager.getFont("맑은 고딕", 8, SWT.BOLD));
		
		
		Composite tabComp = new Composite(tapFolder, SWT.NONE);
		tapItemStr.setControl(tabComp);
		formToolkit.paintBordersFor(tabComp);
		tabComp.setLayout(new GridLayout(1, false));
		
		sqlInfoText = new StyledText(tabComp, SWT.BORDER | SWT.V_SCROLL);
		sqlInfoText.setFont(SWTResourceManager.getFont("Tahoma", 8, SWT.NORMAL));
		GridData gd_sqlInfoText = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		gd_sqlInfoText.heightHint = 105;
		sqlInfoText.setLayoutData(gd_sqlInfoText);
		sqlInfoText.addLineStyleListener(new SqlCommandLineStyleListener(SWTResourceUtil.getColor(SWT.COLOR_BLACK), SWTResourceUtil.getColor(SWT.COLOR_WHITE), SWT.BOLD));
		formToolkit.adapt(sqlInfoText);
		formToolkit.paintBordersFor(sqlInfoText);
		
		scrolledComposite.setContent(mainComposite);
		scrolledComposite.setMinSize(mainComposite.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		
		table.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				Table table = (Table)e.widget;
				TableItem row = table.getSelection()[0];
				SessionViewInfo sessionViewInfo = new SessionViewInfo();
				sessionViewInfo.setInstId(row.getText(2));
				sessionViewInfo.setSqlId(row.getText(14));
				sessionViewInfo.setSqlChildNumber(row.getText(15));
				String currentSql = (String) rpmSessionService.getSqlFullTextInfo(sessionViewInfo).get("sql");
				setCurrentSql(currentSql);
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				
			}
		});
		
		init();
	}
	
	private void init(){
		List<SessionViewInfo> sessionInfoList = this.rpmSessionService.getSessionViewerList();
		setTableInfo(sessionInfoList);
	}
	
	public void setTableInfo(List<SessionViewInfo> sessionInfoList){
		List<String[]> list = new ArrayList<String[]>();
		for(SessionViewInfo sessionViewInfo : sessionInfoList){
			String[] row = {
					sessionViewInfo.getUsername(), sessionViewInfo.getDepth(), sessionViewInfo.getInstId(), sessionViewInfo.getSid()
					,sessionViewInfo.getSerial(), sessionViewInfo.getLogonTime(), sessionViewInfo.getStatus(), sessionViewInfo.getServiceName()
					,sessionViewInfo.getModule(), sessionViewInfo.getAction(), sessionViewInfo.getClientInfo(), sessionViewInfo.getMachine()
					,sessionViewInfo.getOsuser(), sessionViewInfo.getProgram(), sessionViewInfo.getSqlId(), sessionViewInfo.getSqlChildNumber()
					,sessionViewInfo.getSqlExecStart(), sessionViewInfo.getSqlExecId(), sessionViewInfo.getPxDop(), sessionViewInfo.getSaddr()
					,sessionViewInfo.getPaddr(), sessionViewInfo.getTaddr(), sessionViewInfo.getPid(), sessionViewInfo.getPserial()
			};
			list.add(row);
		}
		tableViewer.setInput(list);
	}
	
	private void setCurrentSql(String sqlText){
		sqlInfoText.setText(SQLFormater.format(sqlText));
		if(tapFolder.getSelectionIndex()<0){
			tapFolder.setSelection(0);
		}
	}
	
	@Override
	public void reflesh() {
		init();
	}
}
