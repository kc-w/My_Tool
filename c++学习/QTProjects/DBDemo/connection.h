#ifndef CONNECTION_H
#define CONNECTION_H
#include <QMessageBox>
#include <QSqlDatabase>
#include <QSqlQuery>

static bool createConnection1()
{
    //创建sqlite数据库连接
    QSqlDatabase db = QSqlDatabase::addDatabase("QSQLITE");


//    db.setHostName("主机名");
//    db.setDatabaseName("数据库名");
//    db.setUserName("用户名");
//    db.setPassword("密码");



    //设置数据库名称
    db.setDatabaseName(":memory:");
    //开启数据库
    if (!db.open()) {
        QMessageBox::critical(0, "无法开启数据库","无法建立数据库连接.", QMessageBox::Cancel);
        return false;
    }
    QSqlQuery query;
    query.exec("create table student (id int primary key,name varchar(20))");
    query.exec("insert into student values(0, '小红')");
    query.exec("insert into student values(1, '小蓝')");
    query.exec("insert into student values(2, '小黑')");

    query.exec("create table studentX (id int primary key,name varchar(20),Uid int)");
    query.exec("insert into studentX values(0, '小A',0)");
    query.exec("insert into studentX values(1, '小B',1)");
    query.exec("insert into studentX values(2, '小C',1)");
    return true;
}

static bool createConnection2()
{
    // 创建一个数据库连接，使用“connection1”为连接名
    QSqlDatabase db1 = QSqlDatabase::addDatabase("QSQLITE", "connection1");
    db1.setDatabaseName("my1.db");
    if (!db1.open()) {
        QMessageBox::critical(0, "无法开启数据库","无法建立数据库连接.", QMessageBox::Cancel);
        return false;
    }

    // 这里要指定连接
    QSqlQuery query1(db1);
    query1.exec("create table student1 (id int primary key, name varchar(20))");
    query1.exec("insert into student1 values(0, '小米')");
    query1.exec("insert into student1 values(1, '小面')");
    query1.exec("insert into student1 values(2, '小油')");

    // 创建另一个数据库连接，要使用不同的连接名，这里是“connection2”
    QSqlDatabase db2 = QSqlDatabase::addDatabase("QSQLITE", "connection2");
    db2.setDatabaseName("my2.db");
    if (!db2.open()) {
        QMessageBox::critical(0, "无法开启数据库","无法建立数据库连接.", QMessageBox::Cancel);
        return false;
    }

    // 这里要指定连接
    QSqlQuery query2(db2);
    query2.exec("create table student1 (id int primary key, name varchar(20))");
    query2.exec("insert into student1 values(0, '小树')");
    query2.exec("insert into student1 values(1, '小湖')");
    query2.exec("insert into student1 values(2, '小山')");
    return true;
}
#endif // CONNECTION_H
