#include "myopenglwidget3.h"
#include <QOpenGLShaderProgram>
#include <QOpenGLTexture>

MyOpenGLWidget3::MyOpenGLWidget3(QWidget *parent)
    : QOpenGLWidget(parent)
{

}

// 使用纹理贴图
void MyOpenGLWidget3::initializeGL()
{

//    // 加入图片
//    textures[0] = new QOpenGLTexture(QImage(":/image/side1.png").mirrored());
//    textures[1] = new QOpenGLTexture(QImage(":/image/side2.png").mirrored());


    for (int i = 0; i < 2; ++i){
        //mirrored()返回镜像
        textures[i] = new QOpenGLTexture(QImage(QString("side%1.png").arg(i + 1)).mirrored());
    }

    // 为当前环境初始化OpenGL函数
    initializeOpenGLFunctions();

    // 创建顶点着色器
    QOpenGLShader *vshader = new QOpenGLShader(QOpenGLShader::Vertex, this);
    //vTexCoord用来输入纹理坐标
    const char *vsrc =
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
    //声明了一个sampler2D类型的采样器变量tex,然后在main()函数中使用texture()纹理函数
    //采样器tex会以texCoord表示的纹理坐标进行采样,该函数返回包括采样的纹理数据的向量
    const char *fsrc =
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

void MyOpenGLWidget3::resizeGL(int , int )
{
}

void MyOpenGLWidget3::paintGL()
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



    //纹理坐标{X,Y},将顶点颜色的相关设置更换为纹理坐标的设置
    //对于X坐标0.0表示纹理的左侧,0.5表示纹理的中点,1.0表示纹理的右侧;对于Y坐标,0.0表示纹理的底部,0.5表示纹理的中点.1.0表示纹理的顶部
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
    matrix.translate(0, 0, -3);
    matrix.rotate(-60, 0, 1, 0);
    //绕Y轴逆时针旋转
    program->setUniformValue("matrix", matrix);

    // 绘制
    for(int i=0; i<2; i++) {
        //QOpenGLTexture类的bind()函数可以将纹理绑定到当前活动纹理单元来准备渲染
        textures[i]->bind();
        glDrawArrays(GL_TRIANGLE_FAN, i*4, 4);
    }
}



