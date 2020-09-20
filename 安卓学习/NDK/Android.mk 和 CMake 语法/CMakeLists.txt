#文档:https://d.android.com/studio/projects/add-native-code.html

#设置构建本机库所需的CMake的最小版本。
cmake_minimum_required(VERSION 3.4.1)

#创建并命名一个库，将它设置为静态或共享，并提供到其源代码的相对路径。
#你可以定义多个库， Gradle会自动用你的APK包共享库。
#多次使用add_library，会生成多个so库,想将多个本地文件编译到一个so库中，只要最后一个参数添加多个C/C++文件的相对路径就可以
add_library( #设置库的名称
        native-lib

        #将库设置为共享库
        SHARED

        #提供到源文件的相对路径
        native-lib.cpp )

#搜索指定的预构建库并将路径存储为变量,CMake在搜索路径中包含了系统库 default，只需要指定公共NDK库的名称
find_library( #设置路径变量的名称
        log-lib

        # 指定的NDK库的名称
        #你想让CMake定位。
        log )

#指定CMake应该链接到目标库的库,可以链接多个库
target_link_libraries( #指定目标库
        native-lib

        #将目标库链接到日志库
        ${log-lib} )