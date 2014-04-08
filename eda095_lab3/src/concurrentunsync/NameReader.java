package concurrentunsync;

public class NameReader extends Thread {
	private MailBox m;
	public NameReader(MailBox m) {
		this.m = m;
	}
	
	public void run() {
		while(true) {
			try {
				double r = Math.random()*1000;
				Thread.sleep((long) r);
			}catch(Exception e) {
				System.err.println(getName() + "\tErr " + e.getMessage());
			}
			System.out.println(m.getString());
		}
	}
}
