package p0402;

public class Almacen {
	private Producto[] prod;
	private int MAX;
	private int meter;
	private int sacar;
	private int nElems;
	
	public Almacen(int m) {
		this.MAX = m;
		this.prod = new Producto[MAX];
		this.meter = 0;
		this.sacar = 0;
		this.nElems = 0;
	}
	
	synchronized void almacenar(Producto producto) {
		while(nElems == MAX)
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		System.out.println("vamos a meter el prod " + producto.get() + " en pos " + meter);
		prod[meter] = producto;
		meter = (meter+1) % MAX;
		nElems++;
		notifyAll();
	}

	synchronized Producto extraer() {
		while(nElems ==0)
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		Producto sacado = prod[sacar];
		prod[sacar] = null;
		
		System.out.println("vamos a sacar el prod " + sacado.get() + " en pos " + sacar);

		sacar =(sacar+1)%MAX;
		nElems--;
		notifyAll();
		return sacado;
	}


}
