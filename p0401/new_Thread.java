package p0401;

public class new_Thread extends Thread {
	private int id;
	private int val;
	private A a;
	private int n;
	SemSinc sem;

	public new_Thread(int id, int val, A a, int n, SemSinc sem) {
		this.id = id;
		this.val = val;
		this.a = a;
		this.n = n;
		this.sem = sem;
	}

	public void run() {
		for (int x = 0; x < n; x++) {
			sem.modif(this.val);
		}

	}

}
