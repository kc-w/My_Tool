#include "mainwindow.h"
#include "ui_mainwindow.h"
#include <QDebug>

#include <QDragEnterEvent>
#include <QMimeData>

#include <QBuffer>
#include <QFileInfo>
#include <QImageReader>
#include <QMessageBox>
#include <QSerialPort>
#include <QSerialPortInfo>
#include <QTextDocumentFragment>
#include <QTime>

MainWindow::MainWindow(QWidget *parent)
    : QMainWindow(parent)
    , ui(new Ui::MainWindow)
{
    ui->setupUi(this);


    //得到可用串口的名称
    foreach (const QSerialPortInfo &info,QSerialPortInfo::availablePorts()){
        QSerialPort serial;
        serial.setPort(info);
        if(serial.open(QIODevice::ReadWrite)){
            ui->comboBox_2->addItem(serial.portName());

            qDebug() << serial.portName();
            serial.close();
        }
    }

    //设置波特率下拉菜单默认显示第0项
    ui->comboBox_3->setCurrentIndex(0);

    //打开时默认禁用发送数据按钮
    ui->pushButton->setEnabled(false);


    //为combobo添加事件拦截
    ui->comboBox->installEventFilter(this);


}

MainWindow::~MainWindow()
{
    delete ui;
}


//发出数据
void MainWindow::on_pushButton_2_clicked()
{


    if(ui->pushButton_2->text()==tr("打开串口")){
        serial=new QSerialPort;

        //设置当前的串口名
        serial->setPortName(ui->comboBox_2->currentText());

        //设置波特率
        switch (ui->comboBox_3->currentIndex()) {
        case 0:
            serial->setBaudRate(QSerialPort::Baud9600);
            break;
        case 1:
            serial->setBaudRate(QSerialPort::Baud19200);
            break;
        case 2:
            serial->setBaudRate(QSerialPort::Baud38400);
            break;
        case 3:
            serial->setBaudRate(QSerialPort::Baud57600);
            break;
        case 4:
            serial->setBaudRate(QSerialPort::Baud115200);
            break;
        }

        //设置数据位
        switch (ui->comboBox_4->currentIndex()) {
        case 0:
            serial->setDataBits(QSerialPort::Data8);
            break;
        }

        //设置校验位
        switch (ui->comboBox_5->currentIndex()) {
        case 0:
            serial->setParity(QSerialPort::NoParity);
            break;
        }

        //设置停止位
        switch (ui->comboBox_6->currentIndex()) {
        //停止位为1
        case 0:
            serial->setStopBits(QSerialPort::OneStop);
            break;
        }

        //设置为无流控制
        serial->setFlowControl(QSerialPort::NoFlowControl);


        //读写打开串口
        serial->open(QIODevice::ReadWrite);



        if(!serial->isOpen()){
            QMessageBox::information(NULL, "提示", "串口不存在或已经被占用!");
            return;
        }



        //设置按钮状态
        ui->comboBox_2->setEnabled(false);
        ui->comboBox_3->setEnabled(false);
        ui->comboBox_4->setEnabled(false);
        ui->comboBox_5->setEnabled(false);
        ui->comboBox_6->setEnabled(false);
        ui->pushButton->setEnabled(true);
        ui->pushButton_2->setText(tr("关闭串口"));

        //当串口的接收缓冲区有数据时，QSerilaPort对象会发出一个readyRead()的信号
        QObject::connect(serial,&QSerialPort::readyRead,this,&MainWindow::ReadData);


        ui->comboBox->setAcceptDrops(true);

    }else{
        //关闭串口
        serial->clear();
        serial->close();
        serial->deleteLater();

        //恢复设置使能
        ui->comboBox_2->setEnabled(true);
        ui->comboBox_3->setEnabled(true);
        ui->comboBox_4->setEnabled(true);
        ui->comboBox_5->setEnabled(true);
        ui->comboBox_6->setEnabled(true);
        ui->pushButton->setEnabled(false);
        ui->pushButton_2->setText(tr("打开串口"));

        ui->comboBox->setAcceptDrops(false);
    }
}

//发送数据
void MainWindow::on_pushButton_clicked()
{




    QString path = ui->comboBox->currentText();

    QFileInfo fileInfo(path);
    QBuffer buffer;
    if(fileInfo.isFile()){
        pixmap.load(path);
        pixmap.save(&buffer,"jpg");
    }



    //转换为QByteArray
    char *data=(char*)buffer.data().data();
    //数据长度
    int dataLength=buffer.data().length();

    if(dataLength>120){
        serial->write(data,dataLength);
        serial->write("##1",3);
    }else{

        serial->write(ui->comboBox->currentText().toLatin1());
        serial->write("##2",3);
    }




}


//读取接收到的信息
void MainWindow::ReadData()
{

    QByteArray temp = serial->readAll();

    if(!temp.isEmpty()){

        byteArray.append(temp);
        if(byteArray.contains("##1")){
            QByteArray array=byteArray.left(byteArray.indexOf("##1"));
            QImage image;
            bool flag=image.loadFromData((const uchar *)array.data(),array.size());
            if (flag){
                pixmap=QPixmap::fromImage(image);

//                QTextDocumentFragment fragment;
//                fragment = QTextDocumentFragment::fromHtml("<br><br><img src='C:\\background.png'>");

//                ui->textEdit->textCursor().insertFragment(fragment);

                QString img = QString("%1").arg((QDateTime::currentDateTime().toMSecsSinceEpoch()));

                pixmap.save(img+".jpg", "JPG");
                pic = QTextImageFormat();
                pic.setName(img+".jpg");
                pic.setHeight(50);
                pic.setWidth(50);
                ui->textEdit->append("\n");
                ui->textEdit->textCursor().insertImage(pic);

            }
            byteArray = byteArray.right(byteArray.length()-byteArray.indexOf("##1")-3);
        }
        if(byteArray.contains("##2")){
            QByteArray array=byteArray.left(byteArray.indexOf("##2"));
            ui->textEdit->append("\n");
            ui->textEdit->append(tr(array));
            byteArray = byteArray.right(byteArray.length()-byteArray.indexOf("##2")-3);
        }
    }


}


bool MainWindow::eventFilter(QObject *watched, QEvent *event) {
    if (watched == ui->comboBox) {
        //当拖放时鼠标进入comboBox时, comboBox接受拖放的动作
        if (event->type() == QEvent::DragEnter) {
            QDragEnterEvent *dee = dynamic_cast<QDragEnterEvent *>(event);

            dee->acceptProposedAction();

        }
        //当放操作发生后, 取得拖放的数据
        if (event->type() == QEvent::Drop) {
            QDropEvent *de = dynamic_cast<QDropEvent *>(event);

            QList<QUrl> urls = de->mimeData()->urls();


            if (urls.isEmpty()) {
                return true;
            }

            QString path = urls.first().toLocalFile();

            ui->comboBox->setEditText(path);
//            // [[4]]: 在label上显示拖放的图片
//            // QImage对I/O优化过, QPixmap对显示优化
//            QImage image(path);
//            if (!image.isNull()) {
//                image = image.scaled(ui->label->size(),Qt::KeepAspectRatio,Qt::SmoothTransformation);
//                ui->label->setPixmap(QPixmap::fromImage(image));
//            }


            return true;

        }

    }


    return QWidget::eventFilter(watched, event);

}

void MainWindow::on_textEdit_cursorPositionChanged()
{
    QTextCursor cursor = ui->textEdit->textCursor();
    cursor.movePosition(QTextCursor::End);
    ui->textEdit->setTextCursor(cursor);
}
