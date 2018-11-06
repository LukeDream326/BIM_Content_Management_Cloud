package core;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;

import com.sun.jndi.toolkit.url.Uri;

import common.HttpContext;
import common.ServerContext;
import http.HttpRequest;
import http.HttpResponse;

public class ClientHandler implements Runnable {

	private Socket socket = null;
	public ClientHandler(Socket socket) {
		this.socket = socket;
	}
	
	public void run() {
		try {
			InputStream inputStream = socket.getInputStream();
			OutputStream outputStream = socket.getOutputStream();
			
			HttpRequest httpRequest = new HttpRequest(inputStream);
			
			if (httpRequest.getUri() != null) {
				
				File file = new File(ServerContext.webRoot + httpRequest.getUri());
				
				//创建httpResponse对象，用于封装Http响应信息
				
				HttpResponse httpResponse = new HttpResponse(outputStream);
				httpResponse.setProtocol(ServerContext.protocol);
//				httpResponse.setStatus(200);
//				httpResponse.setContentLength((int)file.length());
				httpResponse.setStatus(HttpContext.STATUS_CODE_OK);
				httpResponse.setContentType("text/html");
				httpResponse.setContentLength((int)file.length());
				
				PrintStream printStream = new PrintStream(httpResponse.getOutputSteam());
				
				BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
				byte[] buff = new byte[(int)file.length()];
				bufferedInputStream.read(buff);
				printStream.write(buff);
				printStream.flush();
				
				socket.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
