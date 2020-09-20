package com.wan.test;

import com.wan.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.*;

import java.io.InputStream;
import java.util.List;

public class test1 {

    //得到SqlSession对象
    public static SqlSession getSqlSession()throws Exception{
        //通过输入流读取配置文件
        InputStream inputStream= Resources.getResourceAsStream("mybatis-config.xml");

        //根据配置文件构建sqlSessionFactory
        SqlSessionFactory sqlSessionFactory=new SqlSessionFactoryBuilder().build(inputStream);

        //通过SqlSessionFactory创建SqlSession
        SqlSession sqlSession=sqlSessionFactory.openSession();

        return sqlSession;
    }
    //根据编号查询
    public static void findUserById()throws Exception{
        SqlSession sqlSession=getSqlSession();
        //SqlSession执行映射文件夹中定义的SQL并返回结果
        User user=sqlSession.selectOne(".UserMapper.findID",1);
        //System.out.println(user.toString());

        //关闭SqlSeesion
        sqlSession.close();
    }

    //根据姓名模糊查询
    public static void findUserName()throws Exception{
        SqlSession sqlSession=getSqlSession();
        //SqlSession执行映射文件夹中定义的SQL并返回结果
        List<User> userList=sqlSession.selectList(".UserMapper.findName","可");
        for (User user: userList){
            System.out.println(user.toString());
        }
        //关闭SqlSeesion
        sqlSession.close();
    }

    //插入数据
    public static void addUser()throws Exception{
        SqlSession sqlSession=getSqlSession();
        User user=new User();
        user.setId(null);
        user.setName("游戏");

        int rows=sqlSession.insert(".UserMapper.addUser",user);
        if(rows>0){
            System.out.println("成功");
        }else {
            System.out.println("失败");
        }
        //提交事务
        sqlSession.commit();
        sqlSession.close();

    }

    //数据更新
    public static void updateUser()throws Exception{
        SqlSession sqlSession=getSqlSession();
        User user=new User();
        user.setId(5);
        user.setName("Kc");

        int rows=sqlSession.update(".UserMapper.updateUser",user);
        if(rows>0){
            System.out.println("成功");
        }else {
            System.out.println("失败");
        }
        //提交事务
        sqlSession.commit();
        sqlSession.close();
    }

    //数据删除
    public static void deleteUer()throws Exception{
        SqlSession sqlSession=getSqlSession();

        int rows=sqlSession.delete(".UserMapper.deleteUser",5);
        if(rows>0){
            System.out.println("成功");
        }else {
            System.out.println("失败");
        }
        //提交事务
        sqlSession.commit();
        sqlSession.close();
    }
    public static void main(String[] args)throws Exception {
        findUserById();
        findUserName();
        addUser();
        updateUser();
        deleteUer();
    }
}
