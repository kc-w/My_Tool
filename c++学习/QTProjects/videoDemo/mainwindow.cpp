#include "mainwindow.h"
#include "ui_mainwindow.h"
#include <QSoundEffect>
#include <QMediaPlayer>
#include <QVideoWidget>
#include <QMovie>

#include <QMediaPlaylist>



MainWindow::MainWindow(QWidget *parent)
    : QMainWindow(parent)
    , ui(new Ui::MainWindow)
{
    ui->setupUi(this);





//当媒体的状况发生改变时播放器会发射mediaStatusChanged()信号,可以通过关联该信号来获取媒体加载的一些信息
//QMediaPlayer::UnknownMediaStatus 媒体的状况无法确定
//QMediaPlayer::NoMedia            当前媒体不存在时,播放器处于停止状态
//QMediaPlayer::LoadingMedia       当前媒体正在被加载时,播放器可以处于任何状态
//QMediaPlayer::LoadedMedia        当前媒体已经加载完成时,播放器处于停止状态
//QMediaPlayer::StalledMedia       没有足够的缓冲或者其他临时中断,而导致当前媒体的播放处于停滞时,播放器处于播放状态或者暂停状态
//QMediaPlayer::BufferingMedia     播放器正在缓冲数据,但已经缓冲了足够的数据以便稍后继续播放时,播放器处于播放状态或者暂停状态
//QMediaPlayer::BufferedMedia      播放器已经完全缓冲了当前媒体时,播放器处于播放状态或者暂停状态
//QMediaPlayer::EndOfMedia         已经播放到了当前媒体的结尾时,播放器处于停止状态
//QMediaPlayer::InvalidMedia       当前媒体无法播放时,播放器处于停止状态

//当播放器发生错误时会发射error()信号，通过关联该信号可以对相应的错误进行处理
//QMediaPlayer::NoError               没有发生错误
//QMediaPlayer::ResourceError         媒体资源无法被解析
//QMediaPlayer::FormatError           媒体格式不(完全)支持,可能依然可以播放,但是会缺少声音或者图像
//QMediaPlayer::NetworkError          发生了一个网络错误
//QMediaPlayer::AccessDeniedError     没有相应的权限来播放媒体资源
//QMediaPlayer::ServiceMissingError   没有发现有效的播放服务,无法进行播放

//QMediaPlayer进行播放时拥有3种状态,它总是处于这3种状态的其中一种,当播放器的状态发生改变时就会发射stateChanged()信号
//QMediaPlayer::StoppedState    停止状态,处于该状态时,播放器会从当前媒体的开始进行播放,调用stop()函数可以直接进入该状态
//QMediaPlayer::PlayingState    播放状态,正在播放媒体内容,调用play()函数可以直接进入该状态
//QMediaPlayer::PausedState     暂停状态,暂停当前的播放,处于该状态时播放器会从当前媒体暂停的位置进行播放,调用pause()函数可以直接进入暂停状态


    //播放视频(Qt中的多媒体播放,底层是使用DirectShowPlayerService,需要一个DirectShow解码器,例如LAV Filters,以管理员身份运行3个.bat文件)
    player1 = new QMediaPlayer(this);
    videoWidget = new QVideoWidget(this);
    videoWidget->resize(460, 300);

    player1->setVideoOutput(videoWidget);
    player1->setMedia(QUrl("qrc:/video/video.wmv"));
    player1->play();

    // 亮度
    ui->horizontalSlider_1->setValue(videoWidget->brightness());
    connect(ui->horizontalSlider_1, &QSlider::sliderMoved,videoWidget, &QVideoWidget::setBrightness);
    connect(videoWidget, &QVideoWidget::brightnessChanged,ui->horizontalSlider_1, &QSlider::setValue);
    // 对比度
    ui->horizontalSlider_2->setValue(videoWidget->contrast());
    connect(ui->horizontalSlider_2, &QSlider::sliderMoved,videoWidget, &QVideoWidget::setContrast);
    connect(videoWidget, &QVideoWidget::contrastChanged,ui->horizontalSlider_2, &QSlider::setValue);
    // 色相
    ui->horizontalSlider_3->setValue(videoWidget->hue());
    connect(ui->horizontalSlider_3, &QSlider::sliderMoved,videoWidget, &QVideoWidget::setHue);
    connect(videoWidget, &QVideoWidget::hueChanged,ui->horizontalSlider_3, &QSlider::setValue);
    // 饱和度
    ui->horizontalSlider_4->setValue(videoWidget->saturation());
    connect(ui->horizontalSlider_4, &QSlider::sliderMoved,videoWidget, &QVideoWidget::setSaturation);
    connect(videoWidget, &QVideoWidget::saturationChanged,ui->horizontalSlider_4, &QSlider::setValue);



    //播放音频
    effect = new QSoundEffect(this);
    effect->setSource(QUrl("qrc:/video/sound.wav"));
    effect->setVolume(0.25f);



    // 设置标签的对齐方式为居中对齐、自动填充背景为暗色
    ui->label_1->setAlignment(Qt::AlignCenter);
    ui->label_1->setBackgroundRole(QPalette::Dark);
    ui->label_1->setAutoFillBackground(true);
    movie = new QMovie(this);
    movie->setFileName(":/video/movie.gif");
    // 设置缓存模式
    movie->setCacheMode(QMovie::CacheAll);
    // 设置动画大小为标签的大小
    QSize size = ui->label_1->size();
    movie->setScaledSize(size);
    ui->label_1->setMovie(movie);
    // 设置水平滑块的最大最小值，当动画播放时自动更改滑块的值
    ui->horizontalSlider->setMinimum(0);
    ui->horizontalSlider->setMaximum(movie->frameCount());
    connect(movie, &QMovie::frameChanged,ui->horizontalSlider, &QSlider::setValue);



    //播放音频
    player2 = new QMediaPlayer(this);
//    player2 ->setMedia(QUrl::fromLocalFile("qrc:/video/music1.mp3"));
    //要想显示元数据触发metaDataAvailableChanged()信号需要写绝对路径才行
//    player2 ->setMedia(QUrl::fromLocalFile("E:/QTProjects/Demo12/music1.mp3"));
    //播放器进度变化
    connect(player2, &QMediaPlayer::positionChanged,this, &MainWindow::updatePosition);
    //播放器状态变化
    connect(player2, &QMediaPlayer::stateChanged, this, &MainWindow::stateChanged);
    //播放器的媒体变化
    connect(player2, &QMediaPlayer::mediaStatusChanged,this, &MainWindow::mediaStatusChanged);
    //播放器发生错误
    connect(player2, static_cast<void(QMediaPlayer::*)(QMediaPlayer::Error)>(&QMediaPlayer::error), this,
            &MainWindow::showError);
    //每当QMediaPlayer对媒体源进行解析,元数据可用时都会发射metaDataAvailableChanged()信号
    connect(player2, &QMediaPlayer::metaDataAvailableChanged,this, &MainWindow::metaDataAvailableChanged);

    //QMediaPlaylist类提供了一个播放列表,它其实是一个QMediaContent对象列表,并包含了一些实用功能
    playlist = new QMediaPlaylist;
    playlist->addMedia(QUrl::fromLocalFile("E:/QTProjects/Demo12/music1.mp3"));
    playlist->addMedia(QUrl::fromLocalFile("E:/QTProjects/Demo12/music2.mp3"));
    playlist->addMedia(QUrl::fromLocalFile("E:/QTProjects/Demo12/music3.mp3"));
    playlist->setPlaybackMode(QMediaPlaylist::Random);
    player2->setPlaylist(playlist);

}

