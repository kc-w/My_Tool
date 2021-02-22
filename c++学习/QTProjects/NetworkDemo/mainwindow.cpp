#include "mainwindow.h"
#include "ui_mainwindow.h"
#include <QFile>
#include <QFileInfo>
#include <QMessageBox>
#include <QNetworkAccessManager>
#include <QNetworkReply>

#include <QTextCodec>
#include <QTreeWidgetItem>
#include <api/ftp/qftp.h>
#include <QtNetwork>
#include <QDebug>

MainWindow::MainWindow(QWidget *parent) :
    QMainWindow(parent),
    ui(new Ui::MainWindow)
{
    ui->setupUi(this);

    //发送网络请求和接收应答
    manager = new QNetworkAccessManager(this);
    //关联http相关信号
    connect(manager, &QNetworkAccessManager::finished,this, &MainWindow::replyFinished);
    //发出get请求,还支持post和put
    manager->get(QNetworkRequest(QUrl("http://www.baidu.com")));



    //清空下载进度
    ui->progressBar->hide();



    //=======================================ftp传输文件
    ui->progressBar->setValue(0);
    connect(ui->fileList, &QTreeWidget::itemActivated,this, &MainWindow::processItem);




    //=======================================ip操作
    QString localHostName = QHostInfo::localHostName();
    QHostInfo info = QHostInfo::fromName(localHostName);



    qDebug() << "本地主机名: " << localHostName << endl
             << "本地IP地址信息: " << info.addresses();

    foreach (QHostAddress address, info.addresses())
    {
        if(address.protocol() == QAbstractSocket::IPv4Protocol)
            qDebug() << "本地IP地址列表: " << address.toString();
    }

    // 获取本机所有网络接口的列表
    QList<QNetworkInterface> list = QNetworkInterface::allInterfaces();

    // 遍历每一个网络接口
    foreach (QNetworkInterface interface, list)
    {
        qDebug() << "=================================================";
        // 接口名称
        qDebug() << "接口名称: " << interface.name();
        // 硬件地址
        qDebug() << "硬件地址(MAC): " << interface.hardwareAddress();

        // 获取IP地址条目列表，每个条目包含一个IP地址，一个子网掩码和一个广播地址
        QList<QNetworkAddressEntry> entryList = interface.addressEntries();

        int i=1;

        // 遍历每一个IP地址条目
        foreach (QNetworkAddressEntry entry, entryList)
        {
            // IP地址
            qDebug() << "IP地址条目"+QString::number(i,10)+": " << entry.ip().toString();

            // 子网掩码
            qDebug() << "子网掩码"+QString::number(i,10)+": " << entry.netmask().toString();

            // 广播地址
            qDebug() << "广播地址"+QString::number(i,10)+": " << entry.broadcast().toString();

            i++;
        }
    }




    dns = new QDnsLookup(this);
    connect(dns,SIGNAL(finished()),this,SLOT(handleServers()));
    dns -> setType(QDnsLookup::SRV);
    dns ->setName("_xmpp-client._tcp.gmail.com");
    dns -> lookup();



    //查找baidu的ip地址,判断是否出错,发生错误就输出错误信息并返回,如果没有问题.则遍历返回的IP列表并输出
    QHostInfo::lookupHost("www.baidu.com", this, SLOT(lookedUp(QHostInfo)));





    //=======================================UDP通信
    sender = new QUdpSocket(this);



    //=======================================TCP通信
    tcpServer = new QTcpServer(this);
    // 使用了IPv4的本地主机地址，等价于QHostAddress("127.0.0.1")
    if (!tcpServer->listen(QHostAddress("127.0.0.1"), 6666)) {
        qDebug() << tcpServer->errorString();
        close();
    }



}

MainWindow::~MainWindow()
{
    delete ui;
}


//====================http请求
void MainWindow::replyFinished(QNetworkReply *reply)
{

    QString all = reply->readAll();

    qDebug()<<all;

    ui->textBrowser->setText(all);
    reply->deleteLater();
}


//=====================点击请求文件下载
void MainWindow::on_pushButton_clicked()
{
    url = ui->lineEdit->text();
    QFileInfo info(url.path());
    QString fileName(info.fileName());
    //如果文件名为空则命名为file
    if (fileName.isEmpty()){
        QMessageBox::information(NULL, "消息", "下载的文件没有文件名", QMessageBox::Yes | QMessageBox::No, QMessageBox::Yes);
        return;
    }

    file = new QFile(fileName);
    if(!file->open(QIODevice::WriteOnly))
    {
        delete file;
        file = 0;
        return;
    }
    startRequest(url);
    ui->progressBar->setValue(0);
    ui->progressBar->show();
}

