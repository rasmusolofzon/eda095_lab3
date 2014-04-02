package se.bproduction.labs.server;

import java.util.Vector;

public class MessagePush extends Thread {
	private Messages m;
	private Vector<Client> clients;
	public MessagePush(Messages m, Vector<Client> clients) {
		this.m = m;
		this.clients = clients;
	}
	
	public void run() {
		while(true) {
			String msg = m.get();
			for (Client c : clients) {
				c.send(msg);
			}
		}
	}
}
