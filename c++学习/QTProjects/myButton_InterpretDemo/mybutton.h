#ifndef MYBUTTON_H
#define MYBUTTON_H

#include <QPushButton>



class MyButton : public QPushButton
{

    Q_OBJECT


public:
    explicit MyButton(QWidget * parent = 0);
    QString getName(){return "自定义按钮";}
};

#endif // MYBUTTON_H
