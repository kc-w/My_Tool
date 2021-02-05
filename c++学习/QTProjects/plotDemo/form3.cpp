#include "form3.h"
#include "ui_form3.h"
#include <QPainter>
#include <QToolTip>
#include <QMouseEvent>
#include <QTimer>
#include <QImage>
#include <QPixmap>
#include <QBitmap>
#include <QPicture>
#include <QDebug>
#include <QLabel>
#include <QWindow>
#include <QScreen>
#include <QDesktopWidget>


Form3::Form3(QWidget *parent) :
    QWidget(parent),
    ui(new Ui::Form3)
{
    ui->setupUi(this);

    //保证不用按下鼠标按键也能触发鼠标移动事件
    setMouseTracking(true);

    //画出一个画布
    pix3 = QPixmap(200, 200);
    //填充为白色
    pix3.fill(Qt::white);
    //赋值给缓冲区
    tempPix3 = pix3;
    //设置绘图标识为false
    isDrawing3 = false;


    //截取屏幕并保存
    QWindow window;
    QPixmap grab =  window.screen()->grabWindow(QApplication::desktop()->winId());
    grab.save("screen.png");
    QLabel *label = new QLabel(this);
    //缩放为指定大小
    label->resize(200, 100);
    QPixmap pix = grab.scaled(label->size(), Qt::KeepAspectRatio, Qt::SmoothTransformation);
    label->setPixmap(pix);
    label->move(350, 150);



}

Form3::~Form3()
{
    delete ui;
}



//Qt提供了4个类来处理图像数据：QImage、QPixmap、QBitmap和QPicture
//QImage主要用来进行I/O处理，它对I/O处理操作进行了优化,而且也可以用来直接访问和操作像素;
//QPixmap主要用来在屏幕上显示图像,它对在屏幕上显示图像进行了优化;
//QBitmap是QPixmap的子类,用来处理颜色深度为1的图像.即只能显示黑白两种颜色;
//QPicture用来记录并重演QPainter命令
void Form3::paintEvent(QPaintEvent *)
{
    QPainter painter;

    // 绘制image,当在QImage上使用QPainter时，绘制操作会在当前GUI线程以外的其他线程中执行
    QImage image(100, 100, QImage::Format_ARGB32);
    //一个painter被通过begin()函数被激活并且使用一个QPainterDevice参数的构造函数进行构造
    painter.begin(&image);
    painter.setPen(QPen(Qt::green, 3));
    painter.setBrush(Qt::yellow);
    painter.drawRect(10, 10, 60, 60);
    painter.drawText(10, 10, 60, 60, Qt::AlignCenter, tr("QImage"));
    painter.setBrush(QColor(0 , 0, 0, 100));
    painter.drawRect(50, 50, 40, 40);
    //调用end()函数和析构函数解除
    painter.end();

    // 绘制pixmap
    QPixmap pix(100, 100);
    painter.begin(&pix);
    painter.setPen(QPen(Qt::green, 3));
    painter.setBrush(Qt::yellow);
    painter.drawRect(10, 10, 60, 60);
    painter.drawText(10, 10, 60, 60, Qt::AlignCenter, tr("QPixmap"));
    painter.setBrush(QColor(0 , 0, 0, 100));
    painter.drawRect(50, 50, 40, 40);
    painter.end();

    // 绘制bitmap
    QBitmap bit(100, 100);
    painter.begin(&bit);
    painter.setPen(QPen(Qt::green, 3));
    painter.setBrush(Qt::yellow);
    painter.drawRect(10, 10, 60, 60);
    painter.drawText(10, 10, 60, 60, Qt::AlignCenter, tr("QBitmap"));
    painter.setBrush(QColor(0 , 0, 0, 100));
    painter.drawRect(50, 50, 40, 40);
    painter.end();

    // 绘制picture
    QPicture picture;
    painter.begin(&picture);
    painter.setPen(QPen(Qt::green, 3));
    painter.setBrush(Qt::yellow);
    painter.drawRect(10, 10, 60, 60);
    painter.drawText(10, 10, 60, 60, Qt::AlignCenter, tr("QPicture"));
    painter.setBrush(QColor(0 , 0, 0, 100));
    painter.drawRect(50, 50, 40, 40);
    painter.end();

    // 在widget部件上进行绘制
    painter.begin(this);
    painter.drawImage(50, 20, image);
    painter.drawPixmap(200, 20, pix);
    painter.drawPixmap(350, 20, bit);
    painter.drawPicture(500, 20, picture);
    painter.end();



    //QImage用来加载一个图像文件，随意操纵图像数据
    QImage image1;
    // 加载一张图片
    image1.load(":/image/image.png");
    // 输出图片的一些信息
    qDebug() << image1.size() << image1.format() << image1.depth();
    painter.begin(this);
    // 在界面上绘制图片
    painter.drawImage(QPoint(50, 150), image1);
    // 获取镜像图片
    QImage mirror = image1.mirrored();
    // 将图片进行扭曲
    QTransform transform;
    transform.shear(0.2, 0);
    QImage image2 = mirror.transformed(transform);
    painter.drawImage(QPoint(50, 310), image2);
    painter.end();
    // 将镜像图片保存到文件
    image2.save("mirror.png");



    //要想访问像素,只能使用QPainter的相应函数,或者将QPixmap转换为QImage
    //与QImage不同，QPixmap中的fill()函数可以使用指定的颜色初始化整个pixmap图像
    //可以使用toImage()和fromImage()函数在QImage和QPixmap之间进行转换
    //QImage用来加载一个图像文件,然后将QImage对象转换为QPixmap类型再显示到屏幕上
    //QPixmap可以使用copy()复制图像上的一个区域,还可以使用mask()实现遮罩效果
    QPixmap pix1;
    pix1.load(":/image/yafeilinux.png");
    painter.begin(this);
    painter.drawPixmap(550, 150, pix1.width(), pix1.height(), pix1);
    painter.setBrush(QColor(255, 255, 255, 100));
    painter.drawRect(550, 150 , pix1.width(), pix1.height());
    painter.drawPixmap(650, 150, pix1.width(), pix1.height(), pix1);
    painter.setBrush(QColor(0, 0, 255, 100));
    painter.drawRect(650, 150, pix1.width(), pix1.height());
    painter.end();




    //QPicture是一个可以记录和重演QPainter命令的绘图设备
    //QPicture可以使用一个平台无关的格式(.pic格式)将绘图命令序列化到I/O设备中
    QPicture picture1;
    //在QPicture上绘制椭圆存保存为文件
    painter.begin(&picture1);
    painter.drawEllipse(0,0,80,70);
    painter.end();
    picture1.save("drawing.pic");

    //载入文件将椭圆绘制到widget上
    QPicture picture2;
    picture2.load("drawing.pic");
    painter.begin(this);
    painter.drawPicture(300, 300, picture2);
    painter.end();


    //QPainter提供了复合模式来定义如何完成数字图像的复合,即如何将源图像的像素和目标图像的像素进行合并
    //若绘图设备是QImage,图像的格式一定要指定为Format_ARGB32Premultiplied或者Format_ARGB32,不然复合模式就不会产生任何效果
    //当设置了复合模式,它就会应用到所有的绘图操作中,如画笔、画刷、渐变和 pixmap/image 绘制

    //创建画布区域,超出此区域的绘图将不显示
    QImage image3(900, 900, QImage::Format_ARGB32_Premultiplied);

    //先绘制到画布上
    painter.begin(&image3);
    painter.setPen(QPen(Qt::black, 1));
    painter.setBrush(Qt::green);
    painter.drawRect(400, 300, 200, 200);

    painter.setBrush(QColor(0, 0, 255, 150));
    painter.drawRect(350, 250, 100, 100);

    painter.setCompositionMode(QPainter::CompositionMode_SourceIn);
    painter.drawRect(550, 250, 100, 100);

    painter.setCompositionMode(QPainter::CompositionMode_DestinationOver);
    painter.drawRect(350, 450, 100, 100);

    painter.setCompositionMode(QPainter::CompositionMode_Xor);
    painter.drawRect(550, 450, 100, 100);
    painter.end();

    //把画布绘制到widget上,从原点开始绘制
    painter.begin(this);
    painter.drawImage(0, 0, image3);
    painter.end();



    //获取起始x,y坐标
    int x = startPoint3.x();
    int y = startPoint3.y();
    //获取结束宽高
    int width = endPoint3.x() - x;
    int height = endPoint3.y() - y;
    //开始在画布绘制
    painter.begin(&tempPix3);
    painter.drawRect(x-700, y-250, width, height);
    painter.end();
    //绘制到widget上
    painter.begin(this);
    painter.drawPixmap(700, 250, tempPix3);
    // 如果已经完成了绘制，那么更新缓冲区
    if(!isDrawing3){
        qDebug("44444444444444");
        //将暂时缓冲区数据赋值给缓冲区后显示
        pix3 = tempPix3;
    }

}

