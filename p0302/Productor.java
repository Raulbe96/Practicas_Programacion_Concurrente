package p0302;

import java.util.concurrent.Semaphore;

public class Productor extends Thread {
	private int id;
	private Semaphore full;
	private Semaphore empty;
	private Almacen stock;

	public Productor(int n, Semaphore lleno, Semaphore vacio, Almacen stock) {
		this.id = n;
		this.full = lleno;
		this.empty = vacio;
		this.stock = stock;
	}

	public void run() {

		for(int i = 0; i < 5; i++) {
			try {
				empty.acquire();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			stock.almacenar(new Producto(id));

			full.release();
		}
	}

}
