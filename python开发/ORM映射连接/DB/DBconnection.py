from sqlalchemy import create_engine
from sqlalchemy.orm import sessionmaker

#连接数据库
#数据库驱动+驱动://用户名:密码@访问路径:端口号/数据库?编码=utf8
conn=create_engine('mysql+mysqlconnector://root:root@localhost:3306/orm?charset=utf8')
#绑定数据库对象
DBSession=sessionmaker(bind=conn)
