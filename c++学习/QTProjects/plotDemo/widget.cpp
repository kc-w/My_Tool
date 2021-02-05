#include "widget.h"
#include "ui_widget.h"
#include <QPainter>
#include <QToolTip>
#include <QMouseEvent>
#include <QTimer>


Widget::Widget(QWidget *parent) :
    QWidget(parent),
    ui(new Ui::Widget)
{
    ui->setupUi(this);

}

Widget::~Widget()
{
    delete ui;
}


//drawArc() 绘制圆弧 drawPoint() 绘制点
//drawChord() 绘制弦 drawPolygon() 绘制多边形
//drawConvexPolygon() 绘制凸多边形 drawPoiyline() 绘制折线
//drawEllipse() 绘制椭圆 drawRect() 绘制矩形
//drawLine() 绘制线条 drawRoundedRect() 绘制圆角矩形
//drawPie() 绘制扇形

void Widget::paintEvent(QPaintEvent *event)
{
    QPainter painter(this);



    //==绘制线条(起点坐标,终点坐标)
    painter.drawLine(QPoint(0, 0), QPoint(100, 100));

    //创建画笔(颜色、线宽、画笔风格、画笔端点风格,画笔连接风格)
    //可以分别使用 setBrush()、setWidth ()、setStyle() ,setCapStyle()和 setjoinStyle()等函数进行设置
    QPen pen(Qt::red, 5, Qt::DotLine, Qt::RoundCap, Qt::RoundJoin);
    //使用画笔
    painter.setPen(pen);
    //(x坐标,y坐标,长度,宽度)
    QRectF rectangle(150, 0, 60.0, 60.0);
    int startAngle = 0 * 16;
    int spanAngle = 360 * 16;
    //==绘制圆弧(矩形,起始角度,跨越角度)
    painter.drawArc(rectangle, startAngle, spanAngle);

    //重新设置画笔
    pen.setWidth(1);//设置线条宽度
    pen.setStyle(Qt::SolidLine);//设置线条为实线
    painter.setPen(pen);
    //==绘制一个矩形
    painter.drawRect(300, 20, 50, 40);

    // 创建画刷(QBrush类提供了画刷来对图形进行填充)
    QBrush brush(QColor(100, 255, 255), Qt::SolidPattern);
    // 使用画刷
    painter.setBrush(brush);
    //==绘制椭圆
    painter.drawEllipse(400, 20, 50, 100);



    //设置纹理
    brush.setTexture(QPixmap("../Demo8/yafeilinux.png"));
    // 重新使用画刷
    painter.setBrush(brush);
    // 定义多个点
    static const QPointF points[5] = {
        QPointF(500.0, 80.0),
        QPointF(600.0, 10.0),
        QPointF(700.0, 110.0),
        QPointF(600.0, 130.0),
        QPointF(500.0, 100.0),
    };
    //==使用多个点绘制多边形
    painter.drawPolygon(points, 5);



    //==使用画刷填充一个矩形区域
    painter.fillRect(QRect(10, 200, 150, 30), QBrush(Qt::blue));

    //==擦除一个矩形区域的内容
    painter.eraseRect(QRect(100, 210, 50, 10));


    //==线性渐变(指定开始点和结束点)
    QLinearGradient linearGradient(QPointF(20, 190), QPointF(40, 190));
    // 插入颜色
    linearGradient.setColorAt(0, Qt::yellow);
    linearGradient.setColorAt(0.5, Qt::red);
    linearGradient.setColorAt(1, Qt::green);
    // 指定渐变区域以外的区域的扩散方式
    linearGradient.setSpread(QGradient::RepeatSpread);
    // 使用渐变作为画刷
    painter.setBrush(linearGradient);
    painter.drawRect(200, 200, 90,40 );


    //==辐射渐变(圆心 center 和半径 radiu,辐射焦点)
    QRadialGradient radialGradient(QPointF(400, 200), 30, QPointF(400, 220));

    //(颜色占比(0-1),(rgb,透明度(0-255))))
    radialGradient.setColorAt(0.7, QColor(255, 0, 255, 255));

    radialGradient.setColorAt(0.9, QColor(100, 100, 0, 255));

    radialGradient.setColorAt(1, QColor(100,200, 200, 255));

    painter.setBrush(radialGradient);
    painter.drawEllipse(QPointF(400, 200), 50, 50);


    //==锥形渐变(指定中心点center和一个角度angle（其值在0〜360之间），然后沿逆时针从给定的角度开始环绕中心点插入颜色)
    QConicalGradient conicalGradient(QPointF(550, 200), 60);
    conicalGradient.setColorAt(0.3, Qt::green);
    conicalGradient.setColorAt(1, Qt::red);
    painter.setBrush(conicalGradient);
    painter.drawEllipse(QPointF(550, 200), 50, 50);







    //==画笔使用线性渐变来绘制直线和文字
    painter.setPen(QPen(linearGradient,3));
    painter.drawLine(10, 400, 100, 280);
    painter.drawText(150, 400, tr("helloQt!"));


    QRectF rect(250,300,300,150);
    painter.setPen(QPen(Qt::red,1));
    painter.drawRect(rect);
    painter.drawText(rect,Qt::AlignHCenter,tr("111111"));
    painter.drawText(rect,Qt::AlignLeft,tr("222222"));
    painter.drawText(rect,Qt::AlignRight,tr("333333"));
    painter.drawText(rect,Qt::AlignVCenter,tr("444444"));
    painter.drawText(rect,Qt::AlignBottom,tr("555555"));
    painter.drawText(rect,Qt::AlignCenter,tr("666666"));
    painter.drawText(rect,Qt::AlignBottom | Qt::AlignRight,tr("777777"));
    painter.drawText(rect,Qt::AlignBottom | Qt::AlignHCenter,tr("888\n888"));



    QFont font("宋体",15, QFont::Bold,true);
    //设置下划线
    font.setUnderline(true);
    //设置上划线
    font.setOverline(true);
    //设置字母大小写
    font.setCapitalization(QFont::SmallCaps);
    //设置字符间的间距
    font.setLetterSpacing(QFont::AbsoluteSpacing, 10);
    //使用字体
    painter.setFont(font);
    painter.setPen(Qt::red);
    painter.drawText(600,350, tr("红旗linux"));





    //==填充界面背景为白色,使用后将覆盖之前的绘画
    //painter.fillRect(rect(),Qt::white);


    //保存绘图的状态参数
    painter.save();

    painter.setPen(QPen(Qt::red,2));
    //绘制一条线段
    painter.drawLine(QPoint(10,500),QPoint(50,550));
    //将坐标系统进行平移,使(10,500)点作为原点,想要回原点可以进行反向平移
    painter.translate(10,500);
    //开启抗锯齿
    painter.setRenderHint(QPainter::Antialiasing);
    //==重新绘制相同的线段
    painter.drawLine(QPoint(20,0),QPoint(60,50));



    //将坐标系统旋转90度(旋转后x坐标与y坐标互换)
    painter.rotate(-90);
    painter.setPen(QPen(Qt::red,2));
    //重新绘制相同的线段
    painter.drawLine(QPoint(10,0),QPoint(50,50));


    //恢复到之前保存绘图的状态参数(将原点重新定为窗口左上角,并将旋转的角度重新复原)
    painter.restore();


    //保存绘图的状态参数
    painter.save();

    painter.setBrush(Qt::darkGreen);
    painter.setPen(QPen(Qt::red,2));
    //绘制一个矩形
    painter.drawRect(200,500,100,50);
    //将坐标系统进行缩放
    painter.scale(0.5,0.5);
    painter.setBrush(Qt::yellow);
    //重新绘制相同的矩形
    painter.drawRect(400,1000,100,50);


    //恢复到之前保存绘图的状态参数(将缩放复原)
    painter.restore();

    //保存绘图的状态参数
    painter.save();

    painter.setPen(Qt::blue);
    painter.setBrush(Qt::darkYellow);
    // 绘制一个椭圆
    painter.drawEllipse(QRect(400, 500, 80, 40));
    // 将坐标系统进行扭曲
    painter.shear(0, 1);
    painter.setBrush(Qt::darkGray);
    // 重新绘制相同的椭圆
    painter.drawEllipse(QRect(400, 50, 80, 40));

    painter.restore();


    //使用setWindow()函数将窗口虚拟为宽100,高100的弹性窗口
    painter.setWindow(0, 0, 100, 100);
    //由于宽高变化最大为100,所以即使画笔粗细为1也很粗
    painter.setPen(QPen(Qt::red,1));
    painter.setBrush(Qt::green);
    //根据弹性窗口进行坐标确定,进行拉伸窗口操作时,相应的绘制图像也会进行拉伸
    painter.drawRect(80, 80, 10, 10);



}



//打开第一个窗口
void Widget::on_pushButton_clicked()
{
    form1.show();
}

void Widget::on_pushButton_2_clicked()
{
    form2.show();
}

void Widget::on_pushButton_3_clicked()
{
    form3.show();
}
