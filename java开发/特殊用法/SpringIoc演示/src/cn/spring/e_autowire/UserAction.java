package cn.spring.e_autowire;

public class UserAction {

	
	private IUserService userService;
	// 接收IOC容器的注入
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
	
	
	public void test() {
		System.out.println("这是一个UserAction的测试方法");
		
	}
	
	public String execute(){
		userService.save();
		return "success";
	}
	
}
