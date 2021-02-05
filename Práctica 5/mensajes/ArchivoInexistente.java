package mensajes;

public class ArchivoInexistente extends Mensaje {
	private String archivo;
	public ArchivoInexistente(String origen, String destino, String archivo) {
		super(MensajeType.ARCHIVO_INEXISTENTE, origen, destino);
		// TODO Auto-generated constructor stub
		this.archivo = archivo;
	}
	
	public String getArchivo() {
		return this.archivo;
	}

}
