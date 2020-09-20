package cn.spring.c_di;

public class User {

	private int  id;
	private String name;
	
	public User() {
		 System.out.println("User 无参构造方法");
	}
	
	public User(int id , String  name) {
		this.id = id;
		this.name = name;
		
		 System.out.println("User 带参构造方法");
	}
	
	
	
	public static void test(){
		
		System.out.println("test方法啦啦啦.../");
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		
		return id+name +"---------------";	
		
	}
	
	
	
}
