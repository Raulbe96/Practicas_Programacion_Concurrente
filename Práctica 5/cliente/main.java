package cliente;

import java.net.InetAddress;
import java.util.Scanner;

public class main {
	
	public static void main(String[] args) {
		try {
			InetAddress localhost = InetAddress.getLocalHost();
			int port = 555;
			Scanner in = new Scanner(System.in);
			System.out.println("Hola, ¿cómo te llamas?");
			String nombre = in.nextLine();
			System.out.println("¿En qué archivo tienes enumerados los archivos que puedes compartir?");
			String archivos = in.nextLine();
			new Cliente(localhost, port, nombre, archivos, in).ClientRun();
			in.close();
			System.out.println("Se ha cerrado la aplicación");
		} catch (Exception e) {}
	}

}
