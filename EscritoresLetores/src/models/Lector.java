package models;

public class Lector implements Runnable{


	private int id;
	private int tiempoDeTrabajo;
	private boolean leyendo;
	private Resource recurso;

	public Lector(int id, int tiempoDeTrabajo) {
		this.id=id;
		leyendo = false;
		this.tiempoDeTrabajo =tiempoDeTrabajo;
	}

	public Lector() {
		this.id=0;
		this.tiempoDeTrabajo=0;
	}


	public void setLeyend(Resource recurso) {
		this.leyendo = true;
		this.recurso =recurso;
	}

	public int getTiempoDeTrabajo() {
		return tiempoDeTrabajo;
	}

	public boolean isLeyendo() {
		return leyendo;
	}

	public int getId() {
		return id;
	}

	@Override
	public void run() {
		synchronized (recurso) {
			leyendo =true;
			while (tiempoDeTrabajo>0) {
				recurso.getData();
				tiempoDeTrabajo--;
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println(recurso.getData());
			System.out.println("termino lectura "+ id);
			leyendo =false;
			recurso.notify();
		}
	}
}
