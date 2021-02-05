package p0303;

import java.util.concurrent.Semaphore;

public class Consumidor extends Thread {
	private int id;
	private Semaphore full;
	private Semaphore mutexProd;
	private Semaphore empty;
	private Almacen stock;

	public Consumidor(int n, Semaphore lleno, Semaphore mut, Semaphore vacio, Almacen stock) {
		this.id = n;
		this.full = lleno;
		this.mutexProd = mut;
		this.empty = vacio;
		this.stock = stock;
	}

	public void run() {
		for(int i = 0; i < 5; i++) {
			try {

				full.acquire();
				mutexProd.acquire();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			Producto aux = stock.extraer();

			mutexProd.release();
			empty.release();
		}
	}
}
