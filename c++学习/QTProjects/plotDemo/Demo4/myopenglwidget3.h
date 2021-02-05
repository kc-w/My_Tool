#ifndef MYOPENGLWIDGET_H
#define MYOPENGLWIDGET_H

#include <QOpenGLWidget>
#include <QOpenGLFunctions>
#include <QOpenGLBuffer>

class QOpenGLShaderProgram;
class QOpenGLTexture;

class MyOpenGLWidget3 : public QOpenGLWidget, protected QOpenGLFunctions
{
    Q_OBJECT

public:
    explicit MyOpenGLWidget3(QWidget *parent = 0);

protected:
    void initializeGL();
    void paintGL();
    void resizeGL(int width, int height);

private:
    QOpenGLShaderProgram *program;
    QOpenGLBuffer vbo;
    QOpenGLTexture *textures[2];
};

#endif // MYOPENGLWIDGET_H
