package se.bproduction.labs.server;

import java.util.LinkedList;

public class Messages extends LinkedList<String> {
	private static final long serialVersionUID = 1L;
	private static final int BUFFER = 10;
	public synchronized String get() {
		while (this.size()==0) {
			try {
				wait();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		notifyAll();
		return super.removeFirst();
	}
	
	public synchronized boolean add(String msg) {
		while(this.size()==BUFFER) {
			try {
				wait();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		notifyAll();
		return super.add(msg);
	}
}
