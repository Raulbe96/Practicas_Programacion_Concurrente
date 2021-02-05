package mensajes;

import usuario.Usuario;

public class InicioConexion extends Mensaje{

	private Usuario propio;
	
	public InicioConexion(String origen, String destino, Usuario nuevo) {
		super(MensajeType.CONEXION, origen, destino);
		
		propio = nuevo;
	}
	public Usuario getUsuario() {
		return this.propio;
	}

}
