package input;

import java.io.BufferedReader;
import java.io.FileInputStream;//包装类
import java.io.InputStreamReader;//包装类
import java.io.FileNotFoundException;
import java.io.IOException;

public class test2 {
//文本流读取
	public static void main(String[] args) {
		BufferedReader br=null;
		try {
			//中文转码
			br=new BufferedReader(new InputStreamReader(new FileInputStream("C:\\Users\\copy.txt"),"GBK"));
			//按行读取文本流
			String line=null;
			while((line=br.readLine())!=null) {
				System.out.println(line);
			}
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}finally{
			if(br!=null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

}
