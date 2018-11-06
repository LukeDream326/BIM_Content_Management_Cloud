package core;

import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import common.ServerContext;

public class WebServer {
	private ServerSocket server;
	private ExecutorService threadPool;
	public WebServer() {
		try {
			server = new ServerSocket(ServerContext.port);
			threadPool = Executors.newFixedThreadPool(ServerContext.maxThread);
			
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
				/*
				PrintStream printStream = new PrintStream(socket.getOutputStream());
				String data = "Hello Luke";
				printStream.println("HTTP/1.1 200 OK");
				printStream.println("Content-Type:text/html");
				printStream.println("Content-Length:"+data.length());
				printStream.println("");
				printStream.write(data.getBytes());
				printStream.flush();
				*/
				threadPool.execute(new ClientHandler(socket));
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
