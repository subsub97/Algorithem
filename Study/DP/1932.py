#DP 1932 try1

#왼쪽 대각선 아래 or 오른쪽 아래로 이동가능

n = int(input())

# input table 생성
num_2d = [
    list(map(int,input().split()))
    for _ in range(n)
]

memo = [
    [0] * i for i in range(1,n+1)
]

for i in range(n):
    if i == 0:
        memo[i][0] = num_2d[i][0]
    else:
        for j in range(i+1):
            if i == 1:
                memo[i][j] = memo[i-1][0] + num_2d[i][j]
            else:
                if j == 0:
                    memo[i][j] =  memo[i-1][j] + num_2d[i][j]
                elif j == i:
                    memo[i][j] =  memo[i-1][j-1] + num_2d[i][j]
                else:
                    memo[i][j] = max(memo[i-1][j],memo[i-1][j-1]) + num_2d[i][j]

print(max(memo[n-1]))

