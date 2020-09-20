package Dao.interfaces;

import java.util.List;

import Bean.merchant.merchant;
import Bean.merchant_sp.merchant_sp;
import Bean.merchant_sp_list_users.merchant_sp_list_users;
import Bean.users.users;

//接口
public interface usersDao_interface {

	
	//注册用户
	public boolean add(users user) throws Exception;
	
	
	//用户登陆验证
	public users login(users user)throws Exception;
	
	
	//账号是否存在
	public users selectN(String Unumber)throws Exception;
	
	//邮箱是否存在
	public boolean selectE(String Uemail)throws Exception;
	
	//手机是否存在
	public boolean selectT(String Utelephone)throws Exception;
	
	//修改用户信息
	public boolean change(users user)throws Exception;
	
	
	//查询商家
	public List<merchant> selectS(String Sname)throws Exception;
	
	
	//查询商品
	public List<merchant_sp> selectP(String Pname)throws Exception;
	
	
	//查询所有订单
	public List<merchant_sp_list_users> selectO(String Unumber)throws Exception;
	
	

}
