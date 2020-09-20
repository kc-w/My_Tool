package cn.spring.b_create_obj;

public class User {

	private  int id;
	private String name;
	
	public User() {
		System.out.println("创建无参构造函数....");
	}
	
	public User(int id, String name){
		this.id = id;
		this.name = name;
		
		System.out.println("有参函数调用了   ....");
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
		return "-----------------"+id+name;
	}
	
	public void init() {
		System.out.println("初始化的时候调用...");
	}
	
	
	
	public void destroy() {
		System.out.println("销毁的生活嗲用...");
	}
	
	
	
}
