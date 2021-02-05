#ifndef FORM3_H
#define FORM3_H

#include <QWidget>

namespace Ui {
class Form3;
}

class Form3 : public QWidget
{
    Q_OBJECT

public:
    explicit Form3(QWidget *parent = nullptr);
    ~Form3();

private:
    Ui::Form3 *ui;



    // 缓冲区
    QPixmap pix3;
    // 临时缓冲区
    QPixmap tempPix3;
    QPoint startPoint3;
    QPoint endPoint3;
    // 是否正在绘图的标志
    bool isDrawing3;


protected:
    void mousePressEvent(QMouseEvent *event);
    void mouseMoveEvent(QMouseEvent *event);
    void mouseReleaseEvent(QMouseEvent *event);
    void paintEvent(QPaintEvent *);
};

#endif // FORM3_H
