#include "mainwindow.h"

#include <QApplication>
#include <QTranslator>

int main(int argc, char *argv[])
{
    QApplication a(argc, argv);

    //使用翻译文件
    QTranslator translator;
    translator.load("../Demo7/Demo7_zh_CN.qm");
    a.installTranslator(&translator);

    MainWindow w;
    w.show();
    return a.exec();
}
