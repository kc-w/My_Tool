#include "mainwindow1.h"
#include "ui_mainwindow1.h"

#include <QSqlQueryModel>
#include <QTableView>

MainWindow1::MainWindow1(QWidget *parent) :
    QMainWindow(parent),
    ui(new Ui::MainWindow1)
{
    ui->setupUi(this);

    //使用查询模型,不可进行修改操作
    QSqlQueryModel *model1 = new QSqlQueryModel(this);

    model1->setHeaderData(0, Qt::Horizontal, tr("id"));
    model1->setHeaderData(1, Qt::Horizontal, tr("姓名"));


    model1 ->setQuery("insert into student values(5,'薛静')");
    model1 ->setQuery("select * from student");


    QTableView *view = new QTableView(this);
    view->setModel(model1);

    setCentralWidget(view);

}

MainWindow1::~MainWindow1()
{
    delete ui;
}
