package DOM4j;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
//解析xml文档
public class test1 {
	public static void parseElement(Element root) {
		//处理属性方法
		parseAttribute(root);
		//得到元素列表
		List element=root.elements();
		for(Object obj:element) {
			Element e=(Element)obj;
			System.out.println("元素名称:"+e.getName());
			//判断该元素是否有文本节点
			if(e.isTextOnly()) {
				parseAttribute(e);
				System.out.println(e.getQName().getName()+"元素的文本内容===>"+e.getText());
			}else {
				parseElement(e);

			}
		}
	}

    //处理元素的属性
	private static void parseAttribute(Element e) {
		//获得该元素的属性列表
		List attlist=e.attributes();
		Iterator iter=attlist.iterator();
		while(iter.hasNext()) {
			Attribute attr=(Attribute)iter.next();
			System.out.println(e.getQName().getName()
					+"元素的"+attr.getQName().getName()
					+"属性值为:"+attr.getValue());
		}
		
	}

	public static void main(String[] args) throws Exception {
		//创建一个SAX解析器
		SAXReader reader=new SAXReader();
		//读取指定的xml文档,获取Document文档对象
		Document doc=reader.read(new File("C:\\Users\\2.xml"));
		
		//获得文档的根元素
		Element root=doc.getRootElement();
		System.out.println("根节点名称:"+root.getName());
		//递归解析各个子元素
		parseElement(root);

	}

}
