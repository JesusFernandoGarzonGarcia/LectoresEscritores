package views;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class PanelActual extends JPanel {

	private static final long serialVersionUID = 1L;
	JLabel escritorActual;
	JLabel tiempoSystema;
	JLabel tareaActual;
	JLabel tiempoParaEscritor;
	JLabel tiempoParaLector;
	JTextArea texto;
	JButton iniciarSymullacion;

	public PanelActual(ActionListener listener) {
		setLayout(new BorderLayout());
		escritorActual = new JLabel();
		escritorActual.setBorder(BorderFactory.createTitledBorder("Escritor Actual"));
		tiempoSystema = new JLabel();
		tiempoSystema.setBorder(BorderFactory.createTitledBorder("Tiempo sistema"));
		tareaActual = new JLabel();
		tareaActual.setBorder(BorderFactory.createTitledBorder("Tarea Actual"));
		tiempoParaEscritor = new JLabel();
		tiempoParaEscritor.setBorder(BorderFactory.createTitledBorder("tiempo nuevo escritor"));
		tiempoParaLector = new JLabel();
		tiempoParaLector.setBorder(BorderFactory.createTitledBorder("tiempo nuevo lector"));
		JPanel panelConten = new JPanel();
		panelConten.setLayout(new GridLayout(5,1));
		panelConten.add(tiempoSystema);
		panelConten.add(escritorActual);
		panelConten.add(tareaActual);
		panelConten.add(tiempoParaEscritor);
		panelConten.add(tiempoParaLector);
		texto = new JTextArea();
		texto.setLineWrap(true);
		iniciarSymullacion = new JButton("iniciar simulacion");
		iniciarSymullacion.addActionListener(listener);
		iniciarSymullacion.setActionCommand("iniciar");
		add(panelConten,BorderLayout.PAGE_START);
		add(texto,BorderLayout.CENTER);
		add(iniciarSymullacion,BorderLayout.PAGE_END);
	}

	public void actualizarEscritorActual(String actual) {
		escritorActual.setText(actual);
		repaint();
	}
	
	public void actualizarTiempoSystema(String actual) {
		tiempoSystema.setText(actual);
		repaint();
	}
	
	public void actualizarTexto(String textoIn) {
		texto.setText(textoIn);
		repaint();
	}
	public void actualizarTareaActual(String actual) {
		tareaActual.setText(actual);
		repaint();
	}
	public void actualizarTiempoEscritor(String actual) {
		tiempoParaEscritor.setText(actual);
		repaint();
	}
	public void actualizarTiempoLector(String actual) {
		tiempoParaLector.setText(actual);
		repaint();
	}

}
