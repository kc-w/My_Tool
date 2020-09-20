package readfile_and_copyfile;
import java.io.BufferedInputStream;
import java.io.FileOutputStream;//写入文件类
import java.io.FileInputStream;//读取文件类
import java.io.FileNotFoundException;
import java.io.IOException;
public class Test1 {

	public static void main(String[] args) {
		BufferedInputStream bis=null;
		FileOutputStream ops=null;
		try {
			//获取文件所在位置
			bis=new BufferedInputStream(new FileInputStream("C:\\Users\\read.txt"));
			//设置所要写入文件的位置
			ops=new FileOutputStream("C:\\Users\\copy.txt");
			byte[] b =new byte[1024];//创建一个字节数组用来存储读取的值
			/*
			 该方法可判断文件的大小,而不需要特意指定数组长度
			 byte[] b=new byte[bis.available()];
			*/
			while(true) {
				//read(b)方法:读取1024字节,存储1024字节,直到全部存完
				int length=bis.read(b);
				if(length==-1) {
					break;
				}
				//写入文件
				ops.write(b,0,length);
			}
			//输出到控制台,trim()方法:将尾部用不到的空格去掉
			String ret=new String(b);
			System.out.println(ret.trim());
			
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			if(bis!=null) {
				try {
					bis.close();
				}catch(IOException e) {
					e.printStackTrace();
				}
			}
			if(ops!=null) {
				try {
					ops.close();
				}catch(IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

}
