#ifndef MAINWINDOW2_H
#define MAINWINDOW2_H

#include <QMainWindow>

class QDataWidgetMapper;

namespace Ui {
class MainWindow2;
}

class MainWindow2 : public QMainWindow
{
    Q_OBJECT

public:
    explicit MainWindow2(QWidget *parent = nullptr);
    ~MainWindow2();

private slots:
    void on_pushButton_clicked();

    void on_pushButton_2_clicked();

private:
    Ui::MainWindow2 *ui;
        QDataWidgetMapper *mapper;
};

#endif // MAINWINDOW2_H
