package com.b2en.rpm.ui.rcp.view.part;

import java.awt.Color;
import java.awt.Font;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.wb.swt.ResourceManager;
import org.eclipse.wb.swt.SWTResourceManager;
import org.jfree.chart.JFreeChart;
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
import org.jfree.data.time.TimeTableXYDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.experimental.chart.swt.ChartComposite;
import org.jfree.ui.HorizontalAlignment;
import org.jfree.ui.RectangleInsets;

import com.b2en.rpm.ui.rcp.view.AbstractUtilView;

public class SystemMonitorViewPart extends AbstractUtilView implements ChartChangeListener, ChartProgressListener {   
	public SystemMonitorViewPart() {
	}

	public static final String ID = "RpmUIRcp.SystemMonitorViewPart";
	private final FormToolkit formToolkit = new FormToolkit(Display.getDefault());
	private Table table_2;
	private Table table;
	private Table table_1;
	private Table table_3;
	private Table table_4;
	private Table table_5;
	private Table table_6;
	private Table table_7;
	
	
	public  ChartComposite chartcomposite_1  = null;
	public  ChartComposite chartcomposite_2  = null;
    final TimeTableXYDataset dataset3 = new TimeTableXYDataset(); 
    
		
	@Override
	public void createPartControl(Composite parent) {
		
		Composite mainComposite = new Composite(parent, SWT.NONE);
		mainComposite.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		GridLayout gl_mainComposite = new GridLayout(2, false);
		gl_mainComposite.horizontalSpacing = 0;
		mainComposite.setLayout(gl_mainComposite);
		
		Composite composite_5 = new Composite(mainComposite, SWT.NONE);
		GridData gd_composite_5 = new GridData(SWT.LEFT, SWT.FILL, false, true, 1, 2);
		gd_composite_5.widthHint = 408;
		composite_5.setLayoutData(gd_composite_5);
		formToolkit.adapt(composite_5);
		formToolkit.paintBordersFor(composite_5);
		GridLayout gl_composite_5 = new GridLayout(1, false);
		gl_composite_5.horizontalSpacing = 0;
		gl_composite_5.verticalSpacing = 0;
		gl_composite_5.marginHeight = 0;
		gl_composite_5.marginWidth = 0;
		composite_5.setLayout(gl_composite_5);
		
		Composite composite_2 = new Composite(composite_5, SWT.NONE);
		composite_2.setSize(258, 95);
		composite_2.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		composite_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		composite_2.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		TabFolder tabFolder_1 = new TabFolder(composite_2, SWT.NONE);
		tabFolder_1.setFont(SWTResourceManager.getFont("Tahoma", 8, SWT.NORMAL));
		formToolkit.adapt(tabFolder_1);
		formToolkit.paintBordersFor(tabFolder_1);
		
		TabItem tbtmNewItem = new TabItem(tabFolder_1, SWT.NONE);
		tbtmNewItem.setImage(ResourceManager.getPluginImage("RpmUIRcp", "icons/systemmonitor/database_icon.png"));
		tbtmNewItem.setText("Database");
		
		Composite composite = new Composite(tabFolder_1, SWT.NONE);
		tbtmNewItem.setControl(composite);
		formToolkit.paintBordersFor(composite);
		composite.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		table_2 = new Table(composite, SWT.FULL_SELECTION);
		formToolkit.adapt(table_2);
		formToolkit.paintBordersFor(table_2);
		table_2.setHeaderVisible(true);
		table_2.setLinesVisible(true);
		
		TabItem tbtmNewItem_1 = new TabItem(tabFolder_1, SWT.NONE);
		tbtmNewItem_1.setImage(ResourceManager.getPluginImage("RpmUIRcp", "icons/systemmonitor/instance_icon.png"));
		tbtmNewItem_1.setText("Instance");
		
		Composite composite_3 = new Composite(tabFolder_1, SWT.NONE);
		tbtmNewItem_1.setControl(composite_3);
		formToolkit.paintBordersFor(composite_3);
		composite_3.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		table = new Table(composite_3, SWT.BORDER | SWT.FULL_SELECTION);
		formToolkit.adapt(table);
		formToolkit.paintBordersFor(table);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TabItem tbtmNewItem_2 = new TabItem(tabFolder_1, SWT.NONE);
		tbtmNewItem_2.setImage(ResourceManager.getPluginImage("RpmUIRcp", "icons/systemmonitor/parameter_icon.png"));
		tbtmNewItem_2.setText("Parameter");
		
		Composite composite_9 = new Composite(tabFolder_1, SWT.NONE);
		tbtmNewItem_2.setControl(composite_9);
		formToolkit.paintBordersFor(composite_9);
		composite_9.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		table_1 = new Table(composite_9, SWT.BORDER | SWT.FULL_SELECTION);
		formToolkit.adapt(table_1);
		formToolkit.paintBordersFor(table_1);
		table_1.setHeaderVisible(true);
		table_1.setLinesVisible(true);
		
		Composite composite_4 = formToolkit.createComposite(composite_5, SWT.NONE);
		composite_4.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		composite_4.setSize(271, 95);
		GridLayout gl_composite_4 = new GridLayout(1, false);
		gl_composite_4.verticalSpacing = 0;
		gl_composite_4.marginWidth = 0;
		gl_composite_4.marginHeight = 0;
		composite_4.setLayout(gl_composite_4);
		formToolkit.paintBordersFor(composite_4);
		
		TabFolder tabFolder_3 = new TabFolder(composite_4, SWT.NONE);
		tabFolder_3.setFont(SWTResourceManager.getFont("Tahoma", 8, SWT.NORMAL));
		tabFolder_3.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		formToolkit.adapt(tabFolder_3);
		formToolkit.paintBordersFor(tabFolder_3);
		
		TabItem tbtmNewItem_3 = new TabItem(tabFolder_3, SWT.NONE);
		tbtmNewItem_3.setImage(ResourceManager.getPluginImage("RpmUIRcp", "icons/systemmonitor/cpu_icon.png"));
		tbtmNewItem_3.setText("CPU");
		
		Composite composite_10 = new Composite(tabFolder_3, SWT.NONE);
		tbtmNewItem_3.setControl(composite_10);
		formToolkit.paintBordersFor(composite_10);
		composite_10.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		table_3 = new Table(composite_10, SWT.FULL_SELECTION);
		formToolkit.adapt(table_3);
		formToolkit.paintBordersFor(table_3);
		table_3.setHeaderVisible(true);
		table_3.setLinesVisible(true);
		
		TabItem tbtmNewItem_4 = new TabItem(tabFolder_3, SWT.NONE);
		tbtmNewItem_4.setImage(ResourceManager.getPluginImage("RpmUIRcp", "icons/systemmonitor/memory_icon.png"));
		tbtmNewItem_4.setText("Memory");
		
		Composite composite_11 = new Composite(tabFolder_3, SWT.NONE);
		tbtmNewItem_4.setControl(composite_11);
		formToolkit.paintBordersFor(composite_11);
		composite_11.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		table_4 = new Table(composite_11, SWT.BORDER | SWT.FULL_SELECTION);
		formToolkit.adapt(table_4);
		formToolkit.paintBordersFor(table_4);
		table_4.setHeaderVisible(true);
		table_4.setLinesVisible(true);
		
		TabItem tbtmNewItem_9 = new TabItem(tabFolder_3, SWT.NONE);
		tbtmNewItem_9.setImage(ResourceManager.getPluginImage("RpmUIRcp", "icons/systemmonitor/harddisk_icon.png"));
		tbtmNewItem_9.setText("Disk");
		
		Composite composite_12 = new Composite(tabFolder_3, SWT.NONE);
		tbtmNewItem_9.setControl(composite_12);
		formToolkit.paintBordersFor(composite_12);
		composite_12.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		table_5 = new Table(composite_12, SWT.BORDER | SWT.FULL_SELECTION);
		formToolkit.adapt(table_5);
		formToolkit.paintBordersFor(table_5);
		table_5.setHeaderVisible(true);
		table_5.setLinesVisible(true);
		
		TabItem tbtmNewItem_10 = new TabItem(tabFolder_3, SWT.NONE);
		tbtmNewItem_10.setImage(ResourceManager.getPluginImage("RpmUIRcp", "icons/systemmonitor/network_icon.png"));
		tbtmNewItem_10.setText("Network");
		
		Composite composite_13 = new Composite(tabFolder_3, SWT.NONE);
		tbtmNewItem_10.setControl(composite_13);
		formToolkit.paintBordersFor(composite_13);
		composite_13.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		table_6 = new Table(composite_13, SWT.BORDER | SWT.FULL_SELECTION);
		formToolkit.adapt(table_6);
		formToolkit.paintBordersFor(table_6);
		table_6.setHeaderVisible(true);
		table_6.setLinesVisible(true);
		
		Composite composite_1 = new Composite(mainComposite, SWT.NONE);
		GridLayout gl_composite_1 = new GridLayout(1, false);
		gl_composite_1.verticalSpacing = 0;
		gl_composite_1.marginHeight = 0;
		gl_composite_1.marginWidth = 0;
		composite_1.setLayout(gl_composite_1);
		GridData gd_composite_1 = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 2);
		gd_composite_1.heightHint = 293;
		composite_1.setLayoutData(gd_composite_1);
		composite_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		
		Composite composite_6 = new Composite(composite_1, SWT.NONE);
		GridLayout gl_composite_6 = new GridLayout(1, false);
		gl_composite_6.marginHeight = 0;
		gl_composite_6.marginWidth = 0;
		composite_6.setLayout(gl_composite_6);
		composite_6.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		formToolkit.adapt(composite_6);
		formToolkit.paintBordersFor(composite_6);
		
