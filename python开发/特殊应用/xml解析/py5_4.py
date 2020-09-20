#sax解析xml文件
import xml.sax

class MovieHandler(xml.sax.ContentHandler):
    #列出所有出现的标签
    def __init__(self):
        self.CurrentData = ""
        self.yweather=""
        self.title = ""
        self.link = ""
        self.description = ""
        self.language = ""
        self.lastBuildDate = ""
        self.ttl = ""
        self.width = ""
        self.height = ""
        self.url = ""
        self.geo = ""
        self.pubDate = ""
        self.guid = ""

    # 元素开始调用
    def startElement(self, tag, attributes):
        self.CurrentData = tag

    # 元素结束调用
    def endElement(self, tag):
        if self.CurrentData == "yweather":
            print("yweather:", self.yweather)
        if self.CurrentData == "title":
            print("title:", self.title)
        if self.CurrentData == "link":
            print("link:", self.link)
        if self.CurrentData == "description":
            print("description:", self.description)
        if self.CurrentData == "language":
            print("language:", self.language)
        if self.CurrentData == "lastBuildDate":
            print("lastBuildDate:", self.lastBuildDate)
        if self.CurrentData == "ttl":
            print("ttl:", self.ttl)
        if self.CurrentData == "width":
            print("width:", self.width)
        if self.CurrentData == "height":
            print("height:", self.height)
        if self.CurrentData == "url":
            print("url:", self.url)
        if self.CurrentData == "geo":
            print("geo:", self.geo)
        if self.CurrentData == "pubDate":
            print("pubDate:", self.pubDate)
        if self.CurrentData == "guid":
            print("guid:", self.guid)
        self.CurrentData = ""

    # 读取字符时调用
    def characters(self, content):
        if self.CurrentData == "yweather":
            self.yweather=content
        if self.CurrentData == "title":
            self.title = content
        if self.CurrentData == "link":
            self.link = content
        if self.CurrentData == "description":
            self.description = content
        if self.CurrentData == "language":
            self.language = content
        if self.CurrentData == "lastBuildDate":
            self.lastBuildDate = content
        if self.CurrentData == "ttl":
            self.ttl = content
        if self.CurrentData == "width":
            self.width = content
        if self.CurrentData == "height":
            self.height = content
        if self.CurrentData == "url":
            self.url = content
        if self.CurrentData == "geo":
            self.geo = content
        if self.CurrentData == "pubDate":
            self.pubDate = content
        if self.CurrentData == "guid":
            self.guid = content


if (__name__ == "__main__"):
    # 创建一个 XMLReader
    parser = xml.sax.make_parser()
    # 关闭命名空间
    parser.setFeature(xml.sax.handler.feature_namespaces, 0)

    # 重写 ContextHandler
    Handler = MovieHandler()
    parser.setContentHandler(Handler)

    parser.parse("雅虎天气预报.xml")