package cliente;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Vector;

import mensajes.ArchivoInexistente;
import mensajes.ConfCierre;
import mensajes.ConfConexion;
import mensajes.ConfListaUsuarios;
import mensajes.EmitirFichero;
import mensajes.PreparadoClienteServidor;
import mensajes.PreparadoServCliente;

public class OyenteServidor extends Thread {
	private String id;
	private InetAddress ip;
	private Socket socket;
	private ObjectInputStream inputC;
	private ObjectOutputStream outputC;

	public OyenteServidor(Socket socket, ObjectInputStream input, ObjectOutputStream output, String id,
			InetAddress ip) {
		this.socket = socket;
		inputC = input;
		outputC = output;
		this.id = id;
		this.ip = ip;
	}

	public void run() {
		boolean acabado = false;
		while (!acabado) {
			try {
				Object m = inputC.readObject();
				// Mensaje de que ya está conectado el usuario
				if (m instanceof ConfConexion) {
					// Como ya se ha establecido la conexión, se enumeran las opciones:
					System.out.println("Bienvenido, " + this.id);
					System.out.println("¿Qué quieres hacer ahora?");
					System.out.println("Lista usuarios: pide la lista de usuarios");
					System.out.println("Solicitar archivo: solicita un archivo");
					System.out.println("Desconectar: cierra la aplicación y borra del servidor");
				}
				// Mensaje con la lista de usuarios
				else if (m instanceof ConfListaUsuarios) {
					ConfListaUsuarios mens = (ConfListaUsuarios) m;
					System.out.println("Lista de usuarios:");
					HashMap<String, Vector<String>> lista = mens.getListaU();

					Iterator<Entry<String, Vector<String>>> usuarios = lista.entrySet().iterator();
					while (usuarios.hasNext()) {
						// Voy recorriendo todas las entradas del hasmap
						Entry<String, Vector<String>> usu = usuarios.next();
						// Saco la lista de archivos y la guardo
						Vector<String> archivo = usu.getValue();
						System.out.print(usu.getKey() + ": ");
						
						//Controlo el caso de que un usuario no tenga ningún usuario asociado
						if (archivo.size() > 0) {
							//Muestro todos los archivos entre comillas, separados por comas (excepto el último de todos).
							for (int i = 0; i < archivo.size(); i++) {
								System.out.print('"' + archivo.elementAt(i) + '"');
								if(i < archivo.size()-1)
									System.out.print(',');
								System.out.print(' ');
							}
						}
						else {
							System.out.print("no tiene archivos asociados");
						}
						//Acabo con un salto de línea
						System.out.println();
					}

				}
				// Mensaje de que ya me he desconectado
				else if (m instanceof ConfCierre) {
					acabado = true;
				}

				// Mensaje de que me han pedido un fichero
				else if (m instanceof EmitirFichero) {
					EmitirFichero mens = (EmitirFichero) m;
					String archivo = mens.getArchivo();
					int puerto = 55;
					new Emisor(archivo, puerto).start();
					PreparadoClienteServidor msj = new PreparadoClienteServidor(this.id, mens.getOrigen(), this.ip,
							puerto, mens.getArchivo());
					this.outputC.writeObject(msj);
				}

				// Mensaje de que el emisor del archivo ya está preparado
				else if (m instanceof PreparadoServCliente) {
					PreparadoServCliente mens = (PreparadoServCliente) m;
					new Receptor(mens.getArchivo(), mens.getPuerto(), mens.getDir()).start();
					System.out.println("Recibiendo archivo");
				}
				// Mensaje de archivo solicitado no existente
				else if (m instanceof ArchivoInexistente) {
					ArchivoInexistente mens = (ArchivoInexistente) m;
					System.out.println("Ninguno de nuestros usuarios actuales tiene el archivo " + mens.getArchivo());
				}

			} catch (Exception e) {
				System.out.println("Problema con la conexión con el servidor");
			}
		}
		try {
			this.socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
