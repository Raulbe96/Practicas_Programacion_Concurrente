package p0204;
import java.util.concurrent.atomic.*;

public class A {
	volatile private AtomicInteger var;
	
	public A () {
		var = new AtomicInteger(0);
	}
	
	public void modif(int a) {
		this.var.getAndAdd(a);
	}	
	
	public int getA() {
		return var.get();
	}

}