void MainWindow::startRequest(QUrl url)
{
    reply = manager->get(QNetworkRequest(url));
    connect(reply, &QNetworkReply::readyRead, this, &MainWindow::httpReadyRead);
    connect(reply, &QNetworkReply::downloadProgress,this, &MainWindow::updateDataReadProgress);
    connect(reply, &QNetworkReply::finished, this, &MainWindow::httpFinished);
}


void MainWindow::httpReadyRead()
{
    if (file) file->write(reply->readAll());
}

void MainWindow::updateDataReadProgress(qint64 bytesRead, qint64 totalBytes)
{
    ui->progressBar->setMaximum(totalBytes);
    ui->progressBar->setValue(bytesRead);
}

void MainWindow::httpFinished()
{
    ui->progressBar->hide();
    if(file) {
        file->close();
        delete file;
        file = 0;
    }
    reply->deleteLater();
    reply = 0;
}




//===================ftp文件传输




void MainWindow::ftpCommandStarted(int)
{
    int id = ftp->currentCommand();
    switch (id)
    {
    case QFtp::ConnectToHost :
        ui->label_7->setText(tr("正在连接到服务器…"));
        break;
    case QFtp::Login :
        ui->label_7->setText(tr("正在登录…"));
        break;
    case QFtp::Get :
        ui->label_7->setText(tr("正在下载…"));
        break;
    case QFtp::Close :
        ui->label_7->setText(tr("正在关闭连接…"));
    }
}

void MainWindow::ftpCommandFinished(int, bool error)
{
    if(ftp->currentCommand() == QFtp::ConnectToHost) {
        if (error)
            ui->label_7->setText(tr("连接服务器出现错误：%1").arg(ftp->errorString()));
        else ui->label_7->setText(tr("连接到服务器成功"));
    } else if (ftp->currentCommand() == QFtp::Login) {
        if (error)
            ui->label_7->setText(tr("登录出现错误：%1").arg(ftp->errorString()));
        else {
            ui->label_7->setText(tr("登录成功"));
            ftp->list();
        }
    } else if (ftp->currentCommand() == QFtp::Get) {
        if(error)
            ui->label_7->setText(tr("下载出现错误：%1").arg(ftp->errorString()));
        else {
            ui->label_7->setText(tr("已经完成下载"));
            file->close();
        }
        ui->downloadButton->setEnabled(true);
    } else if (ftp->currentCommand() == QFtp::List) {
        if (isDirectory.isEmpty())
        {
            ui->fileList->addTopLevelItem(
                        new QTreeWidgetItem(QStringList()<< tr("<empty>")));
            ui->fileList->setEnabled(false);
            ui->label_7->setText(tr("该目录为空"));
        }
    } else if (ftp->currentCommand() == QFtp::Close) {
        ui->label_7->setText(tr("已经关闭连接"));
    }
}

// 连接按钮
void MainWindow::on_connectButton_clicked()
{
    ui->fileList->clear();
    currentPath.clear();
    isDirectory.clear();
    ui->progressBar->setValue(0);
    ftp = new QFtp(this);
    connect(ftp, &QFtp::commandStarted, this,&MainWindow::ftpCommandStarted);
    connect(ftp, &QFtp::commandFinished,this, &MainWindow::ftpCommandFinished);
    connect(ftp, &QFtp::listInfo, this, &MainWindow::addToList);
    connect(ftp, &QFtp::dataTransferProgress,this, &MainWindow::updateDataTransferProgress);

    QString ftpServer = ui->ftpServerLineEdit->text();
    QString userName = ui->userNameLineEdit->text();
    QString passWord = ui->passWordLineEdit->text();
    ftp->connectToHost(ftpServer, 21);
    ftp->login(userName, passWord);
}

void MainWindow::addToList(const QUrlInfo &urlInfo)
{
    // 注意：因为服务器上文件使用UTF-8编码，所以要进行编码转换，这样显示中文才不会乱码
    QString name = QString::fromUtf8(urlInfo.name().toLatin1());
    QString owner = QString::fromUtf8(urlInfo.owner().toLatin1());
    QString group = QString::fromUtf8(urlInfo.group().toLatin1());
    QTreeWidgetItem *item = new QTreeWidgetItem;
    item->setText(0, name);
    item->setText(1, QString::number(urlInfo.size()));
    item->setText(2, owner);
    item->setText(3, group);
    item->setText(4, urlInfo.lastModified().toString("yyyy-MM-dd"));
    QPixmap pixmap(urlInfo.isDir() ? "../NetworkDemo/dir.png" : "../NetworkDemo/file.png");
    item->setIcon(0, pixmap);
    isDirectory[name] = urlInfo.isDir();
    ui->fileList->addTopLevelItem(item);
    if (!ui->fileList->currentItem()) {
        ui->fileList->setCurrentItem(ui->fileList->topLevelItem(0));
        ui->fileList->setEnabled(true);
    }
}

