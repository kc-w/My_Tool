#ifndef WIDGET_H
#define WIDGET_H

#include <QWidget>

//添加前置声明
class QFile;

QT_BEGIN_NAMESPACE
namespace Ui { class Widget; }
QT_END_NAMESPACE

class Widget : public QWidget
{
    Q_OBJECT

public:
    Widget(QWidget *parent = nullptr);
    ~Widget();

private slots:
    void on_pushButton_1_clicked();

protected:
    void paintEvent(QPaintEvent *);
    void mousePressEvent(QMouseEvent *);

private:
    Ui::Widget *ui;

    //添加私有对象
    QFile *qssFile;

};
#endif // WIDGET_H
