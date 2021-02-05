#include "myview.h"
#include <QMouseEvent>
#include <QGraphicsItem>
#include <QDebug>

MyView::MyView(QWidget *parent) :
    QGraphicsView(parent)
{
}

//添加鼠标按下事件处理函数
void MyView::mousePressEvent(QMouseEvent *event)
{
    // 分别获取鼠标点击处在视图、场景和图形项中的坐标，并输出
    QPoint viewPos = event->pos();
    qDebug() << "视图坐标: " << viewPos;
    //使用映射函数将这个坐标转换为了场景中的坐标，并使用scene()函数获取视图当前的场景的指针
    QPointF scenePos = mapToScene(viewPos);
    qDebug() << "场景坐标: " << scenePos;
    //使用QGraphicsScene::itemAt()函数获取了场景中该坐标处的图形项
    QGraphicsItem *item = scene()->itemAt(scenePos, QTransform());
    if (item) {
        QPointF itemPos = item->mapFromScene(scenePos);
        qDebug() << "图形项坐标: " << itemPos;
    }
}

