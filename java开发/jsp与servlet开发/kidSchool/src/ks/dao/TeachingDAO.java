package ks.dao;

import ks.jdbc.DBconnection;
import ks.po.Teaching;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

//课程数据访问类
public class TeachingDAO {
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

    public TeachingDAO() throws Exception {
        conn=db.getdb();
    }

    //查询所有课程信息
    public List<Teaching> selectList() throws Exception {
        List<Teaching> teachingList=new ArrayList();
        String sql="select * from t_teaching";
        try {
            pstmt=conn.prepareStatement(sql);
            rs=pstmt.executeQuery();
            while(rs.next()) {
                Teaching teaching=new Teaching();
                teaching.setId(rs.getInt("id"));
                teaching.setKidId(rs.getInt("kidId"));
                teaching.setTeacherId(rs.getInt("teacherId"));
                teaching.setTeachDate(rs.getString("teachDate"));
                teaching.setContent(rs.getString("content"));
                teaching.setEffect(rs.getString("effect"));
                teachingList.add(teaching);
            }
        }catch(Exception e) {
            e.printStackTrace();
        }finally {
            if(pstmt!=null) {
                pstmt.close();
            }
        }
        return  teachingList;
    }

    //查询课程信息
    public Teaching select(int id) throws Exception {
        Teaching teaching=new Teaching();
        String sql="select * from t_teaching where id=?";
        try {
            pstmt=conn.prepareStatement(sql);
            pstmt.setInt(1,id);
            rs=pstmt.executeQuery();
            while(rs.next()) {
                teaching.setId(rs.getInt("id"));
                teaching.setKidId(rs.getInt("kidId"));
                teaching.setTeacherId(rs.getInt("teacherId"));
                teaching.setTeachDate(rs.getString("teachDate"));
                teaching.setContent(rs.getString("content"));
                teaching.setEffect(rs.getString("effect"));
            }
        }catch(Exception e) {
            e.printStackTrace();
        }finally {
            if(pstmt!=null) {
                pstmt.close();
            }
        }
        return  teaching;
    }


    //增加课程信息
    public boolean add(Teaching teaching) throws Exception {
        boolean mark=true;
        //设置手动提交事务模式
        conn.setAutoCommit(false);

        String sql="insert into t_teaching(kidId,teacherId,teachDate,content,effect)values(?,?,?,?,?)";
        try {
            pstmt=conn.prepareStatement(sql);
            pstmt.setInt(1,teaching.getKidId());
            pstmt.setInt(2,teaching.getTeacherId());
            pstmt.setString(3,teaching.getTeachDate());
            pstmt.setString(4,teaching.getContent());
            pstmt.setString(5,teaching.getEffect());
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

    //修改课程信息
    public boolean change(Teaching teaching) throws Exception {
        boolean mark=true;
        //设置手动提交事务模式
        conn.setAutoCommit(false);

        String sql="update t_teaching set kidId=?,teacherId=?,teachDate=?,content=?,effect=? where id=?";
        try {
            pstmt=conn.prepareStatement(sql);
            pstmt.setInt(1,teaching.getKidId());
            pstmt.setInt(2,teaching.getTeacherId());
            pstmt.setString(3,teaching.getTeachDate());
            pstmt.setString(4,teaching.getContent());
            pstmt.setString(5,teaching.getEffect());
            pstmt.setInt(6,teaching.getId());
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

    //删除课程信息
    public boolean delete(int id) throws Exception {
        boolean mark=true;
        //设置手动提交事务模式
        conn.setAutoCommit(false);

        String sql="delete from t_teaching where id=?";
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
