package impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Bean.merchant.merchant;
import Bean.merchant_sp_list_users.merchant_sp_list_users;
import Bean.sp.goods;
import Bean.time.getDate;
import Dao.interfaces.merchantDao_interface;

//数据操作,实现数据库操作
public class merchantDaoImpl implements merchantDao_interface {
	
	private Connection conn=null;
	private PreparedStatement pstmt=null;
	private ResultSet rs=null;
	private getDate time=new getDate();
	
	public merchantDaoImpl(Connection conn) {
		this.conn=conn;
	}
	
	
	//注册商家
	public boolean add(merchant merchant) throws Exception {
		boolean mark=true;
		//设置手动提交事务模式
		conn.setAutoCommit(false);

		String sql="insert into merchant(Sname,Snumber,Spassword,Stelephone,Semail,Saddress,Sztime)values(?,?,?,?,?,?,?)";
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,"商户"+merchant.getSnumber());
			pstmt.setString(2, merchant.getSnumber());//
			pstmt.setString(3, merchant.getSpassword());//
			pstmt.setString(4, merchant.getStelephone());//
			pstmt.setString(5, merchant.getSemail());//
			pstmt.setString(6, "发货地址未填写");
			pstmt.setString(7, time.getNowTime());
			pstmt.executeUpdate();
			
