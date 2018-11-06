package common;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class ServerContext {
	public static String protocol;
	public static int port;
	public static int maxThread;
	public static String webRoot;
	static {
		init();
	}
	private static void init() {
		// TODO Auto-generated method stub
		SAXReader saxReader = new SAXReader();
		try {
			Document document = saxReader.read("config/server.xml");
			Element rootelement = document.getRootElement();
			Element connelement = rootelement.element("service").element("connector");
			protocol = connelement.attributeValue("protocol");
			port = Integer.parseInt(connelement.attributeValue("port"));
			maxThread = Integer.parseInt(connelement.attributeValue("maxThread"));
			
			webRoot = rootelement.element("service").element("webroot");
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
