class man():

    # 定义构造函数
    def __init__(self,name,age,sex,weight):
        self.name=name
        self.age=age
        self.sex=sex
        # 私有属性, 私有属性在类外部无法直接进行访问
        self.__weight=weight

    #公有方法
    def dayin1(self):
        print("这是父类的方法")
    def dayin2(self):
        print(self.name, self.age, self.sex, self.__weight)
    #私有方法,在类外部无法直接进行访问
    def __dayin(self):
        print(self.name, self.age, self.sex, self.__weight)

#实例化类
man1=man("jack",11,"男",120)
man1.dayin2()

#单继承
class student(man):

    def __init__(self,name,age,sex,weight,xueli):
        self.name=name
        self.age=age
        self.sex=sex
        # 私有属性, 私有属性在类外部无法直接进行访问
        self.__weight=weight
        self.xueli = xueli


    #覆写父类的方法
    def dayin2(self):
        print("覆盖父类方法")
        print(self.name, self.age, self.sex, self.__weight,self.xueli)

    #带返回值的函数
    def sum(self,sum1,sum2):
        total=sum1+sum2
        return total


#匿名函数
defsum = lambda arg1,arg2: arg1 + arg2

student1=student("小明",11,"男",120,"大专")
student1.dayin1()
student1.dayin2()
print(student1.sum(1,2),defsum(10,20))


