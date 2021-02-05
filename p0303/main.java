package p0303;

import java.util.concurrent.Semaphore;

/**
 * 
 * Implementación de la práctica 3.3.
 * Productor consumidor con semáforos.
 * Almacén de N productos, se saca o mete uno cada vez que se ejecuta
 *  uno de los procesos
 *
 */
public class main {
	public static void main(String[] args) {	
		final int MAX = 5; 
		final int nProd = 5;
		final int nCons = 5;
		Productor[] prod = new Productor[nProd];
		Consumidor[] cons = new Consumidor[nCons];
		
		Semaphore full = new Semaphore(0);
		Semaphore mutexProd = new Semaphore(1);
		Semaphore empty = new Semaphore(MAX);
		
		Almacen2 stock = new Almacen2(MAX);
		
		for (int i = 0; i < nProd ; i++) {		
			prod[i] = new Productor(i, full, mutexProd, empty, stock);
		}
		for (int i = 0; i < nCons ; i++) {		
			cons[i] = new Consumidor(i, full, mutexProd, empty, stock);
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
