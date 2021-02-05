#include "dialog1.h"
#include "from1.h"

#include <QApplication>
#include <QLabel>


int main(int argc, char *argv[])
{
    QApplication a(argc, argv);



//    //新建QLabel对象,默认parent参数是0,所以它是一个窗口
//    QLabel * label = new QLabel();
//    label->setWindowTitle(QObject::tr("我是label的标题!"));//设置标题
//    label->setText(QObject::tr("我是label的内容!"));//设置要显示的信息
//    label->resize(500,500);//改变部件大小,以便能显示出完整的内容
//    label->show();

//    //新建QWidget类对象,默认parent参数是0,所以它是一个窗口
//    QWidget * widget = new QWidget();
//    //设置窗口标题
//    widget->setWindowTitle(QObject::tr("我是widget的标题!"));
//    //label2指定了父窗口为widget,所以不是窗口
//    QLabel * label2 = new QLabel(widget);
//    label2->setText(QObject::tr("label2:不是独立的窗口,只是widget的子部件"));
//    label2->resize(500,500);
//    widget->show();




//    delete label;
//    delete widget;

    from1 from1;

    //弹出弹窗
    Dialog1 dialog1;
    dialog1.exec();

    from1.show();

//    //判断dialog的执行结果,如果执行成功就把from1显示出来
//    if(dialog1.exec() == QDialog::Accepted){
//         from1.show();//显示from1
//    }







    return a.exec();
}
