package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.DefaultXYDataset;

public class ViewsPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private PanelEscritores escritores ;
	private PanelLectores lectores ;
	private JPanel panelCentral;
	private PanelActual actual;
	private ScatterPlotDemo grafica;
	DefaultXYDataset dataset;


	public ViewsPrincipal(ActionListener listener) {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(new Dimension(700, 600));
		setLayout(new BorderLayout());
		setLocationRelativeTo(rootPane);
		escritores = new PanelEscritores();
		
		JTabbedPane pestañas=new JTabbedPane();
		
		JPanel data = new JPanel();
		data.setLayout(new BorderLayout());
		
		data.add(escritores,BorderLayout.LINE_START);

		panelCentral = new JPanel();
		panelCentral.setLayout(new BorderLayout());
		actual = new PanelActual(listener);
		panelCentral.add(actual);

		data.add(panelCentral,BorderLayout.CENTER);
		lectores = new PanelLectores();
		data.add(lectores,BorderLayout.LINE_END);
		pestañas.add(data,"Datos generales");
		grafica = new ScatterPlotDemo();
		
		grafica.crearGraficoSymulateHour(new String[] {"0","1"} );
		crearGrafico(new String[] {"0","1"} );
		crearGraficaPromedio();
		pestañas.add(grafica,"Graficas");
		add(pestañas);
		setVisible(true);
	}	
	
	public void graficaHoras(double[][] ds) {
		dataset  = new DefaultXYDataset();
		dataset.addSeries("",ds);
		JFreeChart chart = ChartFactory.createXYLineChart("", "","", dataset);
		XYLineAndShapeRenderer render = new XYLineAndShapeRenderer();
		chart.getXYPlot().setRenderer(render);

		ChartPanel panel = new ChartPanel(chart, false);//ChartPanel es una clase del paquete JFreeChart
		grafica.removeAll();
		grafica.add(panel,BorderLayout.CENTER);
	}
	
	public void crearGraficaPromedio() {
		grafica.addInforHoras("0", new int[] {1,3,4,6});
		grafica.repaint();
	}
	
//	public void crearGraficaPromedio(String key,int[] data) {
//		grafica.addInforHoras(key, data);
//	}
	
	public void crearGrafico(String[] data) {
		if (data!=null) {
			grafica.crearGraficoSymulateHour(data);
		}
	}
	
	public void actualizarEscritorActual(String Actutual) {
		actual.actualizarEscritorActual(Actutual);
	}

	public void actualizarTiempoSistema(String Actutual) {
		actual.actualizarTiempoSystema(Actutual);
	}

	public void actualizarEscritores(Object[] datos) {
		escritores.actualizarEscritores(datos);
	} 

	public void actualizarLectoresFinalizados(Object[] datos) {
		lectores.actualizarLectorFinalizado(datos);
	} 

	public void actualizarTexto(String texto) {
		actual.actualizarTexto(texto);
	}

	public void actualizarTareaActual(String data) {
		actual.actualizarTareaActual(data);
	}

	public void actualizarTiempoEscritor(String datos) {
		actual.actualizarTiempoEscritor(datos);
	} 
	public void actualizarTiempoLector(String datos) {
		actual.actualizarTiempoLector(datos);
	} 

	public void actualizarLectores(Object[] datos) {
		lectores.actualizarLector(datos);
	} 

	public String cantidadIteraciones() {
		return JOptionPane.showInputDialog("Cantidad de Iteaciones a realizar");
	}

	public String tiempoEspera() {
		return JOptionPane.showInputDialog("Tiempo de Espera");
	}
}