			//手动提交事务
			conn.commit();
		}catch(Exception e) {
			mark=false;
			//如有异常则回滚
			conn.rollback();
			e.printStackTrace();
		}finally {
			if(pstmt!=null) {
				pstmt.close();
			}
		}
		return mark;
	}
	
	//商家登陆验证
	public merchant login(merchant merchant) throws Exception {
		merchant merchant1=new merchant();
		String sql="select * from merchant where Snumber=?";
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,merchant.getSnumber());
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				if(rs.getString("Snumber").equals(merchant.getSpassword())) {
					merchant1.setMid(rs.getInt("Mid"));
					merchant1.setSname(rs.getString("Sname"));
					merchant1.setSnumber(rs.getString("Snumber"));
					merchant1.setSpassword(rs.getString("Spassword"));
					merchant1.setStelephone(rs.getString("Stelephone"));
					merchant1.setSemail(rs.getString("Semail"));
					merchant1.setSaddress(rs.getString("Saddress"));
					merchant1.setSztime(rs.getString("Sztime"));
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(pstmt!=null) {
				pstmt.close();
			}
		}
		return merchant1;
	}

	//商家账号是否存在
	public merchant selectN(String Snumber) throws Exception {
		merchant merchant1=new merchant();
		String sql="select * from merchant where Snumber=?";
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, Snumber);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				if(rs.getString("Snumber").equals(Snumber)) {
					merchant1.setMid(rs.getInt("Mid"));
					merchant1.setSname(rs.getString("Sname"));
					merchant1.setSnumber(rs.getString("Snumber"));
					merchant1.setSpassword(rs.getString("Spassword"));
					merchant1.setStelephone(rs.getString("Stelephone"));
					merchant1.setSemail(rs.getString("Semail"));
					merchant1.setSaddress(rs.getString("Saddress"));
					merchant1.setSztime(rs.getString("Sztime"));
				}
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(pstmt!=null) {
				pstmt.close();
			}
		}
		
		return merchant1;
	}

	//商家邮箱是否存在
	public boolean selectE(String Semail) throws Exception {
		boolean mark=false;
		String sql="select Semail from merchant where Semail=?";
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, Semail);
			rs=pstmt.executeQuery();
			
			if(rs.next()!=false) {
				mark=true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(pstmt!=null) {
				pstmt.close();
			}
		}
		
		return mark;
	}

	//商家电话是否存在
	public boolean selectT(String Stelephone) throws Exception {
		boolean mark=false;
		String sql="select Stelephone from merchant where Stelephone=?";
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, Stelephone);
			rs=pstmt.executeQuery();
			
			if(rs.next()!=false) {
				mark=true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(pstmt!=null) {
				pstmt.close();
			}
		}
		
		return mark;
	}
	
	
	//修改商家信息
	public boolean change(merchant merchant) throws Exception {
		boolean mark=true;
		//设置手动提交事务模式
		conn.setAutoCommit(false);
		
		String sql="update merchant set Sname=?,Stelephone=?,Semail=?,Saddress=? where Snumber=?";
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, merchant.getSname());
			pstmt.setString(2, merchant.getStelephone());
			pstmt.setString(3, merchant.getSemail());
			pstmt.setString(4, merchant.getSaddress());
			pstmt.setString(5, merchant.getSnumber());
			pstmt.executeUpdate();
			
			//手动提交事务
			conn.commit();
		}catch(Exception e){
			mark=false;
			//如有异常则回滚
			conn.rollback();
			e.printStackTrace();
		}finally {
			if(pstmt!=null) {
				pstmt.close();
			}
		}
		
		return mark;
	}


	//查询所有订单
	public List<merchant_sp_list_users> selectO(String Snumber) throws Exception {
		List<merchant_sp_list_users> merchant_sp_list_users=new ArrayList<merchant_sp_list_users>();
		
		String sql="select merchant.*,sp.*,list.*,users.* from merchant inner join sp on merchant.Mid=sp.Mid inner join list on sp.Mid=list.Mid inner join users on list.Cid=users.Cid where merchant.Mid=?";
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, Snumber);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				merchant_sp_list_users merchant_sp_list_users1=new merchant_sp_list_users();
				
				//订单创建日期
				merchant_sp_list_users1.setOtime(rs.getString("Otime"));
				//得到用户名
				merchant_sp_list_users1.setSname(rs.getString("Uname"));
				//得到用户电话
				merchant_sp_list_users1.setStelephone(rs.getString("Utelephone"));
				//得到订单编号
				merchant_sp_list_users1.setOid(rs.getInt("Oid"));
				//得到订单内容
				merchant_sp_list_users1.setOlist(rs.getString("Olist"));
				
				merchant_sp_list_users.add(merchant_sp_list_users1);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(pstmt!=null) {
				pstmt.close();
			}
		}

		return  merchant_sp_list_users;
	}

	//上架商品
	public boolean addP(goods goods) throws Exception {
		boolean mark=true;
		//设置手动提交事务模式
		conn.setAutoCommit(false);

		String sql="insert into sp(Mid,Pname,Pprice,Pstock,Ptime)values(?,?,?,?,?)";
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setLong(1, goods.getMid());
			pstmt.setString(2, goods.getPname());
			pstmt.setFloat(3, goods.getPprice());
			pstmt.setLong(4, goods.getPstock());
			pstmt.setString(5, time.getNowTime());
			pstmt.executeUpdate();
			
			//手动提交事务
			conn.commit();
		}catch(Exception e) {
			mark=false;
			//如有异常则回滚
			conn.rollback();
			e.printStackTrace();
			return mark;
		}finally {
			if(pstmt!=null) {
				pstmt.close();
			}
		}
		
		return mark;
	}

	//改变商品信息
	public boolean changeP(goods goods) throws Exception {
		boolean mark=true;
		//设置手动提交事务模式
		conn.setAutoCommit(false);
		
		String sql="update sp set Pname=?,Pprice=?,Pstock=? where Pid=?";
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, goods.getPname());
			pstmt.setFloat(2, goods.getPprice());
			pstmt.setLong(3, goods.getPstock());
			pstmt.setInt(4, goods.getPid());
			pstmt.executeUpdate();
			
			//手动提交事务
			conn.commit();
		}catch(Exception e){
			mark=false;
			//如有异常则回滚
			conn.rollback();
			e.printStackTrace();
		}finally {
			if(pstmt!=null) {
				pstmt.close();
			}
		}
		
		return mark;
	}

	//下架商品
	public boolean deleteP(goods goods) throws Exception {
		boolean mark=true;
		//设置手动提交事务模式
		conn.setAutoCommit(false);
		
		String sql="delete sp where Pid=?";
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, goods.getPid());
			pstmt.executeUpdate();
			
			//手动提交事务
			conn.commit();
		}catch(Exception e){
			mark=false;
			//如有异常则回滚
			conn.rollback();
			e.printStackTrace();
		}finally {
			if(pstmt!=null) {
				pstmt.close();
			}
		}
		
		return mark;
	}

	//查看商品详情
	public goods selectP(int Pid) throws Exception {
		String sql="select * from merchant where Pid=?";
		goods goods=new goods();
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, Pid);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				goods.setPname(rs.getString("Pname"));
				goods.setPprice(rs.getFloat("Pprice"));
				goods.setPstock(rs.getInt("Pstock"));
				goods.setPtime(rs.getString("Ptime"));
			}

		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(pstmt!=null) {
				pstmt.close();
			}
		}
		
		return goods;
	}

	//查看所有商品
	public List<goods> selectAll(int Mid) throws Exception {
		List<goods> goods=new ArrayList<goods>();
		String sql="select * from sp where Mid=?";
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, Mid);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				goods goods1=new goods();
				goods1.setPname(rs.getString("Pname"));
				goods1.setPprice(rs.getFloat("Pprice"));
				goods1.setPstock(rs.getInt("Pstock"));
				goods1.setPtime(rs.getString("Ptime"));
				goods.add(goods1);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(pstmt!=null) {
				pstmt.close();
			}
		}
		
		return goods;
	}

}
