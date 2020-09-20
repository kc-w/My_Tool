from sqlalchemy import Column, Integer,String
from sqlalchemy.ext.declarative import declarative_base


Base = declarative_base()
class Dept(Base):
    #表名
    __tablename__='dept'
    #对应具体字段
    deptno= Column(Integer,primary_key=True)
    deptname=Column(String(50),unique=True)
    note=Column(String(50))

    def __init__(self,deptno=None,deptname=None,note=None):
        self.deptno=deptno
        self.deptname=deptname
        self.note=note

