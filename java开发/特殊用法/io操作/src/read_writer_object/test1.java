package read_writer_object;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class test1 {
	public static void main(String[] args)throws Exception {
		Student stu=new Student("frank",10);
		////将对象从内存中序列化保存到磁盘文件中
		ObjectOutputStream oos=
				new ObjectOutputStream(new FileOutputStream("C:\\Users\\output.data"));
		oos.writeObject(stu);
		oos.close();
		//从文件将对象反序列化到内存,必须要有与之对应的类型
		ObjectInputStream ois=
				new ObjectInputStream(new FileInputStream("C:\\Users\\output.data"));
		stu=(Student)ois.readObject();
		ois.close();
		stu.show();

	}

}
//如果要实现对象反序列化必须实现java.io.Serializable接口
class Student implements java.io.Serializable{
	private String name=null;
	//transient关键字可阻止age序列化到磁盘文件
	private transient Integer age=null;
	public Student(String name,int age) {
		this.name=name;	
		this.age=age;
	}
	public void setName(String name) {
		this.name=name;
	}
	public void setAge(Integer age) {
		this.age=age;
	}
	public void show() {
		System.out.println(this.name);
		System.out.println(this.age);
	}
}
