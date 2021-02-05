package mensajes;


public class EmitirFichero extends Mensaje {
	private String archivo;
	

	public EmitirFichero(String origen, String destino, String archivo) {
		super(MensajeType.EMITIRFICHERO, origen, destino);
		// TODO Auto-generated constructor stub
		this.archivo = archivo;
	}
	
	public String getArchivo() {
		return this.archivo;
	}

}
