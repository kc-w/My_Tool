package File_mapping;

import java.io.IOException;
import java.nio.ByteBuffer;
//内存映射
public class map1 {

	public static void main(String[] args)throws IOException {
		//创建一个10字节的二进制内存缓存
		ByteBuffer bb=ByteBuffer.allocate(10);
		
		System.out.print("指针位置="+bb.position());
		System.out.print("最大界限="+bb.limit());
		System.out.println("容量="+bb.capacity());
		
		bb.put((byte)'h');
		
		System.out.print("指针位置="+bb.position());
		System.out.print("最大界限="+bb.limit());
		System.out.println("容量="+bb.capacity());
		
		bb.put((byte)'q');
		bb.put((byte)'1');
		bb.put((byte)'2');
		
		bb.position(6);//将指针指向第6个位置,然后进行存储进内存
		
		bb.put((byte)'a');
		
		System.out.print("指针位置="+bb.position());
		System.out.print("最大界限="+bb.limit());
		System.out.println("容量="+bb.capacity());
		
		//重新将指针指向0位置,然后进行读取内存数据
		bb.position(0);
		byte[] ret=new byte[10];
		bb.get(ret);
		
		for(byte b:ret) {
			System.out.print(b+"\t");
		}
		
		System.out.println();
		
		//rewind()方法自动将指针指向0,读取插入数据的个数
		bb.rewind();
		for(int i=0;i<bb.remaining();i++) {
			System.out.print(bb.get()+"\t");
		}

	}

}
