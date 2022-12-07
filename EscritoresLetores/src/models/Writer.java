package models;

import java.util.Random;

/**
 * Esta clase define la clase escritor el cual ingresara datos un recurso compartido
 * @author: Jesus Garzon
 * @version: 05/12/2022/A
 */

public class Writer{
	//Campos de la clase
	private int id;
	private boolean writing;
	private int timework;

	/**
	 * Constructor del contralador del programa con parametros
	 * @param id identificador del escritor
	 * @param timework tiempo que se demora realizando una tarea
	 */
	public Writer(int id,int timework) {
		this.id =id;
		writing = false;
		this.timework =timework;
	}
	/**
	 * Constructor del contralador del programa sin parametros
	 */
	public Writer() {
		this.id =0;
		timework =0;
		writing = false;
	}

	/**
	 * Método que inicializa el hilo para poder inicializar la escritura, aqui se generaran letras de forma aleatoria las cuales seran ingresadas al recurso
	 * @param resource recurso en el cual se realizara la escritura
	 * @param id identificador del escritor
	 *  @param timeSleep tiempo de espera
	 * */
	private void write(Resource resource,int id,int timeSleep) {

		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				synchronized (resource) {
					while (timework>0) {
						Random random = new Random();
					    char randomizedCharacter = (char) (random.nextInt(26) + 'a');
						resource.addWritten(randomizedCharacter+"");
						resource.setBusy(true);
						timework--;
						try {
							Thread.sleep(timeSleep);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					resource.addWritten(" ");
					writing=false;
					resource.setBusy(false);
					resource.notifyAll();
				}
			}
		});
		thread.start();
	}

	/**
	 * Método que inicializa el hilo de escritura y cambia el estado de actual a escribiendo = true
	 * @param resource recurso en el cual se realizara la escritura
	 * @param timeSystem tiempo de espera
	 * */
	public void setWriting(Resource resource,int timeSystem) {
		this.writing = true;
		write(resource,id,timeSystem);
	}
	
	/**
	 * Método que retorna el tiempo de trabajo actual del escritor
	 * @return tiempo actual de trabajo
	 */
	public int getWorkTime() {
		return timework;
	}

	/**
	 * Método que retorna el estado actual de la escritura
	 * escribiendo = true
	 * no escribiendo =false
	 * @return writing estado actual del escritor
	 */
	public boolean isWriting() {
		return writing;
	}

	/**
	 * Método que retorna el id del escritor
	 * @return id identificador del escritor
	 */
	public int getId() {
		return id;
	}
	@Override
	public String toString() {
		return "Writer [id=" + id + ", writing=" + writing + ", timework=" + timework + "]";
	}
	

}
