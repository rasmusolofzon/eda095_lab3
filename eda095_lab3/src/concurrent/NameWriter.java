package concurrent;

public class NameWriter extends Thread {
	public NameWriter(String n) {
		super(n);
	}
	
	public void run() {
		for (int i = 0;i<5;i++) {
			
			try {
				double r = Math.random()*1000;
				Thread.sleep((long) r);
			}catch(Exception e) {
				e.printStackTrace();
			}
			System.out.println(this.getName());
		}
	}
}
