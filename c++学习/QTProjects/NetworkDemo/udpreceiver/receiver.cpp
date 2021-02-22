#include "receiver.h"
#include "ui_receiver.h"
#include <QtNetwork>

Receiver::Receiver(QWidget *parent) :
    QDialog(parent),
    ui(new Ui::Receiver)
{
    ui->setupUi(this);


    //===============udp通信
    receiver = new QUdpSocket(this);
    receiver->bind(QHostAddress("127.0.0.1"),45454);
    connect(receiver, &QUdpSocket::readyRead, this, &Receiver::processPendingDatagram);


    //===============tcp通信
    tcpSocket = new QTcpSocket(this);
    connect(tcpSocket, &QTcpSocket::readyRead, this, &Receiver::readMessage);
    connect(tcpSocket, SIGNAL(error(QAbstractSocket::SocketError)),this, SLOT(displayError(QAbstractSocket::SocketError)));

}

Receiver::~Receiver()
{
    delete ui;
}
//===============udp通信
void Receiver::processPendingDatagram()
{
    // 拥有等待的数据包
    while(receiver->hasPendingDatagrams())
    {
        QByteArray datagram;

        // 让datagram的大小为等待处理的数据报的大小，这样才能接收到完整的数据
        datagram.resize(receiver->pendingDatagramSize());

        // 接收数据报，将其存放到datagram中
        receiver->readDatagram(datagram.data(), datagram.size());
        ui->label->setText(datagram);
    }
}


//===============tcp通信
void Receiver::readMessage()
{
    QDataStream in(tcpSocket);
    // 设置数据流版本，这里要和服务器端相同
    in.setVersion(QDataStream::Qt_5_6);

    // 如果是刚开始接收数据
    if (blockSize == 0) {
        //判断接收的数据是否大于两字节，也就是文件的大小信息所占的空间
        //如果是则保存到blockSize变量中，否则直接返回，继续接收数据
        if(tcpSocket->bytesAvailable() < (int)sizeof(quint16)) return;
        in >> blockSize;
    }
    // 如果没有得到全部的数据，则返回，继续接收数据
    if(tcpSocket->bytesAvailable() < blockSize) return;
    // 将接收到的数据存放到变量中
    in >> message;
    // 显示接收到的数据
    ui->messageLabel->setText(message);
}

void Receiver::displayError(QAbstractSocket::SocketError)
{
    qDebug() <<"错误: " <<tcpSocket->errorString();
}

void Receiver::on_connectButton_clicked()
{
    // 初始化数据大小信息为0
    blockSize = 0;

    // 取消已有的连接
    tcpSocket->abort();
    tcpSocket->connectToHost(ui->hostLineEdit->text(),ui->portLineEdit->text().toInt());

    ui->messageLabel->setText("连接成功!");
}
