package views;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import models.Writer;

/**
 * Esta clase crea un panel con el contenido de los escritores
 * @author: Jesus Garzon
 * @version: 05/12/2022/A
 */
public class PanelWriters extends JPanel {

	private static final long serialVersionUID = 1L;
	//Campos de la clase
	JList<String> writers;
	
	
	/**
	 * Constructor del panel de escritores
	 * aqui se inicializaran los campos necesarios para la visualizacion de la lista de escritores disponibles
	 * este se agregaran al la clase actual ya que extiende de la clase JPanel
	 */
	public PanelWriters() {
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(250,250));
		setBorder(BorderFactory.createTitledBorder("Escritores..."));
		writers = new JList<>();
		writers.setBorder(BorderFactory.createTitledBorder("Trabajos Pendientes"));
		add(new JScrollPane(writers),BorderLayout.CENTER);
		
	}
	//Cierre del constructors
	

	/**
	 * metodo que nos permitira actualizar la lista de escritores disponibles 
	 * @param vector de objetos de tipo escritor 
	 */
	public void updateWriters(Object[] data) {
		DefaultListModel<String> model = new DefaultListModel<String>();
		if (data!=null) {
			for (Object string : data) {
				if (string!=null) {
					Writer s = ((Writer)string);
					model.addElement("Escritor "+s.getId()+"escribiendo > "+s.isWriting());
				}
			}
		}
		writers.setModel(model);
	}
	
}
