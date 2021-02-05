package mensajes;

import java.net.InetAddress;

public class PreparadoServCliente extends Mensaje {
	private InetAddress dir;
	private int puerto;
	private String archivo;

	public PreparadoServCliente(String origen, String destino, InetAddress ip, int puerto, String fichero) {
		super(MensajeType.PREP_SERVIDORCLIENTE, origen, destino);
		// TODO Auto-generated constructor stub
		this.dir = ip;
		this.puerto = puerto;
		this.archivo = fichero;
	}
	public InetAddress getDir() {
		return dir;
	}
	
	public int getPuerto(){
		return this.puerto;
	}
	
	public String getArchivo() {
		return this.archivo;
	}
}
