# 4-3-1과 같은 문제지만 인전행렬이 아닌 연결리스트로 해결해보자
class Node:
    def __init__(self,num):
        self.num = num
        self.link = None

class Graph: # 연결리스트를 이용한 그래프
    def __init__(self,size): # 리스트를 바로 생성해준다.
        self.adjList = [None] * size
        self.size = size

    def insertEdge(self,v1,v2):
        newNode = Node(v2)
        newNode.link = self.adjList[v1]
        self.adjList[v1] = newNode
        # 양 뱡향이기 때문에 바로 두개 다 연결
        newNode = Node(v1)
        newNode.link = self.adjList[v2]
        self.adjList[v2] = newNode

    def printGraph(self):  # 연결리스트를 프린트 하는 과정이 아직 미숙함
        for v in range(self.size):
            print(v,":" , end =' ')
            current = self.adjList[v]
            while current != None:
                print(current.num,end=' ')
                current = current.link
            print()

def find_area_dfs(area,v,visit):
    visit[v] = True
    current = area.adjList[v]
    while current != None: # 연결된 vertex를 모두 방문하기 위함
        if visit[current.num] == None:
            find_area_dfs(area,current.num,visit)
        current = current.link



n,m = map(int,input().split()) #n은 vertex 개수, m은 edge 개수

g = Graph(n)

visit = [None] * n # 방문 관리를 위한 Vertex의 수 만큼 만들어준다.
group_cnt = 0
for i in range(m): # input each edge
    v1,v2 = map(int,input().split())
    g.insertEdge(v1,v2)

for i in range(n): # 최대 집단을 찾기위한 반복문 작성
    if visit[i] == None:
        find_area_dfs(g,i,visit)
        group_cnt += 1

print(group_cnt)