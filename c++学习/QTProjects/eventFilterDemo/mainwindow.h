#ifndef MAINWINDOW_H
#define MAINWINDOW_H

#include <QMainWindow>

class MyLineEdit;

QT_BEGIN_NAMESPACE
namespace Ui { class MainWindow; }
QT_END_NAMESPACE

class MainWindow : public QMainWindow
{
    Q_OBJECT

public:
    MainWindow(QWidget *parent = nullptr);
    ~MainWindow();




private:
    Ui::MainWindow *ui;

    MyLineEdit *lineEdit;

protected:
    void mousePressEvent(QMouseEvent *event);      // 鼠标按下事件
    void dragEnterEvent(QDragEnterEvent *event);   // 拖动进入事件
    void dragMoveEvent(QDragMoveEvent *event);     // 拖动事件
    void dropEvent(QDropEvent *event);             // 放下事件
    void keyPressEvent(QKeyEvent *event);

    bool eventFilter(QObject *obj,QEvent *event);
};
#endif // MAINWINDOW_H
