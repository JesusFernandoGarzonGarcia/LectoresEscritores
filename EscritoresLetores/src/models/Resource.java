package models;


/**
 * Esta clase define los parametros necesarios del recurso 
 * @author: Jesus Garzon
 * @version: 05/12/2022/A
 */
public class Resource {


	//Campos de la clase
	private String contents;
	private int writers;
	private int readers;
	private String stateResource;
	private boolean occupied;

	/**
	 * Constructor del contralador del recurso
	 * aqui se inicializan loas variables del recurso
	 */
	public Resource() {
		contents ="";
		occupied=false;
	}

	/**
	 * metodo que nos permite agregar datos a el contenido del recurso
	 * al ingresar aqui se cambiara el estado del recurso a Escribiendo
	 * aqui se aumentara un contador el cual es la cantidad de ingresos de datos
	 */
	public void addWritten(String data) {
		if (!data.contains(" ")) {
			stateResource="Escribiendo";
			contents+= data;
			writers ++;
		}else {
			contents+= data;
		}
	}

	/**
	 * metodo que nos permite mosrar el contenido del recurso
	 * al ingresar aqui se cambiara el estado del recurso a Leyendo
	 * aqui se aumentara un contador el cual es la cantidad de lecturas del contendio
	 * @return contenido del recurso
	 */
	public String getData() {
		stateResource="Leyendo";
		readers++;
		return contents;
	}
	
	/**
	 * metodo que nos muestra el estado actual del recurso
	 * @return estado actual del recurso
	 */
	public String getResourceStatus() {
		return stateResource;
	}

	/**
	 * metodo que nos muestra el contendio del recurso
	 * @return contenido del recurso
	 */
	public String getContent() {
		return contents;
	}

	/**
	 * metodo que nos muestra la cantidad de ingreso de datos al recurso
	 * @return cantidad de ingresos al recurso
	 */
	public int getWriters() {
		return writers;
	}

	/**
	 * metodo que nos muestra la cantidad de lecturas del recurso
	 * @return cantidad de lecturas al recurso
	 */
	public int getReaders() {
		return readers;
	}
	
	/**
	 * metodo que nos muestra si el recurso esta ocupado
	 * ocupado = true
	 * no ocupado = false
	 * @return estado del recurso (ocupado)true o (libre) false
	 */
	public void setBusy(boolean ocupado) {
		this.occupied = ocupado;
	}
	
	public boolean isBusy() {
		return occupied;
	}
	
}
