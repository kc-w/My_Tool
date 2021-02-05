#include "mainwindow.h"

#include <QApplication>
#include <QFileInfo>
#include <QStringList>
#include <QDateTime>
#include <QDebug>
#include <QTemporaryFile>
#include <QTextCodec>
#include <QBuffer>

int main(int argc, char *argv[])
{
    QApplication a(argc, argv);
    MainWindow w;
    w.show();



//    访问一个设备,需要使用open()函数打开该设备,而且必须指定正确的打开模式,可以使用按位或符号“丨”来同时使用
//    打开设备后可以使用write()或者putChar()来进行写入,使用read()、readLine()或者readAll()进行读取,最后使用close()关闭设备
//    QIODevice::NotOpen    设备没有打开
//    QIODevice::Readonly   设备以只读方式打开,这时无法写入
//    QIODevice::WriteOnly  设备以只写方式打开,这时无法读取
//    QIODevice::ReadWrite  设备以读/写方式打开
//    QIODevice::Append     设备以附加模式打开,所有的数据都将写入到文件的末尾
//    QIODevice::Truncate   如果可能,设备在打开前会被截断,设备先前的所有内容都将丢失
//    QIODevice::Text       当读取时,行结尾终止符会被转换为"\n";当写入时,行结尾终止符会被转换为本地编码,在Win32上是"\r\n"
//    QIODevice::Unbuffered 绕过设备所有的缓冲区


//    QIODevice会区别两种类型的设备,随机存取设备和顺序存储设备
//    随机存取设备支持使用seek()函数来定位到任意位置,当前位置可以使用pos()函数来获取,这样的设备有QFile、QBuffer等
//    顺序存储设备不支持定位到任意位置,数据必须一次性读取,无法使用pos()和size(),这样的设备有QTcpSocket、QProcess等

    // 以只写方式打开,没有会创建文件,QFile类提供了一个用于读/写文件的接口,它是一个可以用来读/写文本文件、二进制文件和Qt资源的I/O设备
    QFile file("myfile.txt");
    if (!file.open(QIODevice::WriteOnly | QIODevice::Text)){
        qDebug() << file.errorString();
    }
    file.write("你好\nQt!");
    file.close();

    //QFileInfo类提供了文件信息,包括文件的名称、在文件系统中的路径、文件的访问权限以及是否是一个目录或者符号链接等
    QFileInfo info(file);
    qDebug() << QObject::tr("绝对路径：") << info.absoluteFilePath();
    qDebug() << QObject::tr("文件名：") << info.fileName();
    qDebug() << QObject::tr("基本名称：") << info.baseName();
    qDebug()<< QObject::tr("后缀：") << info.suffix();
    qDebug() << QObject::tr("创建时间：") << info.created();
    qDebug() << QObject::tr("大小：") << info.size();

    // 以只读方式打开
    if (!file.open(QIODevice::ReadOnly  | QIODevice::Text)){
        qDebug() << file.errorString();
    }
    qDebug() << QObject::tr("文件内容：") <<file.readAll();
    qDebug() << QObject::tr("当前位置：") << file.pos();
    //定位到0位置
    file.seek(0);

    QByteArray array;
    //读取5个字符存储到字节数组
    array = file.read(5);
    qDebug() << QObject::tr("前5个字符：") << array<< QObject::tr("当前位置：") << file.pos();

    //定位到15位置
    file.seek(15);
    array = file.read(5);
    qDebug() << QObject::tr("第16-20个字符：") << array;
    file.close();



//QTemporaryFile类是一个用来操作临时文件的I/O设备.它可以安全地创建一个唯一的临时文件,默认会使用QIODevice::ReadWrite模
//    调用close()函数后,该文件会被自动删除掉,重新打开QTemporaryFile是安全的,只要没有调用close()函数,临时文件一直存在保持打开
//    临时文件默认会生成在系统的临时目录里，这个目录的路径可以使用QDir::tempPath()来获取
    QTemporaryFile tempfile;
    if (tempfile.open()) {
        //在这里对临时文件进行操作
        qDebug() << QObject::tr("临时文件名：") << tempfile.fileName();
        qDebug() << QObject::tr("临时文件路径：") << tempfile.fileTemplate();
    }
    tempfile.close();




    //使用文本流写文本文件
    QFile data("output.txt");
    if (data.open(QFile::WriteOnly | QFile::Truncate)) {

        //使用setDevice()或者setString()来设置QTextStream要操作的设备或者字符串
        QTextStream out(&data);
        out.setCodec("utf-8");
        // 写入数据,使用QTextStream进行中文写入时要转换为QString类型,不然输出会乱码
        out << QString("这是aa\n测试数据bb");

        out.flush();
        data.close();

    }

    //使用文本流读文本文件
    QFile file1("output.txt" );
    if(file1.open(QIODevice::ReadOnly | QIODevice::Text)){

        QTextStream in(&file1);
        in.setCodec("utf-8");

        //一次读取全部字符串
        QString line = in.readAll();
        qDebug() <<line;
        //将文本流重新定位到第一个字符
        in.seek(0);
        //按行读取数据
        while (!in.atEnd()) {
            line = in.readLine();
            qDebug() <<line;
        }

        in.flush();
        file1.close();
    }




    //使用数据流读/写二进制数据
    QFile file2("output.dat");
    file2.open(QIODevice::WriteOnly);
    //将文件转换为二进制流
    QDataStream out(&file2);
    //串行化串行化字符串
    out << QString("Mthe answer is");
    //串行化整数
    out << (qint32)42;



//    QFile file3("output.dat");
//    file3.open(QIODevice::ReadOnly);
//    //从file3中读取串行化的数据
//    QDataStream in(&file3);
//    QString str;
//    qint32 a;
//    //提取"the answer is"和 42
//    in >> str >> a;



    QFile file4( "file.xxx");
    file4.open(QIODevice::WriteOnly);
    QDataStream out1(&file4);
    //写入幻数和版本号
    out1 << (quint32)0xA0B0C0D0;
    out1 << (qint32)123;
    out1.setVersion(QDataStream::Qt_4_0);
    //写入数据
    out << QString("Mthe answer is");






//    QBuffer类为QByteArray提供了一个QIODevice接口,ByteArray被视为一个标准的随机访问的文件
//    创建一个QBuffer时,则自动在内部创建一个QByteArray缓冲区,可以直接调用buffer()或setBuffer()来访问这个缓冲区
//    使用write()或者putChar()对缓冲区进行写入
    QByteArray byteArray;
    QBuffer buffer(&byteArray);
    buffer.open(QIODevice::WriteOnly);
    QDataStream out2( &buffer);
    out << QApplication::palette();

//    使用read(),readLine(),readAll()或者getChar()来读取它,size()函数可以返冋缓冲区的当前大小,seek()函数定位到缓冲区的一个指定位置
//    当有新的数据到达了缓冲区时,QBuffer会发射readyRead()信号,通过关联这个信号,可以使用QBuffer来存储临时的数据
    QPalette palette;
    QBuffer buffer1( &byteArray);
    buffer1.open(QIODevice::ReadOnly);
    QDataStream in(&buffer1);
    in >> palette;


    return a.exec();
}
