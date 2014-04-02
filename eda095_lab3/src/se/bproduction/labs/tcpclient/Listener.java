package se.bproduction.labs.tcpclient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

public class Listener extends Thread {
	private Socket s;
	private BufferedReader br;
	public Listener(Socket s) {
		this.s = s;
		try {
			this.br = new BufferedReader(new InputStreamReader(s.getInputStream()));
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void run() {
		try {
			String m = null;
			while((m=br.readLine())!=null) {
				System.out.println(m);
			}
			s.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
