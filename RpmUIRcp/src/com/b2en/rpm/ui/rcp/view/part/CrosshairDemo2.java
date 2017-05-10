package com.b2en.rpm.ui.rcp.view.part;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.event.ChartChangeEvent;
import org.jfree.chart.event.ChartChangeListener;
import org.jfree.chart.event.ChartProgressEvent;
import org.jfree.chart.event.ChartProgressListener;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.Minute;
import org.jfree.data.time.RegularTimePeriod;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RectangleInsets;
import org.jfree.ui.RefineryUtilities;
   
/**  
 * An example of....  
 */   
public class CrosshairDemo2 extends ApplicationFrame {   
   
    private static class DemoPanel extends JPanel implements ChartChangeListener, ChartProgressListener {   
   
        private static final int SERIES_COUNT = 1;   
           
        private TimeSeriesCollection[] datasets;   
           
        private TimeSeries[] series;   
           
        private ChartPanel chartPanel;   
           
        /**  
         * Creates a new demo panel.  
         */   
        public DemoPanel() {   
            super(new BorderLayout());   
            this.datasets = new TimeSeriesCollection[SERIES_COUNT];   
            this.series = new TimeSeries[SERIES_COUNT];   
               
            JPanel content = new JPanel(new BorderLayout());   
               
            JFreeChart chart = createChart();   
            this.chartPanel = new ChartPanel(chart);   
            this.chartPanel.setPreferredSize(new java.awt.Dimension(600, 270));   
            this.chartPanel.setDomainZoomable(true);   
            this.chartPanel.setRangeZoomable(true);   
            Border border = BorderFactory.createCompoundBorder(   
                BorderFactory.createEmptyBorder(4, 4, 4, 4),   
                BorderFactory.createEtchedBorder()   
            );   
            this.chartPanel.setBorder(border);   
            content.add(this.chartPanel);   
               
            JPanel dashboard = new JPanel(new BorderLayout());   
            dashboard.setPreferredSize(new Dimension(400, 120));   
            dashboard.setBorder(BorderFactory.createEmptyBorder(0, 4, 4, 4));   
               
         
            content.add(dashboard, BorderLayout.SOUTH);   
            add(content);   
   
        }   
   
   
        private XYDataset createDataset(int index, String name,   
                                        double base, RegularTimePeriod start,    
                                        int count) {   
   
            this.series[index] = new TimeSeries(name, start.getClass());   
            RegularTimePeriod period = start;   
            double value = base;   
            for (int i = 0; i < count; i++) {   
                this.series[index].add(period, value);       
                period = period.next();   
                value = value * (1 + (Math.random() - 0.495) / 10.0);   
            }   
   
            this.datasets[index] = new TimeSeriesCollection();   
            this.datasets[index].addSeries(this.series[index]);   
   
            return this.datasets[index];   
   
        }   
   
        /**  
         * Receives notification of a {@link ChartChangeEvent}.  
         *   
         * @param event  the event.  
         */   
        public void chartChanged(ChartChangeEvent event) {   
 
        }   
   
        /**  
         * Creates the demo chart.  
         *   
         * @return The chart.  
         */   
        private JFreeChart createChart() {   
               
            JFreeChart chart = ChartFactory.createTimeSeriesChart(   
                "Crosshair Demo 2",    
                "Time of Day",    
                "Value",   
                null,    
                true,    
                true,    
                false   
            );   
   
            XYDataset[] datasets = new XYDataset[SERIES_COUNT];   
            for (int i = 0; i < SERIES_COUNT; i++) {   
                datasets[i] = createDataset(   
                    i, "Series " + i, 100.0 + i * 200.0, new Minute(), 200   
                );   
                if (i == 0) {   
                    chart.getXYPlot().setDataset(datasets[i]);   
                }   
                else {   
                    XYPlot plot = chart.getXYPlot();   
                    plot.setDataset(i, datasets[i]);   
                    plot.setRangeAxis(i, new NumberAxis("Axis " + (i + 1)));   
                    plot.mapDatasetToRangeAxis(i, i);   
                    plot.setRenderer(i, new XYLineAndShapeRenderer(true, false));   
                }   
            }   
            chart.addChangeListener(this);   
            chart.addProgressListener(this);   
            chart.setBackgroundPaint(Color.white);   
            XYPlot plot = chart.getXYPlot();   
            plot.setOrientation(PlotOrientation.VERTICAL);   
            plot.setBackgroundPaint(Color.lightGray);   
            plot.setDomainGridlinePaint(Color.white);   
            plot.setRangeGridlinePaint(Color.white);   
            plot.setAxisOffset(new RectangleInsets(5.0, 5.0, 5.0, 5.0));   
               
            plot.setDomainCrosshairVisible(true);   
            plot.setDomainCrosshairLockedOnData(false);   
            plot.setRangeCrosshairVisible(false);   
   
            XYItemRenderer renderer = plot.getRenderer();   
            renderer.setPaint(Color.black);   
                              
            return chart;   
        }   
           

        /**  
         * Handles a chart progress event.  
         *   
         * @param event  
         *            the event.  
         */   
        public void chartProgress(ChartProgressEvent event) {   
            if (event.getType() != ChartProgressEvent.DRAWING_FINISHED) {   
                return;   
            }   
            if (this.chartPanel != null) {   
                JFreeChart c = this.chartPanel.getChart();   
                if (c != null) {   
                    XYPlot plot = c.getXYPlot();   
                    XYDataset dataset = plot.getDataset();   
                
                    double xx = plot.getDomainCrosshairValue();   
   
               
                    long millis = (long) xx;   
                    
                    SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.KOREA);
	                  String dateText = df2.format(xx);
	                  System.out.println(">>>>>>>>>>>"+dateText);
	                  
                   
                  
                }   
            }   
        }   
   
    }   
   
    /**  
     * A demonstration application showing how to...  
     *  
     * @param title  the frame title.  
     */   
    public CrosshairDemo2(String title) {   
        super(title);   
        setContentPane(new DemoPanel());   
    }   
   
    /**  
     * Creates a panel for the demo (used by SuperDemo.java).  
     *   
     * @return A panel.  
     */   
    public static JPanel createDemoPanel() {   
        return new DemoPanel();   
    }   
       
    /**  
     * Starting point for the demonstration application.  
     *  
     * @param args  ignored.  
     */   
    public static void main(String[] args) {   
        CrosshairDemo2 demo = new CrosshairDemo2("Crosshair Demo 2");   
        demo.pack();   
        RefineryUtilities.centerFrameOnScreen(demo);   
        demo.setVisible(true);   
    }   
   
    
           
      
}   

 