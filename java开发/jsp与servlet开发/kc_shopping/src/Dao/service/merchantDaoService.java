package Dao.service;

import java.util.ArrayList;
import java.util.List;

import Bean.merchant.merchant;
import Bean.merchant_sp_list_users.merchant_sp_list_users;
import Bean.sp.goods;
import Dao.impl.merchantDaoImpl;
import Dao.interfaces.merchantDao_interface;
import Dao.jdbc.DBconnection;

//业务操作,实现数据库开关
public class merchantDaoService implements merchantDao_interface {
	
	private DBconnection conn=null;
	private merchantDao_interface dao=null;
	
	public merchantDaoService() throws Exception {
		this.conn=new DBconnection();
		this.dao=new merchantDaoImpl(this.conn.getdb());
	}

	//注册商家
	public boolean add(merchant merchant) throws Exception {
		merchant merchant1=dao.selectN(merchant.getSnumber());
		boolean mark=false;
		try {
			if(merchant1.getSname()==null&&!dao.selectE(merchant.getSemail())&&!dao.selectT(merchant.getStelephone())) {
				mark=dao.add(merchant);
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

	//商家登陆验证
	public merchant login(merchant merchant) throws Exception {
		merchant merchant1=new merchant();
		try {
			merchant1=dao.login(merchant);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			conn.close();
		}
		return merchant1;
	}

	//商家账号是否存在
	public merchant selectN(String Snumber) throws Exception {
		merchant merchant1=new merchant();
		try {
			merchant1=dao.selectN(Snumber);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			conn.close();
		}
		return merchant1;

	}

	//商家邮箱是否存在
	public boolean selectE(String Semail) throws Exception {
		boolean mark=false;
		try {
			mark=dao.selectE(Semail);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			conn.close();
		}
		return mark;
	}

	//商家电话是否存在
	public boolean selectT(String Stelephone) throws Exception {
		boolean mark=false;
		try {
			mark=dao.selectT(Stelephone);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			conn.close();
		}
		return mark;
	}
	
	//修改商家信息
	public boolean change(merchant merchant) throws Exception {
		boolean mark=false;
		try {
			mark=dao.change(merchant);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			conn.close();
		}
		return mark;
	}


	//查询所有订单
	public List<merchant_sp_list_users> selectO(String Snumber) throws Exception {
		List<merchant_sp_list_users> merchant_sp_list_users=new ArrayList<merchant_sp_list_users>();
		
		try {
			merchant_sp_list_users=dao.selectO(Snumber);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			conn.close();
		}
		return merchant_sp_list_users;
	}

	//上架商品
	public boolean addP(goods goods) throws Exception {
		boolean mark=false;
		try {
			mark=dao.addP(goods);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			conn.close();
		}
		return mark;
	}

	//改变商品信息
	public boolean changeP(goods goods) throws Exception {
		boolean mark=false;
		try {
			mark=dao.changeP(goods);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			conn.close();
		}
		return mark;
	}

	//下架商品
	public boolean deleteP(goods goods) throws Exception {
		boolean mark=false;
		try {
			mark=dao.deleteP(goods);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			conn.close();
		}
		return mark;
	}

	//查看商品详情
	public goods selectP(int Pid) throws Exception {
		goods goods=new goods();
		
		try {
			goods=dao.selectP(Pid);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			conn.close();
		}
		return goods;
	}

	//查看所有商品
	public List<goods> selectAll(int Mid) throws Exception {
		List<goods> goods = null;
		
		try {
			goods=dao.selectAll(Mid);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			conn.close();
		}
		return goods;
		
	}
	
	
	
}

