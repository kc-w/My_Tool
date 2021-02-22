#include "mainwindow.h"
#include "ui_mainwindow.h"

#include <QBuffer>
#include <QDebug>
#include <QFileDialog>
#include <QTextCodec>

MainWindow::MainWindow(QWidget *parent)
    : QMainWindow(parent)
    , ui(new Ui::MainWindow)
{
    ui->setupUi(this);


    //====================进程状态操作
    connect(&myProcess, &QProcess::readyRead, this, &MainWindow::showResult);
    connect(&myProcess, &QProcess::stateChanged,this, &MainWindow::showState);
    connect(&myProcess, &QProcess::errorOccurred,this, &MainWindow::showError);
    //waitForStarted()阻塞,直到进程启动
    //waitForReadyRead()阻塞,直到在当前读通道上有可读的数据
    //waitForBytesWritten()阻塞,直到一个有效负载数据被写入到进程
    //waitForFinished()阻塞,直到进程结束
    connect(&myProcess, SIGNAL(finished(int,QProcess::ExitStatus)),this, SLOT(showFinished(int, QProcess::ExitStatus)));


    //=====================进程间通信
    //设置进程共享标识,可以是任意字符串
    sharedMemory.setKey("QSharedMemoryExample");

}

MainWindow::~MainWindow()
{
    delete ui;
}

//===============================进程状态操作
void MainWindow::on_pushButton_clicked()
{
    QString program = "cmd.exe";
    QStringList arguments;
    arguments << "/c dir&pause";
    myProcess.start(program, arguments);
}

void MainWindow::showResult()
{
    QTextCodec *codec = QTextCodec::codecForLocale();
    qDebug() << "运行结果: " << endl << codec->toUnicode(myProcess.readAll());
}

void MainWindow::showState(QProcess::ProcessState state)
{
    qDebug() << "运行状态: ";
    if (state == QProcess::NotRunning) {
        qDebug() << "没有运行";
    } else if (state == QProcess::Starting) {
        qDebug() << "开始";
    }  else {
        qDebug() << "运行中";
    }
}

void MainWindow::showError()
{
    qDebug() << "错误: " << endl << myProcess.errorString();
}

void MainWindow::showFinished(int exitCode, QProcess::ExitStatus exitStatus)
{
    qDebug() << "完成: " << endl << exitCode << exitStatus;
}





//===============================进程间通信

void MainWindow::detach()
{
    //将该进程与共享内存段进行分离
    if (!sharedMemory.detach())
        ui->label->setText(tr("无法从共享内存中分离！"));
}


void MainWindow::on_loadFromFileButton_clicked()
{
    //判断该进程是否已经连接到共享内存
    if (sharedMemory.isAttached())
        detach();
    ui->label->setText(tr("选择一个图片文件！"));
    QString fileName = QFileDialog::getOpenFileName(0, QString(), QString(),tr("Images (*.png *.jpg)"));
    QImage image;
    if (!image.load(fileName)) {
        ui->label->setText(tr("选择的文件不是图片，请选择图片文件！"));
        return;
    }
    ui->label->setPixmap(QPixmap::fromImage(image));

    // 将图片加载到共享内存
    QBuffer buffer;
    buffer.open(QBuffer::ReadWrite);
    QDataStream out(&buffer);
    out << image;
    int size = buffer.size();
    if (!sharedMemory.create(size)) {
        ui->label->setText(tr("无法创建共享内存段！"));
        return;
    }
    //进行共享内存段的操作时,需要先进行加锁
    sharedMemory.lock();
    char *to = (char*)sharedMemory.data();
    const char *from = buffer.data().data();
    //将buffer对应的数据段复制到了共享内存段
    memcpy(to, from, qMin(sharedMemory.size(), size));
    //进行解锁
    sharedMemory.unlock();
}

void MainWindow::on_loadFromSharedMemoryButton_clicked()
{
    if (!sharedMemory.attach()) {
        ui->label->setText(tr("无法连接到共享内存段，\n请先加载一张图片！"));
        return;
    }
    QBuffer buffer;
    QDataStream in(&buffer);
    QImage image;

    sharedMemory.lock();
    buffer.setData((char*)sharedMemory.constData(), sharedMemory.size());
    buffer.open(QBuffer::ReadOnly);
    in >> image;
    sharedMemory.unlock();


    //得共享数据后将进程与共享内存段进行分离
    sharedMemory.detach();
    ui->label->setPixmap(QPixmap::fromImage(image));
}
