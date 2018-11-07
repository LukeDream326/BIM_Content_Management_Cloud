package common;

import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class ServerContext {
	public static String protocol;
	public static int port;
	public static int maxThread;
	public static String webRoot;
	public static Map<String, String> types;
	
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
			
			webRoot = rootelement.element("service").element("webroot").getText();
			
			List<Element> list = rootelement.element("type-mappings").elements();
			
			for (Element element : list) {
				types.put(element.attributeValue("ext"), element.attributeValue("type"));
			}
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
