HEADERS += \
    myitem.h \
    myview.h

SOURCES += \
    myitem.cpp \
    myview.cpp \
    main.cpp

QT += widgets

qtHaveModule(printsupport): QT += printsupport

RESOURCES += \
    image.qrc
