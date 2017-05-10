package com.b2en.rpm.ui.rcp.view.part;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

import javax.swing.Timer;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.wb.swt.SWTResourceManager;
import org.jfree.chart.ChartMouseEvent;
import org.jfree.chart.ChartMouseListener;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.annotations.XYTextAnnotation;
import org.jfree.chart.axis.AxisSpace;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.event.ChartChangeEvent;
import org.jfree.chart.event.ChartChangeListener;
import org.jfree.chart.event.ChartProgressEvent;
import org.jfree.chart.event.ChartProgressListener;
import org.jfree.chart.labels.XYToolTipGenerator;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.LegendTitle;
import org.jfree.data.time.Minute;
import org.jfree.data.time.Second;
import org.jfree.data.time.TimePeriod;
import org.jfree.data.time.TimeTableXYDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.experimental.chart.swt.ChartComposite;
import org.jfree.ui.HorizontalAlignment;
import org.jfree.ui.RectangleInsets;
import org.jfree.ui.TextAnchor;

import com.b2en.rpm.session.service.RpmSessionService;
import com.b2en.rpm.session.vo.SessionStatisticsInfo;
import com.b2en.rpm.session.vo.SessionViewInfo;
import com.b2en.rpm.sqlviewer.service.RpmSqlViewerService;
import com.b2en.rpm.sqlviewer.vo.SqlViewerOverViewInfo;
import com.b2en.rpm.sqlviewer.vo.SqlViewerParamInfo;
import com.b2en.rpm.ui.rcp.dialog.SqlViwerDialog;
import com.b2en.rpm.ui.rcp.view.AbstractUtilView;
import com.b2en.rpm.viewpart.vo.AshActivityDetailSearchParamVO;
import com.b2en.rpm.viewpart.vo.AshActivityDetailSearchResultVO;
import com.b2en.rpm.viewpart.vo.AshActivitySearchParamVO;
import com.b2en.rpm.viewpart.vo.AshActivitySearchResultVO;
import com.b2en.rpm.viewpart.vo.AshActivitySessionSearchParamVO;
import com.b2en.rpm.viewpart.vo.AshActivitySessionSearchResultVO;
import com.b2en.rpm.viewpart.vo.AshActivitySqlSearchParamVO;
import com.b2en.rpm.viewpart.vo.AshActivitySqlSearchResultVO;
import com.b2en.rpm.viewpart.vo.AshMetricNameResultVO;
import com.b2en.rpm.viewpart.vo.AshSampleTimeResultVO;
import com.b2en.rpm.viewpart.vo.AshSysmetricHistorySearchParamVO;
import com.b2en.rpm.viewpart.vo.AshSysmetricHistorySearchResultVO;
import com.b2en.rpm.viewpart.vo.InstanceNameSearchResultVO;
import com.b2en.ui.rcp.view.table.provider.StringArrayListLabelProvider;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.wb.swt.ResourceManager;

public class AWRViewPart extends AbstractUtilView implements ChartChangeListener, ChartProgressListener {   
	
	
	boolean chartclickFlag = false;
	  private static final int FAST = 1000;
	    private static final int SLOW = FAST * 5;
	  private Timer timer;
	  private static final int COUNT = 360;
	public static final String ID = "RpmUIRcp.AWRViewPart"; //$NON-NLS-1$
	
	public  ChartComposite chartcomposite_1  = null;
    public  ChartComposite chartcomposite_2  = null;
    public  ChartComposite chartcomposite_3  = null;
    boolean flag = false;
    private CCombo instanceCombo;
    private CCombo sampleTimeCombo;
    private CCombo metricCombo;
    Map< String, String > instanceIdMap = new HashMap<String, String>();
    Map< String, String > metricIdMap = new HashMap<String, String>();
    Map< String, String > groupIdMap = new HashMap<String, String>();
    
    final TimeTableXYDataset dataset3 = new TimeTableXYDataset(); 
    final TimeTableXYDataset dataset4 = new TimeTableXYDataset();
    final TimeTableXYDataset dataset5 = new TimeTableXYDataset();
    
    TableViewer tableViewer;
	TableViewer tableViewer_1;
	

    
	private final FormToolkit formToolkit = new FormToolkit(Display.getDefault());
	private  RpmSessionService rpmSessionService;
	private RpmSqlViewerService rpmSqlViewerService;
	
	private Table table;
	private Table table_1;
	private static final float MINMAX = 100;
	   private static final Random random = new Random();
	
	   public AWRViewPart() {
		this.rpmSessionService = (RpmSessionService)getBizService(RpmSessionService.class);
		this.rpmSqlViewerService = (RpmSqlViewerService) getBizService(RpmSqlViewerService.class);
	}

	 private float randomValue() {
	        float randValue = (float) (random.nextGaussian() * MINMAX / 30);
	        return randValue < 0 ? -randValue : randValue;
	    }
	 
