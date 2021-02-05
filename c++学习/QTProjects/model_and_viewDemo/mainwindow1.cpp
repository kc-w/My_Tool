#include "mainwindow1.h"
#include "ui_mainwindow1.h"

#include <QSortFilterProxyModel>
#include <QStringListModel>

MainWindow1::MainWindow1(QWidget *parent) :
    QMainWindow(parent),
    ui(new Ui::MainWindow1)
{
    ui->setupUi(this);


    QStringList list;
    list << "yafei" << "yafeilinux" << "Qt" << "Qt Creator";
    QStringListModel *listModel = new QStringListModel(list, this);
    filterModel = new QSortFilterProxyModel(this);

    // 为代理模型添加源模型
    filterModel->setSourceModel(listModel);

    // 在视图中使用代理模型
    ui->listView->setModel(filterModel);



}

MainWindow1::~MainWindow1()
{
    delete ui;
}

void MainWindow1::on_pushButton_clicked()
{
    QRegExp rx(ui->lineEdit->text());
    filterModel->setFilterRegExp(rx);
}
