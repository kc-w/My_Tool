#ifndef MYFRAME_H
#define MYFRAME_H

#include <QFrame>



class MyFrame : public QFrame
{
    Q_OBJECT
public:
    MyFrame();
    explicit MyFrame(QWidget *parent = 0);

protected:
    void mousePressEvent(QMouseEvent *event);

signals:
    void clicked();



};

#endif // MYFRAME_H
