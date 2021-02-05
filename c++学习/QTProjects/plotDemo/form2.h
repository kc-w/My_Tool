#ifndef FORM2_H
#define FORM2_H

#include <QWidget>

namespace Ui {
class Form2;
}

class Form2 : public QWidget
{
    Q_OBJECT

public:
    explicit Form2(QWidget *parent = nullptr);
    ~Form2();

private:
    Ui::Form2 *ui;


protected:
    void paintEvent(QPaintEvent *event);//声明重绘事件处理函数
    void mouseMoveEvent(QMouseEvent *event);//声明鼠标移动事件处理函数

};

#endif // FORM2_H
