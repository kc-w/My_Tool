#include "logindialog.h"
#include "mainwindow.h"
#include <QDebug>

#include <QApplication>


int main(int argc, char *argv[])
{

    QApplication a(argc, argv);
    MainWindow w;
    LoginDialog dlg;


    //弹出窗口判断是否登录
    if (dlg.exec() == QDialog::Accepted){

        //创建数据库连接读取数据
        SqliteDBAOperator db;
        db.OpenDb();
        QString number = dlg.getNumber();
        QString password = dlg.getPassword();
        //用户名密码正确完成登录否则弹出消息框提示
        if(!db.checkLogin(number,password)){


            db.closeDb();
        }else {
            w.show();
            db.closeDb();

            return a.exec();
        }

    }else{
        //如果没有被按下，则不会进入主窗口，整个程序结束运行
        return 0;
    }

}


