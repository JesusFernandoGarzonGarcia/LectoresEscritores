package views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import org.jfree.data.xy.DefaultXYDataset;

/**
 * Esta clase define la vista principal del simulador
 * @author: Jesus Garzon
 * @version: 05/12/2022/A
 */
public class ViewsPrincipal extends JFrame {

	//Campos de la clase
	private static final long serialVersionUID = 1L;
	private PanelWriters writers ;
	private PanelReaders readers ;
	private JPanel panelCenter;
	private PanelCurrent current;
	private ScatterPlotDemo graph;
	DefaultXYDataset dataset;

	/**
	 * Constructor de la vista del simulador
	 * @param listener metodo que nos permitira capturar las interaciones de los usuarios
	 * en este metodo se inicializaran los paneles botones y pestañas necesarias para la visualizacion del contenido del sistema
	 * 
	 */
	public ViewsPrincipal(ActionListener listener) {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(new Dimension(800, 600));
		setLayout(new BorderLayout());
		setLocationRelativeTo(rootPane);
		writers = new PanelWriters();
		
		JTabbedPane tabs=new JTabbedPane();
		
		JPanel data = new JPanel();
		data.setLayout(new BorderLayout());
		
		data.add(writers,BorderLayout.LINE_START);

		panelCenter = new JPanel();
		panelCenter.setLayout(new BorderLayout());
		current = new PanelCurrent(listener);
		panelCenter.add(current);

		data.add(panelCenter,BorderLayout.CENTER);
		readers = new PanelReaders();
		data.add(readers,BorderLayout.LINE_END);
		tabs.add(data,"Datos generales");
		
		add(tabs);
		setVisible(true);
	}	
	
//	public void graphHours(double[][] ds) {
//		dataset  = new DefaultXYDataset();
//		dataset.addSeries("",ds);
//		JFreeChart chart = ChartFactory.createXYLineChart("", "","", dataset);
//		XYLineAndShapeRenderer render = new XYLineAndShapeRenderer();
//		chart.getXYPlot().setRenderer(render);
//
//		ChartPanel panel = new ChartPanel(chart, false);//ChartPanel es una clase del paquete JFreeChart
//		graph.removeAll();
//		graph.add(panel,BorderLayout.CENTER);
//	}
	
//	public void createGraphAverage() {
//		graph.addInforHoras("0", new int[] {1,3,4,6});
//		graph.repaint();
//	}
	
//	public void crearGraficaPromedio(String key,int[] data) {
//		grafica.addInforHoras(key, data);
//	}
	
//	public void creategraphic(String[] data) {
//		if (data!=null) {
//			graph.crearGraficoSymulateHour(data);
//		}
//	}
	
	/**
	 * actualiza el escritor actual
	 * @param actual escritor actual
	 */
	public void updateWriterCurrent(String actual) {
		current.updateWriterCurrent(actual);
	}

	/**
	 * actualiza el tiempo del sistema
	 * @param actual tiempo actual del sistema
	 */
	public void updateSystemTime(String actual) {
		current.updateSystemTime(actual);
	}

	/**
	 * actualiza la lista de escritores actuales
	 * @param data vector de objetos que contiene los datos de los escritores
	 */
	public void updateWriters(Object[] data) {
		writers.updateWriters(data);
	} 

	/**
	 * actualiza la lista de lectores actuales que realizan un trabajo
	 * @param data vector de objetos que contiene los datos de los lectores
	 */
	public void updateReaderDone(Object[] datos) {
		readers.updateReaderDone(datos);
	} 

	/**
	 * actualiza el texto actual del recurso
	 * @param text texto actual del recurso
	 */
	public void updateText(String text) {
		current.updateText(text);
	}

	/**
	 * actualiza la trea actual que se esta realizando
	 * @param data tarea actual que se esta ejecutando
	 */
	public void updateCurrentTask(String data) {
		current.updateCurrentTask(data);
	}

	/**
	 * actualiza el tiempo para un nuevo escritor
	 * @param data tiempo faltante para un nuevo escritor
	 */
	public void updateTimeWriter(String data) {
		current.updateTimeWriter(data);
	} 
	
	/**
	 * actualiza el tiempo para un nuevo lector
	 * @param data tiempo faltante para un nuevo lector
	 */
	public void updateTimeReader(String datos) {
		current.updateTimeReader(datos);
	} 

	/**
	 * actualiza la lista de lectores esperando servicio
	 * @param vector de objetos de lectores que se encuentran esperando servicio
	 */
	public void refreshReader(Object[] data) {
		readers.refreshReader(data);
	} 

	/**
	 * @return solicita el tiempo o las iteraciones que realizara el sistema
	 */
	public String quantityIterations() {
		return JOptionPane.showInputDialog("Cantidad de Iteaciones a realizar");
	}

	/**
	 * @return timepo de espera entre cada iteracion
	 */
	public String Standbytime() {
		return JOptionPane.showInputDialog("Tiempo de Espera");
	}
}
