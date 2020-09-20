#解析库
import re
import time

import pymongo
from pyquery import PyQuery
#模拟用户操作库
from selenium import webdriver

# 制定爬取的浏览器
driver = webdriver.Chrome(r'C:\Users\Wkc23\Desktop\PycharmProjects\crawl\chromedriver.exe')
# 访问指要访问的页面
driver.get('https://www.taobao.com/')
#输出当前页面的源码
# print(driver.page_source)

# 得到文本输入框,通过页面对象来获取指定id的元素
selectText = driver.find_element_by_id('q')
# 模拟在该元素中输入关键字
selectText.send_keys("人工智能")
# 通过页面对象来获取指定class的元素
clickBtn = driver.find_element_by_xpath('//*[@id="J_TSearchForm"]/div[1]/button')
# 模拟点击事件
clickBtn.click()

page=driver.find_element_by_xpath('//*[@id="mainsrp-pager"]/div/div/div/div[1]')
page=int(re.findall('.*?(\d+).*?',page.text)[0])

x=1
#建立数据库连接
table = pymongo.MongoClient('localhost')['taobao']['shop']
for i in range(2,page+1):
    # 得到源代码开始使用标签解析
    doc = PyQuery(driver.page_source)

    #得到单条记录的文本标签迭代
    items=doc('#mainsrp-itemlist .grid .items .item').items()

    #对单个标签进行迭代
    for item in items:
        #将单条记录写入字典
        dict1={
            'image':item.find('.pic .img').attr('src').replace('\n',''),
            'price':item.find('.price').text().replace('\n',''),
            'number':item.find('.deal-cut').text().replace('\n',''),
            'title': item.find('.title').text().replace('\n',''),
            'shopname': item.find('.shopname').text().replace('\n',''),
            'address': item.find('.location').text().replace('\n','')
        }
        #插入记录
        table.insert(dict1)
        print(x)
        x=x+1
    time.sleep(3)

    #获得输入框
    input=driver.find_element_by_xpath('//*[@id="mainsrp-pager"]/div/div/div/div[2]/input')
    # 清空输入框
    input.clear()
    #在输入框中输入页码
    input.send_keys(str(i))
    # 获得跳页按钮
    span = driver.find_element_by_xpath('//*[@id="mainsrp-pager"]/div/div/div/div[2]/span[3]')
    #点击按钮
    span.click()