class Node:
    def __init__(self,val):
        self.data = val
        self.next = None
    
    def setNext(self,val):
        self.next = val


class LinkedList:
    def create(self):
        self.head=None
        self.lsize=0
    
    def size(self):
        return self.lsize
    
    def first(self):
        if self.head:
            return self.head
        else:
            raise "List is empty"
    
    def isEmpty(self):
        return self.lsize==0
    
    def insertFirst(self,val):
        temp = self.head
        self.head = Node(val)
        self.head.setNext(temp)
        self.lsize += 1
    
    def insertAfter(self,val,bef):
    
        if bef:
            x = Node(val)
            x.setNext(bef.next)
            bef.setNext(x)
            self.lsize += 1
        else:
            raise "Entered element does not exist!"

    def deleteFirst(self):
        if self.head:
            self.head = self.head.next
            self.lsize -= 1
        else:
            raise "List is empty"

    def deleteAfter(self,bef):
        if bef:
            bef.setNext(bef.next.next)
            self.lsize -= 1
        else:
            raise "Entered element does not exist!"
    
    def purgeList(self):
        p = self.head
        while p:
            q = p
            while q.next:
                if q.next.data == p.data:
                    self.deleteAfter(q)
                else:
                    q = q.next
            p = p.next

    def printList(self):
        p = self.head
        while p:
            print(p.data)
            p = p.next

    def recursiveReverse(self, p):
        if p is None or p.next is None:
            return p
        q = p.next
        r = self.recursiveReverse(q)
        q.setNext(p)
        p.setNext(None)
        return r

    def nrReverse(self):
        p = self.head
        q = p.next
        p.next = None
        while q:
            r = q.next
            q.next = p
            p = q
            q = r
        return p



llist = LinkedList()
llist.create()


for i in range(1,11):
    val = input("Enter the "+str(i)+"th number:")
    if i == 1:
        llist.insertFirst(val)
        x = llist.first()
    else :
        llist.insertAfter(val,x)
        x = x.next
        
llist.head = llist.recursiveReverse(llist.first())
llist.printList()