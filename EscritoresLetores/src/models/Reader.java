package models;

/**
 *Esta clase define la clase lector el cual consultara datos un recurso compartido 
 * @author: Jesus Garzon
 * @version: 02/11/2022/A
 */
public class Reader implements Runnable{

	
	//Campos de la clase
	private int id;
	private int timework;
	private boolean Reading;
	private Resource resource;
	private int timeSleep;

	/**
	 * Constructor de lector con ingreso de parametros
	 * @param id identificador del lector
	 * @param timework tiempo que el lector demora realizando su tarea
	 * @param timeSleep tiempo de espera por cada iteracion
	 */
	public Reader(int id, int timework,int timeSleep) {
		this.id=id;
		Reading = false;
		this.timework =timework;
		this.timeSleep=timeSleep;
	}

	/**
	 * Constructor de lector sin ingreso de parametros
	 */
	public Reader() {
		this.id=0;
		this.timework=0;
	}

	/**
	 * Método me modifica el esatdo de lectura del lector y a su vez realiza actualiza el recurso al cual se solicitara el acceso
	 */
	public void setLegend(Resource resourse) {
		this.Reading = true;
		this.resource =resourse;
	}

	/**
	 * Método que nos permite obtener el tiempo actual necesario para realizar la tarea
	 * @return tiempo actual para terminar de realizar la tarea de lectura
	  */
	public int getWorkTime() {
		return timework;
	}

	/**
	 * Método que nos permite obtener el estado actual del lector leyendo(true) no leyendo(false)
	 * @return el estado actual (leyendo = true o no leyendo = fale)del lector 
	  */
	public boolean isReading() {
		return Reading;
	}

	/**
	 * Método que nos permite obtener el identificador del lector
	 * @return el identificador del lector
	  */
	public int getId() {
		return id;
	}

	/**
	 * Método que sobre escribe el metodo run de la interface Runnable
	 * este metodo se encuentra sincronizado con un recurso, este metodo permitira descontar el tiempo de trabajo de lectura
	 * notificara a los hilos que a realizado la lectura
	  */
	@Override
	public void run() {
		synchronized (resource) {
			Reading =true;
			while (timework>0) {
				resource.getData();
				timework--;
				try {
					Thread.sleep(this.timeSleep);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			Reading =false;
//			resource.notify();
		}
	}

	@Override
	public String toString() {
		return "Reader [id=" + id + ", timework=" + timework + ", Reading=" + Reading + "]";
	}
	
	
	
}
