package p0301;

import java.util.concurrent.Semaphore;

public class new_thread extends Thread {
	private int val;
	private A a;
	private Semaphore sem;

	public new_thread(int val, A a, Semaphore semaforo) {
		this.val = val;
		this.a = a;
		this.sem = semaforo;
	}

	public void run() {

		try {
			sem.acquire();

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		a.modif(val);
		sem.release();
	}
}
