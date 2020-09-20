import pymysql as pymysql


class DBconnection():
    def __init__(self):
        #与数据库进行连接
        self.db=pymysql.connect("localhost","root","999999999852","db",charset="utf8")
        #获得操作游标
        self.cursor=self.db.cursor()

    def executeQuery(self,sql):
        try:
            self.cursor.execute(sql)
            tuple=self.cursor.fetchall()
            return tuple
        except BaseException as base:
            print(base)
        finally:
            self.db.close()

    def executeUpdate(self,sql):
        flag="ok"
        try:
            self.cursor.execute(sql)
            self.db.commit()
        except BaseException as base:
            print(base)
            self.db.rollback()
            flag="error"
        finally:
            self.db.close()
            return flag