#include "myitem.h"
#include <QPainter>

//自定义图形项
MyItem::MyItem()
{

}

//返回要绘制图形项的矩形区域,超出不显示
QRectF MyItem::boundingRect() const
{
    qreal penWidth = 1;
    return QRectF(0 - penWidth / 2, 0 - penWidth / 2,20 + penWidth, 20 + penWidth);
}
//执行实际的绘图操作
void MyItem::paint(QPainter *painter, const QStyleOptionGraphicsItem *, QWidget *)
{
    painter->setBrush(Qt::red);
    painter->drawRect(0, 0, 20, 20);
}
