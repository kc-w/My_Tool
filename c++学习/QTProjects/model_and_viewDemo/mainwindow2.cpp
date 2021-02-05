#include "mainwindow2.h"
#include "ui_mainwindow2.h"
#include <QDataWidgetMapper>
#include <QStandardItemModel>



//数据窗口映射
MainWindow2::MainWindow2(QWidget *parent) :
    QMainWindow(parent),
    ui(new Ui::MainWindow2)
{
    ui->setupUi(this);

    QStandardItemModel *model = new QStandardItemModel(3, 2, this);
    model->setItem(0, 0, new QStandardItem("xiaoming"));
    model->setItem(0, 1, new QStandardItem("90"));
    model->setItem(1, 0, new QStandardItem("xiaogang"));
    model->setItem(1, 1, new QStandardItem("75"));
    model->setItem(2, 0, new QStandardItem("xiaohong"));
    model->setItem(2, 1, new QStandardItem("80"));
    mapper = new QDataWidgetMapper(this);

    // 设置模型
    mapper->setModel(model);

    // 设置窗口部件和模型中的列的映射
    mapper->addMapping(ui->lineEdit, 0);
    mapper->addMapping(ui->lineEdit_2, 1);

    // 显示模型中的第一行
    mapper->toFirst();


}

MainWindow2::~MainWindow2()
{
    delete ui;
}


// 上一条按钮
void MainWindow2::on_pushButton_clicked()
{
    mapper->toPrevious();
}
// 下一条按钮
void MainWindow2::on_pushButton_2_clicked()
{
    mapper->toNext();
}
