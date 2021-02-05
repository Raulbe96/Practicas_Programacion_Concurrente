package p0202;

public class A {
	volatile private int var;
	
	public A () {
		this.var = 0;
	}
	
	public void modif(int a) {
		this.var += a;
	}	
	
	public int getA() {
		return var;
	}

}
