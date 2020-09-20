package cn.spring.e_autowire;

import java.sql.Savepoint;

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
