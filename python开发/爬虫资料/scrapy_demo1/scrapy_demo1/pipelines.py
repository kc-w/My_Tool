# -*- coding: utf-8 -*-

# Define your item pipelines here
#
# Don't forget to add your pipeline to the ITEM_PIPELINES setting
# See: https://doc.scrapy.org/en/latest/topics/item-pipeline.html
import requests

#对抓取到的数据进行处理的配置
class ScrapyDemo1Pipeline(object):
    def process_item(self, item, spider):
        url='http://qq.yh31.com'+item['url_address']
        #请求网页数据
        response=requests.get(url)
        response.encoding='utf-8'

        with open('%d.gif' %item['name'],'wb') as f:
            #写入二进制数据
            f.write(response.content)
