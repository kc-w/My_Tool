package Dao.interfaces;

import java.util.List;

import Bean.merchant.merchant;
import Bean.merchant_sp_list_users.merchant_sp_list_users;
import Bean.sp.goods;

//接口
public interface merchantDao_interface {
	
	//注册商家
	public boolean add(merchant merchant) throws Exception;
	
	//商家登陆验证
	public merchant login(merchant merchant) throws Exception;
	
	//商家账号是否存在
	public merchant selectN(String Snumber)throws Exception;
	
	//商家邮箱是否存在
	public boolean selectE(String Semail)throws Exception;
	
	//商家手机是否存在
	public boolean selectT(String Stelephone)throws Exception;
	
	//修改商家信息
	public boolean change(merchant merchant)throws Exception;
	
	
	//查询订单
	public List<merchant_sp_list_users> selectO(String Snumber)throws Exception;
	
	
	//上架商品
	public boolean addP(goods goods)throws Exception;
	
	//修改商品
	public boolean changeP(goods goods)throws Exception;
	
	//下架商品
	public boolean deleteP(goods goods)throws Exception;
	
	//查看商品信息
	public goods selectP(int Pid)throws Exception;
	
	//查看商品列表
	public List<goods> selectAll(int Mid)throws Exception;

}