	/**
	 * Create contents of the view part.
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		
		
		
		String[] titles1 = {"INSTANCE", "ID", "SERIAL","RT", "CPU","WAIT", "CPU TIME", "DB TIME", "READ IO BYTES", "WRITE IO BYTES", "INTERCONNECT IO BYTES", "READ MEM BYTES"};
		 int[] bounds1 =    {150        , 150  , 150      , 150 , 150   ,150    , 150       , 150      , 150, 150,150, 150};
		 String[] titles2 = {"INSTANCE", "SQL ID","SQL NUM","RT", "CPU","WAIT", "CPU TIME", "DB TIME", "READ IO BYTES", "WRITE IO BYTES", "INTERCONNECT IO BYTES", "READ MEM BYTES"};
		 int[] bounds2 =    { 150       , 150      ,150       , 150, 150,150, 150, 150, 150, 150,150, 150};
			int[] aligns1 = {  SWT.RIGHT  , SWT.RIGHT      , SWT.RIGHT , SWT.RIGHT , SWT.RIGHT , SWT.RIGHT , SWT.RIGHT , SWT.RIGHT , SWT.RIGHT , SWT.RIGHT , SWT.RIGHT , SWT.RIGHT};
			int[] aligns2 = {  SWT.RIGHT   , SWT.RIGHT , SWT.RIGHT	, SWT.RIGHT, SWT.RIGHT, SWT.RIGHT, SWT.RIGHT, SWT.RIGHT, SWT.RIGHT, SWT.RIGHT, SWT.RIGHT, SWT.RIGHT};
		
			 timer = new Timer(FAST, new ActionListener() {
				
		            @Override
		            public void actionPerformed(ActionEvent e) { 
		            	
		                TimePeriod period = new Second();
		            
		                dataset3.add(period, randomValue(), "application");
	 					dataset3.add(period, randomValue(), "cpu");
	 					dataset3.add(period, randomValue(), "userIo");
	 					dataset3.add(period, randomValue(), "otherWait");		 					
	 					dataset3.add(period, randomValue(), "concurrency");
	 					
		                if(dataset3.getItemCount() > COUNT) {
		                    TimePeriod firstItemTime = dataset3.getTimePeriod(0);
		                    dataset3.remove(firstItemTime, "application");
		                    dataset3.remove(firstItemTime, "cpu");
		                    dataset3.remove(firstItemTime, "userIo");
		                    dataset3.remove(firstItemTime, "otherWait");
		                    dataset3.remove(firstItemTime, "concurrency");
		                }
		            }
		        });
		GridLayout gl_parent = new GridLayout(1, false);
		gl_parent.verticalSpacing = 0;
		gl_parent.marginWidth = 0;
		gl_parent.marginHeight = 0;
		parent.setLayout(gl_parent);
	
		     
		{
			Composite composite_MAIN = new Composite(parent, SWT.NONE);
			composite_MAIN.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
			composite_MAIN.setBackground(SWTResourceManager.getColor(255, 255, 255));
			GridLayout gl_composite_MAIN = new GridLayout(1, false);
			gl_composite_MAIN.verticalSpacing = 0;
			gl_composite_MAIN.marginWidth = 0;
			gl_composite_MAIN.marginHeight = 0;
			gl_composite_MAIN.horizontalSpacing = 0;
			composite_MAIN.setLayout(gl_composite_MAIN);
			{
				Composite composite_TOOLBAR = new Composite(composite_MAIN, SWT.BORDER);
				composite_TOOLBAR.setFont(SWTResourceManager.getFont("Tahoma", 8, SWT.NORMAL));
				composite_TOOLBAR.setLayout(new GridLayout(8, false));
				GridData gd_composite_TOOLBAR = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
				gd_composite_TOOLBAR.heightHint = 33;
				composite_TOOLBAR.setLayoutData(gd_composite_TOOLBAR);
				composite_TOOLBAR.setBounds(0, 0, 64, 64);
				
				CLabel lblNewLabel = new CLabel(composite_TOOLBAR, SWT.NONE);
				lblNewLabel.setFont(SWTResourceManager.getFont("Tahoma", 8, SWT.NORMAL));
				lblNewLabel.setText("Instance");
				
				instanceCombo = new CCombo(composite_TOOLBAR, SWT.BORDER);
				instanceCombo.setFont(SWTResourceManager.getFont("Tahoma", 9, SWT.NORMAL));
				new Label(composite_TOOLBAR, SWT.NONE);
				
				instanceCombo.addSelectionListener(new SelectionAdapter() {
				      public void widgetSelected(SelectionEvent e) {
				    	  select();
				      }
				});
				
				CLabel lblNewLabel_1 = new CLabel(composite_TOOLBAR, SWT.NONE);
				lblNewLabel_1.setFont(SWTResourceManager.getFont("Tahoma", 8, SWT.NORMAL));
				lblNewLabel_1.setText("Snapshot");
				
				sampleTimeCombo = new CCombo(composite_TOOLBAR, SWT.BORDER);
				sampleTimeCombo.setFont(SWTResourceManager.getFont("Tahoma", 9, SWT.NORMAL));
				GridData gd_sampleTimeCombo = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
				gd_sampleTimeCombo.widthHint = 140;
				sampleTimeCombo.setLayoutData(gd_sampleTimeCombo);
				sampleTimeCombo.addSelectionListener(new SelectionAdapter() {
				      public void widgetSelected(SelectionEvent e) {
				    	  select();
				      }
				});
				
				
				
				
				
				CLabel lblNewLabel_2 = new CLabel(composite_TOOLBAR, SWT.NONE);
				lblNewLabel_2.setFont(SWTResourceManager.getFont("Tahoma", 8, SWT.NORMAL));
				lblNewLabel_2.setText("Metric");
				
				metricCombo = new CCombo(composite_TOOLBAR, SWT.BORDER);
				metricCombo.setFont(SWTResourceManager.getFont("Tahoma", 9, SWT.NORMAL));
				GridData gd_metricCombo = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
				gd_metricCombo.widthHint = 209;
				metricCombo.setLayoutData(gd_metricCombo);
				
				metricCombo.addSelectionListener(new SelectionAdapter() {
				      public void widgetSelected(SelectionEvent e) {
				    	  select();
				      }
				});
				
				Button btnNewButton = new Button(composite_TOOLBAR, SWT.NONE);
				btnNewButton.setFont(SWTResourceManager.getFont("Tahoma", 8, SWT.NORMAL));
				formToolkit.adapt(btnNewButton, true, true);
				btnNewButton.setText("Search");
				btnNewButton.addSelectionListener(new SelectionAdapter() {
					 
					
				 	public void widgetSelected(SelectionEvent event) {
				 		select();
					
				 	}
				 });
			}
			
			Section sctnNewSection = formToolkit.createSection(composite_MAIN, Section.TWISTIE | Section.TITLE_BAR);
			sctnNewSection.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
			sctnNewSection.setFont(SWTResourceManager.getFont("Tahoma", 8, SWT.NORMAL));
	
			GridData gd_sctnNewSection = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
			gd_sctnNewSection.horizontalIndent = 1;
			gd_sctnNewSection.heightHint = 305;
			sctnNewSection.setLayoutData(gd_sctnNewSection);
			formToolkit.paintBordersFor(sctnNewSection);
			sctnNewSection.setText("Activiy");
			sctnNewSection.setExpanded(true);
			sctnNewSection.setTitleBarForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BORDER));
			sctnNewSection.setTitleBarBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
			sctnNewSection.setTitleBarBorderColor(SWTResourceManager.getColor(SWT.COLOR_GRAY));
			
			Composite composite = new Composite(sctnNewSection, SWT.NONE);
			formToolkit.adapt(composite);
			formToolkit.paintBordersFor(composite);
			sctnNewSection.setClient(composite);
			GridLayout gl_composite = new GridLayout(2, false);
			gl_composite.marginLeft = 15;
			composite.setLayout(gl_composite);
			
			Composite composite_5 = new Composite(composite, SWT.NONE);
			composite_5.setLayout(new FillLayout(SWT.VERTICAL));
			GridData gd_composite_5 = new GridData(SWT.LEFT, SWT.FILL, false, false, 1, 1);
			gd_composite_5.widthHint = 105;
			composite_5.setLayoutData(gd_composite_5);
			formToolkit.adapt(composite_5);
			formToolkit.paintBordersFor(composite_5);
			
			Composite composite_6 = new Composite(composite_5, SWT.NONE);
			formToolkit.adapt(composite_6);
			formToolkit.paintBordersFor(composite_6);
			GridLayout gl_composite_6 = new GridLayout(2, false);
			gl_composite_6.marginLeft = 7;
			composite_6.setLayout(gl_composite_6);
			new Label(composite_6, SWT.NONE);
			new Label(composite_6, SWT.NONE);
			new Label(composite_6, SWT.NONE);
			
			Label lblNewLabel_3 = new Label(composite_6, SWT.NONE);
			GridData gd_lblNewLabel_3 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
			gd_lblNewLabel_3.heightHint = 51;
			lblNewLabel_3.setLayoutData(gd_lblNewLabel_3);
			formToolkit.adapt(lblNewLabel_3, true, true);
			
				Label label = new Label(composite_6, SWT.SEPARATOR | SWT.WRAP | SWT.HORIZONTAL | SWT.SHADOW_NONE);
				GridData gd_label = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
				gd_label.widthHint = 20;
				label.setLayoutData(gd_label);
				label.setBackground(SWTResourceManager.getColor(48, 85, 141));
			
			Label btnCheckButton_2 = new Label(composite_6, SWT.NONE);
			btnCheckButton_2.setForeground(SWTResourceManager.getColor(SWT.COLOR_TITLE_INACTIVE_FOREGROUND));
			btnCheckButton_2.setFont(SWTResourceManager.getFont("Tahoma", 8, SWT.NORMAL));
			btnCheckButton_2.setText("application");
				
				Label label_1 = new Label(composite_6, SWT.SEPARATOR | SWT.WRAP | SWT.HORIZONTAL | SWT.SHADOW_NONE);
				GridData gd_label_1 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
				gd_label_1.widthHint = 20;
				label_1.setLayoutData(gd_label_1);
				label_1.setBackground(SWTResourceManager.getColor(255, 191, 9));
				
				
				Label btnCheckButton_3 = new Label(composite_6, SWT.NONE);
				btnCheckButton_3.setFont(SWTResourceManager.getFont("Tahoma", 8, SWT.NORMAL));
				btnCheckButton_3.setForeground(SWTResourceManager.getColor(SWT.COLOR_TITLE_INACTIVE_FOREGROUND));
				btnCheckButton_3.setText("cpu");
					
					Label label_2 = new Label(composite_6, SWT.SEPARATOR | SWT.WRAP | SWT.HORIZONTAL | SWT.SHADOW_NONE);
					GridData gd_label_2 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
					gd_label_2.widthHint = 20;
					label_2.setLayoutData(gd_label_2);    		
					label_2.setBackground(SWTResourceManager.getColor(99, 181, 51));
				
					
					Label btnCheckButton_4 = new Label(composite_6, SWT.NONE);
					btnCheckButton_4.setFont(SWTResourceManager.getFont("Tahoma", 8, SWT.NORMAL));
					btnCheckButton_4.setForeground(SWTResourceManager.getColor(SWT.COLOR_TITLE_INACTIVE_FOREGROUND));
					btnCheckButton_4.setText("userIo");
					
					Label label_6 = new Label(composite_6, SWT.SEPARATOR | SWT.WRAP | SWT.HORIZONTAL | SWT.SHADOW_NONE);										
					GridData gd_label_6 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
					gd_label_6.widthHint = 20;
					label_6.setLayoutData(gd_label_6);    						
					label_6.setBackground(SWTResourceManager.getColor(58, 137, 159));
					
					
					
					Label btnOtherwait = new Label(composite_6, SWT.CHECK);
					btnOtherwait.setText("otherWait");
					btnOtherwait.setFont(SWTResourceManager.getFont("Tahoma", 8, SWT.NORMAL));
					btnOtherwait.setForeground(SWTResourceManager.getColor(SWT.COLOR_TITLE_INACTIVE_FOREGROUND));
					
					Label label_7 = new Label(composite_6, SWT.SEPARATOR | SWT.WRAP | SWT.HORIZONTAL | SWT.SHADOW_NONE);
					GridData gd_label_7 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
					gd_label_7.widthHint = 20;
					label_7.setLayoutData(gd_label_7);  
					label_7.setBackground(SWTResourceManager.getColor(255, 127, 0));
					
					Label btnConcurrency = new Label(composite_6, SWT.NONE);
					btnConcurrency.setText("concurrency");
					btnConcurrency.setFont(SWTResourceManager.getFont("Tahoma", 8, SWT.NORMAL));
					btnConcurrency.setForeground(SWTResourceManager.getColor(SWT.COLOR_TITLE_INACTIVE_FOREGROUND));
					
					Composite composite_7 = new Composite(composite_5, SWT.NONE);
					formToolkit.adapt(composite_7);
					formToolkit.paintBordersFor(composite_7);
					GridLayout gl_composite_7 = new GridLayout(4, false);
					gl_composite_7.marginLeft = 7;
					composite_7.setLayout(gl_composite_7);
					new Label(composite_7, SWT.NONE);
					new Label(composite_7, SWT.NONE);
					new Label(composite_7, SWT.NONE);
					new Label(composite_7, SWT.NONE);
					new Label(composite_7, SWT.NONE);
					
					Label label_3 = new Label(composite_7, SWT.NONE);
					GridData gd_label_3 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
					gd_label_3.heightHint = 52;
					label_3.setLayoutData(gd_label_3);
					formToolkit.adapt(label_3, true, true);
					new Label(composite_7, SWT.NONE);
					new Label(composite_7, SWT.NONE);
					
					
					Label label_5 = new Label(composite_7, SWT.SEPARATOR | SWT.WRAP | SWT.HORIZONTAL | SWT.SHADOW_NONE);
					GridData gd_label_5 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
					gd_label_5.widthHint = 20;
					label_5.setLayoutData(gd_label_5);
					label_5.setBackground(SWTResourceManager.getColor(48, 85, 141));
					
					Label lblValue = new Label(composite_7, SWT.NONE);
					GridData gd_lblValue = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
					gd_lblValue.widthHint = 56;
					lblValue.setLayoutData(gd_lblValue);
					lblValue.setText("value");
					lblValue.setForeground(SWTResourceManager.getColor(SWT.COLOR_TITLE_INACTIVE_FOREGROUND));
					lblValue.setFont(SWTResourceManager.getFont("Tahoma", 8, SWT.NORMAL));
					new Label(composite_7, SWT.NONE);
					
					
					Label label_4 = new Label(composite_7, SWT.SEPARATOR | SWT.WRAP | SWT.HORIZONTAL | SWT.SHADOW_NONE);
					label_4.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION));
					formToolkit.adapt(label_4, true, true);
			
			Composite composite_2 = new Composite(composite, SWT.NONE);
			composite_2.setLayout(new FillLayout(SWT.VERTICAL));
			GridData gd_composite_2 = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
			gd_composite_2.heightHint = 446;
			composite_2.setLayoutData(gd_composite_2);
			formToolkit.adapt(composite_2);
			formToolkit.paintBordersFor(composite_2);
			
			Section sctnNewSection_1 = formToolkit.createSection(composite_MAIN, Section.TWISTIE | Section.TITLE_BAR);
			sctnNewSection_1.setFont(SWTResourceManager.getFont("Tahoma", 9, SWT.NORMAL));
			GridData gd_sctnNewSection_1 = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
			gd_sctnNewSection_1.horizontalIndent = 1;
			gd_sctnNewSection_1.heightHint = 0;
			gd_sctnNewSection_1.minimumHeight = 400;
			sctnNewSection_1.setLayoutData(gd_sctnNewSection_1);
			formToolkit.paintBordersFor(sctnNewSection_1);
			sctnNewSection_1.setText("Detail");
			sctnNewSection_1.setExpanded(true);
			sctnNewSection_1.setTitleBarForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BORDER));
			sctnNewSection_1.setTitleBarBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
			sctnNewSection_1.setTitleBarBorderColor(SWTResourceManager.getColor(SWT.COLOR_GRAY));
			
			Composite composite_1 = new Composite(sctnNewSection_1, SWT.NONE);
			formToolkit.adapt(composite_1);
			formToolkit.paintBordersFor(composite_1);
			sctnNewSection_1.setClient(composite_1);
			GridLayout gl_composite_1 = new GridLayout(2, false);
			gl_composite_1.marginLeft = 15;
			composite_1.setLayout(gl_composite_1);
			
			Composite composite_8 = new Composite(composite_1, SWT.NONE);
			composite_8.setForeground(SWTResourceManager.getColor(SWT.COLOR_TITLE_INACTIVE_FOREGROUND));
			composite_8.setLayout(new GridLayout(2, false));
			GridData gd_composite_8 = new GridData(SWT.LEFT, SWT.FILL, false, false, 1, 1);
			gd_composite_8.widthHint = 105;
			composite_8.setLayoutData(gd_composite_8);
			formToolkit.adapt(composite_8);
			formToolkit.paintBordersFor(composite_8);
			new Label(composite_8, SWT.NONE);
			
			Label label_8 = new Label(composite_8, SWT.NONE);
			GridData gd_label_8 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
			gd_label_8.heightHint = 39;
			label_8.setLayoutData(gd_label_8);
			formToolkit.adapt(label_8, true, true);
			
			Label label_9 = new Label(composite_8, SWT.SEPARATOR | SWT.WRAP | SWT.HORIZONTAL | SWT.SHADOW_NONE);
			GridData gd_label_9 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
			gd_label_9.widthHint = 20;
			label_9.setLayoutData(gd_label_9);
			label_9.setBackground(SWTResourceManager.getColor(48, 85, 141));
			
			
			
	
			
			
			Label lblNewLabel_6 = new Label(composite_8, SWT.NONE);
			lblNewLabel_6.setForeground(SWTResourceManager.getColor(SWT.COLOR_TITLE_INACTIVE_FOREGROUND));
			lblNewLabel_6.setFont(SWTResourceManager.getFont("Tahoma", 8, SWT.NORMAL));
		
			
			lblNewLabel_6.setText("other");
			
			Label label_10 = new Label(composite_8, SWT.SEPARATOR | SWT.WRAP | SWT.HORIZONTAL | SWT.SHADOW_NONE);
			GridData gd_label_10 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
			gd_label_10.widthHint = 20;
			label_10.setLayoutData(gd_label_10);
			label_10.setBackground(SWTResourceManager.getColor(255, 191, 9));
			
			
			Label lblNewLabel_7 = new Label(composite_8, SWT.NONE);
			lblNewLabel_7.setForeground(SWTResourceManager.getColor(SWT.COLOR_TITLE_INACTIVE_FOREGROUND));
			lblNewLabel_7.setFont(SWTResourceManager.getFont("Tahoma", 8, SWT.NORMAL));
			lblNewLabel_7.setText("application");
			
			Label label_11 = new Label(composite_8, SWT.SEPARATOR | SWT.WRAP | SWT.HORIZONTAL | SWT.SHADOW_NONE);
			GridData gd_label_11 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
			gd_label_11.widthHint = 20;
			label_11.setLayoutData(gd_label_11);
			label_11.setBackground(SWTResourceManager.getColor(99, 181, 51));
		
			
			Label lblNewLabel_8 = new Label(composite_8, SWT.NONE);
			lblNewLabel_8.setForeground(SWTResourceManager.getColor(SWT.COLOR_TITLE_INACTIVE_FOREGROUND));
			lblNewLabel_8.setFont(SWTResourceManager.getFont("Tahoma", 8, SWT.NORMAL));
			lblNewLabel_8.setText("configuration");
			
			Label label_12 = new Label(composite_8, SWT.SEPARATOR | SWT.WRAP | SWT.HORIZONTAL | SWT.SHADOW_NONE);
			GridData gd_label_12 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
			gd_label_12.widthHint = 20;
			label_12.setLayoutData(gd_label_12);
			label_12.setBackground(SWTResourceManager.getColor(58, 137, 159));
			
			
			Label lblNewLabel_9 = new Label(composite_8, SWT.NONE);
			lblNewLabel_9.setForeground(SWTResourceManager.getColor(SWT.COLOR_TITLE_INACTIVE_FOREGROUND));
			lblNewLabel_9.setText("administrative");
			lblNewLabel_9.setFont(SWTResourceManager.getFont("Tahoma", 8, SWT.NORMAL));
			
			Label label_13 = new Label(composite_8, SWT.SEPARATOR | SWT.WRAP | SWT.HORIZONTAL | SWT.SHADOW_NONE);
			GridData gd_label_13 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
			gd_label_13.widthHint = 20;
			label_13.setLayoutData(gd_label_13);
			label_13.setBackground(SWTResourceManager.getColor(255, 127, 0));

			
			Label lblNewLabel_10 = new Label(composite_8, SWT.NONE);
			lblNewLabel_10.setForeground(SWTResourceManager.getColor(SWT.COLOR_TITLE_INACTIVE_FOREGROUND));
			lblNewLabel_10.setFont(SWTResourceManager.getFont("Tahoma", 8, SWT.NORMAL));
			lblNewLabel_10.setText("concurrency");
			
			Label label_14 = new Label(composite_8, SWT.SEPARATOR | SWT.WRAP | SWT.HORIZONTAL | SWT.SHADOW_NONE);
			GridData gd_label_14 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
			gd_label_14.widthHint = 20;
			label_14.setLayoutData(gd_label_14);
			label_14.setBackground(SWTResourceManager.getColor(116, 140, 157));
		
			
			Label lblNewLabel_11 = new Label(composite_8, SWT.NONE);
			lblNewLabel_11.setForeground(SWTResourceManager.getColor(SWT.COLOR_TITLE_INACTIVE_FOREGROUND));
			lblNewLabel_11.setFont(SWTResourceManager.getFont("Tahoma", 8, SWT.NORMAL));
			lblNewLabel_11.setText("commit");
			
			Label label_15 = new Label(composite_8, SWT.SEPARATOR | SWT.WRAP | SWT.HORIZONTAL | SWT.SHADOW_NONE);
			GridData gd_label_15 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
			gd_label_15.widthHint = 20;
			label_15.setLayoutData(gd_label_15);
			label_15.setBackground(SWTResourceManager.getColor(126, 82, 158));
	
			
			Label lblNewLabel_12 = new Label(composite_8, SWT.NONE);
			lblNewLabel_12.setForeground(SWTResourceManager.getColor(SWT.COLOR_TITLE_INACTIVE_FOREGROUND));
			lblNewLabel_12.setFont(SWTResourceManager.getFont("Tahoma", 8, SWT.NORMAL));
			lblNewLabel_12.setText("Idle");
			
			Label label_16 = new Label(composite_8, SWT.SEPARATOR | SWT.WRAP | SWT.HORIZONTAL | SWT.SHADOW_NONE);
			GridData gd_label_16 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
			gd_label_16.widthHint = 20;
			label_16.setLayoutData(gd_label_16);
			label_16.setBackground(SWTResourceManager.getColor(200, 27, 89));
		
			
			Label lblNewLabel_13 = new Label(composite_8, SWT.NONE);
			lblNewLabel_13.setForeground(SWTResourceManager.getColor(SWT.COLOR_TITLE_INACTIVE_FOREGROUND));
			lblNewLabel_13.setFont(SWTResourceManager.getFont("Tahoma", 8, SWT.NORMAL));
			lblNewLabel_13.setText("network");
			
			Label label_17 = new Label(composite_8, SWT.SEPARATOR | SWT.WRAP | SWT.HORIZONTAL | SWT.SHADOW_NONE);
			GridData gd_label_17 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
			gd_label_17.widthHint = 20;
			label_17.setLayoutData(gd_label_17);
			label_17.setBackground(SWTResourceManager.getColor(23, 111, 41));
			
			
			Label lblNewLabel_14 = new Label(composite_8, SWT.NONE);
			lblNewLabel_14.setForeground(SWTResourceManager.getColor(SWT.COLOR_TITLE_INACTIVE_FOREGROUND));
			lblNewLabel_14.setFont(SWTResourceManager.getFont("Tahoma", 8, SWT.NORMAL));
			lblNewLabel_14.setText("userIo");
			
			Label label_18 = new Label(composite_8, SWT.SEPARATOR | SWT.WRAP | SWT.HORIZONTAL | SWT.SHADOW_NONE);
			GridData gd_label_18 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
			gd_label_18.widthHint = 20;
			label_18.setLayoutData(gd_label_18);
			label_18.setBackground(SWTResourceManager.getColor(191, 210, 29));
		
			
			Label lblNewLabel_15 = new Label(composite_8, SWT.NONE);
			lblNewLabel_15.setForeground(SWTResourceManager.getColor(SWT.COLOR_TITLE_INACTIVE_FOREGROUND));
			lblNewLabel_15.setFont(SWTResourceManager.getFont("Tahoma", 8, SWT.NORMAL));
			lblNewLabel_15.setText("systemIo");
			
			Label label_19 = new Label(composite_8, SWT.SEPARATOR | SWT.WRAP | SWT.HORIZONTAL | SWT.SHADOW_NONE);
			GridData gd_label_19 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
			gd_label_19.widthHint = 20;
			label_19.setLayoutData(gd_label_19);
			label_19.setBackground(SWTResourceManager.getColor(255, 65, 0));
	
			
			Label lblNewLabel_16 = new Label(composite_8, SWT.NONE);
			lblNewLabel_16.setForeground(SWTResourceManager.getColor(SWT.COLOR_TITLE_INACTIVE_FOREGROUND));
			lblNewLabel_16.setFont(SWTResourceManager.getFont("Tahoma", 8, SWT.NORMAL));
			lblNewLabel_16.setText("scheduler");
			
			Label label_20 = new Label(composite_8, SWT.SEPARATOR | SWT.WRAP | SWT.HORIZONTAL | SWT.SHADOW_NONE);
			GridData gd_label_20 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
			gd_label_20.widthHint = 20;
			label_20.setLayoutData(gd_label_20);
			label_20.setBackground(SWTResourceManager.getColor(246, 69, 106));
		
			
			Label lblNewLabel_17 = new Label(composite_8, SWT.NONE);
			lblNewLabel_17.setForeground(SWTResourceManager.getColor(SWT.COLOR_TITLE_INACTIVE_FOREGROUND));
			lblNewLabel_17.setFont(SWTResourceManager.getFont("Tahoma", 8, SWT.NORMAL));
			lblNewLabel_17.setText("clust");
			
			Label label_21 = new Label(composite_8, SWT.SEPARATOR | SWT.WRAP | SWT.HORIZONTAL | SWT.SHADOW_NONE);
			GridData gd_label_21 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
			gd_label_21.widthHint = 20;
			label_21.setLayoutData(gd_label_21);
			label_21.setBackground(SWTResourceManager.getColor(191, 0, 0));
	
			
			Label lblNewLabel_18 = new Label(composite_8, SWT.NONE);
			lblNewLabel_18.setForeground(SWTResourceManager.getColor(SWT.COLOR_TITLE_INACTIVE_FOREGROUND));
			lblNewLabel_18.setFont(SWTResourceManager.getFont("Tahoma", 8, SWT.NORMAL));
			lblNewLabel_18.setText("queueing");
			
			Label label_22 = new Label(composite_8, SWT.SEPARATOR | SWT.WRAP | SWT.HORIZONTAL | SWT.SHADOW_NONE);
			GridData gd_label_22 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
			gd_label_22.widthHint = 20;
			label_22.setLayoutData(gd_label_22);
			label_22.setBackground(SWTResourceManager.getColor(138, 91, 41));
		
			
			Label lblNewLabel_19 = new Label(composite_8, SWT.NONE);
			lblNewLabel_19.setForeground(SWTResourceManager.getColor(SWT.COLOR_TITLE_INACTIVE_FOREGROUND));
			lblNewLabel_19.setFont(SWTResourceManager.getFont("Tahoma", 8, SWT.NORMAL));
			lblNewLabel_19.setText("cpu");
			
			Label label_23 = new Label(composite_8, SWT.SEPARATOR | SWT.WRAP | SWT.HORIZONTAL | SWT.SHADOW_NONE);
			GridData gd_label_23 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
			gd_label_23.widthHint = 20;
			label_23.setLayoutData(gd_label_23);
			label_23.setBackground(SWTResourceManager.getColor(111, 123, 34));
			
			
			Label lblNewLabel_20 = new Label(composite_8, SWT.NONE);
			lblNewLabel_20.setForeground(SWTResourceManager.getColor(SWT.COLOR_TITLE_INACTIVE_FOREGROUND));
			lblNewLabel_20.setFont(SWTResourceManager.getFont("Tahoma", 8, SWT.NORMAL));
			lblNewLabel_20.setText("bcpu");
			
			Composite composite_3 = new Composite(composite_1, SWT.NONE);
			composite_3.setLayout(new FillLayout(SWT.HORIZONTAL));
			GridData gd_composite_3 = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
			gd_composite_3.heightHint = 326;
			composite_3.setLayoutData(gd_composite_3);
			formToolkit.adapt(composite_3);
			formToolkit.paintBordersFor(composite_3);
			
    	
    		   
			final JFreeChart chartAcivity       = createAreaChart(dataset3 ,"· Activity History Chart");
			
    		chartcomposite_1 = new ChartComposite(composite_2, SWT.NONE, chartAcivity,
		    		   ChartComposite.DEFAULT_WIDTH, 
		    		   ChartComposite.DEFAULT_HEIGHT, 
		    		   ChartComposite.DEFAULT_MINIMUM_DRAW_WIDTH, 
		    		   ChartComposite.DEFAULT_MINIMUM_DRAW_HEIGHT, 
		    		   3000, // max draw width. We don't want it to zoom, so we put a big number 
		    		   3000, // max draw height. We don't want it to zoom, so we put a big number 
		    		   true,  // off-screen buffer 
		    		   true,  // properties 
		    		   true,  // save 
		    		   true,  // print 
		    		   true,  // zoom 
		    		   true);
    		//chartcomposite_1.setFont(SWTResourceManager.getFont("Tahoma", 9, SWT.NORMAL));
    		chartcomposite_1.setDisplayToolTips(true);
    		
    		chartcomposite_1.addChartMouseListener(new ChartMouseListener() {
				

		

				public void chartMouseClicked(ChartMouseEvent event) {
					
					chartclickFlag = true;
					
					dataset5.clear();
				
			                  XYPlot xyplot = (XYPlot) chartcomposite_1.getChart().getPlot();
			                 
			                  
			               
			                  
				     
							//XYPlot  currentPlot = (XYPlot) event.getChart().getPlot();
						      /*
							
							 try {
								  XYPlot xyPlot2 = event.getChart().getXYPlot();
								 System.out.println(xyPlot2.getDomainCrosshairValue() + " "
								            + xyPlot2.getRangeCrosshairValue());
							        
								
								  if (event.getEntity() instanceof XYItemEntity) {
					                    XYItemEntity item = (XYItemEntity) event.getEntity();		                   		                    
					                  
					                    
					                    DateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
					                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.KOREA);
		
					                    String beforeDate = dataset3.getTimePeriod(item.getItem()).toString();
		
					                    Date date = dateFormat.parse(beforeDate);
		
					                    String afterDate = sdf.format(date);
					                    System.out.println(afterDate);
			                    
			                 
			    		 		// chartcomposite_3.redraw();
			                }
			             
						 
						 
							   } catch (Exception e) {
							   	
							   }
     */
					
					 
						}

				@Override
				public void chartMouseMoved(ChartMouseEvent event) {
				
				}
				
				 /*
				 public void chartMouseMoved(ChartMouseEvent event) 
				 { 
				     int mouseX = event.getTrigger().getX(); 
				     int mouseY = event.getTrigger().getY(); 
				     Point mousePoint = new Point(mouseX, mouseY); 

				                     // convert the Java2D coordinate to axis coordinates... 
				     CombinedDomainXYPlot cdPlot = this.mJFreeChart.getCombinedPlot(); 
				     ChartRenderingInfo chartInfo = this.mChartPanel.getChartRenderingInfo(); 
				     Point2D java2DPoint = this.mChartPanel.translateScreenToJava2D(mousePoint); 
				     PlotRenderingInfo plotInfo = chartInfo.getPlotInfo(); 

				                     // see if the point is in one of the subplots; this is the 
				                     // intersection of the range and domain crosshairs 
				     int subplotIndex = plotInfo.getSubplotIndex(java2DPoint); 

				     if (subplotIndex >= 0)   // yep, position the crosshairs 
				     { 
				                                     // all subplots have the domain crosshair 
				                                     // the x coordinate is the same for all subplots 
				         Rectangle2D dataArea = plotInfo.getDataArea(); 
				         double xx = cdPlot.getDomainAxis().java2DToValue(java2DPoint.getX(), dataArea, cdPlot.getDomainAxisEdge()); 

				         Rectangle2D panelArea = this.mChartPanel.getScreenDataArea(mouseX,mouseY); 

				         List subplotsList = cdPlot.getSubplots(); 
				         Iterator iterator = subplotsList.iterator(); 
				         int index = 0; 

				         while (iterator.hasNext())   // for each subplot ... 
				         { 
				             XYPlot subplot = (XYPlot) iterator.next(); 
				                                                // set domain crosshair for each plot
				             subplot.setDomainCrosshairValue(xx, true); 

				             if (subplotIndex == index) 
				             { 
				                                      // this subplot has the range crosshair 
				                                      // get the y axis positon 
				                 double yy = subplot.getRangeAxis().java2DToValue(mousePoint.getY(), panelArea, subplot.getRangeAxisEdge()); 
				                                      // make sure the range crosshair is on 
				                 subplot.setRangeCrosshairVisible(true); 
				                                      // and plot it 
				                 subplot.setRangeCrosshairValue(yy, true); 
				             } 
				             else 
				             { 
				                                     // this subplot does not have the range 
				                                     // crosshair, make sure its off 
				                 subplot.setRangeCrosshairVisible(false); 
				             } 

				             index++; 
				         } 
				     } 
				 } 
				 */

			});
    		
    		final JFreeChart chartMetricHistory = createAreaChart(dataset4 ,"· Metric History Chart");
    		chartcomposite_2 = new ChartComposite(composite_2, SWT.NONE, chartMetricHistory,
		    		   ChartComposite.DEFAULT_WIDTH, 
		    		   ChartComposite.DEFAULT_HEIGHT, 
		    		   ChartComposite.DEFAULT_MINIMUM_DRAW_WIDTH, 
		    		   ChartComposite.DEFAULT_MINIMUM_DRAW_HEIGHT, 
		    		   3000, // max draw width. We don't want it to zoom, so we put a big number 
		    		   3000, // max draw height. We don't want it to zoom, so we put a big number 
		    		   true,  // off-screen buffer 
		    		   true,  // properties 
		    		   true,  // save 
		    		   true,  // print 
		    		   true,  // zoom 
		    		   true);   // tooltips
    		//chartcomposite_2.setSize(0, 0);
    		chartcomposite_2.setDisplayToolTips(true);
    		
    		final JFreeChart chartAcivityDetail = createAreaChart(dataset5 ,"· Activity Detail History Chart");
    		
			chartcomposite_3 = new ChartComposite(composite_3, SWT.NONE, chartAcivityDetail,
		    		   ChartComposite.DEFAULT_WIDTH, 
		    		   ChartComposite.DEFAULT_HEIGHT, 
		    		   ChartComposite.DEFAULT_MINIMUM_DRAW_WIDTH, 
		    		   ChartComposite.DEFAULT_MINIMUM_DRAW_HEIGHT, 
		    		   3000, // max draw width. We don't want it to zoom, so we put a big number 
		    		   3000, // max draw height. We don't want it to zoom, so we put a big number 
		    		   true,  // off-screen buffer 
		    		   true,  // properties 
		    		   true,  // save 
		    		   true,  // print 
		    		   true,  // zoom 
		    		   true);
			//chartcomposite_3.setDisplayToolTips(true);
			
		}
		
		Composite composite = new Composite(parent, SWT.BORDER);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		formToolkit.adapt(composite);
		formToolkit.paintBordersFor(composite);
		GridLayout gl_composite = new GridLayout(1, false);
		gl_composite.verticalSpacing = 0;
		gl_composite.horizontalSpacing = 0;
		gl_composite.marginWidth = 0;
		gl_composite.marginHeight = 0;
		composite.setLayout(gl_composite);
		
		TabFolder tabFolder = new TabFolder(composite, SWT.NONE);
		tabFolder.setFont(SWTResourceManager.getFont("Tahoma", 8, SWT.NORMAL));
		tabFolder.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		formToolkit.adapt(tabFolder);
		formToolkit.paintBordersFor(tabFolder);
		
		TabItem tbtmNewItem = new TabItem(tabFolder, SWT.NONE);
		tbtmNewItem.setImage(ResourceManager.getPluginImage("RpmUIRcp", "icons/document_info.png"));
		tbtmNewItem.setText("Session List");
		
		 tableViewer = new TableViewer(tabFolder, SWT.BORDER | SWT.FULL_SELECTION);
		 
		 
		 
		 createTableColumn(tableViewer, titles1, bounds1, aligns1);
		 tableViewer.setContentProvider(new ArrayContentProvider());
		 tableViewer.setLabelProvider(new StringArrayListLabelProvider());
		 table = tableViewer.getTable();
		 tbtmNewItem.setControl(table);
		 table.setFont(SWTResourceManager.getFont("Tahoma", 8, SWT.NORMAL));
		 table.setLinesVisible(true);
		 table.setHeaderVisible(true);
		 
		 formToolkit.paintBordersFor(table);
		
		TabItem tbtmNewItem_1 = new TabItem(tabFolder, SWT.NONE);
		tbtmNewItem_1.setImage(ResourceManager.getPluginImage("RpmUIRcp", "icons/sqlviewer/script.png"));
		tbtmNewItem_1.setText("SQL List");
		
			tableViewer_1 = new TableViewer(tabFolder, SWT.BORDER | SWT.FULL_SELECTION);
			createTableColumn(tableViewer_1, titles2, bounds2, aligns2);
			tableViewer_1.setContentProvider(new ArrayContentProvider());
			tableViewer_1.setLabelProvider(new StringArrayListLabelProvider());
			table_1 = tableViewer_1.getTable();
			tbtmNewItem_1.setControl(table_1);
			table_1.setFont(SWTResourceManager.getFont("Tahoma", 8, SWT.NORMAL));
			table_1.setHeaderVisible(true);
			table_1.setLinesVisible(true);
			
			formToolkit.paintBordersFor(table_1);
			
			
			table_1.addSelectionListener(new SelectionListener() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					Table table = (Table)e.widget;
					TableItem row = table.getSelection()[0];
					SessionViewInfo sessionViewInfo = new SessionViewInfo();
					sessionViewInfo.setInstId(row.getText(0));
					sessionViewInfo.setSqlId(row.getText(1));
					sessionViewInfo.setSqlChildNumber(row.getText(2));
					
    
					SqlViewerParamInfo sqlViewerParamInfo = new SqlViewerParamInfo();
					sqlViewerParamInfo.setInstId(row.getText(0));
					sqlViewerParamInfo.setSqlId(row.getText(1));
					sqlViewerParamInfo.setChildNumber(row.getText(2));
					
					
					List<SqlViewerOverViewInfo> overViewList = rpmSqlViewerService.getOverViewList(sqlViewerParamInfo);
					
					String currentSql = (String) rpmSessionService.getSqlFullTextInfo(sessionViewInfo).get("sql");
					
					
					if (currentSql!=null){
						List<SessionStatisticsInfo> planList =  (List<SessionStatisticsInfo>) rpmSessionService.getSqlFullTextInfo(sessionViewInfo).get("plan");
        				
        				SqlViwerDialog sqlViwerDialog = new SqlViwerDialog( Display.getCurrent().getActiveShell(), -4, currentSql, overViewList, planList);
        				sqlViwerDialog.open();
					} else {    					
						 MessageDialog.openInformation(Display.getCurrent().getActiveShell(), "Info", "Information does not exist.");
					}
					
					
					
				
				}
				
				@Override
				public void widgetDefaultSelected(SelectionEvent e) {
					
				}
			});

		
		createActions();
		initializeToolBar();
		initializeMenu();
		init();
	}
	
	private void init(){
		List<AshSampleTimeResultVO> ashSampleTimeResultVOList = this.rpmSessionService.getAwrSampleTimeList();
		List<AshMetricNameResultVO> ashMetricNameResultVOList = this.rpmSessionService.getAwrMetricNameList();
		List<InstanceNameSearchResultVO> instanceNameSearchResultVOList = this.rpmSessionService.getInstanceNameList();
		
		setTableInfo(ashSampleTimeResultVOList);
		setTableInfo2(ashMetricNameResultVOList);
		setTableInfo3(instanceNameSearchResultVOList);
	 
	    
	}

