package p0402;

import java.util.concurrent.Semaphore;


/**
 * 
 * Implementación de la práctica 4.2
 *Reimplementación de la práctica 3.3 con métodos synchronized.
 *
 */
public class main {
	public static void main(String[] args) {	
		final int MAX = 10; 
		final int nProd = 20;
		final int nCons = 20;
		Productor[] prod = new Productor[nProd];
		Consumidor[] cons = new Consumidor[nCons];
		
		Almacen stock = new Almacen(MAX);
		
		for (int i = 0; i < nProd ; i++) {		
			prod[i] = new Productor(i, stock);
		}
		for (int i = 0; i < nCons ; i++) {		
			cons[i] = new Consumidor(i, stock);
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
