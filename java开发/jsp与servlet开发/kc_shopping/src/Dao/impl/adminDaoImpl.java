package impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Bean.list.itemOrder;
import Bean.merchant.merchant;
import Bean.sp.goods;
import Bean.users.users;
import Dao.interfaces.adminDao_interface;

public class adminDaoImpl implements adminDao_interface{
	
	private Connection conn=null;
	private PreparedStatement pstmt=null;
	private ResultSet rs=null;
	
	public adminDaoImpl(Connection conn) {
		this.conn=conn;
	}

	//查询所有用户
	public List<users> selectAllU() throws Exception {
		List<users> users=new ArrayList<users>();
		String sql="seslct * from customer";
		
		try {
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				users users1=new users();
				users1.setCid(rs.getInt("Cid"));
				users1.setUaddress(rs.getString("Uaddress"));
				users1.setUemail(rs.getString("Uemail"));
				users1.setUname(rs.getString("Uname"));
				users1.setUnumber(rs.getString("Unumber"));
				users1.setUpassword(rs.getString("Upassword"));
				users1.setUtelephone(rs.getString("Utelephone"));
				users1.setUztime(rs.getString("Uztime"));
				
				users.add(users1);
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

	//查询用户
	public users selectU(String Unumber) throws Exception {
		users users=new users();
		String sql="select * from customer where Unumber=?";
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, Unumber);
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				users.setCid(rs.getInt("Cid"));
				users.setUaddress(rs.getString("Uaddress"));
				users.setUemail(rs.getString("Uemail"));
				users.setUname(rs.getString("Uname"));
				users.setUpassword(rs.getString("Upassword"));
				users.setUnumber(rs.getString("Unumber"));
				users.setUpassword(rs.getString("Upassword"));
				users.setUtelephone(rs.getString("Utelephone"));
				users.setUztime(rs.getString("Uztime"));
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

	//查询所有商户
	public List<merchant> selectAllS() throws Exception {
		List<merchant> merchant=new ArrayList<merchant>();
		String sql="seslct * from merchant";
		
		try {
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				merchant merchant1=new merchant();
				merchant1.setMid(rs.getInt("Mid"));
				merchant1.setSaddress(rs.getString("Saddress"));
				merchant1.setSemail(rs.getString("Semail"));
				merchant1.setSname(rs.getString("Sname"));
				merchant1.setSnumber(rs.getString("Snumber"));
				merchant1.setSpassword(rs.getString("Spassword"));
				merchant1.setStelephone(rs.getString("Stelephone"));
				merchant1.setSztime(rs.getString("Sztime"));
				
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

	//查询商户
	public merchant selectS(String Snumber) throws Exception {
		merchant merchant=new merchant();
		String sql="select * from merchant where Snumber=?";
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, Snumber);
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				merchant.setMid(rs.getInt("Mid"));
				merchant.setSaddress(rs.getString("Saddress"));
				merchant.setSemail(rs.getString("Semail"));
				merchant.setSname(rs.getString("Sname"));
				merchant.setSpassword(rs.getString("Spassword"));
				merchant.setSnumber(rs.getString("Snumber"));
				merchant.setStelephone(rs.getString("Stelephone"));
				merchant.setSztime(rs.getString("Sztime"));
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

	//查询所有商品
	public List<goods> selectAllP() throws Exception {
		List<goods> goods=new ArrayList<goods>();
		String sql="seslct * from sp";
		
		try {
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				goods goods1=new goods();
				goods1.setPid(rs.getInt("Cid"));
				goods1.setMid(rs.getInt("Mid"));
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

	//查询商品
	public goods selecP(int Mid) throws Exception {
		goods goods=new goods();
		String sql="select * from sp where Mid=?";
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, Mid);
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				goods.setMid(rs.getInt("Mid"));
				goods.setPid(rs.getInt("Pid"));
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

	//查询所有订单
	public List<itemOrder> selectAllO() throws Exception {
		List<itemOrder> itemOrder=new ArrayList<itemOrder>();
		String sql="seslct * from list";
		
		try {
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				itemOrder itemOrder1=new itemOrder();
				itemOrder1.getOid();
				itemOrder1.getCid();
				itemOrder1.getMid();
				itemOrder1.getOlist();
				itemOrder1.getOtime();
				
				itemOrder.add(itemOrder1);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(pstmt!=null) {
				pstmt.close();
			}
		}
		return itemOrder;
	}

	//查询订单
	public itemOrder selectOc(int Cid) throws Exception {
		itemOrder itemOrder=new itemOrder();
		String sql="select * from list where Cid=?";
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, Cid);
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				itemOrder.setCid(rs.getInt("Cid"));
				itemOrder.setMid(rs.getInt("Mid"));
				itemOrder.setOid(rs.getInt("Oid"));
				itemOrder.setOlist(rs.getString("Olist"));
				itemOrder.setOtime(rs.getString("Otime"));
			}
	    }catch(Exception e) {
	    	e.printStackTrace();
	    }finally {
	    	if(pstmt!=null) {
				pstmt.close();
			}
	    }
		
		return itemOrder;
	}

	//查询订单
	public itemOrder selectOm(int Mid) throws Exception {
		itemOrder itemOrder=new itemOrder();
		String sql="select * from list where Mid=?";
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, Mid);
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				itemOrder.setCid(rs.getInt("Cid"));
				itemOrder.setMid(rs.getInt("Mid"));
				itemOrder.setOid(rs.getInt("Oid"));
				itemOrder.setOlist(rs.getString("Olist"));
				itemOrder.setOtime(rs.getString("Otime"));
			}
	    }catch(Exception e) {
	    	e.printStackTrace();
	    }finally {
	    	if(pstmt!=null) {
				pstmt.close();
			}
	    }
		
		return itemOrder;
	}

	//删除用户
	public boolean deleteU(int Cid) throws Exception{
		boolean mark=true;
		String sql="delete customer where Cid=?";
		//设置手动提交事务模式
		conn.setAutoCommit(false);
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, Cid);
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

	//删除商户
	public boolean deleteS(int Mid) throws Exception {
		boolean mark=true;
		String sql="delete merchant where Mid=?";
		//设置手动提交事务模式
		conn.setAutoCommit(false);
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, Mid);
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

	//删除商品
	public boolean deleteP(int Pid) throws Exception {
		boolean mark=true;
		String sql="delete sp where Pid=?";
		//设置手动提交事务模式
		conn.setAutoCommit(false);
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, Pid);
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

	//删除订单
	public boolean deleteO(int Oid) throws Exception {
		boolean mark=true;
		String sql="delete list where Oid=?";
		//设置手动提交事务模式
		conn.setAutoCommit(false);
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, Oid);
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


}