public void realTimeSelect2(String interval){
	
		
 		AshActivitySearchParamVO ashActivitySearchParamVO = new AshActivitySearchParamVO();
 		AshSysmetricHistorySearchParamVO  ashSysmetricHistorySearchParamVO = new AshSysmetricHistorySearchParamVO();
 		
 		String sampleTime = sampleTimeCombo.getText();
 		String instId = instanceIdMap.get(instanceCombo.getText());
 		String groupId = groupIdMap.get(metricCombo.getText());
 		String metricId = metricIdMap.get(metricCombo.getText());
 		
 		
 		
 		ashActivitySearchParamVO.setSecond("10");
 		ashActivitySearchParamVO.setInstId(instId);
 		ashActivitySearchParamVO.setInterval(interval);
 	
 		

 		ashSysmetricHistorySearchParamVO.setInstId(instId);
 		ashSysmetricHistorySearchParamVO.setInstCn("1");
 		ashSysmetricHistorySearchParamVO.setBeginTime(sampleTime);
        ashSysmetricHistorySearchParamVO.setBeginIntervalTime(sampleTime);		 		
 		ashSysmetricHistorySearchParamVO.setHour("1");
 		ashSysmetricHistorySearchParamVO.setGroupId(groupId);
 		ashSysmetricHistorySearchParamVO.setMetricId(metricId);
 		
 		ashActivitySearchParamVO.setAshSysmetricHistorySearchParamVO(ashSysmetricHistorySearchParamVO);
 		
 		Map<String, Object> resultMap = rpmSessionService.getAshActivityList(ashActivitySearchParamVO);
 		
 		List<AshActivitySearchResultVO> ashActivitySearchResultVOList = (List<AshActivitySearchResultVO>) resultMap.get("AshActivityList");				 		
 		
 		//List<AshSysmetricHistorySearchResultVO> ashSysmetricHistorySearchResultVOList = (List<AshSysmetricHistorySearchResultVO>) resultMap.get("AshSysmetricHistory");
 		
 		TimePeriod period = null;
 		

//	
 		String sampleTime2 = sampleTime.replace("-","").trim();
 		
 		Integer year = Integer.parseInt(sampleTime2.substring(0,4));
		Integer month = Integer.parseInt(sampleTime2.substring(4,6));
		Integer day = Integer.parseInt(sampleTime2.substring(6,8));
		
		Integer hour = 0;
		Integer min = 0;
		Integer sec = 0;
		String chartSampleTime = null;
			
 		for(int i = 0; i < ashActivitySearchResultVOList.size(); i++ ){
 		
 			chartSampleTime = ashActivitySearchResultVOList.get(i).getSampleTime().replace(":","").trim();
 		
 		
 			hour = Integer.parseInt(chartSampleTime.substring(0,2));
 			min = Integer.parseInt(chartSampleTime.substring(2,4));
 			sec = Integer.parseInt(chartSampleTime.substring(4,6));
 				
 					period	= new Second(sec, min, hour, day, month, year);

 					dataset3.add(period, ashActivitySearchResultVOList.get(i).getApplication(), "application");
 					dataset3.add(period, ashActivitySearchResultVOList.get(i).getCpu(), "cpu");
 					dataset3.add(period, ashActivitySearchResultVOList.get(i).getUserIo(), "userIo");
 					//dataset3.add(period, ashActivitySearchResultVOList.get(i).getOtherWait(), "otherWait");		 					
 					//dataset3.add(period, ashActivitySearchResultVOList.get(i).getConcurrency(), "concurrency");
 					
		} 

 	
 		//chartcomposite_1.redraw();
 		//chartcomposite_2.redraw(); 
	}
	

