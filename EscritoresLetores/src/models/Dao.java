package models;

import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Esta clase define la logica del symulador propuesto
 * @author: Jesus Garzon
 * @version: 05/12/2022/A
 */
public class Dao {

	//Campos de la clase
	private final Logger  LOG = Logger.getLogger(Dao.class.getName());

	private int timeSystem;
	private int nextWriter;
	private int nexReader;
	private int writerid;
	private int readerid;
	private Reader currentreader;
	private Writer writercurrent;
	private boolean waitingServiceWriting=false;
	private boolean waitingServiceRead=false;
	private Queue<Writer> writers; 
	private Queue<Reader> readers; 
	private CopyOnWriteArrayList<Reader> readersDoingTask;
	private ArrayList<Reader> readFinished;
	private ArrayList<Writer> writerFinished;
	private int numberIterations;
	private Resource resource;
	private String dataSaveLog;
	private int timeWrite;
	private int timeRead;

	/**
	 * metodo permite almacenar la informacion del sistema en un archivo de texto plano por cada iteracion que realicemos
	 * @param iteracion tiempo actual del sistema
	 */
	public void addInforLog( int iteracion) {
		dataSaveLog="";
		dataSaveLog+="----------------"+iteracion+"--------------------";
		dataSaveLog+=" Tiempo del sistema: " +iteracion+"\n";
		dataSaveLog+=" Tiempo para nuevo escritor: " +nextWriter+"\n";
		dataSaveLog+=" siguiente escritor: " +writerid+"\n";
		dataSaveLog+=" escritor actual: " +writercurrent.toString()+"\n";
		dataSaveLog+=" escritor esperando servicio: " +waitingServiceWriting+"\n";
		dataSaveLog+=" escritores esperando servicio: " +writers.toString()+"\n \n";

		dataSaveLog+=" Tiempo para nuevo lector: " +nexReader+"\n";
		dataSaveLog+=" siguiente lector: " +readerid+"\n";
		dataSaveLog+=" lector actual: " +currentreader.toString()+"\n";
		dataSaveLog+=" lector esperando servicio: " +waitingServiceRead+"\n";
		dataSaveLog+=" lectores esperando servicio: " +readers.toString()+"\n \n";
		
		dataSaveLog+=" Tarea actual: " +resource.getResourceStatus()+"\n";
		dataSaveLog+=" Datos actuales del recurso: " +resource.getContent()+"\n \n \n";
		dataSaveLog+=" Solicitudes para escritura: " +writerid+"\n";
		dataSaveLog+=" Tiempo total de escritura actual: " +timeWrite+"\n";
		dataSaveLog+=" Tiempo promedio de escritura actual: " +timeWrite/writerFinished.size()+"\n \n";
		dataSaveLog+=" Solicitudes para lectura: " +readerid+"\n";
		dataSaveLog+=" Tiempo total de lectura actual: " +timeRead+"\n";
		dataSaveLog+=" Tiempo promedio de lectura actual: " +timeRead/readFinished.size()+"\n";
		
		LOG.log(Level.INFO, dataSaveLog);
	}

