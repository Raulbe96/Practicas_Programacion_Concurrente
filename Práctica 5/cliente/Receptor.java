package cliente;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class Receptor extends Thread {
	private String archivo;
	private int puerto;
	private InetAddress dir;
	
	public Receptor(String archivo, int port, InetAddress ip) {
		this.archivo = archivo;
		puerto = port;
		dir = ip;
	}
	
	public void run() {
		try{
			Socket receptor = new Socket(dir, puerto);
			receptor.setSoLinger(true,  10);
			byte[] receivedData = new byte[1024];
			BufferedInputStream coger = new 
					BufferedInputStream(receptor.getInputStream());
			DataInputStream din = new
					DataInputStream(receptor.getInputStream());
			BufferedOutputStream bout = new
					BufferedOutputStream(new FileOutputStream(archivo));
			int i;
			while((i = coger.read(receivedData))!= -1){
				bout.write(receivedData,0,i);
			}
			din.close();
			bout.close();
			coger.close();
			receptor.close();
		}catch(Exception e){}
		
	}
}
