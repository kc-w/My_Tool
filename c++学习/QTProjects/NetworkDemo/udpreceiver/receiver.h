#ifndef RECEIVER_H
#define RECEIVER_H

#include <QDialog>
#include <QTcpSocket>
#include <QUdpSocket>
namespace Ui {
class Receiver;
}

class Receiver : public QDialog
{
    Q_OBJECT

public:
    explicit Receiver(QWidget *parent = 0);
    ~Receiver();

private:
    Ui::Receiver *ui;
    QUdpSocket *receiver;

    QTcpSocket *tcpSocket;
    QString message;
    // 用来存放数据的大小信息
    quint16 blockSize;

private slots:
    void processPendingDatagram();

    void readMessage();
    void displayError(QAbstractSocket::SocketError);
    void on_connectButton_clicked();
};

#endif // RECEIVER_H
