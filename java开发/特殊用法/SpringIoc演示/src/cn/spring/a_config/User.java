package cn.spring.a_config;

public class User {

	private  int id;
	private String name;
	
	public User() {
		System.out.println("创建无参构造函数....");
	}
	
	public User(int id, String name){
		this.id = id;
		this.name = name;
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
	
	
	
	public void init() {
		System.out.println("初始化的时候调用...");
	}
	
	
	
	public void destroy() {
		System.out.println("销毁的生活嗲用...");
	}
	
	
	
}
