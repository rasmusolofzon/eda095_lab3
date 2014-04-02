package se.bproduction.labs.tcpclient;

import java.net.Socket;

public class ChatClient {
	public static void main(String[] args) {
		if (args.length!=2) {
			System.out.println("Usage: java ChatClient <host> <port>");
			System.exit(-1);
		}
		
		String host = args[0];
		int port = Integer.parseInt(args[1]);
		new ChatClient(host,port);
	}
	
	public ChatClient(String host, int port) {
		try {
			Socket s = new Socket(host,port);
			Listener l = new Listener(s);
			Writer w = new Writer(s);
			
			l.start();
			w.start();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
