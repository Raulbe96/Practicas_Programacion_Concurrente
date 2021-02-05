package p0403;

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

	synchronized void almacenar(Producto producto, int aMeter) {
		//System.out.println("Alguien quiere meter el prod " + producto.get() + ", " + aMeter + " veces");
		for (int i = 0; i < aMeter; i++) {
			if(nElems == MAX) { 
				try {
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				notifyAll();
			}
			while (nElems == MAX)
				try {
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			System.out.println("vamos a meter el prod " + producto.get() + " en pos " + meter);
			prod[meter] = producto;
			meter = (meter + 1) % MAX;
			nElems++;
		}
		notifyAll();
	}

	synchronized Producto[] extraer(int aSacar) {
		//System.out.println("Alguien quiere sacar " + aSacar + " productos");
		Producto sacado[] = new Producto[aSacar];
		for (int i = 0; i < aSacar; i++) {
			
			if(nElems == 0) notifyAll();
			while (nElems == 0)
				try {
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			sacado[i] = prod[sacar];
			prod[sacar] = null;

			System.out.println("vamos a sacar el prod " + sacado[i].get() + " en pos " + sacar);

			sacar = (sacar + 1) % MAX;
			nElems--;
		}
		notifyAll();
		return sacado;
	}
}
