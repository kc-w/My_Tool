package ks.dao;

import ks.jdbc.DBconnection;
import ks.po.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

//用户数据访问类
public class UserDAO {
    private static DBconnection db;

    static {
        try {
            db = new DBconnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs=null;

    public UserDAO() throws Exception {
        conn=db.getdb();
    }

    //用户验证
    public User login(User user) throws Exception {
        User user1=new User();
        String sql="select * from t_user where name=?";
        try {
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,user.getName());
            rs=pstmt.executeQuery();
            System.out.println(user.getName()+" "+user.getPwd());
            while(rs.next()) {
                if(rs.getString("pwd").equals(user.getPwd())){
                    user1.setId(rs.getInt("id"));
                    user1.setRole(rs.getString("role"));
                    user1.setName(rs.getString("name"));
                    user1.setPwd(rs.getString("pwd"));
                    user1.setTel(rs.getString("tel"));
                    user1.setAddress(rs.getString("address"));
                }
            }
        }catch(Exception e) {
            e.printStackTrace();
        }finally {
            if(pstmt!=null) {
                pstmt.close();
            }
        }

        return  user1;
    }

    //查询所有用户数据
    public List<User> selectList() throws Exception {
        List<User> userList=new ArrayList();
        String sql="select * from t_user";
        try {
            pstmt=conn.prepareStatement(sql);
            rs=pstmt.executeQuery();
            while(rs.next()) {
                User user=new User();
                user.setId(rs.getInt("id"));
                user.setRole(rs.getString("role"));
                user.setName(rs.getString("name"));
                user.setPwd(rs.getString("pwd"));
                user.setTel(rs.getString("tel"));
                user.setAddress(rs.getString("address"));
                userList.add(user);
            }
        }catch(Exception e) {
            e.printStackTrace();
        }finally {
            if(pstmt!=null) {
                pstmt.close();
            }
        }
        return  userList;
    }

    //查询单个用户
    public User select(int id) throws Exception {
        User user=new User();
        String sql="select * from t_user where id=?";
        try {
            pstmt=conn.prepareStatement(sql);
            pstmt.setInt(1,id);
            rs=pstmt.executeQuery();
            while(rs.next()) {
                user.setId(rs.getInt("id"));
                user.setRole(rs.getString("role"));
                user.setName(rs.getString("name"));
                user.setPwd(rs.getString("pwd"));
                user.setTel(rs.getString("tel"));
                user.setAddress(rs.getString("address"));
            }
        }catch(Exception e) {
            e.printStackTrace();
        }finally {
            if(pstmt!=null) {
                pstmt.close();
            }
        }
        return  user;
    }

    //增加用户
    public boolean add(User user) throws Exception {
        boolean mark=true;
        //设置手动提交事务模式
        conn.setAutoCommit(false);

        String sql="insert into t_user(role,name,pwd,tel,address)values(?,?,?,?,?)";
        try {
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,user.getRole());
            pstmt.setString(2,user.getName());
            pstmt.setString(3,user.getPwd());
            pstmt.setString(4,user.getTel());
            pstmt.setString(5,user.getAddress());
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

    //更新用户
    public boolean change(User user) throws Exception {
        boolean mark=true;
        //设置手动提交事务模式
        conn.setAutoCommit(false);

        String sql="update t_user set role=?,name=?,pwd=?,tel=?,address=? where id=?";
        try {
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,user.getRole());
            pstmt.setString(2,user.getName());
            pstmt.setString(3,user.getPwd());
            pstmt.setString(4,user.getTel());
            pstmt.setString(5,user.getAddress());
            pstmt.setInt(6,user.getId());
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

    //删除用户
    public boolean delete(int id) throws Exception {
        boolean mark=true;
        //设置手动提交事务模式
        conn.setAutoCommit(false);

        String sql="delete from t_user where id=?";
        try {
            pstmt=conn.prepareStatement(sql);
            pstmt.setInt(1, id);
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




    public static void close() throws Exception {
        db.close();
    }
}
