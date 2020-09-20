package ks.dao;

import ks.jdbc.DBconnection;
import ks.po.Kid;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

//幼儿数据访问类
public class KidDAO {
    private static DBconnection db;

    static {
        try {
            db = new DBconnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Connection conn;
    private PreparedStatement pstmt=null;
    private ResultSet rs=null;

    public KidDAO() throws Exception {
        conn=db.getdb();
    }

    //查询所有幼儿
    public List<Kid> selectList() throws Exception {
        List<Kid> kidList=new ArrayList();
        String sql="select * from t_kid";
        try {
            pstmt=conn.prepareStatement(sql);
            rs=pstmt.executeQuery();
            while(rs.next()) {
                Kid kid=new Kid();
                kid.setId(rs.getInt("id"));
                kid.setName(rs.getString("name"));
                kid.setBrithdate(rs.getString("brithdate"));
                kid.setPhoto(rs.getString("photo"));
                kid.setParentsId(rs.getInt("parentsId"));
                kidList.add(kid);
            }
        }catch(Exception e) {
            e.printStackTrace();
        }finally {
            if(pstmt!=null) {
                pstmt.close();
            }
        }
        return  kidList;
    }

    //查询单个幼儿
    public Kid select(int id) throws Exception {
        Kid kid=new Kid();
        String sql="select * from t_kid where id=?";
        try {
            pstmt=conn.prepareStatement(sql);
            pstmt.setInt(1,id);
            rs=pstmt.executeQuery();
            while(rs.next()) {
                kid.setId(rs.getInt("id"));
                kid.setName(rs.getString("name"));
                kid.setBrithdate(rs.getString("brithdate"));
                kid.setPhoto(rs.getString("photo"));
                kid.setParentsId(rs.getInt("parentsId"));
            }
        }catch(Exception e) {
            e.printStackTrace();
        }finally {
            if(pstmt!=null) {
                pstmt.close();
            }
        }
        return  kid;
    }

    //增加幼儿
    public boolean add(Kid kid) throws Exception {
        boolean mark=true;
        //设置手动提交事务模式
        conn.setAutoCommit(false);

        String sql="insert into t_kid(name,brithdate,parentsId)values(?,?,?)";
        try {
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,kid.getName());
            pstmt.setString(2,kid.getBrithdate());
            pstmt.setInt(3,kid.getParentsId());
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

    //修改幼儿
    public boolean change(Kid kid) throws Exception {
        boolean mark=true;
        //设置手动提交事务模式
        conn.setAutoCommit(false);

        String sql="update t_kid set name=?,brithdate=?,parentsId=? where id=?";
        try {
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,kid.getName());
            pstmt.setString(2,kid.getBrithdate());
            pstmt.setInt(3,kid.getParentsId());
            pstmt.setInt(4,kid.getId());
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

    //删除幼儿
    public boolean delete(int id) throws Exception {
        boolean mark=true;
        //设置手动提交事务模式
        conn.setAutoCommit(false);

        String sql="delete from t_kid where id=?";
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
