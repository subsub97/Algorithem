class Graph:
    def __init__(self,size):
        self.adjMatrix = [[0 for _ in range(size)] for _ in range(size)]
        self.size = size

    def insertEdge(self,v1,v2):
        self.adjMatrix[v1][v2] = 1 #삽입되는 에지를 바로 행렬에 기록한다.(만약 가중치가 있다면 1이 아닌 가중치로 기록하면 되겟지?)
        self.adjMatrix[v2][v1] =1 # undirected graph의 경우 adjmatrix가 대칭성을 가진다

    def printGraph(self): # 행렬을 그리기
        for i in range(self.size):
            for j in self.adjMatrix[i]:
                print(j, end = ' ')
            print()

n,m = map(int,input().split()) #n은 vertex 개수, m은 edge 개수
g = Graph(n)
for i in range(m): # input each edge
    v1,v2 = map(int,input().split())
    g.insertEdge(v1,v2)
g.printGraph()