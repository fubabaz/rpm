package com.b2en.rpm.ui.rcp.view.sqlviewer;

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
import com.b2en.rpm.sqlviewer.service.RpmSqlViewerService;
import com.b2en.rpm.sqlviewer.vo.SqlViewerOverViewInfo;
import com.b2en.rpm.sqlviewer.vo.SqlViewerParamInfo;
import com.b2en.rpm.sqlviewer.vo.SqlViewerSqlInfo;
import com.b2en.rpm.ui.rcp.util.SQLFormater;
import com.b2en.rpm.ui.rcp.util.SWTResourceUtil;
import com.b2en.rpm.ui.rcp.view.AbstractUtilView;
import com.b2en.ui.rcp.view.table.provider.StringArrayListLabelProvider;
import com.b2en.ui.rcp.view.text.listener.SqlCommandLineStyleListener;
import org.eclipse.swt.widgets.Label;

public class SqlViewerView extends AbstractUtilView {
	private Logger logger = Logger.getLogger(getClass());

	public static final String ID = "RpmUIRcp.SqlViewerView";

	private final FormToolkit formToolkit = new FormToolkit(
			Display.getDefault());
	private Form form;

	private RpmSqlViewerService rpmSqlViewerService;
	private RpmSessionService rpmSessionService;
	
	private TableViewer sqlTableViewer;
	private Table sqlTable;

	private CTabFolder tapFolder;
	private StyledText sqlInfoText;
	
	private TableViewer overviewTableViewer;
	private Table overviewTable;
	
	public SqlViewerView() {
		this.rpmSessionService = (RpmSessionService)getBizService(RpmSessionService.class);
		this.rpmSqlViewerService = (RpmSqlViewerService) getBizService(RpmSqlViewerService.class);
	}

