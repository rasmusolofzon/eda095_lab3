package concurrentunsync;

public class NameWriter extends Thread {
	private MailBox m;
	public NameWriter(String n, MailBox m) {
		super(n);
		this.m = m;
	}
	
	public void run() {
		for (int i = 0;i<5;i++) {
			try {
				double r = Math.random()*1000;
				Thread.sleep((long) r);
			}catch(Exception e) {
				System.err.println(this.getName() +  "\t Err " + e.getMessage());
			}
			m.setString(getName());
		}
	}
}
