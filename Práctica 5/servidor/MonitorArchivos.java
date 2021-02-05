package servidor;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Vector;

import usuario.Usuario;

public class MonitorArchivos {
	private HashMap<String, Vector<String>> listArchivos;
	
	public MonitorArchivos() {
		this.listArchivos = new HashMap<String, Vector<String>>();
	}
	
	synchronized void nuevoUsuario(Usuario nuevo) {
		this.listArchivos.put(nuevo.getId(), nuevo.getArchivos());
	}
	
	synchronized String buscarPropietario(String archivo) {
		
		Iterator<Entry<String, Vector<String>>> usuarios = listArchivos.entrySet().iterator();
		String usuario = "no";
		boolean encontrado = false;
		while (usuarios.hasNext() && !encontrado) {
			//Voy recorriendo todas las entradas del hasmap
		    Entry<String, Vector<String>> usu = usuarios.next();
		    //Saco la lista de archivos y miro si el que me han pedido está entre ellos
		    Vector<String> listArchivos = usu.getValue();
		    
		    for (String arc : listArchivos) {
				if(arc.equalsIgnoreCase(archivo)) {
					usuario = usu.getKey();
					encontrado = true;
				}
			}
		}
		return usuario;
	}
	
	synchronized HashMap <String, Vector<String>> listaUsuarios(){
		HashMap <String, Vector<String>> lista = new HashMap <String, Vector<String>>();
		
		Iterator<Entry<String, Vector<String>>> usuarios = listArchivos.entrySet().iterator();
		while (usuarios.hasNext()) {
			//Voy recorriendo todas las entradas del hasmap
		    Entry<String, Vector<String>> usu = usuarios.next();
		    //Saco la lista de archivos y guardo el id del cliente con su lista
		    Vector<String> listArchivos = usu.getValue();
		    lista.put(usu.getKey(), usu.getValue());
		}
		return lista;
	}
	
	synchronized void borrarUsuario(String id) {
		this.listArchivos.remove(id);
	}
	
}
