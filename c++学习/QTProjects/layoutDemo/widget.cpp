#include "widget.h"
#include "ui_widget.h"

#include <QStyleFactory>
#include <QPalette>
#include <QFile>
#include <QDebug>

# include <QPixmap>
# include <QBitmap>
# include <QPainter>


Widget::Widget(QWidget *parent)
    : QWidget(parent)
    , ui(new Ui::Widget)
{
    ui->setupUi(this);


    //设置窗体透明度
    setWindowOpacity(0.5);


    //单独将按钮设置为windows风格
    ui ->pushButton_1 ->setStyle(QStyleFactory::create("fusion"));
    ui ->pushButton_2 ->setStyle(QStyleFactory::create("windows"));
    ui ->pushButton_3 ->setStyle(QStyleFactory::create("windows"));
    ui ->pushButton_4 ->setStyle(QStyleFactory::create("windows"));



//    QPalette::Window       一般的背景颜色
//    QPalette::WindowText   一般的前景颜色
//    QPalette::Base         主要作为输入部件(如QLineEdit )的背景色,也可用作QComboBox的下拉列表的背景色或者QToolBar的手柄颜色，一般是白色或其他浅色
//    QPalette::AhernateBase 在交替行颜色的视图中作为交替背景色
//    QPalette::ToolTipBasc  作为QToolTip和QWhatsThis的背景色
//    QPalette::ToolTipText  作为QToolTip和QWhatsThis的前景色
//    QPalette::Text         和Base-起使用，作为前景色
//    QPalette::Button       按钮部件背景色
//    QPalette::ButtonText   按钮部件前景色
//    QPalette::BrightText   一种与深色对比度较大的文本颜色,一般用于当Text或者WindowText的对比度较差时

    //获取pushButton的调色板
    QPalette palette1 = ui ->pushButton_1 ->palette();
    //设置按钮文本颜色为红色
    palette1.setColor(QPalette::ButtonText, Qt::red);
    //设置按钮背景色为绿色
    palette1.setColor(QPalette::Button, Qt::green);
    //pushButton使用修改后的调色板
    ui ->pushButton_1 ->setPalette(palette1);

    //设置pushButton_9不可用
    ui ->checkBox_2 ->setDisabled(true);
    ui ->pushButton_8 ->setDisabled(true);
    ui ->pushButton_9 ->setDisabled(true);
    QPalette palette2 = ui->pushButton_9 ->palette();
    QPalette palette3 = ui->checkBox_2 ->palette();
    //设置pushButton_9不可用时的背景颜色为蓝色,(应用状态,应用部位,应用颜色)
    palette2.setColor(QPalette::Disabled,QPalette::Window,Qt::blue);
    palette3.setColor(QPalette::Disabled,QPalette::Window,Qt::blue);
    ui ->pushButton_9 ->setPalette(palette2);
    ui ->checkBox_2 ->setPalette(palette3);




    //Qt样式表语法
//    通用选择器 * 匹配所有部件
//    类型选择器 QPushButton 匹配所有QPushButton实例和它的所有子类
//    属性选择器 QPushBulton[flat ="false"] 匹配QPushButton的属性flat为false的实例
//    类选择器  .QPushButton 匹配所有QPushButton实例,但不包含它的子类
//    ID选择器  #okButton 匹配所有QPushButton中以okButton为对象名的实例
//    后代选择器 QDialog QPushButton 匹配所有QPushButton实例,它们必须是QDialog的子孙部件
//    孩子选择器 QDialog>QPushButton 匹配所有QPushButton实例,它们必须是QDialog的直接子部件



    //设置样式表,代码设置的样式表优先级高于设计师界面设置的样式表

    //设置pushButton的背景为黄色
    ui ->pushButton_2 -> setStyleSheet("background:yellow");
    //设置horizontalSlider的背景为蓝色
    ui ->horizontalSlider -> setStyleSheet("border-color:black;border-width:1px;border-style: solid;");

    setStyleSheet("QPushButton{background:yellow}QSlider{background:black}");




//    QPixmap pixmap(":/new/prefix1/logo.png");
//    //将label的遮罩设置为图片
//    ui->label ->setPixmap(pixmap);
//    //为label设置遮罩
//    ui ->label ->setMask(pixmap.mask());

    QPixmap pixmap;
    // 加载图片
    pixmap.load(":/new/prefix1/logo.png");
    // 设置窗口大小为图片大小
    resize(pixmap.size());
    // 为窗口设置遮罩
    setMask(pixmap.mask());




    qssFile = new QFile(":/new/qss/my1.qss", this);
    // 只读方式打开该文件
    qssFile->open(QFile::ReadOnly);
    // 读取文件全部内容，使用tr()函数将其转换为QString类型
    QString styleSheet = tr(qssFile->readAll());
    // 为QApplication设置样式表
    qApp->setStyleSheet(styleSheet);
    qssFile->close();


}

//ui绘制事件
void Widget::paintEvent(QPaintEvent *)
{
    QPainter painter(this);
    // 从窗口左上角开始绘制图片
    painter.drawPixmap(0, 0, QPixmap(":/new/prefix1/logo.png"));
}

//鼠标点击事件
void Widget::mousePressEvent(QMouseEvent *)
{   // 关闭窗口
    close();
}


//切换qss样式表文件,其中本体样式表和代码编写样式优先级最高,外部样式表无法改变
void Widget::on_pushButton_1_clicked()
{
    if(qssFile->fileName() == ":/new/qss/my1.qss"){
        qssFile->setFileName(":/new/qss/my2.qss");
    }else{
        qssFile->setFileName(":/new/qss/my1.qss");
    }
    qssFile->open(QFile::ReadOnly);
    QString styleSheet = tr(qssFile->readAll());
    qApp->setStyleSheet(styleSheet);
    qssFile->close();
}

Widget::~Widget()
{
    delete ui;
}

