package p0203;

public class A {
	volatile private int var;
	
	public A () {
		var = 0;
	}
	
	public void modif(int a) {
		this.var += a;
	}	
	
	public int getA() {
		return var;
	}

}
