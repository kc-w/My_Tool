#include "user.h"

User::User()
{

}

User::User(int id, QString number, QString password, int permission)
{
    this->id=id;
    this->number=number;
    this->password=password;
    this->id=permission;
}

User::~User()
{

}

QString User::getPassword()
{
    return password;
}

void User::setPassword(QString value)
{
    password = value;
}

QString User::getNumber()
{
    return number;
}

void User::setNumber(QString value)
{
    number = value;
}

int User::getId()
{
    return id;
}

void User::setId(int value)
{
    id = value;
}

int User::getPermission()
{
    return permission;
}

void User::setPermission(int value)
{
    permission = value;
}
