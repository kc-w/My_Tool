#ifndef FROM1_H
#define FROM1_H

#include <QWidget>

QT_BEGIN_NAMESPACE
namespace Ui { class from1; }
QT_END_NAMESPACE

class from1 : public QWidget
{
    Q_OBJECT

public:
    from1(QWidget *parent = nullptr);
    ~from1();


//槽声明
private slots:
    void on_pushButton_clicked();

    void on_pushButton_2_clicked();

private:
    Ui::from1 *ui;
};
#endif // FROM1_H
