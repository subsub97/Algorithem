# 과제 3번 문제중 1번문제 3 - 1 인접행렬을 이용한 문제 해결
# 친구관계로 연결되어 있는 집단의 개수를 출력 (연결요소 개수 출력)

import sys
input = sys.stdin.readline
sys.setrecursionlimit(10**6)

class Graph:
    def __init__(self,size):
        self.adjMatrix =[[0 for _ in range(size)] for _ in range(size)]
        self.size = size

    def insertEdge(self,v1,v2):
        self.adjMatrix[v1][v2] = 1
        self.adjMatrix[v2][v1] = 1  # 무향 그래프의 인접행렬은 대칭적이기에 바로 그려준다.

def find_area_dfs(area,r,visit): # r:row c:col
    visit[r] = True # 방문된 행이라고 체크
    for i in range(people):
        if area[r][i] == 1 and visit[i] == None:
            find_area_dfs(area,i,visit)


people,relation = map(int,input().split()) # n은 사람들의 수 , 친구 관계의 수

g = Graph(people) # 인접행렬을 만들어줄 그래프 클래스 생성

# 에지연결 관계를 인접행렬에 기록하기
for _ in range(relation):
    row,col = map(int,input().split())
    g.insertEdge(row,col)

# 방문 여부를 기록해줄 2차원 배열 생성
visit = [None for _ in range(people)]
cnt = 0
for i in range(people):
    if visit[i] == None:
        find_area_dfs(g.adjMatrix,i,visit)
        cnt +=1
print(cnt)



