##表明该项目要构建库文件
TEMPLATE        = lib
##告知qmake要创建一个插件
CONFIG         += plugin
##项目中使用了regexpwindow目录中的regexpinterface.h文件,所以这里将该目录的路径添加到INCLUDEPATH中
INCLUDEPATH    +=regexpwindow
HEADERS += \
    plugin.h

SOURCES += \
    plugin.cpp
##指定产生的dll文件的名字
TARGET          = regexpplugin
##指定bulid生成的dll文件所在的目录
DESTDIR         = pluginDLL


