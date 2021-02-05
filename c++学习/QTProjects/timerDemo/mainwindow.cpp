#include "mainwindow.h"
#include "ui_mainwindow.h"
#include <QDebug>
#include <QTime>
#include <QTimer>

MainWindow::MainWindow(QWidget *parent)
    : QMainWindow(parent)
    , ui(new Ui::MainWindow)
{
    ui->setupUi(this);

    id1 = startTimer(1000);                  // 开启一个1秒定时器，返回其ID
    id2 = startTimer(2000);
    id3 = startTimer(3000);

    QTimer *timer = new QTimer(this);           // 创建一个新的定时器
    // 关联定时器的溢出信号到槽上
    connect(timer, &QTimer::timeout, this, &MainWindow::timerUpdate);
    timer->start(1000);                         // 设置溢出时间为1秒，并启动定时器

    //QTimer类中还有一个singleShot()函数来开启一个只运行一次的定时器,(运行10秒关闭窗口)
    QTimer::singleShot(10000, this, &MainWindow::close);
}
    

MainWindow::~MainWindow()
{
    delete ui;
}

void MainWindow::timerEvent(QTimerEvent *event)
{
    if (event->timerId() == id1) {       // 判断是哪个定时器
        qDebug() << "timer1";
    }
    else if (event->timerId() == id2) {
        qDebug() << "timer2";
    }
    else {
        qDebug() << "timer3";
    }
}

void MainWindow::timerUpdate()                  // 定时器溢出处理
{
    QTime time = QTime::currentTime();      // 获取当前时间
    QString text = time.toString("mm:ss");  // 转换为字符串

    if((time.second() % 2) == 0){
        text[2]=' '; // 每隔一秒就将“：”显示为空格
    }

    ui->lcdNumber->display(text);

    int rand = qrand() % 100;            // 产生300以内的正整数
    ui->lcdNumber->move(rand, rand);

}
