package DOM4j;

import java.io.File;
import java.io.FileOutputStream;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.XMLWriter;

public class test2 {
//创建xml文档
	public static void main(String[] args) throws Exception{
		//创建文档对象
		Document doc=DocumentHelper.createDocument();
		//创建根元素
		Element root=doc.addElement("books");
		
		//创建子元素
		Element book=root.addElement("book");
		//创建子子元素
		Element name=book.addElement("name");
		Element price=book.addElement("price");
		
		//创建属性(属性名,属性值)
		book.addAttribute("ISBN","233");
		//设置文本节点
		name.setText("ajax");
		price.setText("666");
		
		//写入xml文件
		XMLWriter writer=new XMLWriter(new FileOutputStream(new File("C:\\Users\\2.xml")));
		writer.write(doc);
		writer.close();

	}

}
