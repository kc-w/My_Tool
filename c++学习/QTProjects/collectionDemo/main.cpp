#include <QCoreApplication>
#include <QList>
#include <QMapIterator>
#include <QMutableMapIterator>
#include <QDebug>
int main(int argc, char *argv[])
{


    //Qt提供了一些顺序容器：QList、QLinkedList、QVector、QStack和QQueue,使用最多而且最好用的是QLis
    //如果需要使用一个链表，那么可以使用QLinkedList； 如果希望数据项可以占用连续的内存空间，那么可以使用QVector
    //QStack和QQueue分别提供了后进先出(LIFO)和先进先出(FIFO)语义
    //Qt 还提供了<键,值>容器：QMap、QMultiMap、QHash、QMultiHash 和 QSet，其中,Multi容器用来支持一个键多个值的情况
    //还有QCache和QContiguousCache,它们提供了对缓存存储中对象的高效散列查找




    QCoreApplication a(argc, argv);

    //===QList使用数组来实现,以确保进行快速的基于索引的访问。
    //可以使用QList::append()和QList::prepend()在列表的两端添加项目,也可以使用QList::insert()在列表的中间插入项目
    QList<QString> list;
    list << "aa" << "bb" << "cc"; // 插入字符串
    if(list[1] == "bb"){
        list[1] = "ab";
    }
    list.replace(2, "bc");        // 按下标对字符串进行替换

    qDebug() << "输出列表中存储的数据: ";  // 输出整个列表
    for(int i=0; i<list.size(); ++i){
        qDebug() << list.at(i);   // 现在列表为aa ab bc
    }
    list.append("dd");            // 在列表尾部添加
    list.prepend("mm");           // 在列表头部添加

    QString str = list.takeAt(2); // 从列表中删除第3个项目，并返回
    qDebug() << "下标为2的列表元素为: " << str;
    qDebug() << "新的list列表元素为: ";
    for(int i=0; i<list.size(); ++i){
        qDebug() << list.at(i);   // 现在列表为mm aa bc dd
    }
    list.insert(2, "mm");         // 在下标2插入字符串
    list.swapItemsAt(1,3);         // 交换下标1和下标3
    qDebug() << "新的list列表元素为: ";
    for(int i=0; i<list.size(); ++i){
        qDebug() << list.at(i);   // 现在列表为mm bc mm aa dd
    }
    qDebug() << "列表中是否包含“mm” ?" << list.contains("mm"); // 列表中是否包含“mm”
    qDebug() << "包含“mm”的个数: " << list.count("mm"); // 包含“mm”的个数
    // 第一个“mm”的位置，默认从位置0开始往前查找，返回第一个匹配的项目的位置
    qDebug() << "第一个'mm'出现的下标: " << list.indexOf("mm");
    // 第二个“mm”的位置，我们指定从下标3之后查找,并返回查找到指定元素的下标,如果往后没有查找到指定元素则向前查找
    qDebug() << "第二个'mm'出现的下标: " << list.indexOf("mm", 3);



    //===QLinkedList除了使用迭代器而不使用整数索引进行项目访问外，它基本与QList相同。
    //当向一个很大的列表的中间插入项目时.QLinkedList比QList拥有更好的性能,而且它拥有更好的迭代器语义
    //迭代器指向QLinkedList,只要这个列表还存在,迭代器就依然有效;迭代器指向QList,如果QList进行了插入或者删除操作,这个迭代器就无效


    //===QVector在内存地址中是连续的一个数组,在vector的前面或者中间插入项目是非常缓慢的，因为可能导致大量的项目在内存中移动一个位置

    //===QStac是QVector的一个便捷子类，提供了后进先出(LIFO)语义,它添加了 push()、pop()和 top()等函数

    //===QQucu是QList的一个便捷子类，提供了先进先出(FIFO)语义,它添加了 enqueue()、dequeue()和 head()等函数

    //===QSet提供了一个可以快速査询单值的数学集

    //===QHash与QMap拥有基本相同的接口,但是它的査找速度更快,QHash的数据是以任意的顺序存储的

    //===QMultiHash是QHash的一个便捷类,提供了实现多值散列的接口函数


    //===QMap提供了一个字典(关联数组),将Key类型的键值映射到T类型的值上,QMap使用键顺序来存储它的数据；如果不关心存储顺序，那么可以使用QHash来代替它，因为QHash速度更快
    QMap<QString, int> map;
    map["one"] = 1;          // 向map中插入("one",1)
    map["three"] = 3;
    map.insert("seven", 7);   // 使用insert()函数进行插入

    // 获取键的值，使用“[ ]”操作符时如果map中没有该键，那么会自动插入
    int value1 = map["six"];
    qDebug() << "key=\"six\":value=" << value1;
    qDebug() << "是否存在key为'six'的集合:" << map.contains("six");

    // 使用value()函数获取键的值，这样当键不存在时不会自动插入
    int value2 = map.value("five");
    qDebug() << "value2:" << value2;
    qDebug() << "是否存在key为'five'的集合:" << map.contains("five");

    // 当键不存在时，value()默认返回0，这里可以设定该值，比如这里设置为9
    int value3 = map.value("nine", 9);
    qDebug() << "value3:" << value3;

    // map默认是一个键对应一个值，如果重新给该键设置了值，那么以前的会被擦除
    map.insert("ten", 10);
    map.insert("ten", 100);
    qDebug() << "ten: " << map.value("ten");

    // 可以使用insertMulti()函数来实现一键多值，然后使用values()函数来获取值的列表
    map.insertMulti("two", 2);
    map.insertMulti("two", 4);
    QList<int> values = map.values("two");
    qDebug() << "two: " << values;


    //===QMultiMap是QMap的一个便捷类,提供了实现多值映射的接口函数,一个键可以关联多个值
    QMultiMap<QString, int> map1, map2, map3;
    map1.insert("values", 1);
    map1.insert("values", 2);
    map2.insert("values", 3);
    // 可以进行相加，这样map3的“values”键将包含2,1,3三个值
    map3 = map1+map2;
    QList<int> myValues = map3.values("values");
    qDebug() << "value值: ";
    for (int i=0; i<myValues.size(); ++i) {
        qDebug() << myValues.at(i);
    }




    //==list迭代器
    QList<QString> list1;
    list1 << "A" << "B" << "C" << "D";
    QListIterator<QString> i(list1); // 创建列表的只读迭代器，将list作为参数
    qDebug() << "正向遍历列表元素:";
    while (i.hasNext()){    // 正向遍历列表，结果为A，B，C，D
        qDebug() << i.next();
    }
    qDebug() << "反向遍历列表元素:";
    while (i.hasPrevious()){    // 反向遍历列表，结果为D，C，B，A
        qDebug() << i.previous();
    }

    QMutableListIterator<QString> j(list1);
    j.toBack();                 // 将迭代器移动到容器末端
    while (j.hasPrevious()) {
        QString str = j.previous();
        qDebug() << "==="+str+"===";
        if(str == "B"){
            j.remove();   // 删除项目“B”
        }
    }

    j.insert("Q");       // 在列表最前面插入“Q”

    j.toFront(); //将迭代器移动到最前端
    while (j.hasNext()){
        qDebug() << j.next();
    }

    j.toBack(); // 将迭代器移动到容器末端

    //将列表最后一个元素赋值为“N”
    if(j.hasPrevious()) {
        j.previous() = "N";
    }

    j.previous();
    //在“N”前面赋值为“M”
    j.setValue("M");                           // 使用setValue()进行赋值
    j.toFront(); //将迭代器移动到最前端
    qDebug()<< "重新排序为 :";
    while (j.hasNext()){                        // 正向遍历列表，结果为Q，A，M，N
        qDebug() << j.next();
    }






    //==map迭代器
    QMap<QString, QString> map_1;
    map_1.insert("小米", "k20");
    map_1.insert("华为", "mate40");
    map_1.insert("苹果", "iphone11");
    map_1.insert("oppo", "R11");
    QMapIterator<QString,QString> m1(map_1);
    while(m1.hasNext()) {
        m1.next();
        qDebug() << m1.key() << " : " << m1.value();
    }
//    if(m1.findPrevious("华")){
//        qDebug() << "找 '华'";  // 向前查找键的值
//    }
//    if(m1.findPrevious("华")){
//        qDebug() << "找 '华'";  // 向前查找键的值
//    }
//    if(m1.findPrevious("华")){
//        qDebug() << "找 '华'";  // 向前查找键的值
//    }
    QMutableMapIterator<QString, QString> m2(map_1);
    while (m2.hasNext()) {
        if (m2.next().key().endsWith("果")){ // endsWith()是QString类的函数
            m2.remove();                      // 删除含有“果”结尾的键的map集合元素
        }
    }
    while(m2.hasPrevious()) {
        m2.previous();
        qDebug() << m2.key() << " : " << m2.value();
    }


    //==STL风格迭代器
    QList<QString> list_1;
    list_1 << "A" << "B" << "C" << "D";
    QList<QString>::iterator i1;      // 读写迭代器
    qDebug() << "遍历结果1 :";
    for (i1 = list_1.begin(); i1 != list_1.end(); ++i1) {
        *i1 = (*i1).toLower();         // 使用QString的toLower()函数转换为小写
        qDebug() << *i1;              // 结果为a，b，c，d
    }
    qDebug() << "遍历结果2 :";
    while (i1 != list_1.begin()) {
        --i1;//由于迭代器已经迭代到了end()处,所以需要先--移动到末尾元素d处
        qDebug() << *i1;               // 结果为d，c，b，a
    }
    QList<QString>::const_iterator j1; // 只读迭代器
    qDebug() << "遍历结果3 :";
    for (j1 = list_1.constBegin(); j1 != list_1.constEnd(); ++j1)
        qDebug() << *j1;               // 结果为a，b，c，d

    QMap<QString, int> map_2;
    map_2.insert("one",1);
    map_2.insert("two",2);
    map_2.insert("three",3);
    QMap<QString, int>::const_iterator p;
    qDebug() << "遍历结果4 :";
    for (p = map_2.constBegin(); p != map_2.constEnd(); ++p){
        qDebug() << p.key() << ":" << p.value();// 结果为(one,1),(three,3),(two,2)
    }


    //==foreach关键字
    QList<QString> list_2;
    list_2.insert(0, "A");
    list_2.insert(1, "B");
    list_2.insert(2, "C");
    qDebug() <<"列表元素为 :";
    foreach (QString str, list_2) {   // 从列表中获取每一项
        qDebug() << str;            // 结果为A，B，C
    }

    QMap<QString,int> map_3;
    map_3.insert("first", 1);
    map_3.insert("second", 2);
    map_3.insert("third", 3);
    qDebug() << endl << "键值对列表为 :";
    foreach (QString str, map_3.keys())   // 从map中获取每一个键
        // 输出键和对应的值，结果为(first,1),(second,2),(third,3)
        qDebug() << str << " : " << map_3.value(str);

    QMultiMap<QString,int> map_4;
    map_4.insert("first", 1);
    map_4.insert("first", 2);
    map_4.insert("first", 3);
    map_4.insert("second", 2);
    qDebug() << endl << "键值对列表为 :";
    QList<QString> keys = map_4.uniqueKeys(); // 返回所有键的列表
    foreach (QString str, keys) {            // 遍历所有的键
        foreach (int i, map_4.values(str)){// 遍历键中所有的值
            qDebug() << str << " : " << i;
        }
    }




    //QString类提供了一个Unicode(Unicode是一种支持大部分文字系统的国际字符编码标准)字符串
    //QString存储了一串QChar,而QChar提供了一个16位的Unicode 4. 0字符
    //在后台,QString使用隐式共享来减少内存使用和避免不必要的数据复制，这也有助于减少存储16位字符的固有开销







    return a.exec();
}
