# 과제 3번 문제중 1번문제 3 - 1 인접행렬을 이용한 문제 해결
# 친구관계로 연결되어 있는 집단의 개수를 출력 (연결요소 개수 출력)

import sys
input = sys.stdin.readline
sys.setrecursionlimit(10**9)

class Graph:
    def __init__(self,size):
        self.adjMatrix =[[0 for _ in range(size)] for _ in range(size)]
        self.size = size

    def insertEdge(self,v1,v2):
        self.adjMatrix[v1][v2] = 1
        self.adjMatrix[v2][v1] = 1  # 무향 그래프의 인접행렬은 대칭적이기에 바로 그려준다.

def find_area_dfs(area,r,c,visit): # r: row c:col
    if area[r][c] == 1 and visit[r][c] == None:
        visit[r][c] = True
        visit[c][r] = True # 무방향 그래프이기에 한번에 처리
        find_area_dfs(area,c,c,visit)

    for i in range(c,people):
        if visit[r][i] == None and area[r][i] == 1:
            visit[r][i] = True
            visit[i][r] = True
            find_area_dfs(area,i,i,visit)



people,relation = map(int,input().split()) # n은 사람들의 수 , 친구 관계의 수

g = Graph(people) # 인접행렬을 만들어줄 그래프 클래스 생성

# 에지연결 관계를 인접행렬에 기록하기
for _ in range(relation):
    row,col = map(int,input().split())
    g.insertEdge(row,col)

# 방문 여부를 기록해줄 2차원 배열 생성
visit_list = [
    [None for _ in range(people)]
    for _ in range(people)
]
cnt = 0

for i in range(people):
    zero_cnt = 0
    for j in range(people):
        if visit_list[i][j] == None and g.adjMatrix[i][j] == 1:
            cnt +=1
            find_area_dfs(g.adjMatrix,i,j,visit_list)
            break
        if g.adjMatrix[i][j] == 0:
            zero_cnt +=1
    if zero_cnt == people:
        cnt +=1

if relation == 0:
    cnt = people
print(cnt)



