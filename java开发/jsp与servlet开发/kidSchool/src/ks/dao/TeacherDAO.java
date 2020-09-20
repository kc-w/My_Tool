package ks.dao;

import ks.jdbc.DBconnection;
import ks.po.Kid;
import ks.po.Teacher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

//教师数据访问类
public class TeacherDAO {
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

    public TeacherDAO() throws Exception {
        conn=db.getdb();
    }


    //查询所有教师
    public List<Teacher> selectList() throws Exception {
        List<Teacher> teacherList=new ArrayList();
        String sql="select * from t_teacher";
        try {
            pstmt=conn.prepareStatement(sql);
            rs=pstmt.executeQuery();
            while(rs.next()) {
                Teacher teacher=new Teacher();
                teacher.setId(rs.getInt("id"));
                teacher.setName(rs.getString("name"));
                teacherList.add(teacher);
            }
        }catch(Exception e) {
            e.printStackTrace();
        }finally {
            if(pstmt!=null) {
                pstmt.close();
            }
        }
        return  teacherList;
    }

    //查询单个教师
    public Teacher select(int id) throws Exception {
        Teacher teacher=new Teacher();
        String sql="select * from t_teacher where id=?";
        try {
            pstmt=conn.prepareStatement(sql);
            pstmt.setInt(1,id);
            rs=pstmt.executeQuery();
            while(rs.next()) {
                teacher.setId(rs.getInt("id"));
                teacher.setName(rs.getString("name"));
            }
        }catch(Exception e) {
            e.printStackTrace();
        }finally {
            if(pstmt!=null) {
                pstmt.close();
            }
        }
        return  teacher;
    }

    //增加教师
    public boolean add(Teacher teacher) throws Exception {
        boolean mark=true;
        //设置手动提交事务模式
        conn.setAutoCommit(false);

        String sql="insert into t_teacher(name)values(?)";
        try {
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,teacher.getName());
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

    //修改教师
    public boolean change(Teacher teacher) throws Exception {
        boolean mark=true;
        //设置手动提交事务模式
        conn.setAutoCommit(false);

        String sql="update t_teacher set name=? where id=?";
        try {
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,teacher.getName());
            pstmt.setInt(2,teacher.getId());
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

    //删除教师
    public boolean delete(int id) throws Exception {
        boolean mark=true;
        //设置手动提交事务模式
        conn.setAutoCommit(false);

        String sql="delete from t_teacher where id=?";
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
