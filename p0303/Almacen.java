package p0303;

public interface Almacen {
/**
 * Almacena (como ultimo) un producto en el almac�n. Si no hay
 * hueco, el proceso que ejecute el m�todo bloquear� hasta que lo
 * haya.
 */
	public void almacenar(Producto producto);
	/**
	 * Extrae el primer producto disponible. Si no hay productos el
	 * proceso que ejcute el m�todo se bloquear� hasta que se almacene un
	 * dato.
	 */
	public Producto extraer();
}
