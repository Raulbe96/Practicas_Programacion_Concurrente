package usuario;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class UsuarioCanales {
	
	private ObjectOutputStream fout;
	private ObjectInputStream fin;
	
	public UsuarioCanales(ObjectInputStream entrada, ObjectOutputStream salida) {
		fin = entrada;
		fout = salida;
		
	}
	public ObjectOutputStream getFout() {
		return fout;
	}

	public ObjectInputStream getFin() {
		return fin;
	}
}
