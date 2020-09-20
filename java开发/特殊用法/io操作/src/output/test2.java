package output;

import java.io.FileWriter;//基础的文本写入流
import java.io.PrintWriter;//文本过滤装饰流
import java.io.IOException;

public class test2 {
//文本写入流
	public static void main(String[] args) {
		PrintWriter pw=null;
		try {
			pw=new PrintWriter(new FileWriter("C:\\Users\\output.txt"));
			pw.println("1122aa哼哼哈");
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			if(pw!=null) {
				
			   pw.close();
			}
		}

	}

}
