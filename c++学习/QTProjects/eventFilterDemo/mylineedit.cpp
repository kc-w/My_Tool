#include "mylineedit.h"
#include <QKeyEvent>
#include <QDebug>

MyLineEdit::MyLineEdit(QWidget *parent) :
    QLineEdit(parent)
{

}

void MyLineEdit::keyPressEvent(QKeyEvent *event) // 键盘按下事件
{
    qDebug() << tr("MyLineEdit键盘按下事件");
    QLineEdit::keyPressEvent(event);          // 执行QLineEdit类的默认事件处理
    event->ignore();                          // 忽略该事件
}

//事件
bool MyLineEdit::event(QEvent *event){
    if(event->type()==QEvent::KeyPress){
        qDebug() << tr("MyLinet的event函数");
    }
    return QLineEdit::event(event);//执行QLineEdit类event()函数的默认操作
}

