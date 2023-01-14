# Brute_Force 1018 체스판 다시 칠하기 try2

row,col = map(int,input().split())

b_board = [
    [0] * col for _ in range(row)
]

w_board = [
    [0] * col for _ in range(row)
]

for i in range(row):
    row_color = input()
    # 홀수 행인 경우
    if i % 2 == 0:
        for j in range(col):
            #홀수 열인 경우
            if j % 2 == 0:
                # 체스판 왼쪽 상단이 blk이어야 하는경우
                if row_color[j] == 'B':
                    w_board[i][j] = 1
                # 체스판 왼쪽 상단이 white이어야 하는경우
                else:
                    b_board[i][j] = 1
            # 짝수 열인 경우
            else:
                if row_color[j] == 'W':
                    w_board[i][j] = 1
                else:
                    b_board[i][j] = 1
    # 짝수 행인 경우
    else:
        for j in range(col):
            #홀수 열인 경우
            if j % 2 == 0:
                # 체스판 왼쪽 상단이 blk이어야 하는경우
                if row_color[j] == 'W':
                    w_board[i][j] = 1
                # 체스판 왼쪽 상단이 white이어야 하는경우
                else:
                    b_board[i][j] = 1
            # 짝수 열인 경우
            else:
                if row_color[j] == 'B':
                    w_board[i][j] = 1
                else:
                    b_board[i][j] = 1
min_cnt = 33
for i in range((row+1)-8):
    b_sum = 0
    w_sum = 0
    for j in range(8):
        w_sum += w_board[i+j][0:8].count(1)
        b_sum += b_board[i+j][0:8].count(1)
    cnt = min(w_sum,b_sum)
    if cnt < min_cnt:
        min_cnt = cnt

for i in range((col + 1) -8):
    b_sum = 0
    w_sum = 0
    for j in range(8):
        w_sum += w_board[j][i:8+i].count(1)
        b_sum += b_board[j][i:8+i].count(1)
    cnt = min(w_sum, b_sum)
    if cnt < min_cnt:
        min_cnt = cnt

for i in range((row+1)-8):
    for j in range((col + 1) - 8):
        b_sum = 0
        w_sum = 0
        for k in range(8):
            w_sum += w_board[k+i][j:8+j].count(1)
            b_sum += b_board[k+i][j:8+j].count(1)
        cnt = min(w_sum, b_sum)
        if cnt < min_cnt:
            min_cnt = cnt

print(min_cnt)





