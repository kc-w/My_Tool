#include "mainwindow.h"

#include <QMediaPlayer>
#include <QGraphicsVideoItem>
#include <QGraphicsView>
#include <QGraphicsScene>


#include <QApplication>

int main(int argc, char *argv[])
{
    QApplication a(argc, argv);
    MainWindow w;
    w.show();

    //创建场景
    QGraphicsScene scene;
    //将场景放入视图
    QGraphicsView view(&scene);
    //设置视图大小
    view.resize(600, 320);
    //创建图形项将图形项加入到场景
    QGraphicsVideoItem item;
    scene.addItem(&item);
    item.setSize(QSizeF(500, 300));
    //创建播放器,设置在图形项播放
    QMediaPlayer player;
    player.setVideoOutput(&item);
    player.setMedia(QUrl("qrc:/video/video.wmv"));
    player.play();
    view.show();



    return a.exec();
}
