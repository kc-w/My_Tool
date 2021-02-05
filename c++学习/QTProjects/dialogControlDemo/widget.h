#ifndef WIDGET_H
#define WIDGET_H

#include <QWidget>
#include <QWizard>

QT_BEGIN_NAMESPACE
namespace Ui { class Widget; }
QT_END_NAMESPACE

class MyWidget : public QWidget
{
    Q_OBJECT

public:
    MyWidget(QWidget *parent = nullptr);
    ~MyWidget();

//声明信号槽
private slots:
    void on_pushButton_clicked();

    void on_pushButton_5_clicked();

    void on_pushButton_2_clicked();

    void on_pushButton_6_clicked();

    void on_pushButton_3_clicked();

    void on_pushButton_7_clicked();

    void on_pushButton_4_clicked();

    void on_pushButton_8_clicked();

    //这里声明了 3个返回值为QWizardPage类对象的指针函数，用来生成3个向导页面
    void on_pushButton_9_toggled(bool checked);

private:
    Ui::Widget *ui;
    QWizardPage * createPage1(); //新添加
    QWizardPage * createPage2(); //新添加
    QWizardPage * createPage3(); //新添加

};
#endif // WIDGET_H
