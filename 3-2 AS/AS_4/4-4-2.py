# 4번 문제의 소문제 1
# 연결리스트 형태에서 BFS를 이용하여 지하철역간 최단 경로 길이를 찾아내자
# 만약  ex) a역에서 b역으로 가는 최단거리를 찾는데 가는 경로가 없다면 -1 출력
from collections import deque

class Node:
    def __init__(self,num):
        self.num = num
        self.link = None
        self.parent = None

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
    newnode = Node(v)
    temp = newnode
    while current != None: # 행의 연결리스트를 모두 queue에 넣어준다.
        queue.append(current)
        current.parent = temp
        current = current.link

    q_size = len(queue)
    cnt = 0
    while queue:
        if q_size == cnt:
            min_path += 1
            q_size = len(queue)
            cnt = 0
        node = queue.popleft()
        cnt += 1

        if node.num == destination:
            queue.clear()
            flag = 1
            return node

        temp = node
        if visit[node.num] == None:
            visit[node.num] = True
            current = map.adjLink[node.num] # 리스트에 접근
            while current != None:
                current.parent = temp
                if visit[current.num] == None:
                    queue.append(current)
                current = current.link


n,m = map(int,input().split())

g = Graph(n)
path_list =[]
visit = [None] * n # 방문기록용 리스트 생성
q = deque() #BFS 방식의 탐방을 위한 queue 생성

min_path = 0 # 최단경로 값을 기록 할 변수 생성
flag = 0 # 답을 찾음을 알려줄 변수
for _ in range(m): # 에지관계 형성
    v1,v2 = map(int,input().split())
    g.insertEdge(v1,v2)

departure,destination = map(int,input().split()) #최단거리를 알고싶은 두역을 받는다.
a = find_min_path(g,departure,visit,q)
if flag == 1:
    while a != None:
        path_list.append(a.num)

        a = a.parent
    path_list.reverse()
    for i in path_list:
        print(i, end=' ')

else:
    print('-1')


# 부모노드가 바뀔수도있다.