public void realTimeSelect(String interval){
		
		dataset3.clear();
		dataset4.clear();
		
		
 		AshActivitySearchParamVO ashActivitySearchParamVO = new AshActivitySearchParamVO();
 		AshSysmetricHistorySearchParamVO  ashSysmetricHistorySearchParamVO = new AshSysmetricHistorySearchParamVO();
 		
 		String sampleTime = sampleTimeCombo.getText();
 		String instId = instanceIdMap.get(instanceCombo.getText());
 		String groupId = groupIdMap.get(metricCombo.getText());
 		String metricId = metricIdMap.get(metricCombo.getText());
 		
 		
 		
 		ashActivitySearchParamVO.setSecond("10");
 		ashActivitySearchParamVO.setInstId(instId);
 		ashActivitySearchParamVO.setInterval(interval);
 	

 		ashSysmetricHistorySearchParamVO.setInstId(instId);
 		ashSysmetricHistorySearchParamVO.setInstCn("1");
 		ashSysmetricHistorySearchParamVO.setInterval("60");
 
 		ashSysmetricHistorySearchParamVO.setGroupId(groupId);
 		ashSysmetricHistorySearchParamVO.setMetricId(metricId);
 		
 		ashActivitySearchParamVO.setAshSysmetricHistorySearchParamVO(ashSysmetricHistorySearchParamVO);
 		
 		Map<String, Object> resultMap = rpmSessionService.getAshActivityList(ashActivitySearchParamVO);
 		
 		List<AshActivitySearchResultVO> ashActivitySearchResultVOList = (List<AshActivitySearchResultVO>) resultMap.get("AshActivityList");				 		
 		
 		
 		
 		TimePeriod period = null;
 		Double cpu = 0.0;
 		Double otherWait = 0.0;
 		Double userIo = 0.0;
 		Double application = 0.0;
 		Double concurrency = 0.0;

//	
 		String sampleTime2 = sampleTime.replace("-","").trim();
 		
 		Integer year = Integer.parseInt(sampleTime2.substring(0,4));
		Integer month = Integer.parseInt(sampleTime2.substring(4,6));
		Integer day = Integer.parseInt(sampleTime2.substring(6,8));
		
		Integer hour = 0;
		Integer min = 0;
		Integer sec = 0;
		String chartSampleTime = null;
			
 		for(int i = 0; i < ashActivitySearchResultVOList.size(); i++ ){
 		
 			chartSampleTime = ashActivitySearchResultVOList.get(i).getSampleTime().replace(":","").trim();
 		
 			hour = Integer.parseInt(chartSampleTime.substring(0,2));
 			min = Integer.parseInt(chartSampleTime.substring(2,4));
 			sec = Integer.parseInt(chartSampleTime.substring(4,6));
 				
 					period	= new Second(sec, min, hour, day, month, year);

 					dataset3.add(period, ashActivitySearchResultVOList.get(i).getApplication(), "application");
 					dataset3.add(period, ashActivitySearchResultVOList.get(i).getCpu(), "cpu");
 					dataset3.add(period, ashActivitySearchResultVOList.get(i).getUserIo(), "userIo");
 					dataset3.add(period, ashActivitySearchResultVOList.get(i).getOtherWait(), "otherWait");		 					
 					dataset3.add(period, ashActivitySearchResultVOList.get(i).getConcurrency(), "concurrency");
 					
		
		} 
 		
 		List<AshSysmetricHistorySearchResultVO> ashSysmetricHistorySearchResultVOList = this.rpmSessionService.getAshSysmetricHistory(ashSysmetricHistorySearchParamVO);
 		
 		
 		Double value = 0.0;
 		String chartSampleTime2 = null;
 	
 		
 		for(int i = 0; i < ashSysmetricHistorySearchResultVOList.size(); i++ ){
	 		
 			chartSampleTime2 = ashSysmetricHistorySearchResultVOList.get(i).getBeginTime().replace(":","").trim();
 			value            = Double.parseDouble(ashSysmetricHistorySearchResultVOList.get(i).getValue());
 			
 		
 			hour = Integer.parseInt(chartSampleTime2.substring(0,2));
 			min = Integer.parseInt(chartSampleTime2.substring(2,4));
 	
 				
 					period	= new Minute(min, hour, day, month, year);
				
 					dataset4.add(period, value, "value");
 					
		
		} 

	}
	


	public void select(){
		 ProgressMonitorDialog pmd = new ProgressMonitorDialog(Display.getCurrent().getActiveShell());
		    pmd.setCancelable(true);
		    pmd.create();
  
		dataset3.clear();
		dataset4.clear();
		dataset5.clear();
		
 		AshActivitySearchParamVO ashActivitySearchParamVO = new AshActivitySearchParamVO();
 		AshSysmetricHistorySearchParamVO  ashSysmetricHistorySearchParamVO = new AshSysmetricHistorySearchParamVO();
 		
 		String sampleTime = sampleTimeCombo.getText();
 		String instId = instanceIdMap.get(instanceCombo.getText());
 		String groupId = groupIdMap.get(metricCombo.getText());
 		String metricId = metricIdMap.get(metricCombo.getText());
 		
 		ashActivitySearchParamVO.setHour("1");
 		ashActivitySearchParamVO.setMinute("6");
 		ashActivitySearchParamVO.setInstId(instId);
 		ashActivitySearchParamVO.setSampleTime(sampleTime);
 		

 		ashSysmetricHistorySearchParamVO.setInstId(instId);
 		ashSysmetricHistorySearchParamVO.setInstCn("1");
 		System.out.println(sampleTime);
 		
 		
 		ashSysmetricHistorySearchParamVO.setBeginTime(sampleTime);
        ashSysmetricHistorySearchParamVO.setBeginIntervalTime(sampleTime);		 		
 		ashSysmetricHistorySearchParamVO.setHour("1");
 		ashSysmetricHistorySearchParamVO.setGroupId(groupId);
 		ashSysmetricHistorySearchParamVO.setMetricId(metricId);
 		
 		ashActivitySearchParamVO.setAshSysmetricHistorySearchParamVO(ashSysmetricHistorySearchParamVO);
 		
 		
 		
 		Map<String, Object> resultMap = rpmSessionService.getAwrActivityList(ashActivitySearchParamVO);
 		
 		List<AshActivitySearchResultVO> ashActivitySearchResultVOList = (List<AshActivitySearchResultVO>) resultMap.get("AwrActivityList");				 		
 		List<AshSysmetricHistorySearchResultVO> ashSysmetricHistorySearchResultVOList = (List<AshSysmetricHistorySearchResultVO>) resultMap.get("AwrSysmetricHistory");				 		
 	
 		
 		TimePeriod period = null;
 		Double cpu = 0.0;
 		Double otherWait = 0.0;
 		Double userIo = 0.0;
 		Double application = 0.0;
 		Double concurrency = 0.0;
 		
// 		final XYSeries series1 = new XYSeries("application");
// 		final XYSeries series2 = new XYSeries("cpu");
//		SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-DD HH:mm:ss");
//	
 		String sampleTime2 = sampleTime.replace("-","").trim();
 		
 		Integer year = Integer.parseInt(sampleTime2.substring(0,4));
			Integer month = Integer.parseInt(sampleTime2.substring(4,6));
			Integer day = Integer.parseInt(sampleTime2.substring(6,8));
			
			Integer hour = 0;
			Integer min = 0;
			Integer sec = 0;
 		for(int i = 0; i < ashActivitySearchResultVOList.size(); i++ ){
 		
 			String chartSampleTime = ashActivitySearchResultVOList.get(i).getSampleTime().replace(":","").trim();
 			application = ashActivitySearchResultVOList.get(i).getApplication();
 			cpu = ashActivitySearchResultVOList.get(i).getCpu();
 			otherWait =ashActivitySearchResultVOList.get(i).getOtherWait();
 			 userIo =ashActivitySearchResultVOList.get(i).getUserIo();
 			 concurrency = ashActivitySearchResultVOList.get(i).getConcurrency();
 			
 			
 		
 			hour = Integer.parseInt(chartSampleTime.substring(0,2));
 			min = Integer.parseInt(chartSampleTime.substring(2,4));
 		//	sec = Integer.parseInt(chartSampleTime.substring(4,6));
 				
 					period	= new Minute( min, hour, day, month, year);

 					dataset3.add(period, application, "application");
 					dataset3.add(period, cpu, "cpu");
 					dataset3.add(period, userIo, "userIo");
 					dataset3.add(period, otherWait, "otherWait");		 					
 					dataset3.add(period, concurrency, "concurrency");
 					
		
		} 
 		
 		
 		Double value = 0.0;
 		for(int i = 0; i < ashSysmetricHistorySearchResultVOList.size(); i++ ){
	 		
 			String chartSampleTime2 = ashSysmetricHistorySearchResultVOList.get(i).getBeginTime().replace(":","").trim();
 			value = Double.parseDouble(ashSysmetricHistorySearchResultVOList.get(i).getValue());
 			
 		
 			hour = Integer.parseInt(chartSampleTime2.substring(0,2));
 			min = Integer.parseInt(chartSampleTime2.substring(2,4));
 		//	Integer sec = Integer.parseInt(chartSampleTime2.substring(4,6));
 				
 					period	= new Minute(min, hour, day, month, year);
				
 					dataset4.add(period, value, "value");
 					
		
		} 
		  
 		
      
		  
   
		
	}
