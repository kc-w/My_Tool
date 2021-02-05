#ifndef MAINWINDOW_H
#define MAINWINDOW_H

#include <QMainWindow>

class QLineEdit;
class QDialog;
class MySyntaxHighlighter;
class QPrinter;

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
    void showTextFrame();//遍历文档框架
    void showTextBlock();//遍历所有文本块
    void setTextFont(bool checked); //设置字体格式

    void insertTable(); // 插入表格
    void insertList(); // 插入列表
    void insertImage(); // 插入图片

    void textFind(); //査找文本
    void findLast(); //査找上一个
    void findNext(); //査找下一个

    void dragEnterEvent(QDragEnterEvent *event); // 拖动进入事件
    void dropEvent(QDropEvent *event);           // 放下事件


    void doPrint();
    void doPrintPreview();
    void printPreview(QPrinter *printer);
    void createPdf();



private:
    Ui::MainWindow *ui;
    QLineEdit * lineEdit;
    QDialog * findDialog;
    MySyntaxHighlighter *highlighter;


};
#endif // MAINWINDOW_H
