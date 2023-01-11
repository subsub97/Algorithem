#DP RGB거리 try1
import sys
n = int(input())

memo = [
    [0]*3 for _ in range(n)
]



for i in range(n):
    rgb_cost = list(map(int,input().split()))
    for j in range(3):
        if i == 0:
            memo[i][j] = rgb_cost[j]
        else:
            if j == 0:
                memo[i][j] = min(memo[i-1][j+1],memo[i-1][j+2]) + rgb_cost[j]
            elif j == 1:
                memo[i][j] = min(memo[i-1][j - 1], memo[i-1][j + 1]) + rgb_cost[j]
            else:
                memo[i][j] = min(memo[i-1][j - 1], memo[i-1][j - 2]) + rgb_cost[j]
min_cost = sys.maxsize
for k in range(3):
    if memo[n-1][k] < min_cost:
        min_cost = memo[n-1][k]
print(min_cost)


