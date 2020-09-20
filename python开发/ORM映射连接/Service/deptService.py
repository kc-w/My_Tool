from DB.DBconnection import DBSession
from Bean.dept import Dept


class deptImpl():

    def __init__(self):
        # 获取session对象
        self.session = DBSession()

    #添加数据
    def addData(self,data):
        try:
            self.session.add(data)
            self.session.commit()
        except BaseException as base:
            print(base)
            self.session.rollback()
        finally:
            self.session.close()

    #删除数据
    def deleteDate(self,deptno):
        try:
            #先查询对象
            dept = self.session.query(Dept).filter(Dept.deptno == deptno).one()
            # 进行删除
            self.session.delete(dept)
            self.session.commit()
        except BaseException as base:
            print(base)
            self.session.rollback()
        finally:
            self.session.close()

    #修改数据
    def updateDate(self,data):
        try:
            self.session.merge(data)
            self.session.commit()
        except BaseException as base:
            print(base)
            self.session.rollback()
        finally:
            self.session.close()

    #查询符合要求的5个数据
    def getDate(self,deptno,nowPage):
        try:
            return self.session.query(Dept).filter(Dept.deptno == deptno).offset((nowPage-1)*5).limit(5).all()
        except BaseException as base:
            print(base)
            self.session.rollback()
        finally:
            self.session.close()

    #查询所有数据
    def getAllDate(self,nowPage):
        try:
            return self.session.query(Dept).offset((nowPage-1)*5).limit(5).all()
        except BaseException as base:
            print(base)
            self.session.rollback()
        finally:
            self.session.close()