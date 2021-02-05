package p0402;

import java.util.concurrent.Semaphore;

public class Consumidor extends Thread {
	private int id;
	private Almacen stock;

	public Consumidor(int n, Almacen stock) {
		this.id = n;
		this.stock = stock;
	}

	public void run() {
		for(int i = 0; i < 5; i++) {
			
			Producto aux = stock.extraer();
		}
	}
}
