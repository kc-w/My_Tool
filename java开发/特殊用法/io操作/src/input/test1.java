package input;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
public class test1 {
//二进制流读取
	public static void main(String[] args) {
		FileInputStream fis=null;
		try {
			byte[] b=new byte[128];
			fis=new FileInputStream("C:\\Users\\output.txt");
			fis.read(b);
			String ret=new String();
			System.out.println(ret);
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			if(fis!=null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
