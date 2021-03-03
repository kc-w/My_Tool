#ifndef LOGINDIALOG_H
#define LOGINDIALOG_H

#include <QDialog>

#include <db/sqlitedbaoperator.h>


namespace Ui {
class LoginDialog;
}

class LoginDialog : public QDialog
{
    Q_OBJECT

public:
    LoginDialog(QWidget *parent = nullptr);
    ~LoginDialog();

    void mousePressEvent(QMouseEvent *event);
    void mouseMoveEvent(QMouseEvent *event);
    void mouseReleaseEvent(QMouseEvent *event);
    void paintEvent(QPaintEvent *event);
    void setHint(QString hint);
    QString getNumber();
    QString getPassword();



private:
    Ui::LoginDialog *ui;
    bool		mMoveing;
    QPoint	    mMovePosition;
    SqliteDBAOperator * db;
    QList<User> users;
    User user;
};

#endif // LOGINDIALOG_H