MainWindow::~MainWindow()
{
    delete ui;
}


// 播放音频1
void MainWindow::on_pushButton_1_clicked()
{
    effect->play();
}
// 停止音频1
void MainWindow::on_pushButton_2_clicked()
{
    effect->stop();
}
//监听改变值设置音频循环播放次数1
void MainWindow::on_spinBox_1_valueChanged(int arg1)
{
    effect->setLoopCount(arg1);
}

//设置gif播放进度2
void MainWindow::on_horizontalSlider_valueChanged(int value)
{
    movie->jumpToFrame(value);
}
//开始播放gif2
void MainWindow::on_pushButton_3_clicked()
{
    movie->start();
}
//暂停播放gif2
void MainWindow::on_pushButton_4_toggled(bool checked)
{
    movie->setPaused(checked);
}
//停止播放gif2
void MainWindow::on_pushButton_5_clicked()
{
    movie->stop();
}
//对gif进行截屏2
void MainWindow::on_pushButton_6_clicked()
{
    int id = movie->currentFrameNumber();
    QPixmap pix = movie->currentPixmap();
    pix.save(QString("%1.png").arg(id));
}
//设置gif的播放速度2
void MainWindow::on_spinBox_2_valueChanged(int arg1)
{
    movie->setSpeed(arg1);
}


