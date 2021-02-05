package servidor;

import java.net.Socket;
import java.util.HashMap;
import java.util.Vector;

import mensajes.ArchivoInexistente;
import mensajes.CerrarConexion;
import mensajes.ConfCierre;
import mensajes.ConfConexion;
import mensajes.ConfListaUsuarios;
import mensajes.EmitirFichero;
import mensajes.InicioConexion;
import mensajes.Mensaje;
import mensajes.MensajeType;
import mensajes.PedirFichero;
import mensajes.PreparadoClienteServidor;
import mensajes.PreparadoServCliente;
import usuario.Usuario;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class OyenteCliente extends Thread {
	private Socket socket;
	private String id;
	private ObjectInputStream fin;
	private ObjectOutputStream fout;
	private InfoServer infoS;
	private MonitorRW monRW;
	private MonitorArchivos monAr;

	public OyenteCliente(Socket soc, InfoServer info, MonitorRW mon, MonitorArchivos monitorAr) {
		this.socket = soc;
		this.monRW = mon;
		this.infoS = info;
		monAr = monitorAr;
		try {
			fin = new ObjectInputStream(socket.getInputStream());
			fout = new ObjectOutputStream(socket.getOutputStream());
		} catch (Exception e) {
		}
	}

	public void run() {
		boolean acabar = false;
		while (!acabar) {
			try {
				Mensaje m = (Mensaje) fin.readObject();

				// Mensaje recibido: iniciar conexión
				if (m.getTipo() == MensajeType.CONEXION) {
					InicioConexion mens = (InicioConexion) m;
					Usuario nuevo = mens.getUsuario();

					// Guardo al nuevo usuario tanto en infoS como en monAr.
					monRW.requestWrite();
					infoS.nuevoUsuario(nuevo, this.fout, this.fin);
					monRW.releaseWrite();
					monAr.nuevoUsuario(nuevo);
					
					
					ConfConexion msj = new ConfConexion("Servidor", nuevo.getId());
					try {
						fout.writeObject(msj);
					} catch (Exception e) {
						System.out.println("Fallo al confirmar la conexión");
					}
				}
				// Mensaje recibido: petición de la lista de usuarios
				else if (m.getTipo() == MensajeType.LISTA_USUARIOS) {
					
					HashMap <String, Vector<String>> lista = monAr.listaUsuarios();
					ConfListaUsuarios msj = new ConfListaUsuarios("Servidor", this.id, lista);
					try {
						this.fout.writeObject(msj);
					} catch (Exception e) {
						System.out.println("No ha podido devolverse la lista de usuarios correctamente");
					}
				}
				// Mensaje recibido: petición de desconexión
				else if (m.getTipo() == MensajeType.CERRAR_CONEXION) {
					String id = ((CerrarConexion) m).getOrigen();

					monRW.requestWrite();
					infoS.borrarUsuario(id);
					monRW.releaseWrite();
					
					monAr.borrarUsuario(id);

					ConfCierre msj = new ConfCierre("Servidor", id);
					fout.writeObject(msj);
					socket.close();
					acabar = true;
				}
				// Mensaje recibido: petición de un archivo
				else if (m.getTipo() == MensajeType.PEDIR_FICHERO) {
					PedirFichero mens = (PedirFichero) m;

					String usuario = monAr.buscarPropietario(mens.getArchivo());

					if (usuario == "no") {
						ArchivoInexistente msj = new ArchivoInexistente("Servidor", mens.getOrigen(), mens.getArchivo());
						fout.writeObject(msj);
					}
					else {
						monRW.requestRead();
						ObjectOutputStream nuevOut = infoS.getOut(usuario);
						monRW.releaseRead();

						EmitirFichero msj = new EmitirFichero(mens.getOrigen(), usuario, mens.getArchivo());
						nuevOut.writeObject(msj);
					}
				}
				//Mensaje recibido: cliente emisor del archivo está preparado
				else if (m.getTipo() == MensajeType.PREP_CLIENTESERVIDOR) {
					PreparadoClienteServidor mens = (PreparadoClienteServidor) m;
					PreparadoServCliente msj = new PreparadoServCliente(mens.getOrigen(), mens.getDestino(),
							mens.getDir(), mens.getPuerto(), mens.getArchivo());

					monRW.requestRead();
					ObjectOutputStream nuevOut = infoS.getOut(mens.getDestino());
					monRW.releaseRead();

					nuevOut.writeObject(msj);
				}

			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				System.out.println("clase no encontrada");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				System.out.println("No llega lo que debe");
			}

		}
	}
}
