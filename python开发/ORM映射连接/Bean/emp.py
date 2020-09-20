from sqlalchemy import Column, Integer, ForeignKey, String
from sqlalchemy.ext.declarative import declarative_base
from sqlalchemy.orm import relationship

Base = declarative_base()
class Emp(Base):
    # 表名
    __tablename__='emp'
    # 对应具体字段
    empno=Column(Integer,primary_key=True)
    deptno = Column(Integer,ForeignKey('dept.deptno'))
    empname=Column(String(50))
    sex=Column(String(2))
    age=Column(Integer)

    #将部门信息加载到员工表中
    dept=relationship('Dept',backref='dept')


    def __init__(self,empno=None,deptno=None,empname=None,sex=None,age=None):
        self.empno=empno
        self.deptno=deptno
        self.empname=empname
        self.sex=sex
        self.age=age


