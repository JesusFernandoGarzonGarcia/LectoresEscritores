package views;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;


public class ScatterPlotDemo extends JPanel{

	private static final long serialVersionUID = 1L;
	JFreeChart chart;//declaramos un objeto de la clase JFreeChart para construir el grafico4
	XYSeriesCollection dataset;

	
	 /**
	  *@param rondas lista que contiene todas las rondas que se van a graficar 
*/
	public ScatterPlotDemo() {
		setBackground(Color.BLACK);
		dataset = new XYSeriesCollection();
		XYSeries  seriesPA = new XYSeries("0");//"Producto A" es la etiqueta o nombre
		dataset.addSeries(seriesPA);
		ChartPanel panel = new ChartPanel(chart, false);//ChartPanel es una clase del paquete JFreeChart
		add(panel,BorderLayout.CENTER); //anadimos el panel al JFrame
	}

	 /**
	  * este metodo carga las series por cada jugador 
	  *@param jugadoresEquipoUno lista que contiene todas los jugadores que participaron en los juegos 
*/
	public void crearGraficoSymulateHour(String[] hours) { 
		dataset = new XYSeriesCollection();
		for (@SuppressWarnings("unused") String hour : hours) {
			XYSeries  seriesPA = new XYSeries(hour);//"Producto A" es la etiqueta o nombre
			dataset.addSeries(seriesPA);//anadir la serie del producto A
		}
		chart = ChartFactory.createScatterPlot(
				"Gráfica Horas vs Estado", // Titulo
				"Horas", // Etiqueta Coordenada X
				"Estado", // Etiqueta Coordenada Y
				dataset, // Datos
				PlotOrientation.VERTICAL,
				true, // Muestra la leyenda de los productos en el eje de la X
				true,// mostrar la leyenda en cada punto
				false
				);
		repaint();
	}
	
	 /**
	  * este metodo carga los datos para cada uno de los jugadores  (series)
	  *@param nombre representa el nombre de cada serie (jugador)
	  *@param puntajeJuegos lista que contiene cada uno de los puntajes obtenidos por el jugador (serie) 
*/
	public void addInforHoras(String id,int[] puntajeJuegos) {
		for (int i = 0; i < puntajeJuegos.length; i++) {
			dataset.getSeries(id).add(i,puntajeJuegos[i]);
		}
	}
	 /**
	  * este metodo re valida , re pinta y muestra la grafica con todos los datos ingresados
*/
	public void iniciarGrafic() {
		revalidate();
		repaint();
		setVisible(true);
	}

}
