package cn.spring.f_anno;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;



//@Component("userService")    业务逻辑型的组件
@Service("userService")
public class UserService implements IUserService {

	
	
	private IUserDao userDao;
	
	//接收IOC容器的注入    通过set方法
	@Resource(name = "userDao")    //根据名称去容器找对象， 找到后注入到当前方法参数
	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public void save() {
		userDao.save();
	}
	
	
	
	
}
