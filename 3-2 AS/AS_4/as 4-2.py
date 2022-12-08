 # 2번 문제 최대 크기의 영역의 크기와 영역의 개수를 찾자

# 3 x 3 인경우에만 찾을 수 있음
def dfs(maze,row,col,visit_2d):
    visit_2d[row][col] = True
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
for i in range(n):
    for j in range(m):
        if visit_2d[i][j] == None and maze[i][j] != 1:
            cnt += 1
            dfs(maze,i,j,visit_2d)
print(cnt)

 # if row > 0 and row < n-1 and col < m-1 and col > 0: # 어떠한 제약도 걸리지 않는 위치라면
 #         if maze[row][col+1] == 0 and visit_2d[row][col+1] == None: # 우측이 0이고 미방문이라면
 #             dfs(maze,row,col+1,visit_2d)
 #         if maze[row + 1][col+1] == 0 and visit_2d[row + 1][col] == None: #하단이 0이로 미방문이라면
 #             dfs(maze,row+1,col,visit_2d)
 #         if maze[row][col -1] == 0 and visit_2d[row][col -1] == None: # 좌측이 0이고 미방문이라면
 #             dfs(maze,row,col-1,visit_2d)
 #         if maze[row-1][col] == 0 and visit_2d[row -1][col] == None:  # 상단이 0이고 미방문이라면
 #             dfs(maze,row-1,col,visit_2d)
