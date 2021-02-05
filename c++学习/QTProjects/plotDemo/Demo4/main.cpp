#include <QApplication>

//导入头文件问题:QOpenGLWidget类不能同时导入,否则报错
//#include "myopenglwidget.h"
//#include "myopenglwidget1.h"
//#include "myopenglwidget2.h"
//#include "myopenglwidget3.h"
#include "myopenglwidget4.h"

int main(int argc, char *argv[])
{
    QApplication app(argc,argv);
    MyOpenGLWidget4 w;
    w.resize(400, 300);
    w.show();
    return app.exec();
}
