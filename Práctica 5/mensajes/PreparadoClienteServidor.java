package mensajes;

import java.net.InetAddress;

public class PreparadoClienteServidor extends Mensaje {
	private InetAddress dir;
	private int puerto;
	private String archivo;
	
	public PreparadoClienteServidor(String origen, String destino, InetAddress ip, int puerto, String archivo) {
		super(MensajeType.PREP_CLIENTESERVIDOR, origen, destino);
		// TODO Auto-generated constructor stub
		this.dir = ip;
		this.puerto = puerto;
		this.archivo = archivo;
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
