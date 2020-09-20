package read_writer_zip;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;//基础类
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;//压缩装饰类

public class write_zip {
//压缩流
	public static void main(String[] args) {
		String zip="111222333aaa是是是";
		ZipOutputStream zos=null;
		try {
			zos=new ZipOutputStream(new FileOutputStream("C:\\write.zip"));
			//压缩文件的基本信息
			ZipEntry ze=new ZipEntry("1.txt");
			//添加文件注解
			ze.setComment("my zip");
			//设置该文件的大小和zip字符串转换二进制数组长度相同
			ze.setSize(zip.getBytes().length);
			
			//将设置保存到压缩文件中
			zos.putNextEntry(ze);
			//通过二进制数组形式将zip字符串写入到1.txt文件中
			zos.write(zip.getBytes());
		} catch (FileNotFoundException e) {
			e.printStackTrace();	
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(zos!=null) {
				try {
					zos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

}
