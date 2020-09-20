#正则表达式匹配库
import re
from urllib.parse import urlencode
#操作mongodb库
import pymongo
#发出请求库
import requests



data={
    'min_behot_time': 0,
    'category': '组图',
    'utm_source': 'toutiao',
    'widen': 1,
    'tadrequire': 'true',
    'as':'A1453B8A36DDA13',
    'cp': '5BA67DFA01531E1',
    '_signature': 'VH.IKRAYD9qaFE774ViD01R.yD'
}

#将url请求对象转换为请求参数

headers = {"user-agent" : "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_0) AppleWebKit/535.11 (KHTML, like Gecko) Chrome/17.0.963.56 Safari/535.11"}
url1 = 'https://www.toutiao.com/api/pc/feed/?'+urlencode(data)
url_1='https://www.toutiao.com/api/pc/feed/?min_behot_time=0&category=%E7%BB%84%E5%9B%BE&utm_source=toutiao&widen=1&tadrequire=true&as=A1453B8A36DDA13&cp=5BA67DFA01531E1&_signature=VH.IKRAYD9qaFE774ViD01R.yD'
#请求ajax返回的json数据
response1=requests.get(url1,headers=headers)
# print(type(response1.text),eval('{"message": "error", "data": [], "has_more": "false"}'))


url2 = 'https://www.toutiao.com/a6532822418853986830'
response2 = requests.get(url2, headers=headers)
print(response2.text)
str=response2.text.replace('\\', '')
info=re.findall('\[{"(.*?)":"(.*?)".*?\]',str)

list=[]
for i in range(len(info)-1):
    dict = {}
    dict[info[i][0]]=info[i][1]
    list.append(dict)
print(list)


#1:先获取mongodb的数据库地址,2:再获取数据库名,3:再获取表名
table=pymongo.MongoClient('localhost')['images']['image']

#插入单条数据到mongodb时数据的格式为字典形式:table.insert(dict)
#插入多条数据到mongodb时数据的的格式应为列表包裹字典的形式:table.insert(list)
if table.insert_many(list):
    print('ok')