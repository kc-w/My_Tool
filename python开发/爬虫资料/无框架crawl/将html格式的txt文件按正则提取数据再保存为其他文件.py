#读写csv库
import csv
import pymysql
#读写excel库
import xlwt
import re,requests

# 请求网页数据并存到txt文件中
request1=requests.get("https://book.douban.com/tag/%E5%93%B2%E5%AD%A6")
with open('book1.txt','w',encoding='utf-8') as file:
    file.write(request1.text)

#将爬下来的html格式的txt文件中的数据按行读取为一个列表
lines=""
with open('book1.txt','r',encoding='utf-8') as file:
    lines=file.readlines()

#将读取到的列表以""进行拼接,变成字符串
html=''.join(lines)

#进行正则匹配返回一个列表
infos = re.findall(r'<a href="(.*?)" title="(.*?)"', html)


#保存到mysql数据库
for info in infos:
    db = pymysql.connect("localhost","root","999999999852","test",charset="utf8").cursor()
    db.execute("insert into t1 values ('%s','%s')" %(info[0],info[1]))

	
#将列表按指定格式保存到txt中
with open('format.txt', 'a',encoding='utf-8') as file:
    for i in infos:
        line="链接:%s\t名称:%s\n" %(i[0],i[1])
        file.write(line)

		
#将读取的数据变成双层列表
list1=[]
with open('format.txt', 'r',encoding='utf-8') as file:
    for i in file.readlines():
        # strip:将换行去除,split:切割成一个列表进行操作
        info = i.strip('\n').split('\t')
        list1.append(info)
print(list1)


		
#将双层列表保存为csv格式文件
with open('data1.csv', 'w',encoding='gbk',newline='')as csvFile:
    csvWrite = csv.writer(csvFile)
    csvWrite.writerows(infos)
		

#将文件保存为excel文件		
outCode=xlwt.Workbook(encoding='ascii')
outTitle=outCode.add_sheet('数据')
k=0
for i in infos:
    for j in range(len(i)):
        outTitle.write(k,j,i[j])
    k=k+1
outCode.save('infos.xls')