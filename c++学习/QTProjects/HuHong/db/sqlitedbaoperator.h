#ifndef SQLITEDBAOPERATOR_H
#define SQLITEDBAOPERATOR_H
#include <QSqlDatabase>
#include <QSqlQuery>
#include <QSqlQueryModel>
#include <QDebug>
#include <QSqlError>

#include <bean/user.h>


class SqliteDBAOperator
{
public:
    SqliteDBAOperator();
    ~SqliteDBAOperator();
public:
    //创建数据库并建立连接
    bool OpenDb(void);
    //创建数据表（student）
    void createTable(void);
    //在表格中增加新的字段
    void addNewcolumn(QString &columnNameAndproperty);
    //判断数据表是否存在
    bool IsTaBexists(QString &Tabname);

//    //插入数据
//    void singleinsertdata(w2dba &singledb);//插入单条数据
//    void Moreinsertdata(QList<w2dba> &moredb);//插入多条数据

    //查询所有用户信息
    QList<User> selectAllUser();
    //登陆验证
    bool checkLogin(QString &number,QString &password);
    //删除数据
    void deletedata();
    //修改数据
    void updatedata();
    //关闭数据库
    void closeDb(void);

private:
    //数据库连接声明
    QSqlDatabase db;
    //用户列表
    QList<User> users;
    //用户
    User user;
};

#endif // SQLITEDBAOPERATOR_H
