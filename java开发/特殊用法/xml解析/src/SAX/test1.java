package SAX;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

public class test1 {
//将文档从上到下进行逐个解析,不能修改文档
	public static void main(String[] args) throws Exception {
		//获得SAX解析器的工厂实例,启用DTD验证文档的有效性
		SAXParserFactory factory=SAXParserFactory.newInstance();
		factory.setValidating(true);
		//创建SAX解析器对象
		SAXParser saxparser=factory.newSAXParser();
		
		//指定要解析的xml文档,并选择事件处理器创建对象
		saxparser.parse("C:\\Users\\kc-w\\Desktop\\java_test\\xml_test\\1.xml",new The_event_handler());

	}

}
