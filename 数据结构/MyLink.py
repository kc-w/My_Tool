# class Node(object):
#     def __init__(self, elem):
#         #节点数据
#         self.elem = elem
#         #节点指针
#         self.next = None
#
# class Link(object):
#     def __init__(self, node=None):
#         #初始化头指针指向None
#         self.head = node
#
#     def travel(self):
#         #头指针
#         cur = self.head
#         while cur != None:
#             print(cur.elem , end=' ')
#             cur = cur.next
#
#     def add(self, item):
#         #创建新节点
#         node = Node(item)
#         #新节点指针指向上一个节点
#         node.next = self.head
#         #头指针指向新插入的节点
#         self.head = node




class Node(object):
    def __init__(self, elem):
        # 节点前指针
        self.last = None
        #节点数据
        self.elem = elem
        #节点后指针
        self.next = None

class Link(object):
    def __init__(self, node=None):
        #初始化头指针指向None
        self.head = node
        # 初始化头指针指向None
        self.end = node

    def travel(self):
        #头指针
        cur = self.head
        while cur != None:
            print(cur.elem , end=' ')
            cur = cur.next

    def add(self, item):
        #创建新节点
        node = Node(item)
        # 新节点指针指向下一个节点
        node.next = self.head
        # 新节点指针指向上一个节点
        node.last = self.end
        #头指针指向新插入的节点
        self.head = node



if __name__ == '__main__':
    MyLink=Link()
    MyLink.add(1)
    MyLink.add(3)
    MyLink.travel()