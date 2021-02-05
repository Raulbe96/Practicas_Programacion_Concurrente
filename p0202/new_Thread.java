package p0202;

public class new_Thread extends Thread {
	private int id;
	private int val;
	private A a;
	private int m;
	private int n;
	private LockRompeEmpate bloqueo;

	public new_Thread(int id, int val, A a, int n, int m, LockRompeEmpate bloqueo) {
		this.id = id;
		this.val = val;
		this.a = a;
		this.n = n;
		this.m = m;
		this.bloqueo = bloqueo;
	}

	public void run() {
		for (int x = 0; x < n; x++) {
			bloqueo.takeLock(this.id);
			System.out.println("HILO " + this.id +" bloqueado");
			a.modif(val);
			bloqueo.releaseLock(this.id);
			System.out.println("HILO " + this.id +" desbloqueado");
		}

	}

}
