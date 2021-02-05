package cliente;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Emisor extends Thread {
	private String archivo;
	private int puerto;

	public Emisor(String ar, int p) {
		this.archivo = ar;
		this.puerto = p;
	}

	public void run() {
		try {
			final File localFile = new File(archivo);
			
			ServerSocket emisor= new ServerSocket(puerto);
			Socket receptor = emisor.accept();
			BufferedInputStream bin = new
					BufferedInputStream(new FileInputStream(localFile));
			BufferedOutputStream bout = new
					BufferedOutputStream(receptor.getOutputStream());
			byte[] byteArray = new byte[8192];
			int i;
			while((i = bin.read(byteArray))!=-1) {
				bout.write(byteArray,0,i);
			}
			bin.close();
			bout.close();
			receptor.close();
			emisor.close();
			
		} catch (Exception e) {
		}

	}
}
