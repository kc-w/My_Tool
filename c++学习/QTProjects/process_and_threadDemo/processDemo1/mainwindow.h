#ifndef MAINWINDOW_H
#define MAINWINDOW_H

#include <QMainWindow>
#include <QProcess>
#include <QSharedMemory>

QT_BEGIN_NAMESPACE
namespace Ui { class MainWindow; }
QT_END_NAMESPACE

class MainWindow : public QMainWindow
{
    Q_OBJECT

public:
    MainWindow(QWidget *parent = nullptr);
    ~MainWindow();

    void detach();


private slots:
    void on_pushButton_clicked();

    void showResult();
    void showState(QProcess::ProcessState);
    void showError();
    void showFinished(int, QProcess::ExitStatus);

    void on_loadFromFileButton_clicked();

    void on_loadFromSharedMemoryButton_clicked();

private:
    Ui::MainWindow *ui;
    QProcess myProcess;
    QSharedMemory sharedMemory;
};
#endif // MAINWINDOW_H
