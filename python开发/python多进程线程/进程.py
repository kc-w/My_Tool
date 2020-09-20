from multiprocessing import Process, Queue
import time
from lxml import etree
import requests


class DouBanSpider(Process):
    def __init__(self, url, q):
        Process.__init__()
        self.url = url
        self.q = q
        self.headers = {
            'Host': 'movie.douban.com',
            'Referer': 'https://movie.douban.com/top250?start=225&filter=',
            'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.104 Safari/537.36',
        }

    def run(self):
        self.parse_page()

    def parse_page(self):
        response = self.send_request(self.url)
        html = etree.HTML(response)
        # 　获取该页的电影数据
        node_list = html.xpath("//div[@class='info']")
        for move in node_list:
            # 电影名称
            title = move.xpath('.//a/span/text()')[0]
            # 评分
            score = move.xpath('.//div[@class="bd"]//span[@class="rating_num"]/text()')[0]
            # 将每一部电影的名称跟评分加入到队列
            self.q.put(score + "\t" + title)


    def send_request(self, url):
        i = 0
        while i <= 3:
            try:
                print(u"[INFO]请求url:" + url)

                return requests.get(url=url, headers=self.headers).content
            except Exception as e:
                # 切换ip
                requests.get('http://127.0.0.1:5000/get')
                print(u'[INFO] %s%s' % (e, url))
                i += 1

def main():
    # 创建一个队列用来保存进程获取到的数据
    q = Queue()
    base_url = 'https://movie.douban.com/top250?start='
    # 构造所有url列表
    url_list = [base_url + str(num) for num in range(0, 225 + 1, 25)]

    # 保存进程
    Process_list = []
    # 循环创建并启动子进程
    for url in url_list:
        # 实例化传入url和队列
        p = DouBanSpider(url, q)
        p.start()
        Process_list.append(p)

    # 让主进程等待子进程执行完成
    for i in Process_list:
        i.join()

    while not q.empty():
        print(q.get())


if __name__ == "__main__":
    start = time.time()
    main()
    print('[info]耗时：%s' % (time.time() - start))