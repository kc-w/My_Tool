# -*- coding: gbk -*-
import csv
import json
import os
import shutil

#############��һ��(��data.txt �ļ�ת����.csv ��ʽ�ļ�(д,��))
# ��data.txt�ļ���ȡΪ˫��list�б�
list1=[]
with open('data.txt', 'r',encoding='utf-8') as obj:
    for i in obj.readlines():
        # ����ָ���е����ݣ����������ȫ��ȥ��, ��������ָ���ĸ�ʽ���в��,split:�и��һ���б���в���
        info = i.strip('\n').split('\t')
        list1.append(info)
print(list1)

# , newline=''csv д���ʱ����Զ�����, ȥ������
#����ȡ����˫���б�����д�뵽data.csv�ļ���
with open('data.csv', 'w', encoding='utf-8', newline='') as csvFile:
    csvWrite = csv.writer(csvFile)
    for d in list1:
        csvWrite.writerow(d)

# ��data.csv�ļ��ж�ȡ����
with open('data.csv', 'r', encoding='utf-8') as csvFile:
    csvReader = csv.reader(csvFile)
    for i in csvReader:
        print(i[0],i[1],i[2],i[3])



#############��һ��(��data.txt �ļ�ת����.json ��ʽ�ļ�(д,��))
list2=[]
with open('data.txt', 'r',encoding='utf-8') as obj:
    # ��ȡ�����в�ʹ��forѭ����ȡÿһ�е�����
    for i in obj.readlines():
        # ����ָ���е����ݣ����ַ��������е�"������ת�壬ȥ��ͷ����β���Ļ��У����Ʊ��Ϊ���и��һ���б���в���
        info = i.replace("\"", r"\"").strip('\n').split('\t')
        str1='{"����":"%s","����":"%s","�۸�":"%s","����":"%s","����":"%s","����":"%s"}' % (info[0],info[1],info[2],info[3],info[4],info[5])
        #��jaon�ַ���ת��Ϊ�ֵ�����
        dict1 = json.loads(str1)
        list2.append(dict1)

#��list2�б��������json����д�뵽data.json�ļ���
with open('data.json', 'w', encoding='utf-8') as jsonFile:
    # dump���ļ���д��json����, ��һ��������д���json��ʽ���ݣ� �ڶ���������д����ļ�
    json.dump(list2, jsonFile)

#��data.json�ļ���ȡ����
with open('data.json', 'r', encoding='utf-8') as jsonFile:
    # json.load(jsonFile):��ȡ�ļ������е����ݣ���ת�����ֵ�����
    data = json.load(jsonFile)
    print(type(data),data)



#############�ڶ��⣬��ȡuser_info.txt�ļ�����ת��
list1=[]
with open('user_info.txt', 'r',encoding='utf-8') as obj:
    for i in obj.readlines():
        #����ָ���е����ݣ����������ȫ��ȥ��, ��������ָ���ĸ�ʽ���в��,split:�и��һ���б���в���
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
        #����ָ���е����ݣ����������ȫ��ȥ��, ��������ָ���ĸ�ʽ���в��,split:�и��һ���б���в���
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

#############�����⣬�ļ����
shutil.move("d��\\1.txt","d:\\python")


#############�����⣬�ļ�����
def search_file(dir,sname):
    if sname in os.path.split(dir)[1]: #�����ļ������Ƿ����sname
        print(os.path.relpath(dir)) #��ӡ���·�������ָ����ڵ�ǰ·��
    if os.path.isfile(dir):   # ��������dirֱ����һ���ļ�Ŀ¼ ����û����Ŀ¼���Ͳ����ٱ���������Ŀ¼��
        return

    for dire in os.listdir(dir): # ������Ŀ¼  �����direΪ��ǰ�ļ���
        search_file(os.path.join(dir,dire),sname) #jionһ�¾ͱ���˵�ǰ�ļ��ľ���·��
                                           # ��ÿ����Ŀ¼·��ִ��ͬ���Ĳ���
search_file("d:\\","python")



