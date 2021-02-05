package servidor;

import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	// Los datos
	private int port;
	private ServerSocket serverSocket;
	private InfoServer infoS;
	private MonitorRW monRW;
	private MonitorArchivos monAr;

	public Server(int puerto) {
		try {
			infoS = new InfoServer();
			port = puerto;
			serverSocket = new ServerSocket(port);
			monRW = new MonitorRW();
			monAr = new MonitorArchivos();
		} catch (Exception e) {
			System.out.println("No se crea el serverSocket");
		}
	}

	public void ServerRun() {
		while (true) {
			try {
			Socket socket = serverSocket.accept();
			new OyenteCliente(socket, infoS, monRW, monAr).start();
			}catch(Exception e) {
				System.out.println("Algo falla al hacer la conexion?");
			}
		}
	}

}
