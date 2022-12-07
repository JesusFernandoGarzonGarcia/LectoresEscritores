package models;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.CopyOnWriteArrayList;

public class Dao {

	private Queue<Writer> writers; 
	private Queue<Lector> readers; 
	private CopyOnWriteArrayList<Lector> readersDoingTask;
//	private ArrayList<Escritor> writeFinished;
	private ArrayList<Lector> readFinished;
	private int timeSystem;
	private Lector currentreader;
	private Writer writercurrent;
	private int writerid;
	private int readerid;
	private int numberIterations;
	private int nextWriter;
	private int nexReader;
	private boolean waitingServiceWriting=false;
	private boolean waitingServiceRead=false;
	private Resource resource;

	public Dao() {
	}



	public void TimeToGenerateAWriter() {
		if (0==nextWriter) {
			writers.add(new Writer(writerid++, (int) (Math.random()*10)));
			waitingServiceWriting=true;
			nextWriter=(int) (Math.random()*10);;
		}else {
			nextWriter--;
		}
	}
	public void timeToGenerateAReader() {
		if (0==nexReader) {
			Lector l = new Lector(readerid++,(int) (Math.random()*10));
			readers.add(l);
			waitingServiceRead=true;
			nexReader=(int) (Math.random()*10);
		}else {
			nexReader--;
		}
	}


	public void sendAlectors() {
		if (!readers.isEmpty()) {
			for (int i = 0; i < readers.size(); i++) {
//				currenttask ="leyendo";
				currentreader = readers.remove();
				currentreader.setLeyend(resource);
				Thread t = new Thread(currentreader);
				t.start();
				readersDoingTask.add(currentreader);
			}
		}
	}

	public void removeWriter() {
		if (!writers.isEmpty()){
			writercurrent = writers.remove();
			if (!resource.isBusy()) {
				writercurrent.setWriting(resource);
			}
		}
	}

	public boolean taskreadingFinished() {
		boolean finalizado = true;
		for (Lector lector : readersDoingTask) {
			if (!lector.isLeyendo()&&lector.getTiempoDeTrabajo()==0) {
				readFinished.add(lector);
				readersDoingTask.remove(lector);
				finalizado =false;
			}
		}
		return finalizado;
	}

	public String[] tiempoTranscurrido(int tiempo) {
		String[] data = new String[tiempo];
		data[0]=0+"";
		for (int i = 0; i < data.length; i++) {
			data[i]=(i)+"";
		}
		return data;
	}


	public void tasksWriters() {

		if (!writercurrent.isWriting()&&taskreadingFinished()) {
			if (!readers.isEmpty()&&waitingServiceRead) {
				sendAlectors();
			}
			removeWriter();
		}
	}


	public void restartSymulation(int cantidadIteraciones,int tiempoEspera) {
//		currenttask ="";
		this.numberIterations =cantidadIteraciones;
		writerid =1;
		readerid=1;
		timeSystem = tiempoEspera;
		resource = new Resource();
		currentreader = new Lector(); 
		writercurrent = new Writer(); 
		nextWriter = (int) (Math.random()*10);
		nexReader = (int) (Math.random()*10);
		readersDoingTask = new CopyOnWriteArrayList<>(); 
		writers = new LinkedList<Writer>(); 
		readers = new LinkedList<Lector>();
		readFinished = new ArrayList<>();
	}


	public Writer getCurrentWriter() {
		return writercurrent;
	}

	public Lector getCurrentReader() {
		return currentreader;
	}

	public boolean isWaitingServiceWriters() {
		return waitingServiceWriting;
	}

	public boolean isWaitingServiceRead() {
		return waitingServiceRead;
	}

	public int getAmountIterations() {
		return numberIterations;
	}


	public void setWaitingService(boolean esperandoServicio) {
		this.waitingServiceWriting = esperandoServicio;
	}


	public Resource getResource() {
		return resource;
	}


	public Queue<Writer> getWriters() {
		return writers;
	}

	public  int getTimeSystem() {
		return timeSystem;
	}

	public Queue<Lector> getReaders() {
		return readers;
	}

	public void setCurrentWriter(Writer escritorActual) {
		this.writercurrent = escritorActual;
	}

	public CopyOnWriteArrayList<Lector> getReadersDoingTask() {
		return readersDoingTask;
	}

	public int getNextReader() {
		return nexReader;
	}

	public int getNextWriter() {
		return nextWriter;
	}

	public String getCurrentTask() {
		return resource.getResourceStatus();
	}


}
