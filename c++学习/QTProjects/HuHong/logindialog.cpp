#include "logindialog.h"
#include "ui_logindialog.h"

#include <QtGui/QMouseEvent>
#include <QPainter>

LoginDialog::LoginDialog(QWidget *parent) :
    QDialog(parent),
    ui(new Ui::LoginDialog)
{
    ui->setupUi(this);

    mMoveing=false;


    //设置窗口背景透明
    this->setAttribute(Qt::WA_TranslucentBackground);
    //设置无边框窗口.设置后使用代码设置style和样式表对边框都无效,只有画刷才能对颜色进行设置
    this->setWindowFlags(Qt::FramelessWindowHint);


    //创建数据库连接读取数据
    db = new SqliteDBAOperator();
    db->OpenDb();
    users = db->selectAllUser();

    QListIterator<User> i(users); // 创建列表的只读迭代器，将list作为参数
    while (i.hasNext()){
        user = i.next();
        ui->comboBox->addItem(user.getNumber());
    }
    //释放内存
    db->closeDb();

//    //禁用边框退出
//    this->setWindowFlags(Qt::Window|Qt::WindowContextHelpButtonHint);
//    //去除边框问号
//    this->setWindowFlags(Qt::Window|Qt::WindowTitleHint);
//    //以下两个方法一起使用将实现无边框除组件外全透明效果
//    //取消弹窗边框
//    setWindowFlags(Qt::FramelessWindowHint);
//    //使窗口背景除了组件都变为透明
//    setAttribute(Qt::WA_TranslucentBackground, true);




}



//重写鼠标按下事件
void LoginDialog::mousePressEvent(QMouseEvent *event)
{
    mMoveing = true;
    //记录下鼠标相对于窗口的位置
    //event->globalPos()鼠标按下时，鼠标相对于整个屏幕位置
    //pos() this->pos()鼠标按下时，窗口相对于整个屏幕位置
    mMovePosition = event->globalPos() - pos();
    return QDialog::mousePressEvent(event);
}

//重写鼠标移动事件
void LoginDialog::mouseMoveEvent(QMouseEvent *event)
{
    //鼠标左键按下并开始移动
    if (mMoveing && event->buttons() && Qt::LeftButton ){
        //通过事件event->globalPos()知道鼠标坐标，鼠标坐标减去鼠标相对于窗口位置，就是窗口在整个屏幕的坐标
        if((event->globalPos()- mMovePosition).manhattanLength() > QApplication::startDragDistance()){
            move(event->globalPos()-mMovePosition);
            mMovePosition = event->globalPos() - pos();
        }
    }
    return QDialog::mouseMoveEvent(event);
}

//重写鼠标放下事件
void LoginDialog::mouseReleaseEvent(QMouseEvent *event)
{
    mMoveing = false;
}

//绘制圆形边框
void LoginDialog::paintEvent(QPaintEvent *event)
{
    QPainter painter(this);
    // 反锯齿
    painter.setRenderHint(QPainter::Antialiasing);
    //设置填充的背景色和透明度
    painter.setBrush(QBrush(QColor(170, 255, 255, 255)));
    painter.setPen(Qt::transparent);
    QRect rect = this->rect();
    rect.setWidth(rect.width() - 1);
    rect.setHeight(rect.height() - 1);
    painter.drawRoundedRect(rect, 15, 15);

//    //也可用QPainterPath 绘制代替 painter.drawRoundedRect(rect, 15, 15);
//    QPainterPath painterPath;
//    painterPath.addRoundedRect(rect, 15, 15);
//    painter.drawPath(painterPath);

    QDialog::paintEvent(event);
}

void LoginDialog::setHint(QString hint)
{

}

QString LoginDialog::getNumber()
{
    return ui->comboBox->currentText();
}

QString LoginDialog::getPassword()
{
    return ui->lineEdit_2->text();
}



LoginDialog::~LoginDialog()
{
    delete ui;
}
