package views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import models.Reader;

public class PanelReaders extends JPanel {

	/**
	 * Esta clase crea un panel con el contenido de los lectores
	 * @author: Jesus Garzon
	 * @version: 05/12/2022/A
	 */
	private static final long serialVersionUID = 1L;
	//Campos de la clase
	JList<String> readers;
	JList<String> ReadersFinished;
	
	/**
	 * Constructor del panel de lectores
	 * aqui se inicializaran los campos necesarios para la visualizacion de las listas de lectores disponibles
	 * este se agregaran al la clase actual ya que extiende de la clase JPanel
	 */
	public PanelReaders() {
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(250,250));
		setBorder(BorderFactory.createTitledBorder("Lectores..."));
		JPanel conten = new JPanel();
		conten.setLayout(new GridLayout(2,1));
		readers = new JList<>();
		readers.setBorder(BorderFactory.createTitledBorder("Lecturas Pendientes"));
		ReadersFinished = new JList<>();
		ReadersFinished.setBorder(BorderFactory.createTitledBorder("Lecturas Actuales..."));
		conten.add(new JScrollPane(readers));
		conten.add(	new JScrollPane(ReadersFinished));
		add(conten,BorderLayout.CENTER);
	}

	/**
	 * metodo que nos permitira actualizar la lista de lectores disponibles 
	 * @param vector de objetos de tipo lector 
	 */
	public void refreshReader(Object[] data) {
		DefaultListModel<String> model = new DefaultListModel<String>();
		if (data!=null) {
			for (Object string : data) {
				if (string!=null) {
					Reader l = ((Reader)string);
					model.addElement("Lector "+l.getId()+"Leyendo >"+l.isReading() );
				}
			}
		}
		readers.setModel(model);
	}
	
	/**
	 * metodo que nos permitira actualizar la lista de lectores realizando tarea 
	 * @param vector de objetos de tipo lector 
	 */
	public void updateReaderDone(Object[] data) {
		DefaultListModel<String> model = new DefaultListModel<String>();
		if (data!=null) {
			for (Object string : data) {
				if (string!=null) {
					Reader l = ((Reader)string);
					model.addElement("Lector "+l.getId()+"Leyendo >"+l.isReading()+" "+l.getWorkTime() );
				}
			}
		}
		ReadersFinished.setModel(model);
	}
	
}
