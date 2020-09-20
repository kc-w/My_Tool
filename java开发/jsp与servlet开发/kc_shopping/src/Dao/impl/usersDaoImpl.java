package impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Bean.merchant.merchant;
import Bean.merchant_sp.merchant_sp;
import Bean.merchant_sp_list_users.merchant_sp_list_users;
import Bean.time.getDate;
import Bean.users.users;
import Dao.interfaces.usersDao_interface;

//数据操作,实现数据库操作
public class usersDaoImpl implements usersDao_interface{
	
	private Connection conn=null;
	private PreparedStatement pstmt=null;
	private ResultSet rs=null;
	private getDate time=new getDate();
	
	public usersDaoImpl(Connection conn) {
		this.conn=conn;
	}
	
	//注册用户
	public boolean add(users user) throws Exception {
		boolean mark=true;
		//设置手动提交事务模式
		conn.setAutoCommit(false);

		String sql="insert into customer(Uname,Unumber,Upassword,Utelephone,Uemail,Uaddress,Uztime)values(?,?,?,?,?,?,?)";
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,"商城用户"+user.getUnumber());
			pstmt.setString(2, user.getUnumber());//
			pstmt.setString(3, user.getUpassword());//
			pstmt.setString(4, user.getUtelephone());//
			pstmt.setString(5, user.getUemail());//
			pstmt.setString(6, "收货地址未填写");
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
	
	
	//用户登陆验证
	public users login(users user) throws Exception {
		users users=new users();
		String sql="select * from customer where Unumber=?";
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, user.getUnumber());
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				if(rs.getString("Upassword").equals(user.getUpassword())) {
					users.setUname(rs.getString("Uname"));
					users.setUnumber(rs.getString("Unumber"));
					users.setCid(rs.getInt("Cid"));
					users.setUnumber(rs.getString("Upassword"));
					users.setUnumber(rs.getString("Utelephone"));
					users.setUnumber(rs.getString("Uemail"));
					users.setUnumber(rs.getString("Uaddress"));
					users.setUnumber(rs.getString("Uztime"));
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(pstmt!=null) {
				pstmt.close();
			}
		}
		return users;
	}
	
	
	//账号是否存在
	public users selectN(String Unumber) throws Exception {
		users users=new users();
		String sql="select * from customer where Unumber=?";
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, Unumber);
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				users.setUname(rs.getString("Uname"));
				users.setUnumber(rs.getString("Unumber"));
				users.setCid(rs.getInt("Cid"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(pstmt!=null) {
				pstmt.close();
			}
		}
		return users;
	}
	
	
	
	//邮箱是否存在
	public boolean selectE(String Uemail) throws Exception {
		boolean mark=false;
		String sql="select Uemail from customer where Uemail=?";
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, Uemail);
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

	//手机是否存在
	public boolean selectT(String Utelephone) throws Exception {
		boolean mark=false;
		String sql="select Utelephone from customer where Utelephone=?";
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, Utelephone);
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
	
	//用户修改信息
	public boolean change(users user) throws Exception {
		boolean mark=true;
		//设置手动提交事务模式
		conn.setAutoCommit(false);
		
		String sql="update customer set Uname=?,Utelephone=?,Uemail=?,Uaddress=? where Unumber=?";
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, user.getUname());
			pstmt.setString(2, user.getUtelephone());
			pstmt.setString(3, user.getUemail());
			pstmt.setString(4, user.getUaddress());
			pstmt.setString(5, user.getUnumber());
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

	//查询商家
	public List<merchant> selectS(String Sname) throws Exception {
		List<merchant> merchant=new ArrayList<merchant>();
		String sql="select * from merchant where Sname like '% ? %'";
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,Sname);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				merchant merchant1=new merchant();
				merchant1.setSname(rs.getString("Sname"));
				merchant1.setSaddress(rs.getString("Sassress"));
				merchant1.setStelephone(rs.getString("Stelephone"));
				merchant.add(merchant1);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(pstmt!=null) {
				pstmt.close();
			}
		}
		
		return merchant;
	}

	//查询商品
	public List<merchant_sp> selectP(String Pname) throws Exception {
		List<merchant_sp> merchant_sp=new ArrayList<merchant_sp>();
		String sql="select merchant.*,sp.* from merchant inner join sp on merchant.Mid=sp.Mid where sp.Pname like '% ? %'";
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,Pname);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				merchant_sp merchant_sp1=new merchant_sp();
				merchant_sp1.setPname(rs.getString("Pname"));
				merchant_sp1.setPprice(rs.getFloat("Pprice"));
				merchant_sp1.setSname(rs.getString("Sname"));
				merchant_sp.add(merchant_sp1);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(pstmt!=null) {
				pstmt.close();
			}
		}
		return merchant_sp;
	}

	//查询订单
	public List<merchant_sp_list_users> selectO(String Unumber) throws Exception {
		List<merchant_sp_list_users> merchant_sp_list_users=new ArrayList<merchant_sp_list_users>();
		String sql="select merchant.*,sp.*,list.*,users.* from merchant inner join sp on merchant.Mid=sp.Mid inner join list on sp.Mid=list.Mid inner join users on list.Cid=users.Cid where users.Cid=?";
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, Unumber);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				merchant_sp_list_users merchant_sp_list_users1=new merchant_sp_list_users();
				
				//订单创建日期
				merchant_sp_list_users1.setOtime(rs.getString("Otime"));
				//得到商家名
				merchant_sp_list_users1.setSname(rs.getString("Sname"));
				//得到商家电话
				merchant_sp_list_users1.setStelephone(rs.getString("Stelephone"));
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

	
	
	

	

}
