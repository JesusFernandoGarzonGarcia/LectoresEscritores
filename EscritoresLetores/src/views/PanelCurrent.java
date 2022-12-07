package views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * Esta clase crea un panel con el contenido de los escritores
 * @author: Jesus Garzon
 * @version: 05/12/2022/A
 */
public class PanelCurrent extends JPanel {

	private static final long serialVersionUID = 1L;
	//Campos de la clase
	JLabel writercurrent;
	JLabel systemtime;
	JLabel currenttask;
	JLabel timeForWriter;
	JLabel timeForReader;
	JTextArea text;
	JButton startSymullation;

	/**
	 * Constructor del panel actual sera toda la informacion del sistema
	 * aqui se inicializaran los campos necesarios para la visualizacion de la informacion mas relevante del sistema
	 * este se agregaran al la clase actual ya que extiende de la clase JPanel
	 */
	public PanelCurrent(ActionListener listener) {
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(300,300));
		writercurrent = new JLabel();
		writercurrent.setBorder(BorderFactory.createTitledBorder("Escritor Actual"));
		systemtime = new JLabel();
		systemtime.setBorder(BorderFactory.createTitledBorder("Tiempo sistema"));
		currenttask = new JLabel();
		currenttask.setBorder(BorderFactory.createTitledBorder("Tarea Actual"));
		timeForWriter = new JLabel();
		timeForWriter.setBorder(BorderFactory.createTitledBorder("tiempo nuevo escritor"));
		timeForReader = new JLabel();
		timeForReader.setBorder(BorderFactory.createTitledBorder("tiempo nuevo lector"));
		JPanel panelConten = new JPanel();
		panelConten.setLayout(new GridLayout(5,1));
		panelConten.add(systemtime);
		panelConten.add(writercurrent);
		panelConten.add(currenttask);
		panelConten.add(timeForWriter);
		panelConten.add(timeForReader);
		text = new JTextArea();
		text.setBorder(BorderFactory.createTitledBorder("Datos actuales en el recurso"));
		text.setLineWrap(true);
		startSymullation = new JButton("iniciar simulacion");
		startSymullation.addActionListener(listener);
		startSymullation.setActionCommand("iniciar");
		add(panelConten,BorderLayout.PAGE_START);
		add(text,BorderLayout.CENTER);
		add(startSymullation,BorderLayout.PAGE_END);
	}
	//Cierre del constructors

	/**
	 * metodo que nos permitira actualizar el escritor actual 
	 * @param current datos del escritor actual
	 */
	public void updateWriterCurrent(String current) {
		writercurrent.setText(current);
		repaint();
	}
	

	/**
	 * metodo que nos permitira actualizar el tiempo del sistema
	 * @param current tiempo actual del sistema
	 */
	public void updateSystemTime(String current) {
		systemtime.setText(current);
		repaint();
	}
	
	/**
	 * metodo que nos permitira actualizar el texto actual del recurso
	 * @param textIn texto actual del recurso
	 */
	public void updateText(String textIn) {
		text.setText(textIn);
		repaint();
	}
	
	/**
	 * metodo que nos permitira actualizar la tarea actual que se esta realizando
	 * @param current tarea actual realizando
	 */
	public void updateCurrentTask(String current) {
		currenttask.setText(current);
		repaint();
	}
	
	/**
	 * metodo que nos permitira actualizar el tiempo para el sigueinte escritor
	 * @param current tiempo para el siguiente escritor
	 */
	public void updateTimeWriter(String current) {
		timeForWriter.setText(current);
		repaint();
	}
	
	/**
	 * metodo que nos permitira actualizar el tiempo para el sigueinte lector
	 * @param current tiempo para el siguiente lector
	 */
	public void updateTimeReader(String current) {
		timeForReader.setText(current);
		repaint();
	}

}
