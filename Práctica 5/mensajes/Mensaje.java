package mensajes;

import java.io.Serializable;

public abstract class Mensaje implements Serializable{

	private static final long serialVersionUID = 1L;
	private MensajeType tipo;
	private String origen;
	private String destino;
	
	public Mensaje(MensajeType tipo, String origen, String destino) {
		this.tipo = tipo;
		this.origen = origen;
		this.destino = destino;
	}
	
	public MensajeType getTipo() {
		return this.tipo;
	}
	public String getOrigen() {
		return this.origen;
	}
	public String getDestino() {
		return this.destino;
	}
}
