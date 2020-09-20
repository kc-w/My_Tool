package DOM;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
public class test1 {
//文档树对象加载入内存,解析xml文档
	public static void main(String[] args)throws Exception {
		//获取DOM解析工厂实例
		DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
		//使用工厂实例创建DOM解析器
		DocumentBuilder dom=factory.newDocumentBuilder();
		//解析指定的xml文件,获取文档树对象加载入内存
		Document doc=dom.parse(new File("C:\\Users\\kc-w\\Desktop\\java_test\\xml_test\\1.xml"));
		
		//获取文档树的根节点
		Element root=doc.getDocumentElement();
		System.out.println("根节点名:"+root.getNodeName());
		
		//获取所有的子节点列表,并进行遍历
		NodeList books=root.getChildNodes();
		for(int i=0;i<books.getLength();i++) {
			Node book=books.item(i);
			//判断节点是否为元素节点
			if(book.getNodeType()==Node.ELEMENT_NODE) {
				//打印元素节点的名称
				System.out.println("根节点下的子节点名:"+book.getNodeName());
				
				//获取该元素节点的属性值
				String isbn=book.getAttributes().getNamedItem("ISBN").getNodeValue();
				System.out.println("该子节点的ISBN的属性值:"+isbn);
				
				//再次遍历该节点内还存在的子节点(得到该子节点下的第一个元素节点;判断是否为空;转到下一个元素节点)
				for(Node node=book.getFirstChild();node!=null;node=node.getNextSibling()) {
					if(node.getNodeType()==Node.ELEMENT_NODE) {
						System.out.println("\t子子节点名:"+node.getNodeName()+" 值:"+node.getFirstChild().getNodeValue());
					}
				}
				
				
			}
		}
		

	}

}
