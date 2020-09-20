import scrapy
#导入数据格式配置类
from scrapy_demo1.items import ScrapyDemo1Item

#使用scrapy的配置进行数据爬取
class crawl1(scrapy.Spider):
    name='bqb'

    def start_requests(self):
        start_urls=['http://qq.yh31.com/zjbq/2920180.html']

        for url in start_urls:
            yield scrapy.Request(url=url,callback=self.parse)

    def parse(self, response):

        #通过xpath定位到id为fontzoom的div下的所有p标签下的img标签,返回一个列表
        img_urls=response.xpath('//div[@id="fontzoom"]/p/img')
        a=1
        for img_url in img_urls:
            #获取img标签中的img属性值,返回一个列表
            url=img_url.xpath('@src').extract()
            #创建数据格式配置对象
            item=ScrapyDemo1Item()
            item['name']=a
            item["url_address"]=url[0]
            a+=1

            yield item
