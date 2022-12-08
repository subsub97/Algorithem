 # 2번 문제 최대 크기의 영역의 크기와 영역의 개수를 찾자
 # 결국엔 small_dfs만을 사용하지만 노가다한거 지우는게 아까워서 냅둬야겟다..
 '''
 코드 작성후기 나는 처음에 하나하나 그 상황에 맞춰 어느 인덱스를 볼지 정해주는 정말 비효율적인 코드를 작성했다.
 그렇게 작성한 코드에는 ROW 또는 COL 이 3보다 작은경우를 잡는것도 제한조건을 많이 줘야했다. 
 분명 3x3 보다 큰 것을 코드를 작성 할 때도 느낌이 쎄 했지만 생각이 나지 않았다.. 그런데 작은 부분을 해결하려고 하다보니 
 '각 방향마다 다 확인하면서 순서대로 가는 방식으로하면 될 것같은데?' ,라는 생각이들었고 이렇게 코드를 작성한다면 큰 문제에서도 해결 할 수
 있을 거라는 생각이 들었다. 그래서 함수명은 small_dfs로 지었는데 결국엔 해당 문제를 해결하는 만들 func 였다. 
 돌고 돌아왔지만 더 짧은 코드로 해결했다는 점에서 고민한 시간이 보상받은것 같다
 '''
import sys
sys.setrecursionlimit(10**6) # 재귀적 호출 제한을 풀어주기위함

def small_dfs(maze,row,col,visit_2d):
    visit_2d[row][col] = True
    global zero_cnt
    zero_cnt += 1
    if col < m-1:
        if maze[row][col + 1] == 0 and visit_2d[row][col + 1] == None:  # 우측이 0이고 미방문이라면
            small_dfs(maze, row, col + 1, visit_2d)
    if row < n-1:
        if maze[row + 1][col] == 0 and visit_2d[row + 1][col] == None:  # 하단이 0 이고 미방문
            small_dfs(maze, row + 1, col, visit_2d)
    if col > 0:
        if maze[row][col - 1] == 0 and visit_2d[row][col - 1] == None:  # 좌측이 0이고 미방문이라면
            small_dfs(maze, row, col - 1, visit_2d)
    if row > 0:
        if maze[row-1][col] == 0 and visit_2d[row-1][col] == None:  # 상단이 0이고 미방문이라면
            small_dfs(maze, row-1, col, visit_2d)
    return


