package p0402;

import java.util.concurrent.Semaphore;

public class Productor extends Thread {
	private int id;
	private Almacen stock;

	public Productor(int n, Almacen stock) {
		this.id = n;
		this.stock = stock;
	}

	public void run() {

		for(int i = 0; i < 5; i++) {
			
			stock.almacenar(new Producto(id));
		}
	}

}
