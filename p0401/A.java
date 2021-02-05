package p0401;

import java.util.concurrent.atomic.AtomicInteger;

public class A {
	volatile private AtomicInteger var;
	
	public A () {
		var = new AtomicInteger(0);
	}
	
	public void modif(int a) {
		var.getAndAdd(a);
	}	
	
	public int getA() {
		return var.get();
	}

}
