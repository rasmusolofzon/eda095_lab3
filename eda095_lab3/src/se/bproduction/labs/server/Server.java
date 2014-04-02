package se.bproduction.labs.server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class Server {
	public static void main(String[] args) {
		new Server(3000);
	}
	
	private int port;
	private Vector<Client> clients;
	public Server(int port) {
		this.clients = new Vector<Client>();
		this.port = port;
		init();
	}
	
	private void init() {
		Messages m = new Messages();
		MessagePush push = new MessagePush(m,clients);
		push.start();
		try {
			ServerSocket srv = new ServerSocket(port);
			Socket s = null;
			while ((s=srv.accept())!=null) {
				System.out.println("New connection from " + s.getInetAddress().getHostName() + ":" + s.getPort());
				Client c = new Client(s,m,this);
				clients.add(c);
				c.start();
				System.out.println("Connected clients: " + clients.size());
			}
			srv.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public synchronized void removeClient(Client c) {
		clients.remove(c);
		System.out.println("Connected clients: " + clients.size());
	}
}
