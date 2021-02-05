package mensajes;

public class PedirFichero extends Mensaje {
	private String fichero;
	public PedirFichero(String origen, String destino, String fichero) {
		super(MensajeType.PEDIR_FICHERO, origen, destino);
		this.fichero = fichero;
	}
	public String getArchivo() {
		return this.fichero;
	}
}
