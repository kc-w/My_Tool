#include "myopenglwidget2.h"
#include <QOpenGLShaderProgram>

MyOpenGLWidget2::MyOpenGLWidget2(QWidget *parent)
    : QOpenGLWidget(parent)
{
}

//绘制立体图形
void MyOpenGLWidget2::initializeGL()
{
    // 为当前环境初始化OpenGL函数
    initializeOpenGLFunctions();

    // 创建顶点着色器
    QOpenGLShader *vshader = new QOpenGLShader(QOpenGLShader::Vertex, this);
    const char *vsrc =
            "in vec4 vPosition;                        \n"
            "in vec4 vColor;                           \n"
            "out vec4 color;                           \n"
            "uniform mat4 matrix;                      \n"
            "void main() {                             \n"
            "   color = vColor;                        \n"
            "   gl_Position = matrix * vPosition;      \n"
            "}                                         \n";
    vshader->compileSourceCode(vsrc);

    // 创建片段着色器
    QOpenGLShader *fshader = new QOpenGLShader(QOpenGLShader::Fragment, this);
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

void MyOpenGLWidget2::resizeGL(int , int )
{
}

void MyOpenGLWidget2::paintGL()
{
    int w = width();
    int h = height();
    int side = qMin(w, h);
    glViewport((w - side) / 2, (h - side) / 2, side, side);

    glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

    // 顶点位置(设置8个点)
    GLfloat vertices[2][4][3] = {
        { {-0.8f, 0.8f, 0.8f}, {-0.8f, -0.8f, 0.8f}, {0.8f, -0.8f, 0.8f}, {0.8f, 0.8f, 0.8f} },
        { {0.8f, 0.8f, 0.8f}, {0.8f, -0.8f, 0.8f}, {0.8f, -0.8f, -0.8f}, {0.8f, 0.8f, -0.8f} }
    };


    // 顶点颜色(设置8个颜色)
    GLfloat colors[2][4][3] = {
        { {1.0f, 0.0f, 0.0f}, {0.0f, 1.0f, 0.0f}, {0.0f, 0.0f, 1.0f}, {1.0f, 1.0f, 1.0f} },
        { {1.0f, 0.0f, 0.0f}, {0.0f, 1.0f, 0.0f}, {0.0f, 0.0f, 1.0f}, {1.0f, 1.0f, 1.0f} }
    };



    vbo.create();
    vbo.bind();
    vbo.allocate(vertices, 48*sizeof(GLfloat));

    GLuint vPosition = program->attributeLocation("vPosition");
    program->setAttributeBuffer(vPosition, GL_FLOAT, 0, 3, 0);
    glEnableVertexAttribArray(vPosition);


    vbo.write(24*sizeof(GLfloat), colors, 24*sizeof(GLfloat));
    GLuint vColor = program->attributeLocation("vColor");
    program->setAttributeBuffer(vColor, GL_FLOAT, 24*sizeof(GLfloat), 3, 0);
    glEnableVertexAttribArray(vColor);


    //表示—个3D空间中的4X4变换矩阵
    QMatrix4x4 matrix;
    //设置透视投影矩阵(视角为45度,纵横比为窗口的纵横比,最近的位置,最远的位置)
    matrix.perspective(45.0f, (GLfloat)w/(GLfloat)h, 0.1f, 100.0f);
    //平移(X,Y,Z)将Y设置为-3代表向屏幕里移动
    matrix.translate(0, 0, -3);
    //旋转(角度,X,Y,Z),这里将Y轴设置为1,就是绕Y轴逆时针旋转60°;如果角度为正值，则就是顺时针旋转
    matrix.rotate(-60, 0, 1, 0);
    //将矩阵关联到顶点着色器的matrix变量
    program->setUniformValue("matrix", matrix);



    //第一次绘制用去了4个顶点,所以第2次调用时设置了起始位置为4,绘制出了立方体两个相邻的面
    for(int i=0; i<2; i++){
        glDrawArrays(GL_TRIANGLE_FAN, i*4, 4);
    }

}
