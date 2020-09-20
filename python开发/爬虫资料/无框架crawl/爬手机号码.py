#正则表达式匹配库
import re
import time
from urllib.parse import urlencode
#操作mongodb库
import pymongo
#发出请求库
import requests
tmp=1540023952177
#将url请求对象转换为请求参数
data={
    'callback': 'jsonp_queryMoreNums',
    'provinceCode': 71,
    'cityCode': 710,
    'monthFeeLimit': 0,
    'groupKey': 71243032,
    'searchCategory': 3,
    'net': '01',
    'amounts': 200,
    'codeTypeCode':'',
    'searchValue':'',
    'qryType': '02',
    'goodsNet': 4,
    '_': tmp
}

# 1:先获取mongodb的数据库地址,2:再获取数据库名,3:再获取表名
table = pymongo.MongoClient('localhost')['phone']['number']

while True:
    tmp=tmp+1
    headers = {"user-agent" : "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_0) AppleWebKit/535.11 (KHTML, like Gecko) Chrome/17.0.963.56 Safari/535.11"}
    url1 = 'https://m.10010.com/NumApp/NumberCenter/qryNum?'+urlencode(data)
    #请求ajax返回的json数据
    response1=requests.get(url1,headers=headers)
    #print(response1.text)
    info=re.findall('1(.*?),',response1.text)

    list=[]
    for i in range(len(info)):
        if table.find({'number': {'$regex': '1'+str(info[i])}}).count()==0:
            dict = {}
            dict['number']='1'+info[i]
            list.append(dict)
    print(list)

    if len(list)!=0:
        #插入单条数据到mongodb时数据的格式为字典形式:table.insert(dict)
        #插入多条数据到mongodb时数据的的格式应为列表包裹字典的形式:table.insert(list)
        table.insert_many(list)

    time.sleep(0.1)