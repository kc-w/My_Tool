import scrapy

#定义一个爬虫类继承一个爬虫类型
class demo1(scrapy.Spider):

    # 定义蜘蛛名
    name = "small_crawl1"

    #通过这个方法进入下面的初始链接
    def start_requests(self):
        url = 'http://lab.scrapyd.cn/'
        tag = getattr(self, 'tag', None)  # 获取tag值
        if tag is not None:  # 判断是否存在tag，若存在，重新构造url
            url = url + 'tag/' + tag  # 构造url
        yield scrapy.Request(url=url, callback=self.parse)

    #start_requests爬取到页面后，在这个方法里进行提取内容
    def parse(self, response):

        page = response.css('div.quote')  # 提取首页q所有名言，保存至变量mingyan
        for p in page:  # 循环获取每一条名言里面的：名言内容、作者、标签

            text = p.css('.text::text').extract()[0]  # 提取名言
            autor = p.css('.author::text').extract()[0]  # 提取作者
            tags = p.css('.tags .tag::text').extract()  # 提取标签
            tags = ','.join(tags)  # 数组转换为字符串

            with open("名言.txt", "a+",encoding='utf-8') as f:
                f.write('作者：' +autor)
                f.write('\t')
                f.write('名言：' + text)
                f.write('\t')
                f.write('标签：' + tags)
                f.write('\n')

        #使用：response.css('li.next a::attr(href)').extract()[0]查看有木有存在下一页链接
        next_page = response.css('li.next a::attr(href)').extract()[-1]
        if next_page is not None:
            next_page = response.urljoin(next_page)
            yield scrapy.Request(next_page, callback=self.parse)