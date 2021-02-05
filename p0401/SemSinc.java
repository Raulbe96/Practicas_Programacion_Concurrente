package p0401;

public class SemSinc {
	private A a;

	public SemSinc(A a) {
		this.a = a;
	}

	synchronized void modif(int val) {
		a.modif(val);
	}
}
