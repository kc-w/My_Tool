# -*- coding: utf-8 -*-

# Define here the models for your scraped items
#
# See documentation in:
# https://doc.scrapy.org/en/latest/topics/items.html

import scrapy

#对抓取的数据进行格式配置
class ScrapyDemo1Item(scrapy.Item):


    #创建图片名称,存储为字典
    name = scrapy.Field()
    # 创建图片地址
    url_address = scrapy.Field()

