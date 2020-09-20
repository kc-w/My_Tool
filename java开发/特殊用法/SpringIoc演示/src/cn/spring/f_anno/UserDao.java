package cn.spring.f_anno;

import java.sql.Savepoint;

import org.springframework.stereotype.Component;


//把 userDao对象加入IOC容器
@Component("userDao")    //  相当于  <bean id = "userDao" class=""></bean>
public class UserDao implements IUserDao {

	//保存对象
	/* (non-Javadoc)
	 * @see cn.spring.c_di2.IUserDao#save()
	 */
	@Override
	public void save(){
		System.out.println("UserDao  save（）保存对象");
	}
	
	
}
