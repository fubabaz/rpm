package com.b2en.rpm.ui.rcp.dialog;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.wb.swt.SWTResourceManager;

import com.b2en.rpm.session.service.RpmSessionService;
import com.b2en.rpm.session.vo.SessionStatisticsInfo;
import com.b2en.rpm.sqlviewer.vo.SqlViewerOverViewInfo;
import com.b2en.rpm.ui.rcp.util.SQLFormater;
import com.b2en.rpm.ui.rcp.util.SWTResourceUtil;
import com.b2en.ui.rcp.view.table.provider.StringArrayListLabelProvider;
import com.b2en.ui.rcp.view.text.listener.SqlCommandLineStyleListener;
import org.eclipse.wb.swt.ResourceManager;

public class SqlViwerDialog extends Dialog {

	protected Object result;
	protected Shell shell;
	private RpmSessionService rpmSessionService;
	private StyledText sqlInfoText;
	
	
	private String currentSql;
	private TableViewer overviewTableViewer;
	private Table overviewTable;

	
	private TableViewer tableViewer;
	private List<SqlViewerOverViewInfo> overViewList;
	private List<SessionStatisticsInfo> planList;
	private Table table;
	
	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public SqlViwerDialog(Shell parent, int style, String currentSql, List<SqlViewerOverViewInfo> overViewList, List<SessionStatisticsInfo>planList) {
		super(parent, style);
		this.overViewList = overViewList;
		this.currentSql = currentSql;
		this.planList = planList;
		setText("Sql Viewer");
		
		
	
		
		
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open() {
		createContents();
		shell.open();
		shell.layout();
		Display display = getParent().getDisplay();
		while (!shell.isDisposed()) {
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
		shell = new Shell(getParent(), SWT.DIALOG_TRIM);
		shell.setImage(ResourceManager.getPluginImage("RpmUIRcp", "icons/sqlviewer/variables_icon.png"));
		shell.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		shell.setSize(1127, 594);
		shell.setText(getText());
		GridLayout gl_shell = new GridLayout(1, false);
		gl_shell.marginHeight = 0;
		gl_shell.marginWidth = 0;
		gl_shell.horizontalSpacing = 0;
		shell.setLayout(gl_shell);
		
		CTabFolder tabFolder_1 = new CTabFolder(shell, SWT.BORDER);
		tabFolder_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		tabFolder_1.setFont(SWTResourceManager.getFont("Tahoma", 8, SWT.NORMAL));
		GridData gd_tabFolder_1 = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		gd_tabFolder_1.heightHint = 0;
		tabFolder_1.setLayoutData(gd_tabFolder_1);
		tabFolder_1.setSelectionBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		
		CTabItem tbtmNewItem_1 = new CTabItem(tabFolder_1, SWT.NONE);
		tbtmNewItem_1.setImage(ResourceManager.getPluginImage("RpmUIRcp", "icons/document_info.png"));
		tbtmNewItem_1.setFont(SWTResourceManager.getFont("Tahoma", 8, SWT.NORMAL));
		tbtmNewItem_1.setText("SQL Infomation");
		
		Composite composite = new Composite(tabFolder_1, SWT.BORDER);
		tbtmNewItem_1.setControl(composite);
		composite.setLayout(new GridLayout(2, false));
		
		Label lblNewLabel = new Label(composite, SWT.NONE);
		lblNewLabel.setFont(SWTResourceManager.getFont("Tahoma", 8, SWT.BOLD));
		lblNewLabel.setText(" · Variables");
		
		Label lblNewLabel_1 = new Label(composite, SWT.NONE);
		lblNewLabel_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_TITLE_INACTIVE_FOREGROUND));
		lblNewLabel_1.setFont(SWTResourceManager.getFont("Tahoma", 8, SWT.BOLD));
		lblNewLabel_1.setText(" · SQL");
		
		Composite composite_1 = new Composite(composite, SWT.BORDER);
		composite_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		composite_1.setLayout(new FillLayout(SWT.HORIZONTAL));
		GridData gd_composite_1 = new GridData(SWT.LEFT, SWT.FILL, false, true, 1, 1);
		gd_composite_1.widthHint = 427;
		composite_1.setLayoutData(gd_composite_1);
		
		
		String[] overviewTitles = {"name","valueString","datatypeString","datatype"};
		int[] overviewBounds = {100,100,100,100};
		int[] overviewAligns = {SWT.LEFT, SWT.LEFT, SWT.LEFT, SWT.LEFT};
		
		String[] planTitles = {};
		int[] planBounds = {};
		int[] planAligns = {};
		
		overviewTableViewer = new TableViewer(composite_1, SWT.FULL_SELECTION);
		createTableColumn(overviewTableViewer, overviewTitles, overviewBounds, overviewAligns);
		overviewTableViewer.setContentProvider(new ArrayContentProvider());
		overviewTableViewer.setLabelProvider(new StringArrayListLabelProvider());
		overviewTable = overviewTableViewer.getTable();
		overviewTable.setFont(SWTResourceManager.getFont("Tahoma", 8, SWT.NORMAL));
		overviewTable.setHeaderVisible(true);
		overviewTable.setLinesVisible(true);
		
			
			Composite composite_2 = new Composite(composite, SWT.BORDER);
			composite_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
			composite_2.setLayout(new GridLayout(1, false));
			composite_2.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
			
			sqlInfoText = new StyledText(composite_2, SWT.NONE | SWT.V_SCROLL| SWT.H_SCROLL);
			sqlInfoText.setFont(SWTResourceManager.getFont("Courier New", 8, SWT.NORMAL));
			sqlInfoText.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
			sqlInfoText.addLineStyleListener(new SqlCommandLineStyleListener(SWTResourceUtil.getColor(SWT.COLOR_BLACK), SWTResourceUtil.getColor(SWT.COLOR_WHITE), SWT.BOLD));
			
				sqlInfoText.setText(SQLFormater.format(currentSql));
				
				CTabItem tbtmNewItem_2 = new CTabItem(tabFolder_1, SWT.NONE);
				tbtmNewItem_2.setImage(ResourceManager.getPluginImage("RpmUIRcp", "icons/sqlviewer/script.png"));
				tbtmNewItem_2.setFont(SWTResourceManager.getFont("Tahoma", 8, SWT.NORMAL));
				tbtmNewItem_2.setText("SQL Plan");
				
				Composite composite_3 = new Composite(tabFolder_1, SWT.BORDER);
				tbtmNewItem_2.setControl(composite_3);
				composite_3.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
				composite_3.setLayout(new FillLayout(SWT.HORIZONTAL));
				
			
				 tableViewer = new TableViewer(composite_3, SWT.FULL_SELECTION);
				 createTableColumn(overviewTableViewer, planTitles, planBounds, planAligns);
				 tableViewer.setContentProvider(new ArrayContentProvider());
				 tableViewer.setLabelProvider(new StringArrayListLabelProvider());
				 table = tableViewer.getTable();
				 table.setFont(SWTResourceManager.getFont("Courier New", 8, SWT.NORMAL));
				 table.setLinesVisible(false);
				 table.setHeaderVisible(false);
				
		
				 setSqlPlanTableInfo(planList);
				 
		setOverviewTableInfo(overViewList);
	
	}
	
	protected void createTableColumn(TableViewer view, String[] titles, int[] bounds, int[] aligns){
		for(int i=0; i<titles.length; i++){
			TableViewerColumn column = new TableViewerColumn(view, SWT.NONE);
			column.getColumn().setText(titles[i]);
			column.getColumn().setWidth(bounds[i]);
			column.getColumn().setResizable(true);
			column.getColumn().setMoveable(true);
			column.getColumn().setAlignment(aligns[i]);
		}
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
	
	
	public void setSqlPlanTableInfo(List<SessionStatisticsInfo> planList) {
		
		List<String[]> list = new ArrayList<String[]>();
		if(planList!=null){
			for (SessionStatisticsInfo sessionStatisticsInfo : planList) {
				String[] row = { 
						sessionStatisticsInfo.getValue()						
				};
				list.add(row);
			}
		}
		tableViewer.setInput(list);
		
	}
}
