package p0302;

public class Almacen2 implements Almacen{
	private Producto prod;
	
	public Almacen2() {
	}
	
	@Override
	public void almacenar(Producto producto) {
		System.out.println("vamos a meter el prod " + producto.get());
		this.prod = producto;
	}

	@Override
	public Producto extraer() {
		
		Producto sacado = prod;
		prod= null;
		
		System.out.println("vamos a sacar el prod " + sacado.get());
		
		return sacado;
	}


}
