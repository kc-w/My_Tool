#include <QCoreApplication>
#include <QDebug>


int main(int argc, char *argv[])
{
    QCoreApplication a(argc, argv);

    //QPixmap类为一个隐式共享类,由指向一个共享数据块的指针和数据组成,共享数据块中包含了一个引用计数
    //当有新的对象引用了共享数据时引用计数都会递增,而有对象不再引用这个共享数据时引用计数就会递减;当引用计数为0时,这个共享数据就会被销毁掉
    //Qt中主要的隐式共享类有QByteArray、QCursor、QFont、QPixmap、QString、QUrl、QVariant、所有的容器类等
    //QPixmap p1,p2 ;
    //p1.load("image.bmp");
    //这里执行了“p2 = p1;”语句后，p2便与p1共享同一个数据，这时p1的引用计数为2,而p2的引用计数为0,所以p2以前指向的数据结构将会被销毁掉
    //处理共享对象时，有两种复制对象的方法：深复制和浅复制,深复制意味着复制一个对象.而浅复制则是复制一个引用
    //深复制是非常耗能的，需要消耗很多的内存和CPU资源;而浅复制则非常快速，因为它只需要设置一个指针和增加引用计数的值
    //当隐式共享类使用“=”操作符时就是使用浅复制
    //p2 = p1;
    //QPainter paint;
    //当一个对象被修改时,就必须进行一次深复制,比如"paint.begin(&p2);",这时就要对数据进行深复制.使p2和p1指向不同的数据结构
    //然后将p1的引用计数设为1,p2的引用计数也设为1.
    //paint.begin(&p2);
    //paint.end();




    qDebug() << QObject::tr("以下是编辑字符串操作：") << endl;

    QString str = "hello";
    qDebug() << QObject::tr("字符串大小：") << str.size(); // 大小为5
    str[0] = QChar('H');      // 将第一个字符换为‘H'
    qDebug() << QObject::tr("第一个字符：") << str[0]; // 结果为‘H'
    str.append(" Qt");        // 向字符串后添加"Qt"
    str.replace(1,4,"i");     // 将第1个字符开始的后面4个字符替换为字符串"i"
    str.insert(2," my");      // 在第2个字符后插入" my"
    qDebug() << QObject::tr("str为：") << str; // 结果为Hi my Qt
    str = str + "!!!";        // 将两个字符串组合
    qDebug() << QObject::tr("str为：") << str; // 结果为Hi my Qt！！！

    str = " hi\r\n Qt!\n  ";
    qDebug() << QObject::tr("str为：") << str;
    QString str1 = str.trimmed();    // 除去字符串两端的空白字符
    qDebug() << QObject::tr("str1为：") << str1;
    QString str2 = str.simplified(); // 除去字符串两端和中间多余的空白字符
    qDebug() << QObject::tr("str2为：") << str2; //结果为hi Qt！

    str = "hi,my,,Qt";
    // 从字符串中有","的地方将其分为多个子字符串，
    // QString::SkipEmptyParts表示跳过空的条目
    QStringList list = str.split(",",QString::SkipEmptyParts);
    qDebug() << QObject::tr("str拆分后为：") << list;  // 结果为hi,my,Qt
    str = list.join(" "); // 将各个子字符串组合为一个字符串，中间用" "隔开
    qDebug() << QObject::tr("list组合后为：") << str;  // 结果为hi my Qt

    qDebug() << QString().isNull();     // 结果为true
    qDebug() << QString().isEmpty();    // 结果为true
    qDebug() << QString("").isNull();   // 结果为false
    qDebug() << QString("").isEmpty();  // 结果为true

    qDebug() << endl << QObject::tr("以下是在字符串中进行查询的操作：") <<endl;
    str = "yafeilinux";
    qDebug() << QObject::tr("字符串为：") << str;
    // 执行下面一行代码后，结果为linux
    qDebug() << QObject::tr("包含右侧5个字符的子字符串：") << str.right(5);
    // 执行下面一行代码后，结果为yafei
    qDebug() << QObject::tr("包含左侧5个字符的子字符串：") << str.left(5);
    // 执行下面一行代码后，结果为fei
    qDebug() << QObject::tr("包含第2个字符以后3个字符的子字符串：") << str.mid(2, 3);
    qDebug() << QObject::tr("'fei'的位置：") << str.indexOf("fei"); //结果为2
    qDebug() << QObject::tr("str的第0个字符：") << str.at(0); //结果为y
    qDebug() << QObject::tr("str中'i'字符的个数：") << str.count('i'); //结果为2
    // 执行下面一行代码后，结果为true
    qDebug() << QObject::tr("str是否以”ya“开始？") << str.startsWith("ya");
    // 执行下面一行代码后，结果为true
    qDebug() << QObject::tr("str是否以”linux“结尾？") << str.endsWith("linux");
    // 执行下面一行代码后，结果为true
    qDebug() << QObject::tr("str是否包含”lin“字符串？") << str.contains("lin");
    QString temp = "hello";
    if(temp > str) qDebug() << temp; // 两字符串进行比较，结果为yafeilinux
    else qDebug() << str;

    qDebug() << endl << QObject::tr("以下是字符串的转换操作：") << endl;
    str = "100";
    qDebug() << QObject::tr("字符串转换为整数：") << str.toInt(); // 结果为100
    int num = 45;
    qDebug() << QObject::tr("整数转换为字符串：") << QString::number(num);//结果为"45"
    str = "FF";
    bool ok;
    int hex = str.toInt(&ok, 16);
    // 结果为ok：true 255
    qDebug() << "ok: "<< ok << QObject::tr("转换为十六进制：") << hex;
    num = 26;
    qDebug() << QObject::tr("使用十六进制将整数转换为字符串：")
                  << QString::number(num, 16);//结果为1a
    str = "123.456";
    qDebug() << QObject::tr("字符串转换为浮点型：") << str.toFloat();//结果为123.456
    str = "abc";
    qDebug() << QObject::tr("转换为大写：") << str.toUpper();// 结果为ABC
    str = "ABC";
    qDebug() << QObject::tr("转换为小写：") <<str.toLower();// 结果为abc
    int age = 25;
    QString name = "yafei";
    // name代替%1，age代替%2
    str = QString("name is %1, age is %2").arg(name).arg(age);
    // 结果为name is yafei, age is 25
    qDebug() << QObject::tr("更改后的str为：") << str;
    str = "%1 %2";
    qDebug() << str.arg("%1f","hello");      // 结果为%1f hello
    qDebug() << str.arg("%1f").arg("hello"); // 结果为hellof %2
    str = QString("ni%1").arg("hi",5,'*');
    qDebug() << QObject::tr("设置字段宽度为5，使用'*'填充：") << str;//结果为ni***hi
    qreal value = 123.456;
    str = QString("number: %1").arg(value,0,'f',2);
    qDebug() << QObject::tr("设置小数点位数为两位：") << str;  //结果为"number:123.45
    // 执行下面一行代码，结果为number:123.45不会显示引号
    qDebug() << QObject::tr("将str转换为const char* :") << qPrintable(str);



    QVariant v1(15);
    qDebug() << v1.toInt();                     // 结果为15
    QVariant v2(12.3);
    qDebug() << v2.toFloat();                   // 结果为12.3
    QVariant v3("nihao");
    qDebug() << v3.toString();                  // 结果为"nihao"
    QColor color = QColor(Qt::red);
    QVariant v4 = color;
    qDebug() << v4.type();                      // 结果为QVariant::QColor
    qDebug() << v4.value<QColor>();             // 结果为QColor(ARGB 1,1,0,0)
    QString str = "hello";
    QVariant v5 = str;
    qDebug() << v5.canConvert(QVariant::Int);   // 结果为true
    qDebug() << v5.toString();                  // 结果为"hello"
    qDebug() << v5.convert(QVariant::Int);      // 结果为false
    qDebug() << v5.toString();                  // 转换失败，v5被清空，结果为"0"

    return a.exec();
}
