package mensajes;

public class PedirListaUsuarios extends Mensaje {

	public PedirListaUsuarios(String origen, String destino) {
		super(MensajeType.LISTA_USUARIOS, origen, destino);
	}

}
