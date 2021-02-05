package p0401;

import java.util.concurrent.atomic.*;

public class main {

/**
 * Implementación práctica 4.1
 * Reimplementación de la práctica 2 (incrementadores y decrementadores) 
 * con métodos synchronized.
 */
	
	public static void main(String[] args) {
		int n = 20;
		int m = 40;
		A a = new A();
		SemSinc sem = new SemSinc(a);
		
		int num;
		new_Thread hilos[] = new new_Thread[m*2];
		
		for (int i = 0; i < m*2; i++) {
			num = ((i % 2 == 0) ? 1 : -1);
			hilos[i] = new new_Thread(i, num, a, n, sem);
		}

		for (int i = 0; i < m*2; i++) {
			hilos[i].start();
		}
		
		for (int i = 0; i < m*2; i++) {
			try {
				hilos[i].join();
				System.out.println("El hilo " + i + " ha acabado");
			} catch (InterruptedException e) {
				System.out.println("Hilo " + i + " no va");
			}
		}
		System.out.println("El valor de la variable es = " + a.getA());
	}
}