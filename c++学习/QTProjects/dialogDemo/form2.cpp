#include "form2.h"
#include "ui_form2.h"

form2::form2(QWidget *parent) :
    QWidget(parent),
    ui(new Ui::Form2)
{
    ui->setupUi(this);
}

form2::~form2()
{
    delete ui;
}
