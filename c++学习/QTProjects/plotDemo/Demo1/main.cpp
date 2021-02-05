#include <QApplication>
#include <QGraphicsScene>
#include <QGraphicsRectItem>
#include <QDebug>
#include <QGraphicsView>

#include "myitem.h"
#include "myview.h"

int main(int argc,char* argv[ ])
{
    QApplication app(argc,argv);
    // 新建场景,QGraphicsScene的事件传播构架可以将场景事件传递给图形项，也可以管理图形项之间事件的传播和管理图形项的状态
    QGraphicsScene scene;

    // 设置场景的前景色(将作用于所有视图,无论是view1或view2)
    scene.setForegroundBrush(QColor(255, 255, 0, 100));
    // 设置场景的背景图片
    scene.setBackgroundBrush(QPixmap(":/image/background.png"));

    //=================================================
    // 创建矩形图形项
    QGraphicsRectItem *item = new QGraphicsRectItem(0, 0, 100, 100);
    item->setPen(QColor(Qt::red));

    // 将图形项添加到场景中
    scene.addItem(item);
    // 输出(50, 50)点处的图形项
    qDebug() << scene.itemAt(50, 50, QTransform());

    // 为场景创建视图,QGraphicsView提供了视图部件，它用来使场景中的内容可视化
    QGraphicsView view1(&scene);

    //RubberBandDrag:可以使用鼠标拖出橡皮筋框来选择图形项;ScrollHandDrag:使光标变为手掌形状,拖动场景
    view1.setDragMode(QGraphicsView::RubberBandDrag);
    // 设置视图的前景色(将作用于当前视图)
    view1.setForegroundBrush(QColor(255, 255, 0, 100));
    // 设置视图的背景图片
    view1.setBackgroundBrush(QPixmap(":/image/background.png"));
    //设置视图显示大小
    view1.resize(400, 300);
    //显示视图
    view1.show();

    QGraphicsView view2(&scene);
    view2.resize(400, 300);
    view2.show();


//    //================================================
//    // 创建矩形图形项
//    MyItem *item = new MyItem;

//    // 将图形项添加到场景中
//    scene.addItem(item);
//    // 输出(50, 50)点处的图形项
//    qDebug() << scene.itemAt(50, 50, QTransform());

//    // 为场景创建视图,QGraphicsView提供了视图部件，它用来使场景中的内容可视化
//    QGraphicsView view1(&scene);
//    //设置视图显示大小
//    view1.resize(400, 300);
//    //显示视图
//    view1.show();

//    QGraphicsView view2(&scene);
//    view2.resize(400, 300);
//    view2.show();

//    //=======================================================
//    MyItem *item = new MyItem;
//    //场景加入图形项
//    scene.addItem(item);
//    //设置图形项在场景的起始坐标
//    item->setPos(10, 10);
//    //设置z覆盖为1
//    item ->setZValue(1);
//    //添加矩形图形项
//    QGraphicsRectItem *rectItem = scene.addRect(QRect(0, 0, 100, 100),QPen(Qt::blue), QBrush(Qt::green));
//    //item在rectItem之前添加到场景中,所以rectItem会在item之上进行绘制,如果设置setZValue属性则数值越大覆盖高度越高
//    rectItem->setPos(20, 20);

//    rectItem -> setZValue(0);

//    //为item图形项设置父图形
//    item ->setParentItem(rectItem);
//    //rectItem图形项以图形项原点旋转45度
//    rectItem ->setRotation(45);

//    //打印场景中指定坐标处的图形项相关的信息
//    qDebug() << scene.itemAt(20, 90, QTransform());
//    qDebug() << scene.itemAt(42, 98, QTransform());
//    qDebug() << scene.itemAt(21, 42, QTransform());
//    qDebug() << scene.itemAt(29, 54, QTransform());

//    //新建视图
//    MyView view;
//    //在视图中设置场景
//    view.setScene(&scene);
//    view.setForegroundBrush(QColor(255, 255, 0, 100));
//    view.setBackgroundBrush(QPixmap(":/image/background.png"));
//    view.resize(400, 300);
//    view.show();


    return app.exec();
}
