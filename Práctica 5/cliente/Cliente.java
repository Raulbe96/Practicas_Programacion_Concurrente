package cliente;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;
import java.util.Vector;

import mensajes.CerrarConexion;
import mensajes.InicioConexion;
import mensajes.PedirFichero;
import mensajes.PedirListaUsuarios;
import usuario.Usuario;

public class Cliente {
	private Usuario propio;
	private String nombreArchivo;
	private Socket socket;
	private ObjectInputStream inputC;
	private ObjectOutputStream outputC;
	private Scanner in;

	public Cliente(InetAddress ip, int puerto, String id, String archivo, Scanner scaner) {
		this.nombreArchivo = archivo;
		in = scaner;
		try {
			socket = new Socket(ip, puerto);
			outputC = new ObjectOutputStream(socket.getOutputStream());
			inputC = new ObjectInputStream(socket.getInputStream());
			propio = new Usuario(id, ip, leerArchivos());

		} catch (Exception e) {
			System.out.println("No se ha podido establecer la conexión");
			return;
		}
	}

	public Vector<String> leerArchivos() {
		Vector<String> archivos = new Vector<String>();
		try {
			BufferedReader in = new BufferedReader(new FileReader(this.nombreArchivo));
			String linea = in.readLine();
			while (linea != null) {
				archivos.add(linea);
				linea = in.readLine();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Archivo " + this.nombreArchivo + " no encontrado");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Problema con el archivo");
		}
		return archivos;
	}

	public void ClientRun() {
		// Creamos el hilo
		new OyenteServidor(socket, inputC, outputC, this.propio.getId(), this.propio.getIP()).start();
		// Creamos el mensaje
		InicioConexion datos = new InicioConexion(this.propio.getId(), "SERVIDOR", this.propio);
		// Lo enviamos por outputC
		try {
			outputC.writeObject(datos);
		} catch (Exception e) {
			System.out.println("No se ha podido enviar la petición de conexión");
		}

		// Todo lo de la lectura va aquí:
		boolean salir = false;
		while (!salir) {
			String s = in.nextLine();

			if (s.equalsIgnoreCase("Lista usuarios")) {
				PedirListaUsuarios msj = new PedirListaUsuarios(this.propio.getId(), "Servidor");
				try {
					outputC.writeObject(msj);
				} catch (IOException e) {
					System.out.println("No ha podido enviarse la petición");
				}
			}

			else if (s.equalsIgnoreCase("Solicitar archivo")) {
				System.out.println("¿Cómo se llama el archivo?");
				s = in.nextLine();
				PedirFichero msj = new PedirFichero(this.propio.getId(), "Servidor", s);
				try{
					outputC.writeObject(msj);
				}catch(Exception e) {}
			}
			else if (s.equalsIgnoreCase("Desconectar")) {
				CerrarConexion msj = new CerrarConexion(propio.getId(), "Servidor");
				try {
					outputC.writeObject(msj);
					salir = true;
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					System.out.println("No se ha podido iniciar el cierre de conexión");
				}
			}
		}
	}

}