public void setTableInfo(List<AshSampleTimeResultVO> ashSampleTimeResultVOList){
		

		String[] items = new String[ashSampleTimeResultVOList.size()];
		
		for(int i = 0; i < ashSampleTimeResultVOList.size(); i++ ){
			
			items[i] = ashSampleTimeResultVOList.get(i).getBaseTime();
			
		
		} 
		  
		sampleTimeCombo.setItems(items);
		sampleTimeCombo.select(0);
	}

	public void setTableInfo2(List<AshMetricNameResultVO> ashMetricNameResultVOList){
		
		String[] items = new String[ashMetricNameResultVOList.size()];
				
		for(int i = 0; i < ashMetricNameResultVOList.size(); i++ ){
			
					
			String groupId = ashMetricNameResultVOList.get(i).getGroupId();
			String metricName = ashMetricNameResultVOList.get(i).getMetricName();
			String metricId = ashMetricNameResultVOList.get(i).getMetricId();
			
			items[i] = metricName;
			
			metricIdMap.put(metricName, metricId);
			groupIdMap.put(metricName, groupId);
		
		}
	
		
		
		metricCombo.setItems(items);
		metricCombo.select(0);
	}
	
public void setTableInfo3(List<InstanceNameSearchResultVO> instanceNameSearchResultVOList){
		

		String[] items = new String[instanceNameSearchResultVOList.size()];
		
		for(int i = 0; i < instanceNameSearchResultVOList.size(); i++ ){
		
			String instanceName = instanceNameSearchResultVOList.get(i).getInstanceName();
			String instId = instanceNameSearchResultVOList.get(i).getInstId();
			items[i] = instanceNameSearchResultVOList.get(i).getInstanceName();
			
			instanceIdMap.put(instanceName, instId);
			
		
		} 
		  
		instanceCombo.setItems(items);
		instanceCombo.select(0);
	}

