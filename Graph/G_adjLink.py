class Node:
    def __init__(self,v):
        self.vertex = v
        self.link = None
class Graph:
    def __init__(self,size):
        self.adjList = [None] * size
        self.size = size

    def insertEdge(self,v1,v2):
        newnode = Node(v2)
        newnode.link = self.adjList[v1]
        self.adjList[v1] = newnode
        # 무향 그래프라면 아래 코드추가
        newnode = Node(v1)
        newnode.link = self.adjList[v2]
        self.adjList[v2] = newnode

    def printGraph(self):
        for v in range(self.size):
            print(v,end =': ')
            current = self.adjList[v]
            while current is not None:
                print(current.vertex,end =' ')
                current = current.link
            print()


n,m = map(int,input().split()) #n은 vertex 개수, m은 edge 개수
g = Graph(n)
for i in range(m): # input each edge
    v1,v2 = map(int,input().split())
    g.insertEdge(v1,v2)
g.printGraph()