#sax解析xml文件
import xml.sax

class MovieHandler(xml.sax.ContentHandler):
    #列出所有出现的标签
    def __init__(self):
        self.CurrentData = ""
        self.reason=""
        self.key = ""
        self.saicheng1 = ""
        self.saicheng2 = ""
        self.saicheng3 = ""
        self.jifenbang = ""
        self.sheshoubang = ""
        self.c1 = ""
        self.c2 = ""
        self.c3 = ""
        self.c4R = ""
        self.c4T1 = ""
        self.c4T1URL = ""
        self.c4T2 = ""
        self.c4T2URL = ""
        self.c51 = ""
        self.c51Link = ""
        self.c52 = ""
        self.c52Link = ""
        self.c2L = ""
        self.c41 = ""
        self.c42 = ""
        self.c43 = ""
        self.c5 = ""
        self.c6 = ""
        self.c4 = ""
        self.c3L = ""
        self.error_code = ""




    # 元素开始调用
    def startElement(self, tag, attributes):
        self.CurrentData = tag

    # 元素结束调用
    def endElement(self, tag):
        if self.CurrentData == "reason":
            print("reason:", self.reason)
        if self.CurrentData == "key":
            print("key:", self.key)
        if self.CurrentData == "saicheng1":
            print("saicheng1:", self.saicheng1)
        if self.CurrentData == "saicheng2":
            print("saicheng2:", self.saicheng2)
        if self.CurrentData == "saicheng3":
            print("saicheng3:", self.saicheng3)
        if self.CurrentData == "jifenbang":
            print("jifenbang:", self.jifenbang)
        if self.CurrentData == "sheshoubang":
            print("sheshoubang:", self.sheshoubang)
        if self.CurrentData == "c1":
            print("c1:", self.c1)
        if self.CurrentData == "c2":
            print("c2:", self.c2)
        if self.CurrentData == "c3":
            print("c3:", self.c3)
        if self.CurrentData == "c4R":
            print("c4R:", self.c4R)
        if self.CurrentData == "c4T1":
            print("c4T1:", self.c4T1)
        if self.CurrentData == "c4T1URL":
            print("c4T1URL:", self.c4T1URL)
        if self.CurrentData == "c4T2":
            print("c4T2:", self.c4T2)
        if self.CurrentData == "c4T2URL":
            print("c4T2URL:", self.c4T2URL)
        if self.CurrentData == "c51":
            print("c51:", self.c51)
        if self.CurrentData == "c51Link":
            print("c51Link:", self.c51Link)
        if self.CurrentData == "c52":
            print("c52:", self.c52)
        if self.CurrentData == "c52Link":
            print("c52Link:", self.c52Link)
        if self.CurrentData == "c2L":
            print("c2L:", self.c2L)
        if self.CurrentData == "c41":
            print("c41:", self.c41)
        if self.CurrentData == "c42":
            print("c42:", self.c42)
        if self.CurrentData == "c43":
            print("c43:", self.c43)
        if self.CurrentData == "c5":
            print("c5:", self.c5)
        if self.CurrentData == "c6":
            print("c6:", self.c6)
        if self.CurrentData == "c4":
            print("c4:", self.c4)
        if self.CurrentData == "c3L":
            print("c3L:", self.c3L)
        if self.CurrentData == "error_code":
            print("error_code:", self.error_code)
        self.CurrentData = ""

    # 读取字符时调用
    def characters(self, content):
        if self.CurrentData == "reason":
            self.reason=content
        if self.CurrentData == "key":
            self.key=content
        if self.CurrentData == "saicheng1":
            self.saicheng1=content
        if self.CurrentData == "saicheng2":
            self.saicheng2=content
        if self.CurrentData == "saicheng3":
            self.saicheng3=content
        if self.CurrentData == "jifenbang":
            self.jifenbang=content
        if self.CurrentData == "sheshoubang":
            self.sheshoubang=content
        if self.CurrentData == "c1":
            self.c1=content
        if self.CurrentData == "c2":
            self.c2=content
        if self.CurrentData == "c3":
            self.c3=content
        if self.CurrentData == "c4R":
            self.c4R=content
        if self.CurrentData == "c4T1":
            self.c4T1=content
        if self.CurrentData == "c4T1URL":
            self.c4T1URL=content
        if self.CurrentData == "c4T2":
            self.c4T2=content
        if self.CurrentData == "c4T2URL":
            self.c4T2URL=content
        if self.CurrentData == "c51":
            self.c51=content
        if self.CurrentData == "c51Link":
            self.c51Link=content
        if self.CurrentData == "c52":
            self.c52=content
        if self.CurrentData == "c52Link":
            self.c52Link=content
        if self.CurrentData == "c2L":
            self.c2L=content
        if self.CurrentData == "c41":
            self.c41=content
        if self.CurrentData == "c42":
            self.c42=content
        if self.CurrentData == "c43":
            self.c43=content
        if self.CurrentData == "c5":
            self.c5=content
        if self.CurrentData == "c6":
            self.c6=content
        if self.CurrentData == "c4":
            self.c4=content
        if self.CurrentData == "c3L":
            self.c3L=content
        if self.CurrentData == "error_code":
            self.error_code=content


if (__name__ == "__main__"):
    # 创建一个 XMLReader
    parser = xml.sax.make_parser()
    # 关闭命名空间
    parser.setFeature(xml.sax.handler.feature_namespaces, 0)

    # 重写 ContextHandler
    Handler = MovieHandler()
    parser.setContentHandler(Handler)

    parser.parse("体育.xml")