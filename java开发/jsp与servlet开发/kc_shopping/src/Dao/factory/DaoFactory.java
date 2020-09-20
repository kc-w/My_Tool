package Dao.factory;


import Dao.service.usersDaoService;
import Dao.interfaces.adminDao_interface;
import Dao.interfaces.merchantDao_interface;
import Dao.interfaces.usersDao_interface;
import Dao.service.adminDaoService;
import Dao.service.merchantDaoService;
//工厂类
public class DaoFactory {
	public static usersDao_interface getUsersDao() throws Exception{
		return new usersDaoService();
		
	}
	public static merchantDao_interface getmerchantDao() throws Exception {
		return new merchantDaoService();
	}
	
	public static adminDao_interface getadminDao() throws Exception{
		return new adminDaoService();
	}
}

