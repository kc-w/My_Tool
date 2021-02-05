#include "myitem.h"
#include <QPainter>
#include <QCursor>
#include <QKeyEvent>
#include <QGraphicsSceneHoverEvent>
#include <QGraphicsSceneContextMenuEvent>
#include <QMenu>
#include <QGraphicsEffect>

MyItem::MyItem()
{
    brushColor = Qt::red;
    //开启图形项的特殊功能,使图形项可以获得焦点
    setFlag(QGraphicsItem::ItemIsFocusable);
    //开启图形项的特殊功能,使图形项可以拖动
    setFlag(QGraphicsItem::ItemIsMovable);
    //使图形项支持悬停
    setAcceptHoverEvents(true);

}

//返回图形项边界
QRectF MyItem::boundingRect() const
{
    //在Arm平台上,qreal相当于float,其他情况下qreal和double是一样的
    //qreal adjust = 0.5;
    return QRectF( 0, 0, 20 , 20);
}

//绘制图形项
void MyItem::paint(QPainter *painter, const QStyleOptionGraphicsItem *,QWidget *)
{
    //判断获取到了焦点或者检测指定的图形项碰撞设置画笔,
    if (hasFocus() || !collidingItems().isEmpty()) {
        painter->setPen(QPen(QColor(255, 255, 255, 200)));
    } else {
        painter->setPen(QPen(QColor(100, 100, 100, 100)));
    }
    //设置图形项默认填充色,必须设置
    painter->setBrush(brushColor);
    painter->drawRect(0, 0, 20, 20);


}

// 鼠标按下事件处理函数，设置被点击的图形项获得焦点，并改变光标外观
void MyItem::mousePressEvent(QGraphicsSceneMouseEvent *)
{
    //设置获取焦点
    setFocus();
    //设置光标为抓取样式
    setCursor(Qt::ClosedHandCursor);
}

// 键盘按下事件处理函数，判断是否是向下方向键，如果是，则向下移动图形项
void MyItem::keyPressEvent(QKeyEvent *event)
{
    if (event->key() == Qt::Key_Down){
        moveBy(0, 10);
    }

    switch (event->key()){
    case Qt::Key_1 : {
        //模糊效果
        QGraphicsBlurEffect *blurEffect = new QGraphicsBlurEffect;
        //指定模糊怎样来执行
        blurEffect->setBlurHints(QGraphicsBlurEffect::QualityHint);
        //设置模糊像素的半径
        blurEffect->setBlurRadius(8);
        setGraphicsEffect(blurEffect);
        break;
    }
    case Qt::Key_2 : {
        //染色效果
        QGraphicsColorizeEffect *colorizeEffect = new QGraphicsColorizeEffect;
        //设置要染成的颜色
        colorizeEffect->setColor(Qt::white);
        //修改染色效果的强度
        colorizeEffect->setStrength(0.6);
        setGraphicsEffect(colorizeEffect);
        break;
    }
    case Qt::Key_3 : {
        //阴影效果
        QGraphicsDropShadowEffect *dropShadowEffect = new QGraphicsDropShadowEffect;
        //设置阴影的颜色
        dropShadowEffect->setColor(QColor(63, 63, 63, 100));
        //修改阴影的模糊半径默认值为1
        dropShadowEffect->setBlurRadius(2);
        //修改阴影偏移值,默认为右下角8像素
        dropShadowEffect->setOffset(10);
        setGraphicsEffect(dropShadowEffect);
        break;
    }
    case Qt::Key_4 : {
        //透明效果
        QGraphicsOpacityEffect *opacityEffect = new QGraphicsOpacityEffect;
        //修改透明度
        opacityEffect->setOpacity(0.4);
        setGraphicsEffect(opacityEffect);
        break;
    }
    //关闭所有图形效果
    case Qt::Key_5 :
        graphicsEffect()->setEnabled(false);
        break;
    }

}

// 悬停事件处理函数，设置光标外观和提示
void MyItem::hoverEnterEvent(QGraphicsSceneHoverEvent *)
{
    //设置光标为手掌样式
    setCursor(Qt::OpenHandCursor);
    //弹出小标签
    setToolTip("I am item");
}

// 右键菜单事件处理函数，为图形项添加一个右键菜单
void MyItem::contextMenuEvent(QGraphicsSceneContextMenuEvent *event)
{
    QMenu menu;
    QAction *moveAction = menu.addAction("来到中心");
    QAction *selectedAction = menu.exec(event->screenPos());
    if (selectedAction == moveAction) {
        //重新设置图形项的原点坐标
        setPos(-10, -10);
    }
}

//通过动画框架来实现动画效果
void MyItem::advance(int phase)
{
    //图形项的advance()函数会被分为两个阶段调用两次
    // 在第一个阶段不进行处理,phase为0
    if (!phase){
        return;
    }
    //第二次phase为1,在这时才进行具体的操作,图形项向不同方向随机移动
    int value = qrand() % 100;
    if (value < 25) {
        setRotation(45);
        moveBy(qrand() % 10, qrand() % 10);
    } else if (value < 50) {
        setRotation(-45);
        moveBy(- qrand() % 10, - qrand() % 10);
    } else if (value < 75) {
        setRotation(30);
        moveBy(- qrand() % 10, qrand() % 10);
    } else {
        setRotation(-30);
        moveBy(qrand() % 10, - qrand() % 10);
    }
}

//视图碰撞检测
//重新实现QGraphicsItem::shap()函数来返回图形项准确的形状,然后使用默认的collidesWithItem()函数通过两个图形项形状之间的交集来判断是否发生碰撞
//如果图形项的形状很复杂,那么进行这个操作是非常耗时的,如果没有重新实现shape()函数,那么它默认会调用boundingRect()函数返回一个简单的矩形
QPainterPath MyItem::shape()
{
    QPainterPath path;
    path.addRect(0, 0, 20, 20);
    return path;
}
