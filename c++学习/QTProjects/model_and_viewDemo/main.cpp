#include "mainwindow.h"
#include "mainwindow1.h"
#include "mainwindow2.h"
#include "stringlistmodel.h"

#include <QApplication>
#include <QFileSystemModel>
#include <QTreeView>
#include <QListView>
#include <QStandardItem>
#include <QDebug>
#include <QTableView>
#include <QListWidget>
#include <QTreeWidget>
#include <QTableWidget>


int main(int argc, char *argv[])
{
    QApplication a(argc, argv);
//    MainWindow w;
//    MainWindow1 w;
    MainWindow2 w;
    w.show();


//////////////////////////////////////////////////////////


    // 创建文件系统模型
    QFileSystemModel model;

    // 指定要监视的目录
    model.setRootPath(QDir::currentPath());

    // 创建树型视图
    QTreeView tree;
    // 为视图指定模型
    tree.setModel(&model);
    // 指定根索引
    tree.setRootIndex(model.index(QDir::currentPath()));
    tree.show();


    // 创建列表视图
    QListView list;
    list.setModel(&model);
    list.setRootIndex(model.index(QDir::currentPath()));
    list.show();

///////////////////////////////////////////////////////////////////////



//    Qt::DisplayRole     数据被渲染为文本（数据为QString类型）
//    Qt::DecorationRole  数据被谊染为图标等装饰（数据为QColor.QIcon或者QPixmap类型）
//    Qt::EditRole        数据可以在编辑器中进行编辑（数据为QString类型）
//    Qt::ToolTipRole     数据显示在数据项的工具提示中（数据为QString类型）
//    Qt::StatusTipRole   数据显示在状态栏中（数据为QString类型）
//    Qi::WhatsThisRole   数据显示在数据项的"What's This?"模式下（数据为QString类型）
//    Qt::SizeHintRole    数据项的大小提示，将会应用到视图（数摇为QSize类型）


    // 创建标准项模型
    QStandardItemModel model1;

    // 获取模型的根项（Root Item），根项是不可见的
    QStandardItem *parentItem = model1.invisibleRootItem();

    // 创建标准项item0，并设置显示文本，图标和工具提示
    QStandardItem *item0 = new QStandardItem;
    item0->setText("A");
    QPixmap pixmap0(50, 50);
    pixmap0.fill("red");
    item0->setIcon(QIcon(pixmap0));
    item0->setToolTip("indexA");

    // 将创建的标准项作为根项的子项
    parentItem->appendRow(item0);

    // 将创建的标准项作为新的父项
    parentItem = item0;

    // 创建新的标准项，它将作为item0的子项
    QStandardItem *item1 = new QStandardItem;
    item1->setText("B");
    QPixmap pixmap1(50,50);
    pixmap1.fill("blue");
    item1->setIcon(QIcon(pixmap1));
    item1->setToolTip("indexB");
    parentItem->appendRow(item1);

    // 创建新的标准项，这里使用了另一种方法来设置文本、图标和工具提示
    QStandardItem *item2 = new QStandardItem;
    QPixmap pixmap2(50,50);
    pixmap2.fill("green");
    item2->setData("C", Qt::EditRole);//数据可以在编辑器中进行编辑
    item2->setData("indexC", Qt::ToolTipRole);//数据显示在数据项的工具提示中
    item2->setData(QIcon(pixmap2), Qt::DecorationRole);//数据被谊染为图标等装饰（数据为QColor.QIcon或者QPixmap类型）
    parentItem->appendRow(item2);

    // 在树视图中显示模型
    QTreeView view;
    view.setModel(&model1);
    view.show();

    // 获取item0的索引并输出item0的子项数目，然后输出了item1的显示文本和工具提示
    QModelIndex indexA = model1.index(0, 0, QModelIndex());
    qDebug() << "indexA row count: " << model1.rowCount(indexA);
    QModelIndex indexB = model1.index(0, 0, indexA);
    qDebug() << "indexB text: " << model1.data(indexB, Qt::EditRole).toString();
    qDebug() << "indexB toolTip: "<< model1.data(indexB, Qt::ToolTipRole).toString();


///////////////////////////////////////////////////////////////


    QStringList list1;
    list1 << "a" << "b" << "c";
    StringListModel model2(list1);

    QListView listView;
    listView.setModel(&model2);
    listView.show();

    QTableView tableView;
    tableView.setModel(&model2);
    tableView.show();
    //在第0行的位置插入3行
    model2.insertRows(0, 3);
    //在第0行的位置删除1行
    model2.removeRows(0, 1);



/////////////////////////////////////////////////////////




    QListWidget listWidget;
    // 一种添加项目的简便方法
    new QListWidgetItem("a", &listWidget);

    // 添加项目的另一种方法，这样还可以进行各种设置
    QListWidgetItem *listWidgetItem = new QListWidgetItem;
    listWidgetItem->setText("b");
    listWidgetItem->setIcon(QIcon(":/image/background.png"));
    listWidgetItem->setToolTip("this is b!");
    listWidget.insertItem(1, listWidgetItem);

    // 设置排序为倒序
    listWidget.sortItems(Qt::DescendingOrder);

    // 显示列表部件
    listWidget.show();



    QTreeWidget treeWidget;

    // 必须设置列数
    treeWidget.setColumnCount(2);

    // 设置标头
    QStringList headers;
    headers << "name" << "year";
    treeWidget.setHeaderLabels(headers);

    // 添加项目
    QTreeWidgetItem *grade1 = new QTreeWidgetItem(&treeWidget);
    grade1->setText(0, "Grade1");
    QTreeWidgetItem *student = new QTreeWidgetItem(grade1);
    student->setText(0, "Tom");
    student->setText(1, "1986");
    QTreeWidgetItem *grade2 = new QTreeWidgetItem(&treeWidget, grade1);
    grade2->setText(0, "Grade2");
    treeWidget.show();


    // 创建表格部件，同时指定行数和列数
    QTableWidget tableWidget(3, 2);

    // 创建表格项目，并插入到指定单元
    QTableWidgetItem *tableWidgetItem = new QTableWidgetItem("qt");
    tableWidget.setItem(1, 1, tableWidgetItem);

    // 创建表格项目，并将它们作为标头
    QTableWidgetItem *headerV = new QTableWidgetItem("first");
    tableWidget.setVerticalHeaderItem(0, headerV);
    QTableWidgetItem *headerH = new QTableWidgetItem("ID");
    tableWidget.setHorizontalHeaderItem(0, headerH);
    tableWidget.show();


    // 设置选择模式为单选
    listWidget.setSelectionMode(QAbstractItemView::SingleSelection);
    // 启用拖动
    listWidget.setDragEnabled(true);
    // 设置接受拖放
    listWidget.viewport()->setAcceptDrops(true);
    // 设置显示将要被放置的位置
    listWidget.setDropIndicatorShown(true);
    // 设置拖放模式为移动项目，如果不设置，默认为复制项目
    listWidget.setDragDropMode(QAbstractItemView::InternalMove);



    return a.exec();
}
