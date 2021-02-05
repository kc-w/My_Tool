#include "myopenglwidget.h"
#include <QOpenGLShaderProgram>

MyOpenGLWidget::MyOpenGLWidget(QWidget *parent)
    : QOpenGLWidget(parent)
{
}

void MyOpenGLWidget::initializeGL()
{
    // 为当前环境初始化OpenGL函数
    initializeOpenGLFunctions();

    // 创建顶点着色器
    QOpenGLShader *vshader = new QOpenGLShader(QOpenGLShader::Vertex, this);

    //in存储限制符表明了数据进入着色器的流向,与其对应的是out存储限制符
    //vec4:有4个值
    //顶点着色器声明了一个名为vPosition的输入变量,用于获取外部输入的顶点数据
    //glPosition = vPosition表明将输入的顶点位置复制到顶点着色器的指定输出位置gl_Position中
    const char *vsrc =
            "in vec4 vPosition;                        \n"
            "void main() {                             \n"
            "   gl_Position = vPosition;               \n"
            "}                                         \n";
    vshader->compileSourceCode(vsrc);


    // 创建片段着色器
    QOpenGLShader *fshader = new QOpenGLShader(QOpenGLShader::Fragment, this);
    const char *fsrc =
            "void main() {                              \n"
            "   gl_FragColor = vec4(1.0, 1.0, 1.0, 1.0);\n"
            "}                                          \n";
    fshader->compileSourceCode(fsrc);

    // 创建着色器程序
    program = new QOpenGLShaderProgram;
    program->addShader(vshader);
    program->addShader(fshader);

    program->link();
    program->bind();
}

void MyOpenGLWidget::resizeGL(int , int )
{
}

void MyOpenGLWidget::paintGL()
{
    int w = width();
    int h = height();
    int side = qMin(w, h);
    //设置视口为整个窗口中尽可能大的正方形
    //这个操作本应该放到resizeGL()函数中进行,只是由于Qt版本原因,现在并不能实现应有的效果,所以放到了paintGL()中
    glViewport((w - side) / 2, (h - side) / 2, side, side);
    //清除颜色缓存和深度缓存
    glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

    // 顶点位置,vec4的默认值为(0, 0, 0, 1),仅当指定了X和Y坐标时.其他两个坐标值将被自动指定为0和1
    //这里以原点为中心设置了一个正方形的4个顶点,逆时针绘制出来的是正面,而顺时针绘制出来的是反面
    GLfloat vertices[] = {
        -0.8f, 0.8f,
        -0.8f, -0.8f,
        0.8f, -0.8f,
        0.8f, 0.8f
    };




    //可以返回变量在着色器程序参数列表中的位置,这里获取了vPosition的位置
    GLuint vPosition = program->attributeLocation("vPosition");
    //将vPosition与顶点数组vertices进行关联
    //vPosition:要输入变量的位置索引
    //2:每个顶点需要更新的分量数目(这里vertices每行只有2个值，所以size为2)
    //GL_FLOAT:指定了数组中元素的类型(这里vertices是GLfloat类型的)
    //GL_FALSE:设置顶点数据在存储前是否需要进行归一化
    //0:数组中每两个元素之间的大小偏移值,一般设置为0
    //vertices:设置顶点数组指针
    glVertexAttribPointer(vPosition, 2, GL_FLOAT, GL_FALSE, 0, vertices);


//    //========================设置图形缓存================================
//    //在OpenGL服务器中创建缓存对象
//    vbo.create();
//    //将与该对象相关联的缓存绑定到当前OpenGL环境
//    vbo.bind();
//    //在缓存中为数组分配空间并将缓存初始化为数组的内容
//    vbo.allocate(vertices, 8*sizeof(GLfloat));
//    //glVertexAttribPointer()使用的顶点数组中指定的数据会保存在客户端内存中,在进行glDrawArrays()等绘图调用时
//    //该函数与glVertexAttribPointer()类似,可以将其缓存到图形内存中,避免每次绘图时都从客户内存复制到图形内存,使用时注释掉glVertexAttribPointer()
//    //(设置顶点缓存,数组中元素的类型,要使用数据的偏移值,每个顶点需要更新的分量数目,默认为0)
//    program->setAttributeBuffer(vPosition, GL_FLOAT, 0, 2, 0);



    //启用顶点数组
    glEnableVertexAttribArray(vPosition);


    //使用当前绑定的顶点数组元素来建立几何图形(构建图形的类型,指定元素起始位置,顶点个数)
    //GL_POINTS(点)、GL_LINES(线)、GL_LINE_STRIP(条带线)、GL_LINE_LOOP(循环线)
    //GL_TRIANGLES(独立三角形)、GL_TRIANGLE_STRIPC三角形条带)、GL_TRIANGLE_FAN(三角形扇面)
    glDrawArrays(GL_TRIANGLE_FAN, 0, 4);
}