void MainWindow::on_pushButton_clicked()
{
    //播放
    player2->play();
}

void MainWindow::on_pushButton_7_clicked()
{
    //暂停
    player2->pause();
}

void MainWindow::on_pushButton_8_clicked()
{
    //停止
    player2->stop();
}

void MainWindow::on_horizontalSlider_5_sliderMoved(int position)
{
    //设置播放进度
    player2->setPosition(position * 1000);
}

//自定义槽函数
void MainWindow::updatePosition(qint64 position)
{ // 更新滑块显示
    ui->horizontalSlider->setMaximum(player2->duration() / 1000);
    ui->horizontalSlider->setValue(position / 1000);
}

void MainWindow::mediaStatusChanged(QMediaPlayer::MediaStatus status)
{
    switch (status) {
    case QMediaPlayer::UnknownMediaStatus:
        ui->label->setText(tr("媒体未知状况！"));
        break;
    case QMediaPlayer::NoMedia:
        ui->label->setText(tr("没有媒体文件！"));
        break;
    case QMediaPlayer::BufferingMedia:
        ui->label->setText(tr("正在缓冲媒体文件！"));
        break;
    case QMediaPlayer::BufferedMedia:
        ui->label->setText(tr("媒体文件缓冲完成！"));
        break;
    case QMediaPlayer::LoadingMedia:
        ui->label->setText(tr("正在加载媒体！"));
        break;
    case QMediaPlayer::StalledMedia:
        ui->label->setText(tr("播放停滞！"));
        break;
    case QMediaPlayer::EndOfMedia:
        ui->label->setText(tr("播放结束！"));
        break;
    case QMediaPlayer::LoadedMedia:
        ui->label->setText(tr("媒体加载完成！"));
        break;
    case QMediaPlayer::InvalidMedia:
        ui->label->setText(tr("不可用的媒体文件！"));
        break;
    default: break;
    }
}

void MainWindow::stateChanged(QMediaPlayer::State state)
{
    switch (state) {
    case QMediaPlayer::StoppedState:
        ui->label_5->setText(tr("停止状态！"));
        break;
    case QMediaPlayer::PlayingState:
        ui->label_5->setText(tr("播放状态！"));
        break;
    case QMediaPlayer::PausedState:
        ui->label_5->setText(tr("暂停状态！"));
        break;
    default: break;
    }
}

void MainWindow::showError(QMediaPlayer::Error error)
{
    switch (error) {
    case QMediaPlayer::NoError:
        ui->label_6->setText(tr("没有错误！"));
        break;
    case QMediaPlayer::ResourceError:
        ui->label_6->setText(tr("媒体资源无法被解析！"));
        break;
    case QMediaPlayer::FormatError:
        ui->label_6->setText(tr("不支持该媒体格式！"));
        break;
    case QMediaPlayer::NetworkError:
        ui->label_6->setText(tr("发生了一个网络错误！"));
        break;
    case QMediaPlayer::AccessDeniedError:
        ui->label_6->setText(tr("没有播放权限！"));
        break;
    case QMediaPlayer::ServiceMissingError:
        ui->label_6->setText(tr("没有发现有效的播放服务！"));
        break;
    default: break;
    }
}

// 获取元数据
void MainWindow::metaDataAvailableChanged(bool available)
{
    if(available) {
        QString title = player2->metaData("Title").toString();
        QString author = player2->metaData("Author").toString();
        setWindowTitle(title + "-" + author);
    }

}
