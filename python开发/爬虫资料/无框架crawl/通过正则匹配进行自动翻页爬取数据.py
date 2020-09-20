import requests, re

def getMovie(url):
    request = requests.get(url)

    # 如果出现乱码可设置编码格式GBK
    # request.encoding='GBK'

    #将请求到的网页数据写道txt中
    # with open('html.txt', 'w', encoding='utf-8') as f:
    #     f.write(request.text)

    #请求到的数据经过正则表达式匹配后返回一个被列表包裹的元组,每一个元组就是一组数据
    htmls = re.findall(r'<a href="(.*?)" class="ulink">(.*?)</a>', request.text)

    with open('movie.txt', 'a', encoding='utf-8') as f:
        # 循环往文件中追加写入数据
        for i in htmls:
            line = '电影名称:%s \t 电影地址:%s \n' %(i[1], 'http://www.dytt8.net'+i[0])
            f.write(line)

    #再次使用正则表达式进行匹配下一页数据
    pageHTML = re.findall(r'<a href=\'(.*?)\'>下一页</a>', request.text)

    if len(pageHTML[0]) > 0:
        nextPage = 'http://www.dytt8.net/html/gndy/dyzz/'+pageHTML[0]
        #自调用,传入新拼接好的地址
        getMovie(nextPage)

getMovie('http://www.dytt8.net/html/gndy/dyzz/index.html')