	@Override
	public void createPartControl(Composite parent) {

		form = formToolkit.createForm(parent);
		formToolkit.paintBordersFor(form);
	
		form.getHead().setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		form.setFont(SWTResourceManager.getFont("���� ���", 8, SWT.BOLD));
		form.setForeground(SWTResourceManager.getColor(SWT.COLOR_TITLE_INACTIVE_FOREGROUND));
		form.setText("SQL List");
		form.getBody().setLayout(new FillLayout(SWT.HORIZONTAL));

		Composite scrolledComposite= new Composite(	form.getBody(), SWT.NONE);
	
		formToolkit.adapt(scrolledComposite);
		formToolkit.paintBordersFor(scrolledComposite);
		
		String[] sqlTitles = { 
				"instInstId"                        
				,"sqlSqlId"                          
				,"childChildNumber"                  
				,"planPlanHashValue"                 
				,"parsingParsingSchemaName"          
				,"serviceService"                    
				,"moduleModule"                      
				,"actionAction"                      
				,"firstFirstLoadTime"                
				,"lastLastLoadTime"                  
				,"lastLastActiveTime"                
				,"loadsLoads"                        
				,"invalidationsInvalidations"        
				,"parseParseCalls"                   
				,"executionsExecutions"              
				,"pxPxDop"                           
				,"fetchesFetches"                    
				,"sortsSorts"                        
				,"rowsRowsProcessed"                 
				,"bufferBufferGets"                  
				,"diskDiskReads"                     
				,"directDirectWrites"                
				,"physicalPhysicalReadRequests"      
				,"physicalPhysicalWriteRequests"     
				,"optimizedOptimizedPhyReadRequests" 
				,"ioIoInterconnectBytes"             
				,"physicalPhysicalReadBytes"         
				,"physicalPhysicalWriteBytes"        
				,"ioIoCellOffloadEligibleBytes"      
				,"ioIoCellOffloadReturnedBytes"      
				,"ioIoCellUncompressedBytes"         
				,"imImScans"                         
				,"imImScanBytesUncompressed"         
				,"imImScanBytesInmemory"             
				,"elapsedElapsedTime"                
				,"cpuCpuTime"                        
				,"userUserIoWaitTime"                
				,"concurrencyConcurrencyWaitTime"    
				,"applicationApplicationWaitTime"    
				,"clusterClusterWaitTime"            
				,"otherOtherWaitTime"                
				,"plsqlPlsqlExecTime"                
				,"lastLastMemoryUsed"                
				,"lastLastTempsegSize"               
};
int[] sqlBounds = { 
			100,100,100,100,100
			,100,100,100,100,100
			,100,100,100,100,100
			,100,100,100,100,100
			,100,100,100,100,100
			,100,100,100,100,100
			,100,100,100,100,100
			,100,100,100,100,100
			,100,100,100,100
};
int[] sqlAligns = {
	SWT.LEFT, SWT.LEFT, SWT.RIGHT, SWT.RIGHT, SWT.LEFT
	,SWT.LEFT, SWT.LEFT, SWT.LEFT, SWT.LEFT, SWT.LEFT
	,SWT.LEFT, SWT.RIGHT, SWT.RIGHT, SWT.RIGHT, SWT.RIGHT
	,SWT.RIGHT, SWT.RIGHT, SWT.RIGHT, SWT.RIGHT, SWT.RIGHT
	,SWT.RIGHT, SWT.RIGHT, SWT.RIGHT, SWT.RIGHT, SWT.RIGHT
	,SWT.RIGHT, SWT.RIGHT, SWT.RIGHT, SWT.RIGHT, SWT.RIGHT
	,SWT.RIGHT, SWT.RIGHT, SWT.RIGHT, SWT.RIGHT, SWT.RIGHT
	,SWT.RIGHT, SWT.RIGHT, SWT.RIGHT, SWT.RIGHT, SWT.RIGHT
	,SWT.RIGHT, SWT.RIGHT, SWT.RIGHT, SWT.RIGHT 
	};

		String[] overviewTitles = {"name","valueString","datatypeString","datatype"};
		int[] overviewBounds = {100,100,100,100};
		int[] overviewAligns = {SWT.LEFT, SWT.LEFT, SWT.LEFT, SWT.LEFT};
						scrolledComposite.setLayout(new FillLayout(SWT.VERTICAL));
						
						Composite composite = new Composite(scrolledComposite, SWT.NONE);
						formToolkit.adapt(composite);
						formToolkit.paintBordersFor(composite);
						composite.setLayout(new GridLayout(1, false));
						sqlTableViewer = new TableViewer(composite, SWT.BORDER | SWT.FULL_SELECTION);
						
										createTableColumn(sqlTableViewer, sqlTitles, sqlBounds, sqlAligns);
										sqlTableViewer.setContentProvider(new ArrayContentProvider());
										sqlTableViewer.setLabelProvider(new StringArrayListLabelProvider());
										sqlTable = sqlTableViewer.getTable();
										sqlTable.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
										sqlTable.setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_FOREGROUND));
										sqlTable.setFont(SWTResourceManager.getFont("Tahoma", 8, SWT.NORMAL));
										sqlTable.setHeaderVisible(true);
										sqlTable.setLinesVisible(true);
										formToolkit.paintBordersFor(sqlTable);
										
												sqlTable.addSelectionListener(new SelectionListener() {
													
													@Override
													public void widgetSelected(SelectionEvent e) {
														Table table = (Table)e.widget;
														TableItem row = table.getSelection()[0];
														SqlViewerParamInfo sqlViewerParamInfo = new SqlViewerParamInfo();
														sqlViewerParamInfo.setInstId(row.getText(0));
														sqlViewerParamInfo.setSqlId(row.getText(1));
														sqlViewerParamInfo.setChildNumber(row.getText(2));
														List<SqlViewerOverViewInfo> overViewList = rpmSqlViewerService.getOverViewList(sqlViewerParamInfo);
														setOverviewTableInfo(overViewList);
														
														SessionViewInfo sessionViewInfo = new SessionViewInfo();
														sessionViewInfo.setInstId(row.getText(0));
														sessionViewInfo.setSqlId(row.getText(1));
														sessionViewInfo.setSqlChildNumber(row.getText(2));
														
														String currentSql = (String) rpmSessionService.getSqlFullTextInfo(sessionViewInfo).get("sql");
														setCurrentSql(currentSql);
													}
													
													@Override
													public void widgetDefaultSelected(SelectionEvent e) {
														
													}
												});
																
																Composite composite_1 = new Composite(scrolledComposite, SWT.NONE);
																formToolkit.adapt(composite_1);
																formToolkit.paintBordersFor(composite_1);
																		composite_1.setLayout(new GridLayout(1, false));
																
																		
																
																		
																		tapFolder = new CTabFolder(composite_1, SWT.BORDER);
																		tapFolder.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
																		tapFolder.setTabPosition(SWT.TOP);
																		tapFolder.setFont(SWTResourceManager.getFont("���� ���", 8, SWT.BOLD));
																		tapFolder.setForeground(SWTResourceManager.getColor(SWT.COLOR_TITLE_INACTIVE_FOREGROUND));			
																		tapFolder.setSelectionBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
																		
																		CTabItem tapItemStr = new CTabItem(tapFolder, SWT.NONE);
																		tapItemStr.setText("Overview List");
																		tapItemStr.setFont(SWTResourceManager.getFont("���� ���", 8, SWT.BOLD));
																		
																		Composite tabComp = new Composite(tapFolder, SWT.NONE);
																		tapItemStr.setControl(tabComp);
																		formToolkit.paintBordersFor(tabComp);
																		tabComp.setLayout(new GridLayout(2, false));
																		
																		ScrolledComposite overviewTableScrolledComposite = new ScrolledComposite(tabComp, SWT.H_SCROLL | SWT.V_SCROLL);
																		GridData gd_overviewTableScrolledComposite = new GridData(SWT.LEFT, SWT.FILL, false, true, 1, 1);
																		gd_overviewTableScrolledComposite.widthHint = 442;
																		overviewTableScrolledComposite.setLayoutData(gd_overviewTableScrolledComposite);
																		formToolkit.adapt(overviewTableScrolledComposite);
																		formToolkit.paintBordersFor(overviewTableScrolledComposite);
																		overviewTableScrolledComposite.setExpandHorizontal(true);
																		overviewTableScrolledComposite.setExpandVertical(true);
																		
																				overviewTableViewer = new TableViewer(overviewTableScrolledComposite, SWT.FULL_SELECTION | SWT.BORDER);
																				
																						createTableColumn(overviewTableViewer, overviewTitles, overviewBounds, overviewAligns);
																						overviewTableViewer.setContentProvider(new ArrayContentProvider());
																						overviewTableViewer.setLabelProvider(new StringArrayListLabelProvider());
																						overviewTable = overviewTableViewer.getTable();
																						overviewTable.setFont(SWTResourceManager.getFont("Tahoma", 8, SWT.NORMAL));
																						overviewTable.setHeaderVisible(true);
																						overviewTable.setLinesVisible(true);
																						overviewTable.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1));
																						overviewTableScrolledComposite.setContent(overviewTable);
																						overviewTableScrolledComposite.setMinSize(overviewTable.computeSize(SWT.DEFAULT, SWT.DEFAULT));
																						formToolkit.paintBordersFor(overviewTable);
																		
																		sqlInfoText = new StyledText(tabComp, SWT.BORDER | SWT.V_SCROLL);
																		sqlInfoText.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
																		sqlInfoText.setFont(SWTResourceManager.getFont("Tahoma", 8, SWT.NORMAL));
																		sqlInfoText.addLineStyleListener(new SqlCommandLineStyleListener(SWTResourceUtil.getColor(SWT.COLOR_BLACK), SWTResourceUtil.getColor(SWT.COLOR_WHITE), SWT.BOLD));
																		formToolkit.adapt(sqlInfoText);
																		formToolkit.paintBordersFor(sqlInfoText);

		//scrolledComposite.setContent(mainComposite);
		//scrolledComposite.setMinSize(mainComposite.computeSize(SWT.DEFAULT, SWT.DEFAULT));

		init();
	}

	private void init() {
		List<SqlViewerSqlInfo> sqlInfoList = this.rpmSqlViewerService.getSqlInfoList();
		setSqlTableInfo(sqlInfoList);
		
		if(tapFolder.getSelectionIndex()<0){
			tapFolder.setSelection(0);
		}
	}

	public void setSqlTableInfo(List<SqlViewerSqlInfo> sqlInfoList) {
		List<String[]> list = new ArrayList<String[]>();
		if(sqlInfoList!=null){
			for (SqlViewerSqlInfo sqlViewerSqlInfo : sqlInfoList) {
				String[] row = { 
						sqlViewerSqlInfo.getInstId()
						,sqlViewerSqlInfo.getSqlId()
						,sqlViewerSqlInfo.getChildNumber()
						,sqlViewerSqlInfo.getPlanHashValue()
						,sqlViewerSqlInfo.getParsingSchemaName()
						,sqlViewerSqlInfo.getService()
						,sqlViewerSqlInfo.getModule()
						,sqlViewerSqlInfo.getAction()
						,sqlViewerSqlInfo.getFirstLoadTime()
						,sqlViewerSqlInfo.getLastLoadTime()
						,sqlViewerSqlInfo.getLastActiveTime()
						,sqlViewerSqlInfo.getLoads()
						,sqlViewerSqlInfo.getInvalidations()
						,sqlViewerSqlInfo.getParseCalls()
						,sqlViewerSqlInfo.getExecutions()
						,sqlViewerSqlInfo.getPxDop()
						,sqlViewerSqlInfo.getFetches()
						,sqlViewerSqlInfo.getSorts()
						,sqlViewerSqlInfo.getRowsProcessed()
						,sqlViewerSqlInfo.getBufferGets()
						,sqlViewerSqlInfo.getDiskReads()
						,sqlViewerSqlInfo.getDirectWrites()
						,sqlViewerSqlInfo.getPhysicalReadRequests()
						,sqlViewerSqlInfo.getPhysicalWriteRequests()
						,sqlViewerSqlInfo.getOptimizedPhyReadRequests()
						,sqlViewerSqlInfo.getIoInterconnectBytes()
						,sqlViewerSqlInfo.getPhysicalReadBytes()
						,sqlViewerSqlInfo.getPhysicalWriteBytes()
						,sqlViewerSqlInfo.getIoCellOffloadEligibleBytes()
						,sqlViewerSqlInfo.getIoCellOffloadReturnedBytes()
						,sqlViewerSqlInfo.getIoCellUncompressedBytes()
						,sqlViewerSqlInfo.getImScans()
						,sqlViewerSqlInfo.getImScanBytesUncompressed()
						,sqlViewerSqlInfo.getImScanBytesInmemory()
						,sqlViewerSqlInfo.getElapsedTime()
						,sqlViewerSqlInfo.getCpuTime()
						,sqlViewerSqlInfo.getUserIoWaitTime()
						,sqlViewerSqlInfo.getConcurrencyWaitTime()
						,sqlViewerSqlInfo.getApplicationWaitTime()
						,sqlViewerSqlInfo.getClusterWaitTime()
						,sqlViewerSqlInfo.getOtherWaitTime()
						,sqlViewerSqlInfo.getPlsqlExecTime()
						,sqlViewerSqlInfo.getLastMemoryUsed()
						,sqlViewerSqlInfo.getLastTempsegSize()
				};
				list.add(row);
			}
		}
		sqlTableViewer.setInput(list);
	}
	
	public void setOverviewTableInfo(List<SqlViewerOverViewInfo> overViewList) {
		List<String[]> list = new ArrayList<String[]>();
		if(overViewList!=null){
			for (SqlViewerOverViewInfo overViewInfo : overViewList) {
				String[] row = { 
						overViewInfo.getName(), overViewInfo.getValueString()
						,overViewInfo.getDatatypeString(), overViewInfo.getDatatype()
				};
				list.add(row);
			}
		}
		overviewTableViewer.setInput(list);
	}

	@Override
	public void reflesh() {
		init();
	}
	
	

	private void setCurrentSql(String sqlText){
		sqlInfoText.setText(SQLFormater.format(sqlText));
//		if(tapFolder.getSelectionIndex()<0){
//			tapFolder.setSelection(0);
//		}
	}
}
