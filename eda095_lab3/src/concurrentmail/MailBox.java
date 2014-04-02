package concurrentmail;

public class MailBox {
	private String s;
	public MailBox() {
		this.s = "";
	}

	public synchronized void setString(String s) {
		while (!this.s.equals("")) {
			try {
				wait();
			}catch(Exception e) {
				System.err.println("MB\tsetString() " + e.getMessage());
			}
		}
		this.s = s;
		notifyAll();
	}

	public synchronized String getString() {
		while(s.equals("")) {
			try {
				wait();
			}catch(Exception e) {
				System.err.println("MB\tgetString() " + e.getMessage());
			}
		}
		String rVal = this.s;
		this.s = "";
		notifyAll();
		return rVal;
	}
}
