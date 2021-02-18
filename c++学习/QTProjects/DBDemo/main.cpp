#include <QApplication>
#include <QSqlDatabase>
#include <QDebug>
#include <QStringList>
#include <QVariant>
#include "connection.h"
#include <QSqlDriver>
#include <QSqlRecord>
#include <QSqlField>
#include <QSqlError>

int main(int argc, char *argv[])
{
    QApplication a(argc, argv);
    qDebug() << "可用的驱动列表:";
    QStringList drivers = QSqlDatabase::drivers();
    foreach(QString driver, drivers)
        qDebug() << driver;



    // 创建数据库连接
    if (!createConnection1()) {
        return 1;
    }

    // 使用QSqlQuery查询整张表
    QSqlQuery query;
    query.exec("select * from student");
    while(query.next())
    {
        qDebug() << query.value(0).toInt() << query.value(1).toString();
    }



    // 创建数据库连接
    if (!createConnection2()) {
        return 1;
    }

    // 使用QSqlQuery查询连接1的整张表，先要使用连接名获取该连接
    QSqlDatabase db1 = QSqlDatabase::database("connection1");
    QSqlQuery query1(db1);
    qDebug() << "connection1:";
    query1.exec("select * from student1");
    while(query1.next())
    {
        qDebug() << query1.value(0).toInt() << query1.value(1).toString();
    }

    // 使用QSqlQuery查询连接2的整张表
    QSqlDatabase db2 = QSqlDatabase::database("connection2");
    QSqlQuery query2(db2);
    qDebug() << "connection2:";
    query2.exec("select * from student1");
    while(query2.next())
    {
        qDebug() << query2.value(0).toInt() << query2.value(1).toString();
    }



//==============数据查询
    int numRows;

    // 先判断该数据库驱动是否支持QuerySize特性，如果支持，则可以使用size()函数，
    // 如果不支持，那么就使用其他方法来获取总行数
    if (db2.driver()->hasFeature(QSqlDriver::QuerySize)) {
        qDebug() << "支持QuerySize特性";
        numRows = query2.size();
    } else {
        qDebug() << "不支持QuerySize特性";
        query2.last();
        numRows = query2.at() + 1;
    }
    qDebug() << "记录总数: " << numRows;

    // 指向索引为1的记录，即第二条记录
    query2.seek(1);

    // 返回当前索引值
    qDebug() << "返回当前索引: " << query2.at();

    // 获取当前行的记录
    QSqlRecord record = query2.record();

    // 获取记录中“id”和“name”两个字段的值
    int id = record.value("id").toInt();
    QString name = record.value("name").toString();
    qDebug() << "id: " << id << "name: " << name;

    // 获取索引为1的字段，即第二个字段
    QSqlField field = record.field(1);

    // 输出字段名和字段值，结果为“name”和“MaLiang”
    qDebug() << "字段名: " << field.name()<< "字段值: " << field.value().toString();


    //插入数据
    query2.exec("insert into student1 (id, name) values (3, '小溪')");


//    //名称绑定占位符
//    query2.prepare("insert into student1 (id, name) values (:id ,:name)");
//    int idValue = 100 ;
//    QString nameValue = "ChenYun";
//    query2.bindValue(":id", idValue);
//    query2. bindValue(":name", nameValue);
//    query2.exec();

//    //位置绑定占位符
//    query2. prepare(" insert into student1 (id, name) values (?, ?)");
//    int idValue1 = 100;
//    QString nameValue1 = "ChenYun";
//    query2.addBindValue(idValue1);
//    query2.addBindValue(nameValue1);
//    query2.exec();


    //使用占位符插入数据
    query2.prepare("insert into student1 (id, name) values (?, ?)");
    QVariantList ids;
    ids << 4 << 5 << 6;
    query2.addBindValue(ids);
    QVariantList names;
    names << "小一" << "小二" << "小三";
    query2.addBindValue(names);

    if(!query2.execBatch()){
        qDebug() << query2.lastError();
    }

    // 更新
    query2.exec("update student1 set name = '更名' where id = 6");
    // 删除
    query2.exec("delete from student1 where id = 5");


    query2.exec("select * from student1");
    while(query2.next())
    {
        qDebug() << query2.value(0).toInt() << query2.value(1).toString();
    }


//=======================事务
//    //开启一个事务
//    QSqlDatabase::database().transaction();
//    QSqlQuery querys;
//    querys.exec("SELECT id FROM employee WHERE name ='Torild Halvorsen'");
//    if (querys.next()) {
//        int employeeId = querys.value(0).toInt();
//        querys.exec("INSERT INTO project (id,name,ownerid) VALUES (201, 'Manhattan Project',"
//        + QString::number(employeeId) + ')');
//    }
////    //事务回滚
////    QSqlDatabase::database().rollback();
//    //提交事务
//    QSqlDatabase::database().commit();



    return a.exec();
}
