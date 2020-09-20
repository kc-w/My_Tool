package Dao.service;

import java.util.ArrayList;
import java.util.List;

import Bean.list.itemOrder;
import Bean.merchant.merchant;
import Bean.sp.goods;
import Bean.users.users;
import Dao.impl.adminDaoImpl;
import Dao.interfaces.adminDao_interface;
import Dao.jdbc.DBconnection;

public class adminDaoService implements adminDao_interface{
	
	private DBconnection conn=null;
	private adminDao_interface dao=null;
	
	public adminDaoService() throws Exception {
		this.conn=new DBconnection();
		this.dao=new adminDaoImpl(this.conn.getdb());
	}
	

	//查询所有用户
	public List<users> selectAllU() throws Exception {
		List<users> users=new ArrayList<users>();
		
		try {
			users=dao.selectAllU();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			conn.close();
		}
		
		return users;
	}

	//查询用户
	public users selectU(String Unumber) throws Exception {
		users users=new users();
		
		try {
			users=dao.selectU(Unumber);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			conn.close();
		}
		
		return users;
	}

	//查询所有商户
	public List<merchant> selectAllS() throws Exception {
		List<merchant> merchant=new ArrayList<merchant>();
		
		try {
			merchant=dao.selectAllS();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			conn.close();
		}
		
		return merchant;
	}

	//查询商户
	public merchant selectS(String Snumber) throws Exception {
		merchant merchant=new merchant();
		
		try {
			merchant=dao.selectS(Snumber);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			conn.close();
		}
		return merchant;
	}

	//查询所有商品
	public List<goods> selectAllP() throws Exception {
		List<goods> goods=new ArrayList<goods>();
		
		try {
			goods=dao.selectAllP();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			conn.close();
		}
		return goods;
	}

	//查询商品
	public goods selecP(int Mid) throws Exception {
		goods goods=new goods();
		
		try {
			goods=dao.selecP(Mid);
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			conn.close();
		}
		return goods;
	}

	//查询所有订单
	public List<itemOrder> selectAllO() throws Exception {
        List<itemOrder> itemOrder=new ArrayList<itemOrder>();
		
		try {
			itemOrder=dao.selectAllO();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			conn.close();
		}
		return itemOrder;
	}

	//查询订单
	public itemOrder selectOc(int Cid) throws Exception {
		itemOrder itemOrder=new itemOrder();
		
		try {
			itemOrder=dao.selectOc(Cid);
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			conn.close();
		}
		return itemOrder;
	}

	//查询订单
	public itemOrder selectOm(int Mid) throws Exception {
		itemOrder itemOrder=new itemOrder();
		
		try {
			itemOrder=dao.selectOm(Mid);
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			conn.close();
		}
		return itemOrder;
	}

	//删除用户
	public boolean deleteU(int Cid) throws Exception{
		boolean mark=false;
		
		try {
			mark=dao.deleteU(Cid);
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			conn.close();
		}
		
		return mark;
	}

	//删除商户
	public boolean deleteS(int Mid) throws Exception {
		boolean mark=false;
		
		try {
			mark=dao.deleteS(Mid);
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			conn.close();
		}
		
		return mark;
	}

	//删除商品
	public boolean deleteP(int Pid) throws Exception {
		boolean mark=false;
		
		try {
			mark=dao.deleteP(Pid);
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			conn.close();
		}
		
		return mark;
	}

	//删除订单
	public boolean deleteO(int Oid) throws Exception {
		boolean mark=false;
		
		try {
			mark=dao.deleteO(Oid);
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			conn.close();
		}
		
		return mark;
	}

}
