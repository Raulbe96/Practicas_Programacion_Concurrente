package p0203;

import java.util.concurrent.atomic.*;
/**
 * 
 * Implementación de la práctica 2.3
 * Evitar condición de carrera con espera activa y 2m procesos (M inc y M decrementadores)
 * Con lock ticket
 *
 */
public class main {


	public static void main(String[] args) {
		int n = 20;
		int m = 10;
		A a = new A();
		
		LockTicket bloqueo = new LockTicket(n);
		
		int num;
		new_Thread hilos[] = new new_Thread[m*2];
		
		for (int i = 0; i < m*2; i++) {
			num = ((i % 2 == 0) ? 1 : -1);
			hilos[i] = new new_Thread(i, num, a, n, m, bloqueo);
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