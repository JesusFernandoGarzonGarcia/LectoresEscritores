package views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import models.Lector;

public class PanelLectores extends JPanel {


	private static final long serialVersionUID = 1L;

	JList<String> lectores;
	JList<String> lectoresFinalizados;
	
	public PanelLectores() {
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(250,250));
		setBorder(BorderFactory.createTitledBorder("Lectores..."));
		JPanel conten = new JPanel();
		conten.setLayout(new GridLayout(2,1));
		lectores = new JList<>();
		lectores.setBorder(BorderFactory.createTitledBorder("Lecturas Pendientes"));
		lectoresFinalizados = new JList<>();
		lectoresFinalizados.setBorder(BorderFactory.createTitledBorder("Lecturas Actuales..."));
		conten.add(new JScrollPane(lectores));
		conten.add(	new JScrollPane(lectoresFinalizados));
		add(conten,BorderLayout.CENTER);
	}


	public void actualizarLector(Object[] data) {
		DefaultListModel<String> modelo = new DefaultListModel<String>();
		if (data!=null) {
			for (Object string : data) {
				if (string!=null) {
					Lector l = ((Lector)string);
					modelo.addElement("Lector "+l.getId()+"Leyendo >"+l.isLeyendo() );
				}
			}
		}
		lectores.setModel(modelo);
	}
	public void actualizarLectorFinalizado(Object[] data) {
		DefaultListModel<String> modelo = new DefaultListModel<String>();
		if (data!=null) {
			for (Object string : data) {
				if (string!=null) {
					Lector l = ((Lector)string);
					modelo.addElement("Lector "+l.getId()+"Leyendo >"+l.isLeyendo()+" "+l.getTiempoDeTrabajo() );
				}
			}
		}
		lectoresFinalizados.setModel(modelo);
	}
	
}
