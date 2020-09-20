from DB.DBconnection import DBSession
from Bean.emp import Emp


class empImpl():

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
    def deleteDate(self,empno):
        try:
            #先查询对象
            emp = self.session.query(Emp).filter(Emp.empno == empno).one()
            # 进行删除
            self.session.delete(emp)
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

    #查询符合要求的数据
    def getDate(self,empno,nowPage):
        try:
            return self.session.query(Emp).filter(Emp.empno == empno).offset((nowPage-1)*5).limit(5).all()
        except BaseException as base:
            print(base)
            self.session.rollback()
        finally:
            self.session.close()

    #查询所有数据
    def getAllDate(self,nowPage):
        try:
            return self.session.query(Emp).offset((nowPage-1)*5).limit(5).all()
        except BaseException as base:
            print(base)
            self.session.rollback()
        finally:
            self.session.close()