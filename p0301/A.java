package p0301;

public class A {
	private int var;
	
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
