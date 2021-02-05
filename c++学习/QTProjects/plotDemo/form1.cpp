#include "form1.h"
#include "ui_form1.h"

#include <QPainter>
#include <QToolTip>
#include <QMouseEvent>
#include <QTimer>

Form1::Form1(QWidget *parent) :
    QWidget(parent),
    ui(new Ui::Form1)
{
    ui->setupUi(this);

    //保证不用按下鼠标按键也能触发鼠标移动事件
    setMouseTracking(true);


    QTimer * timer = new QTimer(this);
    connect(timer, SIGNAL(timeout()), this ,SLOT(update()));
    timer -> start(1000);
    angle = 0;
}


Form1::~Form1()
{
    delete ui;
}


void Form1::mouseMoveEvent(QMouseEvent *event)
{
    QString pos = QString("%1,%2").arg(event->pos().x()).arg(event->pos().y());
    QToolTip::showText(event->globalPos(), pos, this);
}



void Form1::paintEvent(QPaintEvent *event)
{
    QPainter painter(this);

    int side = qMin(width(), height());
    int x = (width() / 2);
    int y = (height() / 2);
    // 设置视口
    painter.setViewport(x, y, side, side);
    painter.setWindow(0, 0, 100, 100);
    painter.setBrush(Qt::green);
    painter.drawRect(0, 0, 20, 20);


    angle += 10;
    if(angle == 360){
        angle = 0;
    }
    int side1 = qMin(width(), height());
    QPainter painter1(this);
    painter1.setRenderHint(QPainter::Antialiasing);
    //当连续进行多个坐标转换时使用这个类更高效
    QTransform transform;
    transform.translate(width()/2, height()/2);
    transform.scale(side1/300.0, side1/300.0);
    transform.rotate(angle);
    painter1.setWorldTransform(transform);
    painter1.drawEllipse(-120, -120, 240, 240);
    painter1.drawLine(0, 0, 100, 0);
}






