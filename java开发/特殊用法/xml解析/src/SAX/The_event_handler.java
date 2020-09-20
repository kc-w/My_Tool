package SAX;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
//定义事件解析器,当xml文档从上到下进行逐个解析时触发相应事件
public class The_event_handler extends DefaultHandler {
	//保存当前元素
	private String currentTag=null;
	
	public void startDocument()throws SAXException{
		System.out.println("===开始解析文档===");
	}
    public void endDocument()throws SAXException{
    	System.out.println("===解析文档结束===");
	}
    //(qName:元素名称,attr:属性节点对象)
    public void startElement(String uri,String localName,String qName,Attributes attr)throws SAXException{
    	System.out.println("开始解析元素:"+qName);
    	currentTag=qName;
    	if(qName.equals("book")) {
    		for(int i=0;i<attr.getLength();i++) {
    			System.out.println("属性名:"+attr.getQName(i)+"===>属性值:"+attr.getValue(i));
    		}
    	}
	}
    public void endElement(String uri,String localName,String qName)throws SAXException{
    	System.out.println("处理元素结束:"+qName);
	}
    public void characters(char[] ch,int start,int length)throws SAXException{
		String content=new String(ch,start,length);
		if(content.trim().length()>0) {
			System.out.println("<"+currentTag+">元素的文本值为:"+content.trim());
		}
	}


}
