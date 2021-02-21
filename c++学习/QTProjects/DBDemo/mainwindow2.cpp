#include "mainwindow2.h"
#include "ui_mainwindow2.h"

#include <QSqlRelationalDelegate>
#include <QSqlRelationalTableModel>
#include <QTableView>

MainWindow2::MainWindow2(QWidget *parent) :
    QMainWindow(parent),
    ui(new Ui::MainWindow2)
{
    ui->setupUi(this);

    //SQL关系表格模型,提供了对外键的支持
    QSqlRelationalTableModel *model = new QSqlRelationalTableModel(this);
    model->setTable("studentX");
    //setRelation()函数用来在两个表之间创建一个关系,2表示studentX表的第3个字段为外键,它映射到了student表中
    //的id字段，而视图需要向用户显示student表中name字段的值。
    model->setRelation(2, QSqlRelation("student", "id", "name"));
    model->select();

    QTableView *view = new QTableView(this);
    view->setModel(model);
    setCentralWidget(view);

    //可以对外键进行选择修改
    view->setItemDelegate(new QSqlRelationalDelegate(view));
}

MainWindow2::~MainWindow2()
{
    delete ui;
}
