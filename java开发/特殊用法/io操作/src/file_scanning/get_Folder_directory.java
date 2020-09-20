package file_scanning;

import java.io.File;
import java.io.FileFilter;

public class get_Folder_directory {
//获得指定文件夹下的子文件和文件夹
	public static void main(String[] args) {
		File dir=new File("c:\\");
		File[] files=dir.listFiles(new BinFilter());
		for(File file:files) {
			System.out.println(file);
		}
	}
//过滤以Bin为后缀的文件
	static  class BinFilter implements FileFilter{
	public boolean accept(File arg0) {
		if(arg0.getName().endsWith(".Bin")) {
			return false;
		}
		return true;
	}	
	}

}
