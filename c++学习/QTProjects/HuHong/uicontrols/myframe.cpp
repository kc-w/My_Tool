#include "myframe.h"
#include <QKeyEvent>
#include <QDebug>

MyFrame::MyFrame(QWidget *parent) :
    QFrame(parent)
{

}
//自定义控件重写mousePressEvent方法监听鼠标按下
void MyFrame::mousePressEvent(QMouseEvent *event) // 鼠标按下事件
{
    if(event->button() == Qt::LeftButton){

        //触发clicked信号
        emit clicked();
    }

    QFrame::mousePressEvent(event);
}


