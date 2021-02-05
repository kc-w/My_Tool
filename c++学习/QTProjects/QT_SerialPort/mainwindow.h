#ifndef MAINWINDOW_H
#define MAINWINDOW_H


#include <QMainWindow>
#include <QSerialPort>
#include <QTextImageFormat>

QT_BEGIN_NAMESPACE
namespace Ui { class MainWindow; }
QT_END_NAMESPACE

class MainWindow : public QMainWindow
{
    Q_OBJECT

public:
    MainWindow(QWidget *parent = nullptr);
    ~MainWindow();




private slots:
    //向下位机发送数据
    void on_pushButton_clicked();
    //开启串口
    void on_pushButton_2_clicked();
    //发数据
    void ReadData();



    bool eventFilter(QObject *watched, QEvent *event);

    void on_textEdit_cursorPositionChanged();




private:
    Ui::MainWindow *ui;

    //定义全局串口对象
    QSerialPort *serial;
    QByteArray byteArray;
    QPixmap pixmap;
    QTextImageFormat pic;



};
#endif // MAINWINDOW_H
