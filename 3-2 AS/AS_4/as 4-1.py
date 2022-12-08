# 1번 문제
import sys
sys.setrecursionlimit(10**6) # 재귀적 호출 제한을 풀어주기위함
cnt = 0

def dfs(list,n,visit_list):
    visit_list[n] = True # 방문이 이루어지면 방문체크
    global cnt
    if list[n] == 0:
        cnt += 1
        return
    cnt += 1
    if n - list[n] >= 0 and visit_list[n - list[n]] == None: # 왼쪽으로 갈 수 있고 방문하지 않은 상태라면
        dfs(list,n - list[n],visit_list)
    if n + list[n] < len(list) and visit_list[n+list[n]] == None: # 오른쪽으로 갈 수 있고 방문하지 않은 상태라면
        dfs(list,n + list[n],visit_list)
    return


n = int(input()) # 리스트를 구성하는 n개의 셀
list = list(map(int,input().split()))
start = int(input())
visit_list = [ None for _ in range(n) ] # 방문을 관리하기위한 리스트를 만들어준다.

dfs(list,start,visit_list)

print(cnt)
for i in range(n):
    if visit_list[i] == True:
        print(i,end=' ')
