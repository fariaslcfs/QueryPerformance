package br.gov.sp.fatec;

import java.awt.Color;

/**
 * @author Luiz Carlos Farias da Silva
 * email - fariaslcfs@gmail.com luiz.silva116@fatec.sp.gov.br
 */

import java.awt.Paint;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;

@SuppressWarnings("serial")
public class GraficoBarra extends ApplicationFrame {

	public GraficoBarra(String title) {
		super(title);
	}

	class CustomRenderer extends BarRenderer { // tirado de http://www.java2s.com/Code/Java/Chart/JFreeChartBarChartDemo3differentcolorswithinaseries.htm

		/** The colors. */
		private Paint[] colors; 

		/**
		 * Creates a new renderer.
		 *
		 * @param colors
		 *            the colors.
		 */
		public CustomRenderer(final Paint[] colors) { // tirado de http://www.java2s.com/Code/Java/Chart/JFreeChartBarChartDemo3differentcolorswithinaseries.htm
			this.colors = colors;
		}

		/**
		 * Returns the paint for an item. Overrides the default behaviour
		 * inherited from AbstractSeriesRenderer.
		 *
		 * @param row
		 *            the series.
		 * @param column
		 *            the category.
		 *
		 * @return The item color.
		 */
		public Paint getItemPaint(final int row, final int column) { // tirado de http://www.java2s.com/Code/Java/Chart/JFreeChartBarChartDemo3differentcolorswithinaseries.htm
			return this.colors[column % this.colors.length];
		}
	}

	public void geraGrafico(String title, int iteracao, Color c, double timehard, double timefirm, double timesoft) {

		String type = "TIPO DE PARSE";

		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		dataset.setValue(timehard, "TEMPO(s)", "HARDCODED");
		dataset.setValue(timefirm, "TEMPO(s)", "FIRMCODED");
		dataset.setValue(timesoft, "TEMPO(s)", "SOFTCODED");

		JFreeChart chart = ChartFactory.createBarChart(title, type, "TEMPO(s)   para " + iteracao + " iterações", dataset, PlotOrientation.VERTICAL, false, true, true);

		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new java.awt.Dimension(407, 340));
		CategoryPlot plot = chart.getCategoryPlot();
		CategoryItemRenderer renderer = plot.getRenderer();
		renderer.setSeriesPaint(0, c);
		setContentPane(chartPanel);

	}

/*	public void geraGraficoCompleto(String title, int iteracao, double timehardS, double timehardM, double timehardO, 
																double timefirmS, double timefirmM, double timefirmO, 
																double timesoftS, double timesoftM, double timesoftO) {

		String type = "TIPO DE PARSE";

		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		
		dataset.setValue(timehardS, "TEMPO(s)", "H_SQLSERVER");
		dataset.setValue(timehardM, "TEMPO(s)", "H_MYSQL");
		dataset.setValue(timefirmS, "TEMPO(s)", "F_SQLSERVER");
		dataset.setValue(timefirmM, "TEMPO(s)", "F_MYSQL");
		dataset.setValue(timesoftS, "TEMPO(s)", "S_SQLSERVER");
		dataset.setValue(timesoftM, "TEMPO(s)", "S_MYSQL");
		dataset.setValue(timehardO, "TEMPO(s)", "H_ORACLE");
		dataset.setValue(timefirmO, "TEMPO(s)", "F_ORACLE");	
		dataset.setValue(timesoftO, "TEMPO(s)", "S_ORACLE");

		JFreeChart chart = ChartFactory.createBarChart(title, type, "TEMPO(s)   para " + iteracao + " iterações", dataset, PlotOrientation.VERTICAL, false, true, true);

		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new java.awt.Dimension(1265, 340));
		
		CategoryPlot plot = chart.getCategoryPlot();
		// tirado de: http://www.java2s.com/Code/Java/Chart/JFreeChartBarChartDemo3differentcolorswithinaseries.htm
		CategoryItemRenderer renderer = new CustomRenderer(new Paint[] {
				Color.red, Color.green, Color.blue, 
				Color.red, Color.green, Color.blue,
				Color.red, Color.green, Color.blue
				}
		);
		
		plot.setRenderer(renderer); // ajusta a cor da barra individual (verde(mysql) - azul(oracle))
		setContentPane(chartPanel); 
	}*/
}
