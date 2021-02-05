#include "myopenglwidget1.h"
#include <QOpenGLShaderProgram>

MyOpenGLWidget1::MyOpenGLWidget1(QWidget *parent)
    : QOpenGLWidget(parent)
{
}

//绘制彩色3D图形
void MyOpenGLWidget1::initializeGL()
{
    // 为当前环境初始化OpenGL函数
    initializeOpenGLFunctions();

    // 创建顶点着色器
    QOpenGLShader *vshader = new QOpenGLShader(QOpenGLShader::Vertex, this);
    //声明了输入变量vColor和输出变量color,并将vColor获取的颜色数据传递给color,传递给片段着色器
    const char *vsrc =
            "in vec4 vPosition;                        \n"
            "in vec4 vColor;                           \n"
            "out vec4 color;                           \n"
            "void main() {                             \n"
            "   color = vColor;                        \n"
            "   gl_Position = vPosition;               \n"
            "}                                         \n";
    vshader->compileSourceCode(vsrc);

    // 创建片段着色器
    QOpenGLShader *fshader = new QOpenGLShader(QOpenGLShader::Fragment, this);
    //声明了一个输入变量color,用来和顶点着色器的输出变量color对应,而输出变量fColor可以将color输入的颜色数据输出到着色管线中用来为图形着色
    const char *fsrc =
            "in vec4 color;                             \n"
            "out vec4 fColor;                           \n"
            "void main() {                              \n"
            "   fColor = color;                         \n"
            "}                                          \n";
    fshader->compileSourceCode(fsrc);

    // 创建着色器程序
    program = new QOpenGLShaderProgram;
    program->addShader(vshader);
    program->addShader(fshader);

    program->link();
    program->bind();
}

void MyOpenGLWidget1::resizeGL(int , int )
{
}

void MyOpenGLWidget1::paintGL()
{
    int w = width();
    int h = height();
    int side = qMin(w, h);
    glViewport((w - side) / 2, (h - side) / 2, side, side);

    glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

    // 顶点位置
    GLfloat vertices[] = {
        -0.8f, 0.8f,
        -0.8f, -0.8f,
        0.8f, -0.8f,
        0.8f, 0.8f
    };

    // 顶点颜色,分别为4个顶点进行着色
    GLfloat colors[] = {
        1.0f, 0.0f, 0.0f,
        0.0f, 1.0f, 0.0f,
        0.0f, 0.0f, 1.0f,
        1.0f, 1.0f, 1.0f
    };

    vbo.create();
    vbo.bind();
    //8+12=20,为了颜色偏移在缓存中不覆盖顶点偏移
    vbo.allocate(vertices, 20*sizeof(GLfloat));

    //获取vPosition的位置
    GLuint vPosition = program->attributeLocation("vPosition");
    //设置图形缓存
    program->setAttributeBuffer(vPosition, GL_FLOAT, 0, 2, 0);
    //启用顶点数组
    glEnableVertexAttribArray(vPosition);

    //直接在前面创建的缓存中写入了颜色数组数据,并为vColor变量指定了缓存,替换掉缓存中已有的内容
    //(要替换数据开始位置的偏移值,顶点颜色数组,顶点颜色偏移)
    vbo.write(8*sizeof(GLfloat), colors, 12*sizeof(GLfloat));
    //获取vColor的颜色
    GLuint vColor = program->attributeLocation("vColor");
    //偏移值为顶点数的值
    program->setAttributeBuffer(vColor, GL_FLOAT, 8*sizeof(GLfloat), 3, 0);
    //启用颜色数组
    glEnableVertexAttribArray(vColor);

    // 绘制
    glDrawArrays(GL_TRIANGLE_FAN, 0, 4);
}