		TabFolder tabFolder_2 = new TabFolder(composite_6, SWT.NONE);
		tabFolder_2.setFont(SWTResourceManager.getFont("Tahoma", 8, SWT.NORMAL));
		tabFolder_2.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		formToolkit.adapt(tabFolder_2);
		formToolkit.paintBordersFor(tabFolder_2);
		
		TabItem tbtmNewItem_5 = new TabItem(tabFolder_2, SWT.NONE);
		tbtmNewItem_5.setImage(ResourceManager.getPluginImage("RpmUIRcp", "icons/systemmonitor/tutorials16.png"));
		tbtmNewItem_5.setText("Activity");
		
		Composite composite_15 = new Composite(tabFolder_2, SWT.NONE);
		tbtmNewItem_5.setControl(composite_15);
		formToolkit.paintBordersFor(composite_15);
		composite_15.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		final JFreeChart chartAcivity       = createAreaChart(dataset3 ,"");
		
		chartcomposite_1 = new ChartComposite(composite_15, SWT.NONE, chartAcivity,
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
		
		
		Composite composite_8 = new Composite(composite_1, SWT.NONE);
		GridLayout gl_composite_8 = new GridLayout(1, false);
		gl_composite_8.marginHeight = 0;
		gl_composite_8.marginWidth = 0;
		composite_8.setLayout(gl_composite_8);
		composite_8.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		formToolkit.adapt(composite_8);
		formToolkit.paintBordersFor(composite_8);
		
		TabFolder tabFolder = new TabFolder(composite_8, SWT.NONE);
		tabFolder.setFont(SWTResourceManager.getFont("Tahoma", 8, SWT.NORMAL));
		tabFolder.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		formToolkit.adapt(tabFolder);
		formToolkit.paintBordersFor(tabFolder);
		
		TabItem tbtmNewItem_6 = new TabItem(tabFolder, SWT.NONE);
		tbtmNewItem_6.setImage(ResourceManager.getPluginImage("RpmUIRcp", "icons/systemmonitor/icon_screen_item.png"));
		tbtmNewItem_6.setText("Redo Generated");
		
		Composite composite_16 = new Composite(tabFolder, SWT.NONE);
		tbtmNewItem_6.setControl(composite_16);
		formToolkit.paintBordersFor(composite_16);
		
		final JFreeChart chartAcivity1       = createAreaChart(dataset3 ,"");
		composite_16.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		chartcomposite_2 = new ChartComposite(composite_16, SWT.NONE, chartAcivity1,
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
		
		TabItem tbtmNewItem_8 = new TabItem(tabFolder, SWT.NONE);
		tbtmNewItem_8.setImage(ResourceManager.getPluginImage("RpmUIRcp", "icons/systemmonitor/icon_screen_item.png"));
		tbtmNewItem_8.setText("Logical Reads");
		
		TabItem tbtmNewItem_11 = new TabItem(tabFolder, SWT.NONE);
		tbtmNewItem_11.setImage(ResourceManager.getPluginImage("RpmUIRcp", "icons/systemmonitor/icon_screen_item.png"));
		tbtmNewItem_11.setText("Physical Reads");
		
		TabItem tbtmNewItem_12 = new TabItem(tabFolder, SWT.NONE);
		tbtmNewItem_12.setImage(ResourceManager.getPluginImage("RpmUIRcp", "icons/systemmonitor/icon_screen_item.png"));
		tbtmNewItem_12.setText("Physical Writes");
		
		TabItem tbtmNewItem_13 = new TabItem(tabFolder, SWT.NONE);
		tbtmNewItem_13.setImage(ResourceManager.getPluginImage("RpmUIRcp", "icons/systemmonitor/icon_screen_item.png"));
		tbtmNewItem_13.setText("Executions");
		
		TabItem tbtmNewItem_14 = new TabItem(tabFolder, SWT.NONE);
		tbtmNewItem_14.setImage(ResourceManager.getPluginImage("RpmUIRcp", "icons/systemmonitor/icon_screen_item.png"));
		tbtmNewItem_14.setText("User Transaction");
		
		TabItem tbtmNewItem_15 = new TabItem(tabFolder, SWT.NONE);
		tbtmNewItem_15.setImage(ResourceManager.getPluginImage("RpmUIRcp", "icons/systemmonitor/icon_screen_item.png"));
		tbtmNewItem_15.setText("Total Table Scans");
		
		TabItem tbtmNewItem_16 = new TabItem(tabFolder, SWT.NONE);
		tbtmNewItem_16.setImage(ResourceManager.getPluginImage("RpmUIRcp", "icons/systemmonitor/icon_screen_item.png"));
		tbtmNewItem_16.setText("Full Index Scans");
		
		Composite composite_7 = new Composite(composite_1, SWT.NONE);
		GridLayout gl_composite_7 = new GridLayout(1, false);
		gl_composite_7.marginHeight = 0;
		gl_composite_7.marginWidth = 0;
		composite_7.setLayout(gl_composite_7);
		composite_7.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		formToolkit.adapt(composite_7);
		formToolkit.paintBordersFor(composite_7);
		
		TabFolder tabFolder_4 = new TabFolder(composite_7, SWT.NONE);
		tabFolder_4.setFont(SWTResourceManager.getFont("Tahoma", 8, SWT.NORMAL));
		tabFolder_4.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		formToolkit.adapt(tabFolder_4);
		formToolkit.paintBordersFor(tabFolder_4);
		
		TabItem tbtmNewItem_7 = new TabItem(tabFolder_4, SWT.NONE);
		tbtmNewItem_7.setImage(ResourceManager.getPluginImage("RpmUIRcp", "icons/systemmonitor/sqlmonitor_icon.png"));
		tbtmNewItem_7.setText("SQL Monitoring");
		
		Composite composite_14 = new Composite(tabFolder_4, SWT.NONE);
		tbtmNewItem_7.setControl(composite_14);
		formToolkit.paintBordersFor(composite_14);
		composite_14.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		table_7 = new Table(composite_14, SWT.FULL_SELECTION);
		formToolkit.adapt(table_7);
		formToolkit.paintBordersFor(table_7);
		table_7.setHeaderVisible(true);
		table_7.setLinesVisible(true);
		
	
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
		
	}

	@Override
	public void chartProgress(ChartProgressEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void chartChanged(ChartChangeEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
