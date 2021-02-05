package p0201;

public class new_Thread extends Thread {
	private int id;
	private int val;
	private A a;
	private int n;

	public new_Thread(int id, int val, A a, int n) {
		this.id = id;
		this.val = val;
		this.a = a;
		this.n = n;
	}

	public void run() {
		for (int x = 0; x < n; x++) {
			System.out.printf("Hilo %d\n", id);
			a.modif(val);
		}

	}

}
