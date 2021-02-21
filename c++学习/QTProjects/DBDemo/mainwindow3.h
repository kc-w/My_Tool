#ifndef MAINWINDOW3_H
#define MAINWINDOW3_H

#include <QFile>
#include <QMainWindow>

namespace Ui {
class MainWindow3;
}

class MainWindow3 : public QMainWindow
{
    Q_OBJECT

public:
    explicit MainWindow3(QWidget *parent = nullptr);
    ~MainWindow3();


private slots:
    void redxml(QFile * file);
    void writexml(QFile * file);
    void doXml(const QString operate);
    void on_pushButton_4_clicked();

    void on_pushButton_5_clicked();

    void on_pushButton_2_clicked();

    void on_pushButton_3_clicked();

    void on_pushButton_clicked();

private:
    Ui::MainWindow3 *ui;


};

#endif // MAINWINDOW3_H
