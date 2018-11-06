package http;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

import common.HttpContext;



public class HttpResponse {
	private String protocol;
	private int status;
	private String contentType;
	private int contentLength;
	private boolean hasPrintHeader = false;
	private Map<Integer, String> statusMap;
	private OutputStream outputStream;
	/*
	 * 构造函数用于接受指向客户端的输出流
	 */
	
	public HttpResponse(OutputStream outputStream) {
		this.outputStream  = outputStream;
		
		statusMap = new HashMap<Integer, String>();
		statusMap.put(HttpContext.STATUS_CODE_OK, HttpContext.STATUS_REASON_OK);
		statusMap.put(HttpContext.STATUS_CODE_NOT_FOUND, HttpContext.STATUS_REASON_NOT_FOUND);
		statusMap.put(HttpContext.STATUS_CODE_ERROR, HttpContext.STATUS_REASON_ERROR);
	}
	public OutputStream getOutputSteam() {
		if (!hasPrintHeader) {
			PrintStream printStream = new PrintStream(this.outputStream);
			printStream.println(protocol + " " + status + " " + statusMap.get(status));
			printStream.println("Content-Type:" + contentType);
			printStream.println("Content-Length:" + contentLength);
			printStream.println("");
			hasPrintHeader = true;
		}
		return outputStream;
	}
	public String getProtocol() {
		return protocol;
	}
	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public int getContentLength() {
		return contentLength;
	}
	public void setContentLength(int contentLength) {
		this.contentLength = contentLength;
	}
}
