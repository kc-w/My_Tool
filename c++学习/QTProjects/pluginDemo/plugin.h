#ifndef REGEXPPLUGIN_H
#define REGEXPPLUGIN_H


#include <QObject>
#include "regexpinterface.h"

class RegExpPlugin : public QObject, RegExpInterface
{
    Q_OBJECT
    //声明插件中的元数据
    Q_PLUGIN_METADATA(IID "org.qter.Examples.myplugin.RegExpInterface")
    Q_INTERFACES(RegExpInterface)

public:
    //定义一个纯虚函数,通过重写它来实现该插件具体的功能
    QString regexp(const QString &message);

};


#endif // REGEXPPLUGIN_H
