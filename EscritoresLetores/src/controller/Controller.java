package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import models.Dao;
import views.ViewsPrincipal;

/**
 * Esta clase define el contralador del programa, esto quiere decir que esta clase nos permitira  unir la parte grafica y la parte logica del programa 
 * @author: Jesus Garzon
 * @version: 05/12/2022/A
 */
public class Controller implements ActionListener {
	//Campos de la clase
	Dao d;
	ViewsPrincipal v;
	Thread t;
	int iteration;
	
	/**
	 * Constructor del contralador del programa
	 */
	public Controller() {
		iteration=0;
		v = new ViewsPrincipal(this);
		d = new Dao();
	}
	//Cierre del constructors

	/**
	 * que nos permitira inicializar una nueva symulacion
	 * este reiniciara el hilo principal y las iteraciones realizadas por el sistema
	 */
	public void verifyCurrentTask() {
		d.restartSymulation(Integer.parseInt(v.quantityIterations()),Integer.parseInt(v.Standbytime()));
		t = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					iteration=0;
					for (int i = 0; i < d.getAmountIterations(); i++) {
						Thread.sleep(d.getTimeSystem());
						v.updateSystemTime(iteration+"");
						v.updateWriterCurrent(" "+d.getCurrentWriter().getId()+" tiempo de trabajo > "+d.getCurrentWriter().getWorkTime()+" "+d.getCurrentWriter().isWriting());
						d.tasksWriters();
						v.updateCurrentTask(d.getCurrentTask());
						d.timeToGenerateAReader();
						v.updateText(d.getResource().getContent());
						v.refreshReader(d.getReaders().toArray());
						v.updateTimeReader(d.getNextReader()+"");
						v.updateCurrentTask(d.getCurrentTask());
						v.updateWriters(d.getWriters().toArray());
						v.updateReaderDone(d.getReadersDoingTask().toArray());
						v.updateTimeWriter(d.getNextWriter()+"");
						d.TimeToGenerateAWriter();
						d.taskreadingFinished();
						d.addInforLog(iteration);
						iteration++;
					}

				} catch (InterruptedException e) {
				}
			}
		});
		t.start();
	}

	/**
	 * motodo sobre escrito de keylistener que nos permitira capturar las acciones del usuria realizadas en la interfaz grafica
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("iniciar")) {
			if (t!=null) {
				t.interrupt();
			}
			 verifyCurrentTask();
		}
	}

	

	public static void main(String[] args) {
		new Controller();
	}


}
