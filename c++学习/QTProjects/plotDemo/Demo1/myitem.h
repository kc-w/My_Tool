#ifndef MYITEM_H
#define MYITEM_H

#include <QGraphicsItem>

//QGraphicsItem是场景中图形项的基类。图形视图框架为典型的形状提供了标准的图形项，比如矩形、椭圆和文本项
//QGraphicsItem支持功能:(鼠标事件),(键盘输入焦点和键盘事件),(拖放事件),(分组:使用QGraphicsItemGroup通过parent-child实现),(碰撞检测)
//图形项还可以存储自定义的数据,可以使用setData()进行数据存储,然后使用data()获取其中的数据
class MyItem : public QGraphicsItem
{
public:
    MyItem();
    QRectF boundingRect() const;
    void paint(QPainter *painter, const QStyleOptionGraphicsItem *option,QWidget *widget);
};

#endif // MYITEM_H
