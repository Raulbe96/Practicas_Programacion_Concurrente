package p0202;

public class LockRompeEmpate {
	volatile private int[] in;
	volatile private int[] last;
	private int m;

	public LockRompeEmpate(int m) {
		this.m = m;
		this.in = new int[m * 2];
		for (int i = 0; i < m * 2; i++)
			this.in[i] = -1;

		this.last = new int[m * 2];
		for (int i = 0; i < m * 2; i++)
			this.in[i] = -1;

	}

	public void takeLock(int id) {
		int t = 2;
		for (int j = 0; j < m * 2; j++) {
			in[id] = j;
			in = in;
			last[j] = id;
			last=last;
			for (int k = 0; k < m * 2; k++) {
				if (k != id)
					while (in[k] >= in[id] && last[j] == id)
						;
			}
		}
	}

	public void releaseLock(int id) {
		in[id] = -1;
	}
}