	/**
	 * Constructor de la logica del simulador
	 */
	public Dao() {
		dataSaveLog ="";
		timeRead =0;
		timeWrite=0;
		try {
			FileHandler filetxt = new FileHandler("logging.txt");
			SimpleFormatter formaterTxt = new SimpleFormatter();
			filetxt.setFormatter(formaterTxt);
			LOG.addHandler(filetxt);
		} catch (SecurityException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	//Cierre del constructors



	/**
	 * metodo que nos permitira llevar el tiempo necesario para crear un nuevo escritor
	 * cuando el tiempo es ==0 crea un nuevo escritor y se establece un nuevo tiempo ramdon maximo de 10 para el siguiente escritor
	 * cambia la variable waitingServiceWriting a true;
	 */
	public void TimeToGenerateAWriter() {
		if (0==nextWriter) {
			writers.add(new Writer(writerid++, (int) (Math.random()*10)));
			waitingServiceWriting=true;
			nextWriter=(int) (Math.random()*10);;
		}else {
			nextWriter--;
		}
	}

	/**
	 * metodo que nos permitira llevar el tiempo necesario para crear un nuevo lector
	 * cuando el tiempo es ==0 crea un nuevo lector y se establece un nuevo tiempo ramdon maximo de 10 para el siguiente lector
	 * cambia la variable waitingServiceRead a true;
	 */
	public void timeToGenerateAReader() {
		if (0==nexReader) {
			Reader l = new Reader(readerid++,(int) (Math.random()*10),timeSystem);
			readers.add(l);
			waitingServiceRead=true;
			nexReader=(int) (Math.random()*10);
		}else {
			nexReader--;
		}
	}

	/**
	 * envia todos los lectores disponible a leer el recurso, aparte de esto inicializa el respectivo hilo de cada uno de los lectores
	 * agrega a cada uno de los lectores a la lista de readersDoingTask
	 */
	public void sendAlectors() {
		if (!readers.isEmpty()) {
			for (int i = 0; i < readers.size(); i++) {
				//				currenttask ="leyendo";
				currentreader = readers.remove();
				currentreader.setLegend(resource);
				Thread t = new Thread(currentreader);
				t.start();
				 timeRead+= currentreader.getWorkTime();
				readersDoingTask.add(currentreader);
			}
		}
	}

	/**
	 * remueve el siguiente escritor de la cola y lo asigna a el lector actual, 
	 * inicializa el hilo del escritor actual si el recurso no se encuentra ocupado
	 */
	public void removeWriter() {
		if (!writers.isEmpty()){
			writercurrent = writers.remove();
			if (!resource.isBusy()) {
				timeWrite += writercurrent.getWorkTime();
				writerFinished.add(writercurrent);
				writercurrent.setWriting(resource,timeSystem);
			}
		}
	}

	/**
	 * verifica las tareas de lectura que han sido terminadas y las elimina de la lista de readersDoingTask
	 * agrega los lectores finalizados a readFinished
	 *@return retorna si todos los trabajos actuales han sido finalizados
	 */

	public boolean taskreadingFinished() {
		boolean finalizado = true;
		for (Reader lector : readersDoingTask) {
			if (!lector.isReading()&&lector.getWorkTime()==0) {
				readFinished.add(lector);
				readersDoingTask.remove(lector);
				finalizado =false;
			}
		}
		return finalizado;
	}

	/**
	 * ejecuta los metodos de un escritor, verifica que si el escritor actual esta realizando su trabajo y si los trabajos de lectura han sido terminados
	 * envia a lectores a utilizar el recurso o envia a el escritor a utilizar el recurso
	 */
	public void tasksWriters() {

		if (!writercurrent.isWriting()&&taskreadingFinished()) {
			if (!readers.isEmpty()&&waitingServiceRead) {
				sendAlectors();
			}
			removeWriter();
		}
	}

	/**
	 * inicializa todoas los parametros de la clase de forma que se vacien cadauno de ellos 
	 * @param numberIterations cantidad de iteraciones ingresadas por el usuario (sera el tiempo que se demorara la simulacion)
	 * @param timeSystem tiempo que se realizara un sleep en el sistema para ver el funcionamiento del sistema
	 */
	public void restartSymulation(int numberIterations,int timeSystem) {
		//		currenttask ="";
		this.numberIterations =numberIterations;
		dataSaveLog ="";
		timeRead =0;
		timeWrite=0;
		writerid =1;
		readerid=1;
		this.timeSystem = timeSystem;
		resource = new Resource();
		currentreader = new Reader(); 
		writercurrent = new Writer(); 
		nextWriter = (int) (Math.random()*10);
		nexReader = (int) (Math.random()*10);
		readersDoingTask = new CopyOnWriteArrayList<>(); 
		writers = new LinkedList<Writer>(); 
		readers = new LinkedList<Reader>();
		readFinished = new ArrayList<>();
		readFinished.add(new Reader());
		writerFinished = new ArrayList<>();
		writerFinished.add(new Writer());
		LOG.log(Level.SEVERE, " Se a iniciado una nueva simulacion " );
	}

	/**
	 * @return retorna el escritor actual
	 */
	public Writer getCurrentWriter() {
		return writercurrent;
	}

	/**
	 * @return retorna el lector actual
	 */
	public Reader getCurrentReader() {
		return currentreader;
	}

	/**
	 * @return esperando servicio de escritura
	 */
	public boolean isWaitingServiceWriters() {
		return waitingServiceWriting;
	}

	/**
	 * @return esperando servicio de lectura
	 */
	public boolean isWaitingServiceRead() {
		return waitingServiceRead;
	}

	/**
	 * @return cantidad de iteraciones realizadas por el sistema
	 */
	public int getAmountIterations() {
		return numberIterations;
	}

	/**
	 * altera el valor de  waitingServiceWriting
	 */
	public void setWaitingService(boolean WaitingService) {
		this.waitingServiceWriting = WaitingService;
	}

	/**
	 * @return el recurso compartido
	 */
	public Resource getResource() {
		return resource;
	}

	/**
	 * @return cola de escritores
	 */
	public Queue<Writer> getWriters() {
		return writers;
	}

	/**
	 * @return tiempo del sistema
	 */
	public  int getTimeSystem() {
		return timeSystem;
	}

	/**
	 * @return cola de lectores
	 */
	public Queue<Reader> getReaders() {
		return readers;
	}

	/**
	 * actualiza el valor de writercurrent 
	 */
	public void setCurrentWriter(Writer escritorActual) {
		this.writercurrent = escritorActual;
	}

	/**
	 * @return lista de lectores realizando su tarea
	 */
	public CopyOnWriteArrayList<Reader> getReadersDoingTask() {
		return readersDoingTask;
	}

	/**
	 * @return tiempo para siguente lector
	 */
	public int getNextReader() {
		return nexReader;
	}

	/**
	 * @return tiempo para siguente escritor
	 */
	public int getNextWriter() {
		return nextWriter;
	}

	/**
	 * @return tarea en la cual se encuentra el recurso
	 */
	public String getCurrentTask() {
		return resource.getResourceStatus();
	}
	
	

}
