# -*- coding: gbk -*-
import csv
import json
import os
import shutil

#############第一题(将data.txt 文件转换成.csv 格式文件(写,读))
# 将data.txt文件读取为双层list列表
list1=[]
with open('data.txt', 'r',encoding='utf-8') as obj:
    for i in obj.readlines():
        # 操作指定行的数据，将特殊符号全部去掉, 将数据以指定的格式进行拆分,split:切割成一个列表进行操作
        info = i.strip('\n').split('\t')
        list1.append(info)
print(list1)

# , newline=''csv 写入的时候会自动换行, 去掉换行
#将读取到的双层列表数据写入到data.csv文件中
with open('data.csv', 'w', encoding='utf-8', newline='') as csvFile:
    csvWrite = csv.writer(csvFile)
    for d in list1:
        csvWrite.writerow(d)

# 从data.csv文件中读取数据
with open('data.csv', 'r', encoding='utf-8') as csvFile:
    csvReader = csv.reader(csvFile)
    for i in csvReader:
        print(i[0],i[1],i[2],i[3])



#############第一题(将data.txt 文件转换成.json 格式文件(写,读))
list2=[]
with open('data.txt', 'r',encoding='utf-8') as obj:
    # 读取所有行并使用for循环获取每一行的数据
    for i in obj.readlines():
        # 操作指定行的数据，将字符串中所有的"都进行转义，去除头部和尾部的换行，以制表符为界切割成一个列表进行操作
        info = i.replace("\"", r"\"").strip('\n').split('\t')
        str1='{"书名":"%s","介绍":"%s","价格":"%s","评分":"%s","作者":"%s","分类":"%s"}' % (info[0],info[1],info[2],info[3],info[4],info[5])
        #将jaon字符串转换为字典类型
        dict1 = json.loads(str1)
        list2.append(dict1)

#将list2列表里包含的json数据写入到data.json文件中
with open('data.json', 'w', encoding='utf-8') as jsonFile:
    # dump往文件中写入json数据, 第一个参数是写入的json格式数据， 第二个参数是写入的文件
    json.dump(list2, jsonFile)

#将data.json文件读取出来
with open('data.json', 'r', encoding='utf-8') as jsonFile:
    # json.load(jsonFile):获取文件中所有的数据，并转换成字典类型
    data = json.load(jsonFile)
    print(type(data),data)



#############第二题，读取user_info.txt文件进行转换
list1=[]
with open('user_info.txt', 'r',encoding='utf-8') as obj:
    for i in obj.readlines():
        #操作指定行的数据，将特殊符号全部去掉, 将数据以指定的格式进行拆分,split:切割成一个列表进行操作
        info = i.strip('\n').split('\t')
        if len(list1) == 0:
            list1.append([info[0], int(info[1])])
        else:
            for x in range(len(list1)):
                if list1[x][0] == info[0]:
                    list1[x].append(int(info[1]))
                    break
                else:
                    if len(list1)==x+1:
                        list1.append([info[0],int(info[1])])
print(list1)
zidian1={}
for x in range(len(list1)):
    a=list1[x].pop(0)
    zidian1[a] = list1[x]
print(zidian1)


list2=[]
with open('user_info.txt', 'r',encoding='utf-8') as obj:
    for i in obj.readlines():
        #操作指定行的数据，将特殊符号全部去掉, 将数据以指定的格式进行拆分,split:切割成一个列表进行操作
        info = i.strip('\n').split('\t')
        if len(list2) == 0:
            list2.append([info[1], info[0]])
        else:
            for x in range(len(list2)):
                if list2[x][0] == info[1]:
                    list2[x].append(info[0])
                    break
                else:
                    if len(list2)==x+1:
                        list2.append([info[1],info[0]])
print(list2)
zidian2={}
for x in range(len(list2)):
    a=list2[x].pop(0)
    zidian2[a] = set(list2[x])
print(zidian2)

#############第三题，文件搬家
shutil.move("d：\\1.txt","d:\\python")


#############第四题，文件查找
def search_file(dir,sname):
    if sname in os.path.split(dir)[1]: #检验文件名里是否包含sname
        print(os.path.relpath(dir)) #打印相对路径，相对指相对于当前路径
    if os.path.isfile(dir):   # 如果传入的dir直接是一个文件目录 他就没有子目录，就不用再遍历它的子目录了
        return

    for dire in os.listdir(dir): # 遍历子目录  这里的dire为当前文件名
        search_file(os.path.join(dir,dire),sname) #jion一下就变成了当前文件的绝对路径
                                           # 对每个子目录路劲执行同样的操作
search_file("d:\\","python")



