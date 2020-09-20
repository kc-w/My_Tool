package read_writer_zip;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.zip.ZipInputStream;

public class read_zip {

	public static void main(String[] args) {
		ZipInputStream zis=null;
		try {
			byte[] b=new byte[1024];
			zis=new ZipInputStream(new FileInputStream("C:\\write.zip"));
			//按顺序从压缩文件中获取被压缩的内部文件
			zis.getNextEntry();
			zis.read(b);
			zis.closeEntry();
			
			String ret=new String(b);
			System.out.println(ret);
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			if(zis!=null) {
				try {
					zis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

}
