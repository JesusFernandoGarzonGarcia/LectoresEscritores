package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import models.Dao;
import views.ViewsPrincipal;

public class Controller implements ActionListener {

	Dao d;
	ViewsPrincipal v;
	Thread t;
	int iteration;
	public Controller() {
		iteration=0;
		v = new ViewsPrincipal(this);
		d = new Dao();
	}


	public void verifyCurrentTask() {
		d.restartSymulation(Integer.parseInt(v.cantidadIteraciones()),Integer.parseInt(v.tiempoEspera()));

		t = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					iteration=0;
					for (int i = 0; i < d.getAmountIterations(); i++) {
						Thread.sleep(d.getTimeSystem());
						v.crearGrafico(d.tiempoTranscurrido(iteration+1));
						v.actualizarTiempoSistema(iteration+"");
						v.actualizarEscritorActual(" "+d.getCurrentWriter().getId()+" tiempo de trabajo > "+d.getCurrentWriter().getWorkTime()+" "+d.getCurrentWriter().isWriting());
						d.tasksWriters();
						v.actualizarTareaActual(d.getCurrentTask());
						d.timeToGenerateAReader();
						v.actualizarTexto(d.getResource().getContent());
						v.actualizarLectores(d.getReaders().toArray());
						v.actualizarTiempoLector(d.getNextReader()+"");
						v.actualizarTareaActual(d.getCurrentTask());
						v.actualizarEscritores(d.getWriters().toArray());
						v.actualizarLectoresFinalizados(d.getReadersDoingTask().toArray());
						v.actualizarTiempoEscritor(d.getNextWriter()+"");
						d.TimeToGenerateAWriter();
						d.taskreadingFinished();
						iteration++;
						v.crearGraficaPromedio();
//						graficaHoras(double[][] ds);
						System.out.println("id "+ t.getId()+ " "+iteration);
					}

				} catch (InterruptedException e) {
				}
			}
		});
		t.start();
	}


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
