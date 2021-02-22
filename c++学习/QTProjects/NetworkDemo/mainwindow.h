#ifndef MAINWINDOW_H
#define MAINWINDOW_H

#include <QMainWindow>
#include <QUrl>
#include <QHash>
#include <QDnsLookup>
#include <QUdpSocket>
#include <QTcpServer>
class QNetworkReply;
class QNetworkAccessManager;
class QFile;
class QFtp;
class QUrlInfo;
class QTreeWidgetItem;

class QHostInfo;

namespace Ui {
class MainWindow;
}

class MainWindow : public QMainWindow
{
    Q_OBJECT

public:
    explicit MainWindow(QWidget *parent = 0);
    ~MainWindow();

    void startRequest(QUrl url);

private:
    Ui::MainWindow *ui;
    QNetworkAccessManager *manager;

    QNetworkReply *reply;
    QUrl url;
    QFile *file;
    QFtp *ftp;

    // 用来存储一个路径是否为目录的信息
    QHash<QString, bool> isDirectory;
    // 用来存储当前路径
    QString currentPath;

    QDnsLookup *dns;

    QUdpSocket *sender;
    QTcpServer *tcpServer;

private slots:
    void replyFinished(QNetworkReply *);

    void httpFinished();
    void httpReadyRead();
    void updateDataReadProgress(qint64, qint64);

    void on_pushButton_clicked();


    void ftpCommandStarted(int);
    void ftpCommandFinished(int, bool);

    // 更新进度条
    void updateDataTransferProgress(qint64, qint64 );
    // 将服务器上的文件添加到Tree Widget部件中
    void addToList(const QUrlInfo &urlInfo);
    // 双击一个目录时显示其内容
    void processItem(QTreeWidgetItem*, int);



    void on_connectButton_clicked();
    void on_cdToParentButton_clicked();
    void on_downloadButton_clicked();

    void lookedUp(const QHostInfo &host);

    void handleServers();
    void on_pushButton_2_clicked();
    void on_pushButton_3_clicked();
};

#endif // MAINWINDOW_H
