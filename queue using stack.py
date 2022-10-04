#Implementation of queue using stacks
class Stack:
    def __init__(self):
        self.stack = []
    def pop(self):
        if(len(self.stack)!=0):
            return self.stack.pop()
        else:
            return None
    def push(self,x):
        self.stack.append(x)


class Queue:
    def __init__(self):
        self.stack1 = Stack()
        self.stack2 = Stack()
    def push(self,x):
        i = self.stack1.pop()
        while(i):
            self.stack2.push(i)
            i = self.stack1.pop()
        self.stack2.push(x)
        i = self.stack2.pop()
        while(i):
            self.stack1.push(i)
            i = self.stack2.pop()
    def pop(self):
        return self.stack1.pop()

q = Queue()
for i in range(5):
    q.push(input())
print()
for i in range(5):
    print(q.pop())
