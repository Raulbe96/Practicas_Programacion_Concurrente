package p0403;

import java.util.Random;

public class Productor extends Thread {
	private int id;
	private Almacen stock;
	private int nElems;

	public Productor(int n, Almacen stock, int el) {
		this.id = n;
		this.stock = stock;
		this.nElems = el;
	}

	public void run() {

		for(int i = 0; i < 5; i++) {
			
			stock.almacenar(new Producto(id), nElems);
		}
		System.out.println(this.id + " ha acabado");
	}

}