# 3 x 3 인경우에만 찾을 수 있음
def dfs(maze,row,col,visit_2d):
    visit_2d[row][col] = True
    global  zero_cnt
    zero_cnt += 1
    if row == 0 and col == 0: # 왼쪽 상단의 경우
        if maze[row][col + 1] == 0 and visit_2d[row][col + 1] == None:  # 우측이 0이고 미방문이라면
            dfs(maze, row, col + 1, visit_2d)
        if maze[row + 1][col + 1] == 0 and visit_2d[row + 1][col] == None:  # 하단이 0이로 미방문이라면
            dfs(maze, row + 1, col, visit_2d)
    elif row == 0 and col > 0 and col < m-1: # 첫행 끝 열 이 아닌경우
        if maze[row][col + 1] == 0 and visit_2d[row][col + 1] == None:  # 우측이 0이고 미방문이라면
            dfs(maze, row, col + 1, visit_2d)
        if maze[row + 1][col] == 0 and visit_2d[row + 1][col] == None:  # 하단이 0이로 미방문이라면
            dfs(maze, row + 1, col, visit_2d)
        if maze[row][col - 1] == 0 and visit_2d[row][col - 1] == None:  # 좌측이 0이고 미방문이라면
            dfs(maze, row, col - 1, visit_2d)
    elif row == 0 and col == m - 1: # 우측 상단인 경우
        if maze[row + 1][col] == 0 and visit_2d[row + 1][col] == None:  # 하단이 0이로 미방문이라면
            dfs(maze, row + 1, col, visit_2d)
        if maze[row][col - 1] == 0 and visit_2d[row][col - 1] == None:  # 좌측이 0이고 미방문이라면
            dfs(maze, row, col - 1, visit_2d)
    elif row > 0 and row < n-1 and col == 0: # 1열 양 끝행이 아닌경우
        if maze[row][col + 1] == 0 and visit_2d[row][col + 1] == None:  # 우측이 0이고 미방문이라면
            dfs(maze, row, col + 1, visit_2d)
        if maze[row + 1][col] == 0 and visit_2d[row + 1][col] == None:  # 하단이 0이로 미방문이라면
            dfs(maze, row + 1, col, visit_2d)
        if maze[row - 1][col] == 0 and visit_2d[row - 1][col] == None:  # 상단이 0이고 미방문이라면
            dfs(maze, row - 1, col, visit_2d)
    elif row > 0 and row < n-1 and col == m-1: #끝열 양 끝행이 아닌경우
        if maze[row + 1][col] == 0 and visit_2d[row + 1][col] == None:  # 하단이 0이로 미방문이라면
            dfs(maze, row + 1, col, visit_2d)
        if maze[row][col - 1] == 0 and visit_2d[row][col - 1] == None:  # 좌측이 0이고 미방문이라면
            dfs(maze, row, col - 1, visit_2d)
        if maze[row - 1][col] == 0 and visit_2d[row - 1][col] == None:  # 상단이 0이고 미방문이라면
            dfs(maze, row - 1, col, visit_2d)
    elif row == n-1 and col == 0: #좌측하단
        if maze[row - 1][col] == 0 and visit_2d[row - 1][col] == None:  # 상단이 0이고 미방문이라면
            dfs(maze, row - 1, col, visit_2d)
        if maze[row][col + 1] == 0 and visit_2d[row][col + 1] == None:  # 우측이 0이고 미방문이라면
            dfs(maze, row, col + 1, visit_2d)
    elif row == n-1 and col > 0 and col < m-1: # 끝행 양 끝열이 아닌경우
        if maze[row - 1][col] == 0 and visit_2d[row - 1][col] == None:  # 상단이 0이고 미방문이라면
            dfs(maze, row - 1, col, visit_2d)
        if maze[row][col + 1] == 0 and visit_2d[row][col + 1] == None:  # 우측이 0이고 미방문이라면
            dfs(maze, row, col + 1, visit_2d)
        if maze[row][col - 1] == 0 and visit_2d[row][col - 1] == None:  # 좌측이 0이고 미방문이라면
            dfs(maze, row, col - 1, visit_2d)
    elif row == n-1 and col == m-1:
        if maze[row][col - 1] == 0 and visit_2d[row][col - 1] == None:  # 좌측이 0이고 미방문이라면
            dfs(maze, row, col - 1, visit_2d)
        if maze[row - 1][col] == 0 and visit_2d[row - 1][col] == None:  # 상단이 0이고 미방문이라면
            dfs(maze, row - 1, col, visit_2d)

    else:
        if maze[row][col + 1] == 0 and visit_2d[row][col + 1] == None:  # 우측이 0이고 미방문이라면
            dfs(maze, row, col + 1, visit_2d)
        if maze[row + 1][col] == 0 and visit_2d[row + 1][col] == None:  # 하단이 0이로 미방문이라면
            dfs(maze, row + 1, col, visit_2d)
        if maze[row][col - 1] == 0 and visit_2d[row][col - 1] == None:  # 좌측이 0이고 미방문이라면
            dfs(maze, row, col - 1, visit_2d)
        if maze[row - 1][col] == 0 and visit_2d[row - 1][col] == None:  # 상단이 0이고 미방문이라면
            dfs(maze, row - 1, col, visit_2d)
    return


n,m = map(int,input().split())

# 공백이 없는 값을 input 받는 경우 split()을 빼주었다.
maze = [
    list(map(int,input()))
        for _ in range(n)
]
# 방문 체크를 위한 2차원 배열을 만들자
visit_2d = [
    [None for _ in range(m)]
    for _ in range(n)
]


cnt = 0
max_cnt = 0
min_cnt = 2500001
for i in range(n):
    for j in range(m):
        zero_cnt = 0
        if n < 3 or m <3:
            if visit_2d[i][j] == None and maze[i][j] != 1:
                cnt +=1
                small_dfs(maze,i,j,visit_2d)
                if zero_cnt > max_cnt:
                    max_cnt = zero_cnt
                if zero_cnt < min_cnt:
                    min_cnt = zero_cnt
        else:
            if visit_2d[i][j] == None and maze[i][j] != 1:
                cnt += 1
                small_dfs(maze,i,j,visit_2d)
                if zero_cnt > max_cnt:
                    max_cnt = zero_cnt
                if zero_cnt < min_cnt:
                    min_cnt = zero_cnt
print(cnt)
if min_cnt == 2500001: # 0 인경우를 대비
    min_cnt = 0
print(max_cnt,min_cnt)

