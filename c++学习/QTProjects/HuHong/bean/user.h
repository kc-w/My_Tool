#ifndef USER_H
#define USER_H

#include <QString>



class User
{
public:

private:
    int id;
    QString number;
    QString password;
    int permission;
public:
    int getPermission();
    void setPermission(int value);
    QString getPassword();
    void setPassword(QString value);
    QString getNumber();
    void setNumber(QString value);
    int getId();
    void setId(int value);

    User();
    //使用类创建对象时会调用构造函数
    User(int id,QString number,QString password,int permission);

    //每个类都默认缺省析构函数,程序运行结束对象被删除时会调用析构函数
    ~User(void);

};

#endif // USER_H
