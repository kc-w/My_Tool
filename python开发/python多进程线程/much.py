import time
from multiprocessing import Process
from threading import Thread

#子进程
class DouBanSpider1(Process):

    def __init__(self,x):
        Process.__init__(self)
        self.x=x

    def run(self):
        print("子进程" + str(self.x) + "执行........")
        for i in range(10000):
            with open('write'+str(self.x)+'.txt','a') as f:
                f.write(str(111111111111111111111111111111111111111111111111111111111111111111111)+'\n')
        print("子进程" + str(self.x) + "结束........")

#子线程
class DouBanSpider2(Thread):

    def __init__(self,x):
        Thread.__init__(self)
        self.x = x

    def run(self):
        print("子线程"+str(self.x)+"执行........")
        for i in range(10000):
            with open('write'+str(self.x)+'.txt','a') as f:
                f.write(str(111111111111111111111111111111111111111111111111111111111111111111111)+'\n')
        print("子线程" + str(self.x) + "结束........")

#主方法
def test():
    list=[]
    for i in range(10):
        t=DouBanSpider2(i)
        t.start()
        list.append(t)
    for i in list:
        i.join()

start = time.time()
test()
print('[info]耗时：%s'%(time.time()-start))

#子进程开始和结束都是异步执行
#子线程开始同步执行,执行和结束为异步执行
#在4核cpu上多进程的密集(cpu)计算效率是多线程的4倍
#在4核cpu上多进程的密集(io)读写效率和多线程基本一致(多线程效率略高)