package p0403;

import java.util.concurrent.Semaphore;


/**
 * Implementación de la práctica 4.3
 * Productores-consumidores, almacén de tamaño m,
 * cada productor o consumidor podrá coger más de un elemento.
 * Métodos synchronized.
 *
 */
public class main {
	public static void main(String[] args) {	
		final int MAX = 5;
		final int nElems=3;
		final int nProd = 10;
		final int nCons = 10;
		Productor[] prod = new Productor[nProd];
		Consumidor[] cons = new Consumidor[nCons];
		
		Almacen stock = new Almacen(MAX);
		
		for (int i = 0; i < nProd ; i++) {		
			prod[i] = new Productor(i, stock, nElems);
		}
		for (int i = 0; i < nCons ; i++) {		
			cons[i] = new Consumidor(i, stock, nElems);
		}
		
		for (int i = 0; i < nProd ; i++) {
			prod[i].start();
		}
		for (int i = 0; i < nCons ; i++) {
			cons[i].start();
		}
		for (int i = 0; i < nCons ; i++) {			
			try {
				cons[i].join();
			} catch (InterruptedException e) {				
				e.printStackTrace();
			}
		}
		for (int i = 0; i < nProd ; i++) {			
			try {
				prod[i].join();
			} catch (InterruptedException e) {				
				e.printStackTrace();
			}
		}
		
	}
}
