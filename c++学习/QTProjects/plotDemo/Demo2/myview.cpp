#include "myview.h"
#include <QKeyEvent>
#include <QGraphicsItem>
#include <QDebug>

MyView::MyView(QWidget *parent) :
    QGraphicsView(parent)
{
    //RubberBandDrag:可以使用鼠标拖出橡皮筋框来选择图形项;ScrollHandDrag:使光标变为手掌形状,拖动场景
    setDragMode(QGraphicsView::RubberBandDrag);
}

//键盘事件监听
void MyView::keyPressEvent(QKeyEvent *event)
{
    switch (event->key())
    {
    //+键放大视图
    case Qt::Key_Plus :
        scale(2, 2);
        break;
    //-键缩小视图
    case Qt::Key_Minus :
        scale(0.5, 0.5);
        break;
    //键盘右键旋转视图
    case Qt::Key_Right :
        rotate(30);
        break;
    }
    //在视图的事件处理函数的最后一定要调用QGraphicsView类的keyPressEvent()函数，不然在场景或者图形项中就无法再接收到该事件
    QGraphicsView::keyPressEvent(event);
}

////添加鼠标按下事件处理函数,单击其中一个图形项时先被此场景事件拦截,需要双击才会将事件传递到图形项事件
////如果设置该监听的话setDragMode(QGraphicsView::RubberBandDrag);拖动选择将失效
//void MyView::mousePressEvent(QMouseEvent *event)
//{
//    // 分别获取鼠标点击处在视图、场景和图形项中的坐标，并输出
//    QPoint viewPos = event->pos();
//    qDebug() << "视图坐标: " << viewPos;
//    //使用映射函数将这个坐标转换为了场景中的坐标，并使用scene()函数获取视图当前的场景的指针
//    QPointF scenePos = mapToScene(viewPos);
//    qDebug() << "场景坐标: " << scenePos;
//    //使用QGraphicsScene::itemAt()函数获取了场景中该坐标处的图形项
//    QGraphicsItem *item = scene()->itemAt(scenePos, QTransform());
//    if (item) {
//        QPointF itemPos = item ->mapFromScene(scenePos);
//        qDebug() << "图形项坐标: " << itemPos;
//    }
//}

