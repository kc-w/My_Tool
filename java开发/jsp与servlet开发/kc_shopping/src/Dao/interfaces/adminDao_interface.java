package Dao.interfaces;

import java.util.List;

import Bean.list.itemOrder;
import Bean.merchant.merchant;
import Bean.sp.goods;
import Bean.users.users;

//管理员接口
public interface adminDao_interface {
	//查询用户
	public List<users> selectAllU() throws Exception;
	public users selectU(String Unumber) throws Exception;
	
	//查询商户
	public List<merchant> selectAllS() throws Exception;
	public merchant selectS(String Snumber) throws Exception;
	
	//查询商品
	public List<goods> selectAllP() throws Exception;
	public goods selecP(int Mid) throws Exception;
	
	//查询订单
	public List<itemOrder> selectAllO() throws Exception;
	public itemOrder selectOc(int Cid) throws Exception;
	public itemOrder selectOm(int Mid) throws Exception;
	
	
	//删除用户
	public boolean deleteU(int Cid) throws Exception;
	
	//删除商户
	public boolean deleteS(int Mid) throws Exception;
	
	//删除商品
	public boolean deleteP(int Pid) throws Exception;
	
	//删除订单
	public boolean deleteO(int Oid) throws Exception;

}
