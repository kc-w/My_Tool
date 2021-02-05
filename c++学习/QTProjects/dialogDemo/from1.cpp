#include "dialog1.h"
#include "from1.h"
#include "ui_from1.h"

#include <QDialog>

from1::from1(QWidget *parent)
    : QWidget(parent)
    , ui(new Ui::from1)
{
    ui->setupUi(this);

//    //connect是QObject类中的函数,from1继承自QObject,from1可以直接使用它
//    //使用connect函数将按钮单击信号与新建的槽进行手动关联(发射信号的对象,发射的信号,接收信号的对象,要执行的槽)
//    connect(ui->pushButton, &QPushButton::clicked,this,&from1::on_pushButton_clicked);

//    //使用此方法弹出的对话框,在该函数执行完成后就会自动销毁
//    QDialog dialog(this);
//    dialog.show();


}

from1::~from1()
{
    delete ui;
}



//实现头文件中的槽声明方法,自动关联
void from1::on_pushButton_clicked()
{
    //代码设置弹窗
    QDialog * dialog = new QDialog(this);
    dialog->setWindowTitle("from1窗口下的弹窗");
    dialog->setModal(true);//将弹窗设置为模态对话框,设置后只将该弹窗处理后才能处理其他窗口
    dialog->show();
}

void from1::on_pushButton_2_clicked()
{
    //先关闭主界面,然后新建mydialog对象
    close();
    Dialog1 dialog1;
    if(dialog1.exec() == QDialog::Accepted){
        show();
    }
}
