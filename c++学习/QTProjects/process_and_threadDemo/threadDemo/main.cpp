#include <QtCore>
#include <stdio.h>
#include <stdlib.h>
#include <QDebug>

bool flag1 = true;
bool flag2 = true;

// 生产者
class Thread1 : public QThread
{
public:
    void run();
    void setFlag();
};

void Thread1::run()
{
    while (flag1) {
        qDebug()<<"线程1执行一次";
        msleep(1000);
    }
}

void Thread1::setFlag()
{
    flag1=false;
}

// 消费者
class Thread2 : public QThread
{
public:
    void run();
    void setFlag();
};

void Thread2::run()
{
    while (flag2) {
        qDebug()<<"线程2执行一次";
        msleep(1000);
    }
}

void Thread2::setFlag()
{
    flag2=false;
}


int main(int argc, char *argv[])
{
    QCoreApplication app(argc, argv);
    Thread1 t1;
    Thread2 t2;
    t1.start();
    t2.start();
    //阻塞主线程
    t1.wait();
    t2.wait();
    qDebug()<<"主线程结束";
    return app.exec();
}
