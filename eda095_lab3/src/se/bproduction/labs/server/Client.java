package se.bproduction.labs.server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class Client extends Thread {
	private Socket s;
	private Messages m;
	private OutputStream out;
	private Server srv;
	private String name;
	public Client(Socket s, Messages m, Server srv) {
		this.s = s;
		this.m = m;
		this.srv = srv;
		try {
			this.out = s.getOutputStream();
		}catch(Exception e) {
			e.printStackTrace();
		}
		this.name = ""+s.getPort();
	}
	
	public String getClientName() {
		return this.name;
	}
	
	public void run() {
		m.add(name + " has connected!");
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
			String incoming = null;
			while ((incoming=br.readLine())!=null) {
				if (incoming.toUpperCase().startsWith("E:")) {
					send("ECHO: " + incoming);
				}else if (incoming.toUpperCase().startsWith("Q:")) {
					send("Goodbye!");
					break;
//				}else if (incoming.startsWith("M:")) { //Removed this in order to test a bit easier!
//					String message = name + ": " + incoming.substring(2, incoming.length());
//					m.add(message);
				}else {
					String message = name + ": " + incoming;
					m.add(message);
				}
			}
			s.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		srv.removeClient(this);
		m.add(name + " has disconnected");
	}
	
	public void send(String msg) {
		try {
			msg+="\r\n";
			out.write(msg.getBytes());
			out.flush();
		}catch(Exception e) {
			e.printStackTrace();
			srv.removeClient(this);
		}
	}
}
