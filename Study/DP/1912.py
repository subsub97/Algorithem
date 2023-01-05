#연속합 DP try1

n = int(input())

#문제에서 주어진 최저 값이 -1000 이기에 max를 그보다 작은 수로 설정한다면
# max값을 찾을때 놓치는 일이 없을 것이다.
max = -1001

# tuple로 한 이유는 입력받은 값이 변동될 일이 없기에 tuple로 했다.
#(솔직히 상관없음)
num_arr = tuple(map(int,input().split()))

# 값을 기억 저장할 DP 리스트 생성
memo = [
    [0]*n for _ in range(n)
]

for i in range(n):
    for j in range(i,n):
        if j == 0:
            memo[i][j] = num_arr[i]
            if max < memo[i][j]:
                max = memo[i][j]
        else:
            memo[i][j] = memo[i][j-1] + num_arr[j]
            if max < memo[i][j]:
                max = memo[i][j]

print(max)


