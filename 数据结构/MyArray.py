#数组

class Array:

    def __init__(self,len):
        self.len=len
        self.data=[]

    def create(self,datas):
        for i in range(self.len):
            if len(datas)>i:
                self.data.append(datas[i])
            else:
                self.data.append(None)

    def search(self,index):
        return self.data[index]

    def split(self,start, end):
        shuzu=[]
        for i in range(self.len):
            if i>=start and i<=end:
                shuzu.append(self.data[i])
        return shuzu

    def paixu(self):
        shuzu=[]
        for data in self.data:
            if data is None:
                continue
            else:
                shuzu.append(data)
        for i in range(len(shuzu)):
            for j in range(i+1,len(shuzu)):
                pass

    def add(self):
        pass
    def delete(self):
        pass

    def updata(self,index,data):
        self.data[index]=data

shuzu=Array(5)
shuzu.create((1,5,7))
print(shuzu.data,"\n",shuzu.split(2,3))
