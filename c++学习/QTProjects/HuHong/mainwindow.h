#ifndef MAINWINDOW_H
#define MAINWINDOW_H

#include <QMainWindow>

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

private slots:
    void frameClick1();
    void frameClick2();
    void frameClick3();
    void frameClick4();
    void frameClick5();
    void frameClick6();
    void frameClick7();
    void frameClick8();
    void frameClick9();

    void cleanStyle();
    void drawTable();

    void on_pushButton_clicked();
    void on_pushButton_2_clicked();
};
#endif // MAINWINDOW_H
