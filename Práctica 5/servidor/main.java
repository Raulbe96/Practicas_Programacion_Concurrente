package servidor;

public class main {
	
	public static void main(String[] args) {
		try {
			
			int port = 555;
			new Server(port).ServerRun();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Problemas al empezar");
		}
		
	}

}
