#ifndef WIDGET_H
#define WIDGET_H

#include <QWidget>
#include "form1.h"
#include "form2.h"
#include "form3.h"

class QTimer;

namespace Ui {
class Widget;
}

class Widget : public QWidget
{
    Q_OBJECT

public:
    explicit Widget(QWidget *parent = 0);
    ~Widget();

private:
    Ui::Widget *ui;
    Form1 form1;
    Form2 form2;
    Form3 form3;

private slots:
    void on_pushButton_clicked();
    void on_pushButton_2_clicked();

    void on_pushButton_3_clicked();

protected:
    void paintEvent(QPaintEvent *event);//声明重绘事件处理函数


};
#endif // WIDGET_H
