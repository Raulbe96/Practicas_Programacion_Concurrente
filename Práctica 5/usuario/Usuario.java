package usuario;

import java.io.Serializable;
import java.net.InetAddress;
import java.util.Vector;

public class Usuario implements Serializable{
	private String id;
	private Vector<String> archivos;
	private InetAddress dirIp;
	
	public Usuario(String id, InetAddress ip, Vector<String> archivos) {
		this.id = id;
		this.dirIp = ip;
		this.archivos = archivos;
	}
	
	public String getId() {
		return id;
	}
	public Vector<String> getArchivos(){
		return this.archivos;
	}
	public InetAddress getIP() {
		return this.dirIp;
	}
}
