package File_mapping;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;

public class map2 {
//将文件映射入内存
	public static void main(String[] args) throws FileNotFoundException,IOException {
		//创建流对象
		RandomAccessFile raf=
				new RandomAccessFile("C:\\Users\\copy.txt","rw");
		//使物理内存和应用内存建立通道
		FileChannel fc=raf.getChannel();
		
		MappedByteBuffer mbb=
//map()方法与磁盘建立映射关系,(映射模式为读写模式,从0位置开始,将整个文件映射进内存)
				fc.map(MapMode.READ_WRITE,0, fc.size());
		
		mbb.position(33);
		byte[] io=new byte[99];
		mbb.get(io);
		System.out.println(new String(io));
		fc.close();
		raf.close();

	}

}
