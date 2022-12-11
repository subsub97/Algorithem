# 4번 문제의 소문제 1
# 연결리스트 형태에서 BFS를 이용하여 지하철역간 최단 경로 길이를 찾아내자
# 만약  ex) a역에서 b역으로 가는 최단거리를 찾는데 가는 경로가 없다면 -1 출력
from collections import deque

class Node:
    def __init__(self,num):
        self.num = num
        self.link = None

class Graph: # 연결 리스트 형태의 그래프
    def __init__(self,size):
        self.adjLink = [None] * size
        self.size = size

    def insertEdge(self,v1,v2):
        newnode = Node(v2)
        newnode.link = self.adjLink[v1]
        self.adjLink[v1] = newnode
        # 단방향이 아닌 양방향의 그래프니까
        newnode = Node(v1)
        newnode.link = self.adjLink[v2]
        self.adjLink[v2] = newnode

def find_min_path(map,v,visit,queue):
    visit[v] = True #방문상태로 체크해주기
    global min_path,destination,flag
    current = map.adjLink[v] # 연결 리스트에 접근
    min_path += 1
    while current != None:
        if current.num == destination:
            flag = 1
            return
        queue.append(current.num) #방문했다면 queue에 넣어 관리 해준다. (FIFO 방식으로 같은 레벨을 다 탐방하고 다른 레벨을 순차적으로 처리하기 위함)
        current = current.link # 다음 연결된 리스트로 순서를 넘겨준다.
    if len(queue) >= 1:
        next_v = queue.popleft() #만약 방문순서를 원한다면 여기서 프린트르 해주면 될 것 같다(추측)
        while visit[next_v] == True: # 방문이 되어진 곳이라면
            if len(queue) == 0 and visit[next_v] == True:
                return
            next_v = queue.popleft()
        if visit[next_v] == None:
            find_min_path(map,next_v,visit,queue)
        else:
            return
    else:
        return
    if flag == 1:
        return

n,m = map(int,input().split())

g = Graph(n)
visit = [None] * n # 방문기록용 리스트 생성
q = deque() #BFS 방식의 탐방을 위한 queue 생성

min_path = 0 # 최단경로 값을 기록 할 변수 생성
flag = 0 # 답을 찾음을 알려줄 변수
for _ in range(m): # 에지관계 형성
    v1,v2 = map(int,input().split())
    g.insertEdge(v1,v2)

departure,destination = map(int,input().split()) #최단거리를 알고싶은 두역을 받는다.
find_min_path(g,departure,visit,q)
if flag == 1:
    print(min_path)
else:
    print('-1')