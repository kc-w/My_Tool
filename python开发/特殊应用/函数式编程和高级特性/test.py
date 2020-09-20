#map函数接收两个参数，一个是函数，一个是Iterable，map将传入的函数依次作用到序列的每个元素，并把结果作为新的Iterator返回。将运算规则抽象化。
import time

list1=[1,2,3,4,5,6,7,8,9]
list2=[]

def f(x):
    return x * x

#数组遍历相乘,返回一个map对象
map1 = map(f, list1)
print(map1,list(map1),type(map1))


#将列表中的元素从整数转化为字符串
map2=map(str,list1)
print(list(map2))


#reduce把一个函数作用在一个序列[x1, x2, x3, ...]上，这个函数必须接收两个参数第一个为函数,第二个为两个要处理的元素，reduce把结果继续和序列的下一个元素做累积计算
#效果为:reduce(f, [x1, x2, x3, x4]) = f(f(f(x1, x2), x3), x4)
from functools import reduce

#字符串相加
def add(x, y):
    print(x)
    print(y)
    return x + y
reduce1=reduce(add, list1)
print(reduce1)


def fn(x, y):
    return x * 10 + y
reduce2=reduce(fn, [1, 3, 5, 7, 9])
print(reduce2)


#将字符串数字使用map和reduce函数转化为整数
def fn(x, y):
    return x * 10 + y
def char2num(s):
    digits = {'0': 0, '1': 1, '2': 2, '3': 3, '4': 4, '5': 5, '6': 6, '7': 7, '8': 8, '9': 9}
    return digits[s]
number=reduce(fn, map(char2num, '13579'))
print(number)


#filter函数
def not_empty(s):
    return s and s.strip()
list(filter(not_empty, ['A', '', 'B', None, 'C', '  ']))
# 结果: ['A', 'B', 'C']

def is_odd(n):
    return n % 2 == 1
list(filter(is_odd, [1, 2, 4, 5, 6, 9, 10, 15]))
# 结果: [1, 5, 9, 15]


#列表生成器
listx=[i**3 for i in range(1,101) if i%2==1]
print(listx)

listx=[i*x for i in range(1,3) for x in range(1,4)]
print(listx)

dict1={"a":1,"b":2,"c":3,"d":4}
listx=[k+''+str(v) for k,v in dict1.items()]
print(listx)

#生成器
generator1=(k+''+str(v) for k,v in dict1.items())
print(generator1)

range1=range(1,10)
print(type(range1))


#迭代器
it = iter([1, 2, 3, 4, 5])
# 循环:
while True:
    try:
        # 获得下一个值:
        x = next(it)
        print(x)
    except StopIteration:
        # 遇到StopIteration就退出循环
        break

#匿名函数
def build(x, y):
    return lambda: x * x + y * y
print('返回一个匿名函数',build(1,2))


def zhuangshiqi(fun):
    def wapper(*args, **kwargs):
        print("方法名为%s()的方法开始执行..." % (fun.__name__))
        fun()
        print("方法名为%s()的方法结束执行..." % (fun.__name__))
    return wapper

@zhuangshiqi
def run():
    print("略略略1")

run()


#装饰器modul
def mokuai(text):
    def zhuangshiqi(fun):
        def wapper(*args, **kwargs):
            print("%s:方法名为%s()的方法开始执行..." % (text,fun.__name__))
            fun()
            print("%s:方法名为%s()的方法结束执行..." % (text,fun.__name__))
        return wapper
    return zhuangshiqi

@mokuai("测试模块")
def run():
    print("略略略2")

run()

class logger():
    def __init__(self,text):
        self.text=text

    def __call__(self,fun):
        def wapper(*args,**kwargs):
            print("%s方法名为%s()的方法开始执行..." % (self.text,fun.__name__))
            fun(*args,**kwargs)
            print("%s方法名为%s()的方法结束执行..." % (self.text,fun.__name__))
        return wapper()

@logger("111111")
def run():
    print("略略略3")


