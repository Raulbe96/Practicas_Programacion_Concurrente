package p0202;

/**
 * Implementación práctica 2 con RompeEmpate:
 * Evitar condición de carrera con espera activa
 * m procesos decrementadores y m incrementadores
 *
 */
public class main {


	public static void main(String[] args) {
		int n = 2;
		int m = 50;
		A a = new A();
		LockRompeEmpate bloqueo = new LockRompeEmpate(m);
		int num;
		new_Thread hilos[] = new new_Thread[m*2];
		
		for (int i = 0; i < m*2; i++) {
			System.out.println("Creado hilo " + i);
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