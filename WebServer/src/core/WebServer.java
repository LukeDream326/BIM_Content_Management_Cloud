package core;

import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class WebServer {
	private ServerSocket server;
	public WebServer() {
		try {
			server = new ServerSocket(8080);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("服务器启动失败");
		}
	}
	
	private void start() {
		try {
			while (true) {
				System.out.println("等待客户端连接");
				Socket socket = server.accept();
//				OutputStream out = socket.getOutputStream();
//				out.write("Hello Luke".getBytes());
//				out.flush();
				PrintStream printStream = new PrintStream(socket.getOutputStream());
				String data = "Hello Luke";
				printStream.println("HTTP/1.1 200 OK");
				printStream.println("Content-Type:text/html");
				printStream.println("Content-Length:"+data.length());
				printStream.println("");
				printStream.write(data.getBytes());
				printStream.flush();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		WebServer webServer = new WebServer();
		webServer.start();
	}
}
