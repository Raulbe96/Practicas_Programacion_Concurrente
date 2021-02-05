package p0403;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Consumidor extends Thread {
	private int id;
	private Almacen stock;
	private int nElems;

	public Consumidor(int n, Almacen stock, int el) {
		this.id = n;
		this.stock = stock;
		this.nElems = el;
	}

	public void run() {
		for(int i = 0; i < 5; i++) {
			
			//Como mucho, intentará sacar MAX elementos
			Producto aux[] = stock.extraer(nElems);
		}
	}
}
