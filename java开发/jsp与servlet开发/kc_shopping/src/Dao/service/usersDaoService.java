package Dao.service;

import java.util.ArrayList;
import java.util.List;

import Bean.merchant.merchant;
import Bean.merchant_sp.merchant_sp;
import Bean.merchant_sp_list_users.merchant_sp_list_users;
import Bean.users.users;
import Dao.impl.usersDaoImpl;
import Dao.interfaces.usersDao_interface;
import Dao.jdbc.DBconnection;

//业务操作,实现数据库开关
public class usersDaoService implements usersDao_interface {
	
	private DBconnection conn=null;
	private usersDao_interface dao=null;
	
	public usersDaoService() throws Exception {
		this.conn=new DBconnection();
		this.dao=new usersDaoImpl(this.conn.getdb());
	}
	
	//注册用户
	public boolean add(users user) throws Exception {
		boolean mark=false;
		users users=dao.selectN(user.getUnumber());
		try {
			if(users.getUname()==null&&!dao.selectE(user.getUemail())&&!dao.selectT(user.getUtelephone())) {
				mark=dao.add(user);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(conn!=null) {
				conn.close();
			}
		}
		return mark;
	}

	//用户登陆验证
	public users login(users user) throws Exception {
		users users=null;
		
		try {
			users=dao.login(user);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			conn.close();
		}
		return users;
		
	}
	
	//账号是否存在
	public users selectN(String Unumber) throws Exception {
		users users=new users();
		try {
			users=dao.selectN(Unumber);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			conn.close();
		}
		return users;
	}
	
	//邮箱是否存在
	public boolean selectE(String Uemail) throws Exception {
		boolean mark=false;
		try {
			mark=dao.selectE(Uemail);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			conn.close();
		}
		return mark;
	}

	//手机是否存在
	public boolean selectT(String Utelephone) throws Exception {
		boolean mark=false;
		try {
			mark=dao.selectT(Utelephone);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			conn.close();
		}
		return mark;
	}
	
	//用户修改信息
	public boolean change(users user) throws Exception {
		
		boolean mark=false;
		try {
			mark=dao.change(user);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			conn.close();
		}
		return mark;
	}

	//查询商家
	public List<merchant> selectS(String Sname) throws Exception {
		List<merchant> merchant=new ArrayList<merchant>();
		
		try {
			merchant=dao.selectS(Sname);
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			conn.close();
		}
		return merchant;
	}

	//查询商品
	public List<merchant_sp> selectP(String Pname) throws Exception {
		List<merchant_sp> merchant_sp=new ArrayList<merchant_sp>();
		
		try {
			merchant_sp=dao.selectP(Pname);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			conn.close();
		}
		return merchant_sp;
	}

	//查询订单
	public List<merchant_sp_list_users> selectO(String Unumber) throws Exception {
		List<merchant_sp_list_users> merchant_sp_list_users=new ArrayList<merchant_sp_list_users>();
		
		try {
			merchant_sp_list_users=dao.selectO(Unumber);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			conn.close();
		}
		return merchant_sp_list_users;
	}


	

	
	
	
	
}
