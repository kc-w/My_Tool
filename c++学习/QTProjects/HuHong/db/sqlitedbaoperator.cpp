#include "sqlitedbaoperator.h"

#include <QMessageBox>



SqliteDBAOperator::SqliteDBAOperator()
{
    QSqlDatabase database;
    if (QSqlDatabase::contains("connect")){
        db = QSqlDatabase::database("connect");
    }else{
        //建立和sqlite数据的连接
        db = QSqlDatabase::addDatabase("QSQLITE","connect");
        //设置数据库文件的名字
        db.setDatabaseName("HuHong");
    }
}


//判断数据库中某个数据表是否存在
bool SqliteDBAOperator::IsTaBexists(QString& Tabname)
{
    if(db.tables().contains(Tabname)){
        return true;
    }
    return false;
}

//初始化数据表
void SqliteDBAOperator::createTable(void)
{
    QString tableName = "User";
    QSqlQuery query(db);
    //如果该表不存在的话就创建新表
    if(!IsTaBexists(tableName)){
        query.exec("create table User (id int primary key,number varchar(10) not null,password varchar(10),permission int not null)");
        query.exec("insert into User values(0, 'admin','123456',0)");
        query.exec("insert into User values(1, 'engineer','666666',1)");
        query.exec("insert into User values(2, 'domestic_consumer','',2)");
        qDebug() << "表不存在创建表!";
    }else{
        qDebug() << "表存在!";
    }
}


//打开数据库
bool SqliteDBAOperator::OpenDb(void)
{
    //打开数据库
    if(db.open()){
        qDebug() << "连接数据库成功!";

        //创建表
        createTable();

        return true;
    }else{
        qDebug() << "连接数据失败!";
        QMessageBox::critical(0, "无法开启数据库","无法建立数据库连接.", QMessageBox::Cancel);
        exit(EXIT_FAILURE);
        exit(1);
    }
}

//查询所有用户名
QList<User> SqliteDBAOperator::selectAllUser()
{
    QSqlQuery query(db);
    query.exec("select * from User");
    while(query.next()){
        user.setId(query.value(0).toInt());
        user.setNumber(query.value(1).toString());
        user.setPassword(query.value(2).toString());
        user.setPermission(query.value(3).toInt());
        users.append(user);
    }
    return users;
}
//登录验证
bool SqliteDBAOperator::checkLogin(QString &number,QString &password)
{
    QSqlQuery query(db);
    query.prepare("select password from User where number = ?");
    query.addBindValue(number);
    query.exec();
    if(query.next()){
        if(query.value(0).toString()==password){
            return true;
        }
        return false;
    }
    return false;
}




void SqliteDBAOperator::addNewcolumn(QString& columnNameAndproperty)
{

}


////单条插入
//void SqliteDBAOperator::singleinsertdata(w2dba &singledb)
//{
//    QSqlQuery query(db);
//    query.prepare("INSERT INTO MACAddrs VALUES (:ID,:Type,:ProduceTime)");
//    query.bindValue(":ID", singledb.id);
//    query.bindValue(":Type", singledb.type);
//    query.bindValue(":ProduceTime",singledb.prodceTime);
//    query.exec();
//}

////多条插入
//void SqliteDBAOperator::Moreinsertdata(QList<w2dba>& moredb)
//{
//    QSqlQuery query(db);
//    query.prepare("insert into MACAddrs values (?,?,?,?,?,?,?,?)");
//    QVariantList idlist,snlist,typelist,MACAddrlsit,produceTimelist,Pieceslist,Operatorlist,OperateStatlist;
//    for(int i=0; i< moredb.size(); i++){
//        idlist <<  moredb.at(i).id;
//        typelist << moredb.at(i).type;
//        produceTimelist << moredb.at(i).prodceTime;
//    }
//    query.addBindValue(idlist);
//    query.addBindValue(MACAddrlsit);
//    query.addBindValue(produceTimelist);

//    if (!query.execBatch()){
//        qDebug() << query.lastError();
//    }
//}

//删除一条数据
void SqliteDBAOperator::deletedata()
{
    //比较简单，自行补充
}

//修改
void SqliteDBAOperator::updatedata()
{
    //比较简单，自行补充

}

void SqliteDBAOperator::closeDb(void)
{
    db.close();
}

/*析构函数关闭数据库连接*/
SqliteDBAOperator::~SqliteDBAOperator()
{
    db.close();
}
