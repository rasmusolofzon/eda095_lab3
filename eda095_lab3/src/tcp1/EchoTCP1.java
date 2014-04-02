package tcp1;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoTCP1 {
	private ServerSocket srv;
	public static void main(String[] args) {
		new EchoTCP1();
	}
	
	public EchoTCP1() {
		try {
			srv = new ServerSocket(30000);
			System.out.println("Listening for new connections at " + srv.getLocalSocketAddress());
			this.init();
		}catch(Exception e) {
			System.err.println("Error " + e.getMessage());
		}
	}
	
	private void init() throws IOException {
		Socket s = null;
		while((s=srv.accept())!=null) {
			System.out.println("New connection from " + s.getRemoteSocketAddress());
			InputStream in = s.getInputStream();
			OutputStream out = s.getOutputStream();
			
			int read;
			StringBuilder sb = new StringBuilder();
			while ((read=in.read())!=-1) {
				if (read!=13) {
					sb.append((char) read);
				}else{
					String msg = sb.toString().trim();
					System.out.println("Rcv: '"+msg+"'");
					out.write(msg.getBytes());
					out.flush();
					sb.setLength(0);
				}
			}
			System.out.println("Client disconnected");
		}
	}
}
