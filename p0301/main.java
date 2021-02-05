package p0301;

import java.util.concurrent.Semaphore;
/**
 * 
 * Implementación de la práctica 3.1
 * Evitar la condición de carrera con semáforos
 *
 */
public class main {
	
	public static void main(String[] args) {
		int n = 2000;
		A a = new A();
		int num;
		Semaphore sem = new Semaphore(1);
		
		new_thread hilos[] = new new_thread[n];
		for (int i = 0; i < n ; i++) {		
			num = ((i % 2 == 0) ? 1 : -1);
			hilos[i] = new new_thread(num, a, sem);
		}
		
		for (int i = 0; i < n ; i++) {
			hilos [i].start();
		}
		
		for (int i = 0; i < n ; i++) {			
			try {
				hilos[i].join();
			} catch (InterruptedException e) {				
				e.printStackTrace();
			}
		}
		System.out.println("El valor de la variable es = " + a.getA());
	}

}
