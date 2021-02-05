#ifndef MAINWINDOW_H
#define MAINWINDOW_H

#include <QMediaPlayer>
#include <QMainWindow>
class QSoundEffect;
class QMediaPlayer;
class QVideoWidget;
class QMovie;

class QMediaPlaylist;

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
    void on_pushButton_1_clicked();

    void on_pushButton_2_clicked();

    void on_spinBox_1_valueChanged(int arg1);

    void on_horizontalSlider_valueChanged(int value);

    void on_pushButton_3_clicked();

    void on_pushButton_4_toggled(bool checked);

    void on_pushButton_5_clicked();

    void on_pushButton_6_clicked();

    void on_spinBox_2_valueChanged(int arg1);

    void updatePosition(qint64 position);
    void stateChanged(QMediaPlayer::State state);
    void mediaStatusChanged(QMediaPlayer::MediaStatus status);
    void showError(QMediaPlayer::Error error);
    void metaDataAvailableChanged(bool available);

    void on_pushButton_clicked();

    void on_pushButton_7_clicked();

    void on_pushButton_8_clicked();

    void on_horizontalSlider_5_sliderMoved(int position);

private:
    Ui::MainWindow *ui;
    QSoundEffect *effect;

    QMediaPlayer *player1;
    QMediaPlayer *player2;
    QVideoWidget *videoWidget;
    QMovie *movie;

    QMediaPlaylist *playlist;
};
#endif // MAINWINDOW_H
