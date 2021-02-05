package p0303;

public class Almacen2 implements Almacen{
	private Producto[] prod;
	private int MAX;
	private int meter;
	private int sacar;
	
	public Almacen2(int m) {
		this.MAX = m;
		this.prod = new Producto[MAX];
		this.meter = 0;
		this.sacar = 0;
	}
	
	@Override
	public void almacenar(Producto producto) {
		System.out.println("vamos a meter el prod " + producto.get() + " en pos " + meter);
		prod[meter] = producto;
		meter = (meter+1) % MAX;
	}

	@Override
	public Producto extraer() {
		
		Producto sacado = prod[sacar];
		prod[sacar] = null;
		
		System.out.println("vamos a sacar el prod " + sacado.get() + " en pos " + sacar);

		sacar =(sacar+1)%MAX;
		
		return sacado;
	}


}
