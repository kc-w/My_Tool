#栈

class Node(object):
    def __init__(self, elem):
        #节点数据
        self.elem = elem
        #节点指针
        self.next = None

class Link(object):
    def __init__(self, node=None):
        #初始化头指针指向None
        self.head = node

    #入栈
    def into(self, item):
        #创建新节点
        node = Node(item)
        #新节点指针指向上一个节点
        node.next = self.head
        #头指针指向新插入的节点
        self.head = node

    #出栈
    def out(self):
        #头指针指向下一个节点
        node=self.head
        self.head=node.next
        #上一个栈的指针指向none
        node.next=None
        #返回出栈数据
        return node.elem

    #遍历
    def select(self,item):
        # 头指针
        cur = self.head
        while cur != None:
            if cur.elem==item:
                return cur.elem
                break
            cur = cur.next

if __name__=='__main__':
    item=Link()
    item.into(1)
    item.into(2)
    item.into(3)
    print(item.out())
    print(item.select(1))
