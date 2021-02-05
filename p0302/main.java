package p0302;

import java.util.concurrent.Semaphore;
/**
 * 
 * Implementación de la práctica 3.2
 * Productor-consumidor con semáforos.
 * Almacén de un solo producto
 *
 */

public class main {
	public static void main(String[] args) {	
		final int nProd = 5;
		final int nCons = 5;
		Productor[] prod = new Productor[nProd];
		Consumidor[] cons = new Consumidor[nCons];
		
		Semaphore full = new Semaphore(0);
		Semaphore mutexProd = new Semaphore(1);
		Semaphore empty = new Semaphore(1);
		
		Almacen2 stock = new Almacen2();
		
		for (int i = 0; i < nProd ; i++) {		
			prod[i] = new Productor(i, full, empty, stock);
		}
		for (int i = 0; i < nCons ; i++) {		
			cons[i] = new Consumidor(i, full, empty, stock);
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
