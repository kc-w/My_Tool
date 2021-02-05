#ifndef FORM2_H
#define FORM2_H

#include <QWidget>

namespace Ui {
class Form2;
}

class form2 : public QWidget
{
    Q_OBJECT

public:
    explicit form2(QWidget *parent = nullptr);
    ~form2();

private:
    Ui::Form2 *ui;
};

#endif // FORM2_H
