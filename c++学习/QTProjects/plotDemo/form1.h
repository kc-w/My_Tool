#ifndef FORM1_H
#define FORM1_H

#include <QWidget>

namespace Ui {
class Form1;
}

class Form1 : public QWidget
{
    Q_OBJECT

public:
    explicit Form1(QWidget *parent = nullptr);
    ~Form1();

private:
    Ui::Form1 *ui;

    QTimer *timer;
    int angle;


protected:
    void paintEvent(QPaintEvent *event);//声明重绘事件处理函数
    void mouseMoveEvent(QMouseEvent *event);//声明鼠标移动事件处理函数

};

#endif // FORM1_H
