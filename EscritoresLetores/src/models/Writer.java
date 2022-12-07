package models;

import java.util.Random;

public class Writer{
	private int id;
	private boolean writing;
	private int timework;

	public Writer(int id,int timework) {
		this.id =id;
		writing = false;
		this.timework =timework;
	}
	
	public Writer() {
		this.id =0;
		timework =0;
		writing = false;
	}


	private void write(Resource resource,int id) {

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
							Thread.sleep(1000);
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


	public void setWriting(Resource resource) {
		this.writing = true;
		write(resource,id);
	}
	public int getWorkTime() {
		return timework;
	}

	public boolean isWriting() {
		return writing;
	}

	public int getId() {
		return id;
	}

}
