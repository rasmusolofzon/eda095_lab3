package eda095_lab3;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoTCP1 {
	ServerSocket ss;
	
	public EchoTCP1() {
		try {
			ss = new ServerSocket(30000);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void acceptClients() throws IOException {
		Socket socka = null;
		
		while ((socka = ss.accept()) != null) {
			
		}
		
	}
}
