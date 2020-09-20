import scrapy

#定义一个爬虫类继承一个爬虫类型
class demo3(scrapy.Spider):

    # 定义蜘蛛名
    name = "albb_job"

    # 简写方式，无需定义start_requests方法，但必须定义一个方法为：def parse(self, response)，方法名一定是：parse
    start_urls = [
        'https://job.alibaba.com/zhaopin/positionList.htm',
    ]



    def parse(self, response):

        page1 = response.css('td')
        page2 = response.css('div.tb-li-detail')
        list1=[]
        list2=[]
        for p in page1:
            print(job_name = p.css('td span a::text').extract())
            print(1111111111111111111111111111111111)
            job_name = p.css('td span a::text').extract()[0]
            job_type = p.css('td span::text').extract()[0]
            job_address = p.css('td .workLocation ::text').extract()[0]
            job_number = p.css('td span::text').extract()[1]
            job_time=p.css('td span::text').extract()[2]

            str1='工作名称：' +job_name+"\t"+'工作类型：' +job_type+"\t"+'工作地址：' +job_address+"\t"+'招聘人数：' +job_number+"\t"+'发布时间：' +job_time+"\t"
            list1.append(str1)

        for p in page2:
            job_describe = p.css('.pt-20::text').extract()[0]
            job_Requirement = p.css('.pt-20::text').extract()[1]
            str2='岗位描述：' +job_describe+"\t"+'岗位要求：' +job_Requirement+'\n'
            list1.append(str2)

        with open("job.txt", "a+",encoding='utf-8') as f:
            for i in range(len(list1)):
                f.write(list1[i]+list2[i])


        print(response.css('ul .disabled a::attr(href)').extract())
        print(22222222222222222222222222222222222222)
        next_page = response.css('ul li a::attr(href)').extract()[-1]
        print(next_page)
        if next_page is not None:
            next_page = response.urljoin('https://job.alibaba.com/zhaopin/positionList.htm'+next_page)
            print(next_page)
            print(333333333333333333333333333333333333333333333333333)
            yield scrapy.Request(next_page, callback=self.parse)