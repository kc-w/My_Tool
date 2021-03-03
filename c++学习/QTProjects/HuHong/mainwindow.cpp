#include "mainwindow.h"
#include "ui_mainwindow.h"

#include <QFrame>
#include <QMessageBox>
#include <QDebug>
#include <QHeaderView>
#include <QHeaderView>
#include <QStyleFactory>

MainWindow::MainWindow(QWidget *parent)
    : QMainWindow(parent)
    , ui(new Ui::MainWindow)
{
    ui->setupUi(this);


    ui->dateEdit->setDate(QDate::currentDate());
    ui->dateEdit_2->setDate(QDate::currentDate());

    //在widget的构造函数绑定的clicked信号
    connect(ui->frame, SIGNAL(clicked()), this, SLOT(frameClick1()));
    connect(ui->frame_2, SIGNAL(clicked()), this, SLOT(frameClick2()));
    connect(ui->frame_3, SIGNAL(clicked()), this, SLOT(frameClick3()));
    connect(ui->frame_4, SIGNAL(clicked()), this, SLOT(frameClick4()));
    connect(ui->frame_5, SIGNAL(clicked()), this, SLOT(frameClick5()));
    connect(ui->frame_6, SIGNAL(clicked()), this, SLOT(frameClick6()));
    connect(ui->frame_7, SIGNAL(clicked()), this, SLOT(frameClick7()));
    connect(ui->frame_8, SIGNAL(clicked()), this, SLOT(frameClick8()));
    connect(ui->frame_9, SIGNAL(clicked()), this, SLOT(frameClick9()));

    //绘制表格
    drawTable();

}

//连接仪器
void MainWindow::frameClick1()
{
    cleanStyle();
    ui ->frame -> setStyleSheet("background-color: rgb(0, 255, 0);");

}
//初始化
void MainWindow::frameClick2()
{
    cleanStyle();
    ui ->frame_2 -> setStyleSheet("background-color: rgb(0, 255, 0);");

}
//主界面
void MainWindow::frameClick3()
{
    cleanStyle();
    ui->stackedWidget->setCurrentWidget(ui->page_3);
    ui ->frame_3 -> setStyleSheet("background-color: rgb(0, 255, 0);");

}
//标本编辑
void MainWindow::frameClick4()
{
    cleanStyle();
    ui->stackedWidget->setCurrentWidget(ui->page_4);
    ui ->frame_4 -> setStyleSheet("background-color: rgb(0, 255, 0);");

}
//运行
void MainWindow::frameClick5()
{
    cleanStyle();
    ui ->frame_5 -> setStyleSheet("background-color: rgb(0, 255, 0);");

}
//查询报告
void MainWindow::frameClick6()
{
    cleanStyle();
    ui->stackedWidget->setCurrentWidget(ui->page_6);
    ui ->frame_6 -> setStyleSheet("background-color: rgb(0, 255, 0);");

}
//系统管理
void MainWindow::frameClick7()
{
    cleanStyle();
    ui->stackedWidget->setCurrentWidget(ui->page_7);
    ui ->frame_7 -> setStyleSheet("background-color: rgb(0, 255, 0);");

}
//关于仪器
void MainWindow::frameClick8()
{
    cleanStyle();
    ui->stackedWidget->setCurrentWidget(ui->page_8);
    ui ->frame_8 -> setStyleSheet("background-color: rgb(0, 255, 0);");

}
//重新登陆
void MainWindow::frameClick9()
{
    cleanStyle();
    ui ->frame_9 -> setStyleSheet("background-color: rgb(0, 255, 0);");
}


void MainWindow::cleanStyle(){
    ui ->frame -> setStyleSheet("");
    ui ->frame_2 -> setStyleSheet("");
    ui ->frame_3 -> setStyleSheet("");
    ui ->frame_4 -> setStyleSheet("");
    ui ->frame_5 -> setStyleSheet("");
    ui ->frame_6 -> setStyleSheet("");
    ui ->frame_7 -> setStyleSheet("");
    ui ->frame_8 -> setStyleSheet("");
    ui ->frame_9 -> setStyleSheet("");
}

void MainWindow::drawTable()
{

    ui->tableWidget->setRowCount(20);
    ui->tableWidget->setColumnCount(12);

    ui->tableWidget->horizontalHeader()->setSectionResizeMode(QHeaderView::Stretch);
    ui->tableWidget->verticalHeader()->setSectionResizeMode(QHeaderView::Stretch);
    QApplication::setStyle(QStyleFactory::create("Fusion"));

}

MainWindow::~MainWindow()
{
    delete ui;
}





void MainWindow::on_pushButton_clicked()
{

}

void MainWindow::on_pushButton_2_clicked()
{

}
