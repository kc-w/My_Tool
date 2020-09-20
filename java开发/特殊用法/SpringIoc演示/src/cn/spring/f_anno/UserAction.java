package cn.spring.f_anno;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


@Component("userAction")    // 加入IOC容器
@Scope("prototype")     // 作用域   指定加入IOC容器为多例
public class UserAction {

	
	private IUserService userService;
	// 接收IOC容器的注入
	@Resource(name = "userService")    //根据名称去容器找对象， 找到后注入到当前方法参数
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
