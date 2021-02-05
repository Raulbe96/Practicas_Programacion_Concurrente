package mensajes;

import java.util.HashMap;
import java.util.Vector;

public class ConfListaUsuarios extends Mensaje {
	
	private HashMap<String, Vector<String>> listaUsuarios;
	
	public ConfListaUsuarios(String origen, String destino, HashMap<String, Vector<String>> lista) {
		super(MensajeType.CONFLISTA_USUARIOS, origen, destino);
		// TODO Auto-generated constructor stub
		listaUsuarios = lista;
	}
	
	public HashMap<String, Vector<String>> getListaU(){
		return listaUsuarios;
	}

}
