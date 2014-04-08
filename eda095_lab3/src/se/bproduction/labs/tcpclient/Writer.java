package se.bproduction.labs.tcpclient;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Writer extends Thread {
	private Socket s;
	private OutputStream out;
	private PrintWriter pw;
	public Writer(Socket s) {
		this.s = s;
		try {
			out = s.getOutputStream();
			pw = new PrintWriter(out);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		try {
			Scanner scan = new Scanner(System.in);
			while(!s.isClosed()) {
				String s = scan.nextLine();
				pw.println(s);
				pw.flush();
			}
			scan.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
