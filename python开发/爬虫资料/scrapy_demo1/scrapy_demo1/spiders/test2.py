import scrapy

#定义一个爬虫类继承一个爬虫类型
class demo2(scrapy.Spider):

    # 定义蜘蛛名
    name = "small_crawl2"

    # 简写方式，无需定义start_requests方法，但必须定义一个方法为：def parse(self, response)，方法名一定是：parse
    start_urls = [
        'http://lab.scrapyd.cn/page/1/',
    ]



    def parse(self, response):

        page = response.css('div.quote')  # 提取首页q所有名言，保存至变量mingyan
        for p in page:  # 循环获取每一条名言里面的：名言内容、作者、标签

            text = p.css('.text::text').extract()[0]  # 提取名言
            autor = p.css('.author::text').extract()[0]  # 提取作者
            tags = p.css('.tags .tag::text').extract()  # 提取标签
            tags = ','.join(tags)  # 数组转换为字符串
            # 接下来，进行保存

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