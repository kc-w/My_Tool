package cn.spring.e_autowire;

public class UserService implements IUserService {

	
	
	private IUserDao userDao;
	
	//接收IOC容器的注入    通过set方法
	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public void save() {
		userDao.save();
	}
	
	
	
	
}
