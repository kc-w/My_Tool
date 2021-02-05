#include "form2.h"
#include "ui_form2.h"

#include <QPainter>
#include <QToolTip>
#include <QMouseEvent>
#include <QTimer>

Form2::Form2(QWidget *parent) :
    QWidget(parent),
    ui(new Ui::Form2)
{
    ui->setupUi(this);

    //保证不用按下鼠标按键也能触发鼠标移动事件
    setMouseTracking(true);



}

Form2::~Form2()
{
    delete ui;
}

void Form2::mouseMoveEvent(QMouseEvent *event)
{
    QString pos = QString("%1,%2").arg(event->pos().x()).arg(event->pos().y());
    QToolTip::showText(event->globalPos(), pos, this);
}



void Form2::paintEvent(QPaintEvent *event)
{
    QPainter painter(this);

    //一个QPainterPath对象会以坐标原点为当前点进行绘制，可以随时使用moveTo()函数改变当前点
    QPainterPath path;
    //移动当前点到指定坐标
    path.moveTo(50,250);
    //从当前坐标即绘制一条直线到到指定坐标
    path.lineTo(50, 230);
    //从当前坐标绘制一条三次贝塞尔曲线
    path.cubicTo(QPointF(105, 40), QPointF(115, 80), QPointF(120, 60));

    path.lineTo(130 , 130);
    //向路径中添加一个椭圆
    path.addEllipse(QPoint(130, 130), 30, 30);
    painter.setPen(Qt::darkYellow);
    //绘制路径
    painter.drawPath(path);
    //平移坐标系统后重新绘制路径
    path.translate(200,0);
    painter.setPen(Qt::darkBlue);
    painter.drawPath(path);


}
