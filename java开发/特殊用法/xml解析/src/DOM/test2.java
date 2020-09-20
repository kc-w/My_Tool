package DOM;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class test2 {
//修改xml文档
	//"无法添加新节点"
	public static void main(String[] args) throws Exception{
		//获取DOM解析工厂实例
				DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
				//使用工厂实例创建DOM解析器
				DocumentBuilder dom=factory.newDocumentBuilder();
				//解析指定的xml文件,获取文档树对象加载入内存
				Document doc=dom.parse(new File("C:\\Users\\kc-w\\Desktop\\java_test\\xml_test\\1.xml"));
				
				//获取文档树的根节点
				Element root=doc.getDocumentElement();
				
				//创建一个根节点下的子元素节点newbook
				Element newchildnode=doc.createElement("newbook");
				//将该节点放到根节点books下的
				//doc.getElementsByTagName("books").item(0).appendChild(newchildnode);

	}

}
