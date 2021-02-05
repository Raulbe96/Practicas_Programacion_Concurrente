package servidor;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import usuario.Usuario;
import usuario.UsuarioCanales;

public class InfoServer {

	// Los datos
	private HashMap<String, UsuarioCanales> listaUsuarios;
	
	public InfoServer() {
		listaUsuarios = new HashMap<String, UsuarioCanales>();
	}
	
	public void nuevoUsuario(Usuario nuevo, ObjectOutputStream salida,
			ObjectInputStream entrada) {
		UsuarioCanales usu = new UsuarioCanales(entrada, salida);
		listaUsuarios.put(nuevo.getId(), usu);
	}

	
	public void borrarUsuario(String id) {
		listaUsuarios.remove(id);
	}
	
	public ObjectOutputStream getOut(String id) {
		return listaUsuarios.get(id).getFout();
	}
	
}
