#sax解析xml文件
import xml.sax

class MovieHandler(xml.sax.ContentHandler):
    #列出所有出现的标签
    def __init__(self):
        self.CurrentData = ""
        self.resultcode=""
        self.reason = ""
        self.company = ""
        self.com = ""
        self.no = ""
        self.datetime = ""
        self.remark = ""
        self.zone = ""
        self.status = ""

    # 元素开始调用
    def startElement(self, tag, attributes):
        self.CurrentData = tag

    # 元素结束调用
    def endElement(self, tag):
        if self.CurrentData == "resultcode":
            print("resultcode:", self.resultcode)
        if self.CurrentData == "reason":
            print("reason:", self.reason)
        if self.CurrentData == "company":
            print("company:", self.company)
        if self.CurrentData == "com":
            print("com:", self.com)
        if self.CurrentData == "no":
            print("no:", self.no)
        if self.CurrentData == "datetime":
            print("datetime:", self.datetime)
        if self.CurrentData == "remark":
            print("remark:", self.remark)
        if self.CurrentData == "zone":
            print("zone:", self.zone)
        if self.CurrentData == "status":
            print("status:", self.status)
        self.CurrentData = ""

    # 读取字符时调用
    def characters(self, content):
        if self.CurrentData == "resultcode":
             self.resultcode=content
        if self.CurrentData == "reason":
            self.reason = content
        if self.CurrentData == "company":
            self.company = content
        if self.CurrentData == "com":
            self.com = content
        if self.CurrentData == "no":
            self.no = content
        if self.CurrentData == "datetime":
            self.datetime = content
        if self.CurrentData == "remark":
            self.remark = content
        if self.CurrentData == "zone":
            self.zone = content
        if self.CurrentData == "status":
            self.status = content


if (__name__ == "__main__"):
    # 创建一个 XMLReader
    parser = xml.sax.make_parser()
    # 关闭命名空间
    parser.setFeature(xml.sax.handler.feature_namespaces, 0)

    # 重写 ContextHandler
    Handler = MovieHandler()
    parser.setContentHandler(Handler)

    parser.parse("快递.xml")