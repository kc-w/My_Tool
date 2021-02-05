#include "widget.h"
#include "ui_widget.h"

#include <QColorDialog>
#include <QDebug>
#include <QFileDialog>
#include <QFontDialog>
#include <QInputDialog>
#include <QMessageBox>
#include <QProgressDialog>
#include <QErrorMessage>
#include <QMovie>
#include <QMenu>




MyWidget::MyWidget(QWidget *parent)
    : QWidget(parent)
    , ui(new Ui::Widget)
{
    ui->setupUi(this);

    //在标签中设置图片
    ui ->label ->setPixmap(QPixmap("../Demo3/ioc.png"));

    //在标签中添加gif动图
    QMovie * movie = new QMovie( "ioc.gif");
    ui ->label_2 ->setMovie(movie);// 在标签中添加动画
    movie->start(); // 开始播放


    //设置按钮图标
    ui->pushButton_9->setIcon(QIcon("../Demo3/ioc.png"));

    //按钮设置下拉菜单
    ui->pushButton_10->setText( tr( "z&oom"));
    QMenu * menu = new QMenu(this);
    menu ->addAction(QIcon("../Demo3/ioc.png"),tr("放大"));
    ui ->pushButton_10 -> setMenu (menu);


}

MyWidget::~MyWidget()
{
    delete ui;
}


//颜色对话框
void MyWidget::on_pushButton_clicked()
{
    //getColor的三个参数(设置初始颜色,指定父窗口,设置对话框,添加透明的设置)
    QColor color = QColorDialog::getColor(Qt::red,this,tr("颜色对话框"),QColorDialog::ShowAlphaChannel);
    //返回四个值(透明度,红,绿,蓝)数字在0.0-1.0之间
    qDebug()<<"color:"<<color;
}

//文件对话框
void MyWidget::on_pushButton_5_clicked()
{

    QString fileName = QFileDialog::getOpenFileName(this, tr("文件对话框"),"D:",tr("图片文件(*png *jpg));;文本文件(*txt)"));
    qDebug() <<"filepath："<< fileName;
}

//字体对话框
void MyWidget::on_pushButton_2_clicked()
{
    //ok用于标记是否单击了OK按钮
    bool ok;
    //如果单击OK按钮，那么让"字体对话框”按钮使用新字体"如果单击Cancel按钮，那么输出信息
    QFont font = QFontDialog::getFont (&ok, this );
    if (ok){
        ui ->pushButton_3 ->setFont(font);
    }else{
        qDebug()<<tr("没有选择字体！");
    }
}

//输入对话框
void MyWidget::on_pushButton_6_clicked()
{
    bool ok;
    //获取字符串
    QString string = QInputDialog::getText(this, tr("输人字符串对话框"),
                                           tr("请输入用户名："),QLineEdit::Normal ,tr(" admin"), &ok);
    if(ok){
        qDebug()<< "string："<< string;
    }
    //获取整数
    int value1 = QInputDialog::getInt(this, tr("输入整数对话框"),
                                      tr("请输入-1000 到 1000 之间的数值"),100, - 1000, 1000, 10, &ok);
    if(ok){
        qDebug() <<"valuel1:"<< value1;
    }
    //获取浮点数
    double value2 = QInputDialog::getDouble(this,tr("输入浮点数对话框"),
                                            tr("请输入-1000到1000之间的数值"),0.00, - 1000, 1000, 2, &ok);
    if(ok){
        qDebug()<<"value2:"<<value2;
    }
    QStringList items;
    items<<tr("条目 1")<<tr("条目 2");
    //获取条目
    QString item = QInputDialog:: getItem (this, tr("输入条目对话框"),tr("请选择或输入一个条目"),
                                           items, 0, true, &ok);
    if(ok){
        qDebug()<<"item:"<<item;
    }
}


//消息对话框
void MyWidget::on_pushButton_3_clicked()
{
    //问题对话框
    int ret1 = QMessageBox::question(this,tr("问题对话框"),
            tr("你了解 Qt 吗？"),QMessageBox::Yes,QMessageBox::No);
    if(ret1 == QMessageBox::Yes){
        qDebug() << tr("yes!");
    }

    //提示对话框
    int ret2 = QMessageBox::information(this,tr("提示对话框"),
            tr("这是 Qt 书籍!"),QMessageBox::Ok);
    if(ret2 == QMessageBox::Yes){
        qDebug() << tr("提示!");
    }

    //警告对话框
    int ret3 = QMessageBox::warning(this,tr("警告对话框"),
            tr("不能提前结束!"),QMessageBox::Abort);
    if(ret3 == QMessageBox::Abort){
        qDebug() << tr("警告!");
    }

    //错误对话框
    int ret4 = QMessageBox::critical(this,tr("严重错误对话框"),
            tr("发现一个严重错误！现在要关闭所有文件!"),QMessageBox::YesAll);
    if(ret4 == QMessageBox::YesAll){
        qDebug() << tr("错误!");
    }

    //关于对话框
    QMessageBox::about(this,tr("关于对话框"),tr("yafeilinux 致力于 Qt 及 Qt Creator 的普及工作！"));


}

//进度对话框
void MyWidget::on_pushButton_7_clicked()
{
    QProgressDialog dialog(tr("文件复制进度"),tr("取消"),0,50000,this);
    dialog.setWindowTitle(tr("进度对话框"));//设置窗口标题
    dialog.setWindowModality(Qt::WindowModal);//将对话框设置为模态
    dialog.show();
    for(int i=0;i<50000;i++){
        dialog.setValue(i);
        QCoreApplication::processEvents();//避免界面冻结
        if(dialog.wasCanceled()){//按下取消按钮则中断
            break;
        }
    }
    qDebug()<<tr("复制结束!");

}



//错误信息对话框
void MyWidget::on_pushButton_4_clicked()
{
    QErrorMessage * errordlg = new QErrorMessage(this);
    errordlg->setWindowTitle(tr("错误信息对话框"));
    errordlg->showMessage(tr("这里是岀错信息！"));
}




//向导对话框
QWizardPage * MyWidget::createPage1()//向导页面1
{
    QWizardPage * page = new QWizardPage;
    page ->setTitle( tr( "介绍"));
    return page;
}
QWizardPage * MyWidget::createPage2()//向导页面2
{
    QWizardPage * page = new QWizardPage;
    page ->setTitle( tr( "用户选择信息"));
    return page;
}
QWizardPage * MyWidget::createPage3()//向导页面3
{
    QWizardPage * page = new QWizardPage;
    page ->setTitle( tr( "结束"));
    return page;
}

void MyWidget::on_pushButton_8_clicked()
{
    QWizard wizard(this);
    wizard.setWindowTitle(tr("向导对话框!"));
    wizard.addPage(createPage1());// 添加向导页面
    wizard.addPage(createPage2());
    wizard.addPage(createPage3());
    wizard.exec();
}


//监听按钮是否被选中
void MyWidget::on_pushButton_9_toggled(bool checked)
{
    qDebug()<<tr("按钮是否按下：")<<checked;
}
