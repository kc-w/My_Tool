package output;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;//写入文件类
import java.io.IOException;
public class test1 {
//二进制流写入
	public static void main(String[] args){
		FileOutputStream ops=null;
		try {
		ops=new FileOutputStream("C:\\Users\\output.txt");
		//getBytes():字符串不可以直接用于输出,需要转换为比特数组
		ops.write("哈哈哈".getBytes());
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			try {
				//将保存在内存中的文件写入到磁盘,并且消除对象
				ops.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
