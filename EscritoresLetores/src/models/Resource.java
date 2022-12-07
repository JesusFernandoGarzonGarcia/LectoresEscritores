package models;

public class Resource {

	private String contents;
	private int writers;
	private int readers;
	private String stateResource;
	private boolean occupied;

	public Resource() {
		contents ="";
		occupied=false;
		
	}

	public void addWritten(String data) {
		if (!data.contains(" ")) {
			stateResource="Escribiendo";
			contents+= data;
			writers ++;
		}else {
			contents+= data;
		}
	}

	public String getData() {
		stateResource="Leyendo";
		readers++;
		return contents;
	}
	
	public String getResourceStatus() {
		return stateResource;
	}

	public String getContent() {
		return contents;
	}

	public int getWriters() {
		return writers;
	}

	public int getReaders() {
		return readers;
	}
	
	public void setBusy(boolean ocupado) {
		this.occupied = ocupado;
	}
	
	public boolean isBusy() {
		return occupied;
	}
	
}
