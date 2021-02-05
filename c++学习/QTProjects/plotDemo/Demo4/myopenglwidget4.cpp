#include "myopenglwidget4.h"
#include <QOpenGLShaderProgram>
#include <QOpenGLTexture>
#include <QKeyEvent>

MyOpenGLWidget4::MyOpenGLWidget4(QWidget *parent)
    : QOpenGLWidget(parent)
{
    //初始化变量
    translate = -6.0;
    xRot = zRot = 0.0;
    yRot = -30.0;
}

MyOpenGLWidget4::~MyOpenGLWidget4()
{
    makeCurrent();
    vbo.destroy();
    for (int i = 0; i < 2; ++i){
        delete textures[i];
    }
    delete program;
    doneCurrent();
}


void MyOpenGLWidget4::initializeGL()
{
    for (int i = 0; i < 2; ++i){
        //mirrored()返回镜像
        textures[i] = new QOpenGLTexture(QImage(QString(":/image/side%1.png").arg(i + 1)).mirrored());
    }

    // 为当前环境初始化OpenGL函数
    initializeOpenGLFunctions();

    // 启用深度测试
    glEnable(GL_DEPTH_TEST);

    // 创建顶点着色器
    QOpenGLShader *vshader = new QOpenGLShader(QOpenGLShader::Vertex, this);
    const char *vsrc =
            "#version 330; \n"
            "in vec4 vPosition;                        \n"
            "in vec4 vTexCoord;                        \n"
            "out vec4 texCoord;                        \n"
            "uniform mat4 matrix;                      \n"
            "void main() {                             \n"
            "   texCoord = vTexCoord;                  \n"
            "   gl_Position = matrix * vPosition;      \n"
            "}                                         \n";
    vshader->compileSourceCode(vsrc);
    // 创建片段着色器
    QOpenGLShader *fshader = new QOpenGLShader(QOpenGLShader::Fragment, this);
    const char *fsrc =
            "#version 330; \n"
            "uniform sampler2D tex;                     \n"
            "in vec4 texCoord;                          \n"
            "out vec4 fColor;                           \n"
            "void main() {                              \n"
            "   fColor = texture(tex, texCoord);        \n"
            "}                                          \n";
    fshader->compileSourceCode(fsrc);

    // 创建着色器程序
    program = new QOpenGLShaderProgram;
    program->addShader(vshader);
    program->addShader(fshader);

    program->link();
    program->bind();

}

void MyOpenGLWidget4::resizeGL(int , int )
{
}

void MyOpenGLWidget4::paintGL()
{
    int w = width();
    int h = height();
    int side = qMin(w, h);
    glViewport((w - side) / 2, (h - side) / 2, side, side);

    glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

    // 顶点位置
    GLfloat vertices[2][4][3] = {
        { {-0.8f, 0.8f, 0.8f}, {-0.8f, -0.8f, 0.8f}, {0.8f, -0.8f, 0.8f}, {0.8f, 0.8f, 0.8f} },
        { {0.8f, 0.8f, 0.8f}, {0.8f, -0.8f, 0.8f}, {0.8f, -0.8f, -0.8f}, {0.8f, 0.8f, -0.8f} }
    };

    // 纹理坐标
    GLfloat coords[2][4][2] = {
        { {0.0f, 1.0f}, {0.0f, 0.0f}, {1.0f, 0.0f}, {1.0f, 1.0f} },
        { {0.0f, 1.0f}, {0.0f, 0.0f}, {1.0f, 0.0f}, {1.0f, 1.0f} }
    };


    vbo.create();
    vbo.bind();
    vbo.allocate(vertices, 40*sizeof(GLfloat));

    GLuint vPosition = program->attributeLocation("vPosition");
    program->setAttributeBuffer(vPosition, GL_FLOAT, 0, 3, 0);
    glEnableVertexAttribArray(vPosition);

    vbo.write(24*sizeof(GLfloat), coords, 16*sizeof(GLfloat));
    GLuint vTexCoord = program->attributeLocation("vTexCoord");
    program->setAttributeBuffer(vTexCoord, GL_FLOAT, 24*sizeof(GLfloat), 2, 0);
    glEnableVertexAttribArray(vTexCoord);
    program->setUniformValue("tex", 0);

    QMatrix4x4 matrix;
    matrix.perspective(45.0f, (GLfloat)w/(GLfloat)h, 0.1f, 100.0f);
    //平移
    matrix.translate(0, 0, translate);
    //旋转
    matrix.rotate(xRot, 1.0, 0.0, 0.0);
    matrix.rotate(yRot, 0.0, 1.0, 0.0);
    matrix.rotate(zRot, 0.0, 0.0, 1.0);
    program->setUniformValue("matrix", matrix);


    // 绘制
    for(int i=0; i<2; i++) {
        textures[i]->bind();
        glDrawArrays(GL_TRIANGLE_FAN, i*4, 4);
    }
}

void MyOpenGLWidget4::keyPressEvent(QKeyEvent *event)
{
    switch (event->key()) {
    case Qt::Key_Up:
        xRot += 10;
        break;
    case Qt::Key_Left:
        yRot += 10;
        break;
    case Qt::Key_Right:
        zRot += 10;
        break;
    case Qt::Key_Down:
        translate -= 1;
        break;
    case Qt::Key_Space:
        translate += 1;
        break;
    default:
        break;
    }
    update();
    QOpenGLWidget::keyPressEvent(event);
}


