package tcp2;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoTCP2 {
	private ServerSocket srv;
	public static void main(String[] args) {
		new EchoTCP2();
	}

	public EchoTCP2() {
		try {
			srv = new ServerSocket(30000);
			System.out.println("Listening for new connections at "
					+ srv.getLocalSocketAddress());
			this.init();
		} catch (Exception e) {
			System.err.println("Error " + e.getMessage());
		}
	}

	private void init() throws IOException {
		Socket s = null;
		while ((s = srv.accept()) != null) {
			new Client(s).start();
		}
	}

	public class Client extends Thread {
		private Socket s;

		public Client(Socket s) {
			this.s = s;
		}

		public void run() {
			String threadName = Thread.currentThread().getName();

			try {
				System.out.println(threadName + "\tNew connection from " + s.getInetAddress().getHostAddress() + ":" + s.getPort());
				InputStream in = s.getInputStream();
				OutputStream out = s.getOutputStream();

				int read;
				StringBuilder sb = new StringBuilder();
				while ((read = in.read()) != -1) {
					if (read != 13) {
						sb.append((char) read);
					} else {
						String msg = sb.toString().trim();
						System.out.println(threadName + "\tRcv: '" + msg + "'");
						msg+="\r\n";
						out.write(msg.getBytes());
						out.flush();
						sb.setLength(0);
					}
				}
				System.out.println(threadName + "\tClient disconnected");
			} catch (Exception e) {
				System.err.println(threadName + "\tError in Client " + e.getMessage());
			}
		}
	}
}
