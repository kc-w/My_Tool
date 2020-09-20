#队列

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
        # 初始化尾指针指向None
        self.end = node

    #入队
    def into(self, item):
        #创建新节点
        node = Node(item)
        if self.size()==0:
            # 头指针指向新插入的节点
            self.head = node
        #新节点指针指向上一个节点
        node.next = self.head
        # 尾指针指向新插入的节点
        self.end = node

        #挤出队列
        if self.size() == 4:
            return self.out()


    #出队
    def out(self):
        #头指针指向下一个节点
        node=self.head
        self.head=node.next
        #上一个节点的指针指向none
        node.next=None
        #返回出队数据
        return node.elem

    #队列大小
    def size(self):
        # 头指针
        cur = self.head
        count=0
        while cur != None:
            if cur.elem==item:
                count+=1
            cur = cur.next
        return count

    #遍历
    def select(self,item):
        # 头指针
        cur = self.head
        while cur != None:
            if cur.elem==item:
                return cur
                break
            cur = cur.next

if __name__=='__main__':
    item=Link()
    item.into(1)
    item.into(2)
    item.into(3)
    item.into(4)
    print(item.into(5))
    print(item.size())
    print(item.select(1))