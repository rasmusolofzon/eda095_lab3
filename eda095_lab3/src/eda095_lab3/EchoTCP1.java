package eda095_lab3;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoTCP1 {
	ServerSocket ss;
	
	public EchoTCP1() {
		try {
			ss = new ServerSocket(30000);
			acceptClients();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new EchoTCP1();
	}
	
	public void acceptClients() throws IOException {
		Socket socka = null;
		
		while ((socka = ss.accept()) != null) {
			System.out.println(socka.getInetAddress());
			
			InputStream in = socka.getInputStream();
			OutputStream out = socka.getOutputStream();
			
			int read;
			StringBuilder sb = new StringBuilder();
			
			while ((read = in.read()) != -1) {
				if (read != 13) {
					sb.append(read);
				} else {
					System.out.println(sb.toString());
					out.write(sb.toString().getBytes());
					out.flush();
					sb.setLength(0);
				}
			}
		}
		
	}
}
