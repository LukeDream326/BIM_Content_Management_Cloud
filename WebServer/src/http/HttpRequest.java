package http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class HttpRequest {
	private String method;
	private String uri;
	private String protocol;
	
	public HttpRequest(InputStream inputStream) {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
		try {
			String line = bufferedReader.readLine();
			if (line != null && line.length() > 0) {
				String[] data = line.split("\\s");
				method = data[0];
				uri = data[1];
				protocol = data[2];
				if (uri.equals("/")) {
					uri = "/index.html";
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String getMethod() {
		return method;
	}
	public String getUri() {
		return uri;
	}
	public String getProtocol() {
		return protocol;
	}
}
