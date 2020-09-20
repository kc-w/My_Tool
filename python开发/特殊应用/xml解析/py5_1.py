#DOM解析xml文件
import xml.dom.minidom

#获取需要解析的文件，全部映射到内存中
DOMTree=xml.dom.minidom.parse("movie.xml")
#获取dom树的根节点
collection=DOMTree.documentElement
#解析根节点的内容
#检查根节点属性是否存在
if collection.hasAttribute("shelf"):
    #获取根节点的属性值
    print(collection.getAttribute("shelf"))

#解析根节点下的子节点，返回一个子节点列表
movies=collection.getElementsByTagName("movie")
for movie in movies:
    #检查movie节点是否存在title属性，并把属性值打印出来
    if movie.hasAttribute("title"):
        print("movie标签的title属性值",movie.getAttribute("title"))
    # 通过检查movie节点中的子节点长度是否为0，判断该节点是否存在，并把节点值打印出来
    if len(movie.getElementsByTagName("type"))!=0:
        type = movie.getElementsByTagName("type")[0]
        print(type.childNodes[0].data)
    if len(movie.getElementsByTagName("format"))!=0:
        type = movie.getElementsByTagName("format")[0]
        print(type.childNodes[0].data)
    if len(movie.getElementsByTagName("year"))!=0:
        type=movie.getElementsByTagName("year")[0]
        print(type.childNodes[0].data)
    if len(movie.getElementsByTagName("episodes"))!=0:
        type = movie.getElementsByTagName("episodes")[0]
        print(type.childNodes[0].data)
    if len(movie.getElementsByTagName("rating"))!=0:
        type=movie.getElementsByTagName("rating")[0]
        print(type.childNodes[0].data)
    if len(movie.getElementsByTagName("stars"))!=0:
        type=movie.getElementsByTagName("stars")[0]
        print(type.childNodes[0].data)
    if len(movie.getElementsByTagName("description")) != 0:
        type=movie.getElementsByTagName("description")[0]
        print(type.childNodes[0].data)

    print("--------------------------------")