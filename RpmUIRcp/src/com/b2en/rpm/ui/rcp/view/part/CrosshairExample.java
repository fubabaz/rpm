package com.b2en.rpm.ui.rcp.view.part;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartMouseEvent;
import org.jfree.chart.ChartMouseListener;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.annotations.XYTextAnnotation;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.StandardXYItemRenderer;
import org.jfree.data.xy.IntervalXYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.TextAnchor;

public class CrosshairExample extends JFrame {

   private ChartPanel mChartPanel;

   /**
    * Create the GUI and show it.
    */
   public void createAndShowGUI() {
      // Create and set up the window.
      JFrame frame = new JFrame("Crosshair example");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      ChartPanel chartPanel = createChart();
      frame.add(chartPanel);
      // Display the window.
      frame.pack();
      frame.setVisible(true);
   }

   private ChartPanel createChart() {

      mChartPanel = new ChartPanel(ChartFactory.createXYLineChart(null,
            "xAxisLabel", "yAxisLabel", createDataset1(),
            PlotOrientation.VERTICAL, false, true, true));

      mChartPanel.getChart().setTextAntiAlias(true);
      mChartPanel.getChart().setBackgroundPaint(Color.WHITE);

      XYPlot xyplot = (XYPlot) mChartPanel.getChart().getPlot();

      // Add one axis to the right
      final NumberAxis numberaxis2 = new NumberAxis("yAxisLabel2");

      xyplot.setRangeAxis(1, numberaxis2);
      xyplot.setDataset(1, createDataset2());
      xyplot.mapDatasetToRangeAxis(1, 1);

      final StandardXYItemRenderer renderer2 = new StandardXYItemRenderer();
      xyplot.setRenderer(1, renderer2);

      xyplot.setDomainPannable(true);
      xyplot.setRangePannable(true);

      xyplot.setDomainCrosshairVisible(true);
      xyplot.setRangeCrosshairVisible(true);

      xyplot.setDomainCrosshairLockedOnData(true);
      xyplot.setRangeCrosshairLockedOnData(true);

      mChartPanel.addChartMouseListener(new ChartMouseListener() {

         public void chartMouseMoved(ChartMouseEvent chartmouseevent) {

         }

         public void chartMouseClicked(ChartMouseEvent chartmouseevent) {

            SwingUtilities.invokeLater(new Runnable() {
               public void run() {
                  XYPlot xyplot = (XYPlot) mChartPanel.getChart().getPlot();
                  xyplot.clearAnnotations();
                  double x, y;
                  x = xyplot.getDomainCrosshairValue();
                  y = xyplot.getRangeCrosshairValue();
                  XYTextAnnotation annotation = new XYTextAnnotation("("+ x + ", " + y + ")", x, y);
                  annotation.setTextAnchor(TextAnchor.BOTTOM_CENTER);
                  xyplot.addAnnotation(annotation);
               }
            });
         }
      });

      return mChartPanel;

   }

   /**
    * Creates a sample dataset.
    * 
    * @return A sample dataset.
    */
   private static IntervalXYDataset createDataset1() {
      XYSeries series1 = new XYSeries("AA");
      int j = 0;
      for (int i = 0; i < 366; i++) {
         series1.add(i, j);
         j = (i % 2 == 0) ? j += 1 : j + 2;
      }
      XYSeriesCollection dataset = new XYSeriesCollection();
      dataset.addSeries(series1);
      return dataset;
   }

   /**
    * Creates a sample dataset.
    * 
    * @return A sample dataset.
    */
   private static IntervalXYDataset createDataset2() {
      XYSeries series2 = new XYSeries("BB");
      int j = 1000;
      for (int i = 0; i < 366; i++) {
         series2.add(i, j);
         j = (i % 2 == 0) ? j += -2 : j + 3;
      }

      XYSeriesCollection dataset = new XYSeriesCollection();
      dataset.addSeries(series2);
      return dataset;
   }

   public static void main(String[] args) {
      javax.swing.SwingUtilities.invokeLater(new Runnable() {
         public void run() {
            new CrosshairExample().createAndShowGUI();
         }
      });
   }

}