void MainWindow::processItem(QTreeWidgetItem *item, int)
{
    // 如果这个文件是个目录，则打开
    if (isDirectory.value(item->text(0))) {
        // 注意：因为目录名称可能是中文，在使用ftp命令cd()前需要先进行编码转换
        QString name = QLatin1String(item->text(0).toUtf8());
        ui->fileList->clear();
        isDirectory.clear();
        currentPath += "/";
        currentPath += name;
        ftp->cd(name);
        ftp->list();
        ui->cdToParentButton->setEnabled(true);
    }
}

// 返回上级目录按钮
void MainWindow::on_cdToParentButton_clicked()
{
    ui->fileList->clear();
    isDirectory.clear();
    currentPath = currentPath.left(currentPath.lastIndexOf('/'));
    if (currentPath.isEmpty()) {
        ui->cdToParentButton->setEnabled(false);
        ftp->cd("/");
    } else {
        ftp->cd(currentPath);
    }
    ftp->list();
}

// 下载按钮
void MainWindow::on_downloadButton_clicked()
{
    // 注意：因为文件名称可能是中文，所以在使用get()函数前需要进行编码转换
    QString fileName = ui->fileList->currentItem()->text(0);
    QString name = QLatin1String(fileName.toUtf8());
    file = new QFile(fileName);
    if (!file->open(QIODevice::WriteOnly)) {
        delete file;
        return;
    }
    ui->downloadButton->setEnabled(false);
    ftp->get(name, file);
}

void MainWindow::updateDataTransferProgress(qint64 readBytes,qint64 totalBytes)
{
    ui->progressBar->setMaximum(totalBytes);
    ui->progressBar->setValue(readBytes);
}



//==============================ip操作

void MainWindow::handleServers()
{
    qDebug() << "===========================查找DNS===========================";
    if (dns ->error() != QDnsLookup::NoError) {
        qWarning( "DNS查找失败");
        dns ->deleteLater();
        return;
    }
    foreach(const QDnsServiceRecord &record,dns -> serviceRecords()) {

        qDebug() << "dns:" << record.port();

    }
    //处理结果
    dns ->deleteLater();
    qDebug() << "===========================查找DNS===========================";
}

void MainWindow::lookedUp(const QHostInfo &host)
{
    qDebug() << "===========================查找baidu的ip地址===========================";
    if (host.error() != QHostInfo::NoError) {
        qDebug() << "查找失败:" << host.errorString();
        return;
    }
    foreach (const QHostAddress &address, host.addresses())
        qDebug() << "找到地址:" << address.toString();
    qDebug() << "===========================查找baidu的ip地址===========================";
}

//==============================UDP通信
void MainWindow::on_pushButton_2_clicked()
{
    QByteArray datagram = ui->lineEdit_2->text().toUtf8();
    //(字节数据,字节大小,发送地址,发送端口)
    //发送成功返回发送的字节数,发送失败,则返回-1,发送方和接收方需要使用同一个端口号
    //如果数据包过大，这个函数将会返回-1,而且error()函数会返回DatagramTooLargeError错误信息
    //不建议发送大于512字节的数据报,即便被发送成功,它们很可能是在到达最终目的地以前就在IP层被分割
    sender -> writeDatagram(datagram.data(), datagram.size(),QHostAddress("127.0.0.1"), 45454);

    //QHostAddress::Broadcast来表示广播地址,等价于QHostAddress("255.255.255.255")
    sender -> writeDatagram(datagram.data(), datagram.size(),QHostAddress::Broadcast, 45454);

}

//==============================TCP通信
void MainWindow::on_pushButton_3_clicked()
{
    // 用于暂存要发送的数据
    QByteArray block;
    QDataStream out(&block, QIODevice::WriteOnly);

    // 设置数据流的版本，客户端和服务器端使用的版本要相同
    out.setVersion(QDataStream::Qt_5_6);
    out << (quint16)0;
    out << tr(ui->lineEdit_3->text().toUtf8());
    out.device()->seek(0);
    out << (quint16)(block.size() - sizeof(quint16));

    // 获取已经建立的连接的套接字
    QTcpSocket *clientConnection = tcpServer->nextPendingConnection();
    connect(clientConnection, &QTcpSocket::disconnected,clientConnection, &QTcpSocket::deleteLater);
    clientConnection->write(block);

    //每次发送完数据后关闭tcp连接,再次发送需要再客户端点击重新连接服务端
    clientConnection->disconnectFromHost();

}
