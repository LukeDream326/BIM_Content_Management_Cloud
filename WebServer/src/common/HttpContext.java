package common;

public class HttpContext {
	//服务器成功处理请求
	public static final int STATUS_CODE_OK = 200;
	public static final String STATUS_REASON_OK = "OK";
	//客户端请求的资源不存在	
	public static final int STATUS_CODE_NOT_FOUND = 404;
	public static final String STATUS_REASON_NOT_FOUND = "NOT FOUND";
	//服务器内部错误
	public static final int STATUS_CODE_ERROR = 500;
	public static final String STATUS_REASON_ERROR = "Internal Server ERROR";
}
