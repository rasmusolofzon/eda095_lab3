package concurrentunsync;

public class Start {
	
	public static void main(String[] args) {
		MailBox m = new MailBox();
		for (int i = 0;i<10;i++) {
			new NameWriter("TH"+i,m).start();
		}
		
		new NameReader(m).start();
	}
}
