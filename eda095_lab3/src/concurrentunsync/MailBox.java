package concurrentunsync;

public class MailBox {
	private String s;

	public void setString(String s) {
		this.s = s;
	}

	public String getString() {
		String rVal = this.s;
		this.s = "";
		return rVal;
	}
}
