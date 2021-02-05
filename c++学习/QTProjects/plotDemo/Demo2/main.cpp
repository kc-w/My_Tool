#include <QApplication>
#include "myitem.h"
#include "myview.h"
#include <QDialog>
#include <QOpenGLWidget>
#include <QPrintDialog>
#include <QPrinter>
#include <QTime>
#include <QTimer>

int main(int argc, char* argv[ ])
{
    QApplication app(argc, argv);
    qsrand(QTime(0, 0, 0).secsTo(QTime::currentTime()));
    QGraphicsScene scene;
    scene.setSceneRect(-200, -150, 400, 300);
    for (int i = 0; i < 5; ++i) {
        MyItem *item = new MyItem;
        item->setColor(QColor(qrand() % 256, qrand() % 256, qrand() % 256));
        item->setPos(i * 50 - 90, -50);
        scene.addItem(item);
    }
    MyView view;

    //使用OpenGL进行渲染
    view.setViewport(new QOpenGLWidget());



    view.setScene(&scene);
    view.setBackgroundBrush(QPixmap(":/image/background.png"));
    view.show();


    //实现动画效果
    QTimer timer;
    //创建定时器(定时器对象,定时器超时动作,场景对象,执行advance()动作)
    QObject::connect(&timer, &QTimer::timeout, &scene, &QGraphicsScene::advance);
    //开启定时器,指定时间
    timer.start(1000);



    //图形项组
    MyItem *item1 = new MyItem;
    item1->setColor(Qt::black);
    MyItem *item2 = new MyItem;
    item2->setColor(Qt::blue);
    QGraphicsItemGroup *group = new QGraphicsItemGroup;
    group->addToGroup(item1);
    group->addToGroup(item2);
    //图像项设置的鼠标移动在图形项组无效,需要单独在图形项组设置支持鼠标移动,鼠标拖动其中一个图形项来一起移动整个图形项组
    group->setFlag(QGraphicsItem::ItemIsMovable);
    item2->setPos(30, 0);
    scene.addItem(group);

    //从图形项组中删除item1图形项,删除掉的图形项会自动加到场景中
    group->removeFromGroup(item1);
    //把图形项组从场景删除,里面的图形项将加入到场景
    scene.destroyItemGroup(group);


    QPixmap pixmap(400, 300);
    QPainter painter(&pixmap);
    painter.setRenderHint(QPainter::Antialiasing);
    //QGraphicsScene::render()和QGraphicsView::render()来完成打印功能
    //这个两个函数提供了相同的API,可以在绘图设备上绘制场景或者视图的全部或者部分内容
    //两者的不同之处就是一个在场景坐标上进行操作而另一个在视图坐标上
    //QGraphicsScene::render()经常用来打印没有变换的场景，比如几何数据和文本文档等
    //QGraphicsView::render()函数适合用来实现屏幕快照
    view.render(&painter);
    painter.end();
    pixmap.save("view.png");


//    //创建打印机对象,需要在.pro头文件中添加"qtHaveModule(printsupport): QT += printsupport"才能导入QPrinter类
//    QPrinter printer;
//    if (QPrintDialog(&printer).exec() == QDialog::Accepted) {
//        QPainter painter(&printer);
//        painter.setRenderHint(QPainter::Antialiasing);
//        scene.render(&painter);
//    }

    return app.exec();
}