public void setTableInfo33(List<AshActivitySessionSearchResultVO> ashActivitySessionSearchResultVOList){
	List<String[]> list = new ArrayList<String[]>();
	for(AshActivitySessionSearchResultVO ashActivitySessionSearchResultVO : ashActivitySessionSearchResultVOList){
		String[] row = { ashActivitySessionSearchResultVO.getInstId()
				       , ashActivitySessionSearchResultVO.getSessionId()
				       , ashActivitySessionSearchResultVO.getSessionSerial()
				       , ashActivitySessionSearchResultVO.getRt()
				       , ashActivitySessionSearchResultVO.getCpu()
				       , ashActivitySessionSearchResultVO.getWait()					       
				       , ashActivitySessionSearchResultVO.getTmDeltaCpuTime()
				       , ashActivitySessionSearchResultVO.getTmDeltaDbTime()
				       , ashActivitySessionSearchResultVO.getDeltaReadIoBytes()
				       , ashActivitySessionSearchResultVO.getDeltaWriteIoBytes()
				       , ashActivitySessionSearchResultVO.getDeltaInterconnectIoBytes()
				       , ashActivitySessionSearchResultVO.getDeltaReadMemBytes()
				       };
		list.add(row);
	}
	tableViewer.setInput(list);
}


public void setTableInfo333(List<AshActivitySqlSearchResultVO> ashActivitySqlSearchResultVOList){
	
	List<String[]> list = new ArrayList<String[]>();
	for(AshActivitySqlSearchResultVO ashActivitySqlSearchResultVO : ashActivitySqlSearchResultVOList){
		String[] row = { ashActivitySqlSearchResultVO.getInstId()
				 		, ashActivitySqlSearchResultVO.getSqlId()
				 		, ashActivitySqlSearchResultVO.getSqlChildNumber()
				       , ashActivitySqlSearchResultVO.getRt()
				       , ashActivitySqlSearchResultVO.getCpu()
				       , ashActivitySqlSearchResultVO.getWait()					       
				       , ashActivitySqlSearchResultVO.getTmDeltaCpuTime()
				       , ashActivitySqlSearchResultVO.getTmDeltaDbTime()
				       , ashActivitySqlSearchResultVO.getDeltaReadIoBytes()
				       , ashActivitySqlSearchResultVO.getDeltaWriteIoBytes()
				       , ashActivitySqlSearchResultVO.getDeltaInterconnectIoBytes()
				       , ashActivitySqlSearchResultVO.getDeltaReadMemBytes()
				       };
		list.add(row);
	}
	tableViewer_1.setInput(list);
}
	/**
	 * Create the actions.
	 */
	private void createActions() {
		// Create the actions
	}

	/**
	 * Initialize the toolbar.
	 */
	private void initializeToolBar() {
		IToolBarManager toolbarManager = getViewSite().getActionBars()
				.getToolBarManager();
	}

	/**
	 * Initialize the menu.
	 */
	private void initializeMenu() {
		IMenuManager menuManager = getViewSite().getActionBars()
				.getMenuManager();
	}

	 private JFreeChart createAreaChart(final TimeTableXYDataset dataset,String title) {
//	        final JFreeChart chart = ChartFactory.createStackedXYAreaChart(
//	        		""
//	        		, ""
//	        		, ""
//	        		, dataset
//	        		, PlotOrientation.VERTICAL
//	        		, true
//	        		, true
//	        		, false);

	    	DateAxis dateaxis = new DateAxis("");
			NumberAxis numberaxis = new NumberAxis("");
		
			dateaxis.setTickLabelFont(new Font("Tahoma", 0, 11));
			dateaxis.setLabelFont(new Font("Tahoma", 0, 11));
			dateaxis.setTickLabelPaint(Color.DARK_GRAY);
			
			//dateaxis.setLabelPaint(Color.DARK_GRAY);
			//dateaxis.setAxisLinePaint(Color.DARK_GRAY);
			
			numberaxis.setTickLabelFont(new Font("Tahoma", 0, 11));
			numberaxis.setLabelFont(new Font("Tahoma", 0, 11));
			numberaxis.setTickLabelPaint(Color.DARK_GRAY);
			
			//numberaxis.setLabelPaint(Color.DARK_GRAY);
			//numberaxis.setAxisLinePaint(Color.DARK_GRAY);
			
			
			
			XYToolTipGenerator xyToolTipGenerator = new XYToolTipGenerator()
			
			 {
			     public String generateToolTip(XYDataset dataset, int series, int item)
			     {
			         Number x1 = dataset.getX(series, item);
			         Number y1 = dataset.getY(series, item);
			         StringBuilder stringBuilder = new StringBuilder();
			         stringBuilder.append(String.format("Value\n"));
			         stringBuilder.append(String.format("X:'%d'\n", x1.intValue()));
			         stringBuilder.append(String.format("Y:'%d'\n", y1.intValue()));
			         
			         return stringBuilder.toString();
			         
			         
			     
			     }
			 };
			
			XYLineAndShapeRenderer xylineandshaperenderer = new XYLineAndShapeRenderer(true, false);
			xylineandshaperenderer.setSeriesPaint(0, new Color(48, 85, 141));
			xylineandshaperenderer.setSeriesPaint(1, new Color(255, 191, 9));
			xylineandshaperenderer.setSeriesPaint(2, new Color(99, 181, 51));
			xylineandshaperenderer.setSeriesPaint(3, new Color(58, 137, 159));
			xylineandshaperenderer.setSeriesPaint(4, new Color(255, 127, 0));
			xylineandshaperenderer.setSeriesPaint(5, new Color(116, 140, 157));
			xylineandshaperenderer.setSeriesPaint(6, new Color(126, 82, 158));
			xylineandshaperenderer.setSeriesPaint(7, new Color(200, 27, 89));
			xylineandshaperenderer.setSeriesPaint(8, new Color(23, 111, 41));
			xylineandshaperenderer.setSeriesPaint(9, new Color(191, 210, 29));
			xylineandshaperenderer.setSeriesPaint(10, new Color(255, 65, 0));
			xylineandshaperenderer.setSeriesPaint(11, new Color(246, 69, 106));
			xylineandshaperenderer.setSeriesPaint(12, new Color(191, 0, 0));
			xylineandshaperenderer.setSeriesPaint(13, new Color(138, 91, 41));
			xylineandshaperenderer.setSeriesPaint(14, new Color(111, 123, 34));
		
		//	xylineandshaperenderer.setBaseStroke(new BasicStroke(9.0F, 0, 1));
			
			xylineandshaperenderer.setBaseToolTipGenerator(xyToolTipGenerator);
			XYPlot xyplot = new XYPlot(dataset, dateaxis, numberaxis, xylineandshaperenderer);
			
			xyplot.setBackgroundPaint(Color.white);
			xyplot.setDomainGridlinePaint(Color.lightGray);
			xyplot.setDomainGridlinesVisible(true);		       
			
			
			xyplot.setRangeGridlinePaint(Color.lightGray);
			xyplot.setRangeGridlinesVisible(true);
	
			xyplot.setDomainCrosshairVisible(true);
			xyplot.setDomainCrosshairLockedOnData(true);
			
			xyplot.setRangeCrosshairVisible(true);
			xyplot.setRangeCrosshairLockedOnData(true);
			
	            
			xyplot.setDomainCrosshairPaint(Color.DARK_GRAY);
			xyplot.setRangeCrosshairPaint(Color.DARK_GRAY);
			

			
			xyplot.setOutlinePaint(Color.white);					
			xyplot.setAxisOffset(new RectangleInsets(15D, 15D, 15D, 15D));
	      
		
			dateaxis.setLowerMargin(0.0D);
			dateaxis.setUpperMargin(0.0D);
			dateaxis.setTickLabelsVisible(true);
			
			dateaxis.setAutoRange(true);
			//dateaxis.setFixedAutoRange(10);
			
		
		//	numberaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
			
			numberaxis.setAxisLineVisible(true);
			//numberaxis.setTickMarksVisible(true);

			numberaxis.setAutoRange(true);


			JFreeChart jfreechart = new JFreeChart(title, new Font("맑은 고딕", 1, 11), xyplot, true);
			jfreechart.addProgressListener(this);   
			
			
			jfreechart.setBackgroundPaint(Color.white);
			jfreechart.setBorderVisible(false);
			jfreechart.setPadding(new RectangleInsets(0, 0, 0, 50));
			    
			jfreechart.getTitle().setHorizontalAlignment(HorizontalAlignment.LEFT);
			jfreechart.getTitle().setMargin(5.0D, 2.0D, 2.0D, 0.0D);
			jfreechart.getTitle().setBackgroundPaint(Color.white);
			jfreechart.getTitle().setPaint(Color.DARK_GRAY);
			jfreechart.getTitle().setVisible(false);
		

			
			AxisSpace axisSpace = new AxisSpace(); 
			axisSpace.setTop(20); 
		
			axisSpace.setBottom(30); 
			axisSpace.setRight(0);
			axisSpace.setLeft(25.0);
			xyplot.setFixedDomainAxisSpace(axisSpace); 										
			xyplot.setFixedRangeAxisSpace(axisSpace);
			
			
			//Legend
			LegendTitle legend = jfreechart.getLegend();
			legend.setVisible(false);			
			//legend.setFrame(new BlockBorder(Color.white));			
			//legend.setItemFont(new Font("Tahoma", 0, 12));
			//legend.setPosition(RectangleEdge.RIGHT);
			
		
			return jfreechart;
	    }

    
	@Override
	public void reflesh() {
		//init();
	}
	
	 public void setFocus() {
		PlatformUI.getWorkbench().getThemeManager().setCurrentTheme("de.spiritlink.custom.ui.theme");
	 }
	
	
	@Override
	public void chartProgress(ChartProgressEvent event) {
	
		if (event.getType() != ChartProgressEvent.DRAWING_FINISHED) {   
            return;   
        }   
		
        if (this.chartcomposite_1 != null) {   
            JFreeChart c = this.chartcomposite_1.getChart();   
            if (c != null) {   
                XYPlot plot = c.getXYPlot();   
           
                
                double xx = plot.getDomainCrosshairValue();   

               
             
                
                
                double x,y;
                x = plot.getDomainCrosshairValue();
                y = plot.getRangeCrosshairValue();
                
              	  SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.KOREA);
                    String afterDate = df2.format(xx);
                    System.out.println(">>>>>>>>>>>"+afterDate);
                  
                
                    XYTextAnnotation annotation = new XYTextAnnotation(afterDate, x, y);
                    annotation.setTextAnchor(TextAnchor.BOTTOM_CENTER);
                    
              	  plot.clearAnnotations();
              	  plot.addAnnotation(annotation);
               
                
          
                  if (chartclickFlag){
                	  

                     
	               
		                  
	                  String sampleTime = afterDate;
	                  String minute = "3";
	                  String instId = instanceIdMap.get(instanceCombo.getText());
	                  
	                  
	                  
	                 //Session
	                  AshActivitySessionSearchParamVO ashActivitySessionSearchParamVO = new AshActivitySessionSearchParamVO();		
	  		 		ashActivitySessionSearchParamVO.setSampleTime(afterDate);			                    
	  		 		ashActivitySessionSearchParamVO.setMinute(minute);
	  		 		
	                 //SQL
	                  AshActivitySqlSearchParamVO ashActivitySqlSearchParamVO = new AshActivitySqlSearchParamVO();		
	                  ashActivitySqlSearchParamVO.setInstId(instId);
	                  ashActivitySqlSearchParamVO.setMinute(minute);
	                  ashActivitySqlSearchParamVO.setSampleTime(sampleTime);			 
	                  
	                 
	  		 		
	                  //Detail
	                  AshActivityDetailSearchParamVO ashActivityDetailSearchParamVO = new AshActivityDetailSearchParamVO();	
	                  ashActivityDetailSearchParamVO.setInstId(instId);
	                  ashActivityDetailSearchParamVO.setMinute(minute);
	                  ashActivityDetailSearchParamVO.setSampleTime(sampleTime);
	                  
	                  
	                  ashActivityDetailSearchParamVO.setAshActivitySessionSearchParamVO(ashActivitySessionSearchParamVO);
	                  ashActivityDetailSearchParamVO.setAshActivitySqlSearchParamVO(ashActivitySqlSearchParamVO);
	                
	                  
	                  Map<String, Object> resultMap = rpmSessionService.getAshActivityDetail(ashActivityDetailSearchParamVO);
	                 
	                  List<AshActivityDetailSearchResultVO> ashActivityDetailSearchResultVOList = (List<AshActivityDetailSearchResultVO>) resultMap.get("detail");
	                  
	                  List<AshActivitySqlSearchResultVO> ashActivitySqlSearchResultVO = (List<AshActivitySqlSearchResultVO>) resultMap.get("sql");
	                  List<AshActivitySessionSearchResultVO> ashActivitySessionSearchResultVOList = (List<AshActivitySessionSearchResultVO>) resultMap.get("sesiion");
	              
	                  
	                  
	                
	                  TimePeriod period3 = null;
	                
	                  
	                  String time  = afterDate.replace("-","");
	                  
	              	Integer year = Integer.parseInt(time.substring(0,4));
	  		 		Integer month = Integer.parseInt(time.substring(4,6));
	  		 		Integer day = Integer.parseInt(time.substring(6,8));
	  		 		
	  		 		String chartSampleTime2 = null;
	  		 		Integer hour = 0;
			 			Integer min = 0;
			 			Integer sec = 0;
			 			
	  		 		for(int i = 0; i < ashActivityDetailSearchResultVOList.size(); i++ ){
	  		 			
	  			 		
	  		 			chartSampleTime2 = ashActivityDetailSearchResultVOList.get(i).getBeginTime().replace(":","").trim();
	
		    		 			 hour = Integer.parseInt(chartSampleTime2.substring(0,2));
		    		 			 min = Integer.parseInt(chartSampleTime2.substring(2,4));
		    		 			 sec = Integer.parseInt(chartSampleTime2.substring(4,6));
	  		 					    		 			
	  		 				period3	= new Second(sec,min,hour, day,month,year );
	  		 					
	  		 					
	  		 					
			 					dataset5.add(period3, ashActivityDetailSearchResultVOList.get(i).getOther(), "other");
			 					dataset5.add(period3, ashActivityDetailSearchResultVOList.get(i).getApplication(), "application");
			 					dataset5.add(period3, ashActivityDetailSearchResultVOList.get(i).getConfiguration(), "configuration");
			 					dataset5.add(period3, ashActivityDetailSearchResultVOList.get(i).getAdministrative(), "administrative");
			 					dataset5.add(period3, ashActivityDetailSearchResultVOList.get(i).getConcurrency(), "concurrency");
			 					dataset5.add(period3, ashActivityDetailSearchResultVOList.get(i).getCommit(), "commit");
			 					dataset5.add(period3, ashActivityDetailSearchResultVOList.get(i).getIdle(), "Idle");
			 					dataset5.add(period3, ashActivityDetailSearchResultVOList.get(i).getNetwork(), "network");
			 					dataset5.add(period3, ashActivityDetailSearchResultVOList.get(i).getUserIo(), "userIo");
			 					dataset5.add(period3, ashActivityDetailSearchResultVOList.get(i).getSystemIo(), "systemIo");
			 					dataset5.add(period3, ashActivityDetailSearchResultVOList.get(i).getScheduler(), "scheduler");
			 					dataset5.add(period3, ashActivityDetailSearchResultVOList.get(i).getClust(), "clust");
			 					dataset5.add(period3, ashActivityDetailSearchResultVOList.get(i).getQueueing(), "queueing");
			 					dataset5.add(period3, ashActivityDetailSearchResultVOList.get(i).getCpu(), "cpu");
			 					dataset5.add(period3, ashActivityDetailSearchResultVOList.get(i).getBcpu(), "bcpu");			    		 							    				
	  		 				} 
	  		 		
	  		 		
	
	  		 		 
	  		 				setTableInfo333(ashActivitySqlSearchResultVO);
		                   
		                   setTableInfo33(ashActivitySessionSearchResultVOList);
                  }          
                  chartclickFlag = false;
            }   
         
        }   

	}

	@Override
	public void chartChanged(ChartChangeEvent arg0) {
		
		System.out.println("dfdfdf");
		// TODO Auto-generated method stub
		
	}
}
