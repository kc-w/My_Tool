from bs4 import BeautifulSoup
from selenium import webdriver

# 制定爬取的浏览器
driver = webdriver.Chrome(r'C:\Users\Wkc23\PycharmProjects\demo1\chromedriver.exe')
# 访问指要访问的页面
driver.get('http://book.dangdang.com')
#输出当前页面的源码
# print(driver.page_source)

#  通过页面对象来获取指定id的元素
selectText = driver.find_element_by_id('key_S')
# 模拟在该元素中输入关键字
selectText.send_keys("人工智能")
# 通过页面对象来获取指定class的元素
clickBtn = driver.find_element_by_class_name('button')
# 模拟点击事件
clickBtn.click()


#得到源代码开始使用标签解析
soup = BeautifulSoup(driver.page_source,'lxml')


