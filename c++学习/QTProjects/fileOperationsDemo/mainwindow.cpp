#include "mainwindow.h"
#include "ui_mainwindow.h"
#include <QDir>

MainWindow::MainWindow(QWidget *parent) :
    QMainWindow(parent),
    ui(new Ui::MainWindow)
{
    ui->setupUi(this);

//QFileSystemWatcher类提供了一个接口来监控文件和目录的修改
//调用addPath()来监视一个指定的文件或者目录.多个路径可以使用addPaths()函数来添加,现有的路径可以使用removePath()和removePaths()函数来移除
//QFileSystemWatcher会检测每一个添加到它上面的路径.添加到其上的文件的路径可以使用files()来获取.目录的路径可以使用directories()函数来获取
//当文件被修改、重命名或者移除后,会发射fileChanged()信号,当目录或者它的内容被修改或者移除后,会发射directoryChanged()信号,之后终止监视

    // 将监视器的信号和自定义的槽进行关联
    connect(&myWatcher, &QFileSystemWatcher::directoryChanged,this, &MainWindow::showMessage);
    connect(&myWatcher, &QFileSystemWatcher::fileChanged,this, &MainWindow::showMessage);

    // 获取当前目录
    QDir myDir(QDir::currentPath());
    //设置过滤器得到当前目录下所有以.h结尾的文件
    myDir.setNameFilters(QStringList("*.h"));
    //加入当前目录的绝对路径
    ui->listWidget->addItem(myDir.absolutePath() + tr("目录下的.h文件有："));
    //加入文件名列表
    ui->listWidget->addItems(myDir.entryList());
    // 创建目录，并将其加入到监视器中
    myDir.mkdir("mydir");
    //转到该目录
    myDir.cd("mydir");

    //将监视目录加入到列表
    ui->listWidget->addItem(tr("监视的目录：") + myDir.absolutePath());
    //对目录进行监视
    myWatcher.addPath(myDir.absolutePath());
    //创建文件,并将其加入到监视器中
    QFile file(myDir.absolutePath() + "/myfile.txt");
    if (file.open(QIODevice::WriteOnly)) {
        //获取文件信息
        QFileInfo info(file);
        //将监视文件的绝对路径加到列表
        ui->listWidget->addItem(tr("监视的文件：") + info.absoluteFilePath());
        //对文件进行监视
        myWatcher.addPath(info.absoluteFilePath());
        file.close();
    }
}

MainWindow::~MainWindow()
{
    delete ui;
}

// 监视器收到信号触发函数
void MainWindow::showMessage(const QString &path)
{
    //获取创建的目录
    QDir dir(QDir::currentPath() + "/mydir");
    //如果是目录发生了改变
    if (path == dir.absolutePath()) {
        //加入改变的目录信息及文件列表
        ui->listWidget->addItem(dir.dirName() + tr("目录发生改变: "));
        ui->listWidget->addItems(dir.entryList());
    } else {
        //如果是文件发生了改变就加入文件列表
        ui->listWidget->addItem(path + tr("文件发生改变！"));
    }
}