//鼠标移动监听,获取要绘制矩形的右下角的位置,然后动态绘制矩形，为了不产生拖影现象，就要在绘制临时缓冲区前，将缓冲区的内容复制到临时缓冲区中。
//这样每次都是在缓冲区图像的基础上进行绘制的，所以不会产生拖影现象
void Form3::mouseMoveEvent(QMouseEvent *event)
{
    if(event->buttons() & Qt::LeftButton) {

        qDebug("111111111111111");

        // 当按着鼠标左键进行移动时，获取当前位置作为结束点，绘制矩形
        endPoint3 = event->pos();
        // 将缓冲区的内容复制到临时缓冲区，这样进行动态绘制时，都是在缓冲区图像的基上进行绘制，就不会产生拖影现象了
        tempPix3 = pix3;
        // 更新显示
        update();
    }
}

//鼠标按下监听,获取了要绘制矩形左上角的位置.然后标记正在绘制矩形。
void Form3::mousePressEvent(QMouseEvent *event)
{
    if(event->button() == Qt::LeftButton) {

        qDebug("2222222222222222");

        // 当鼠标左键按下时获取当前位置作为矩形的开始点
        startPoint3 = event->pos();
        // 标记正在绘图
        isDrawing3 = true;
    }
}

//鼠标松开监听,取矩形的右下角坐标，标记已经结束绘制
void Form3::mouseReleaseEvent(QMouseEvent *event)
{
    if(event->button() == Qt::LeftButton) {

        qDebug("33333333333333");

        // 当鼠标左键松开时，获取当前位置为结束点，完成矩形绘制
        endPoint3= event->pos();
        // 标记已经结束绘图
        isDrawing3 = false;
        update();
    }
}
