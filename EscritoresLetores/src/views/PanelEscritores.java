package views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import models.Writer;

public class PanelEscritores extends JPanel {

	private static final long serialVersionUID = 1L;

	JList<String> escritores;
	public PanelEscritores() {
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(250,250));
		setBorder(BorderFactory.createTitledBorder("Escritores..."));
		escritores = new JList<>();
		escritores.setBorder(BorderFactory.createTitledBorder("Trabajos Pendientes"));
		add(new JScrollPane(escritores),BorderLayout.CENTER);
		
	}
	


	public void actualizarEscritores(Object[] data) {
		DefaultListModel<String> modelo = new DefaultListModel<String>();
		if (data!=null) {
			for (Object string : data) {
				if (string!=null) {
					Writer s = ((Writer)string);
					modelo.addElement("Escritor "+s.getId()+"escribiendo > "+s.isWriting());
				}
			}
		}
		escritores.setModel(modelo);
	}
	
}
